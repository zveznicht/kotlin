/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.resolve

import org.jetbrains.kotlin.builtins.KotlinBuiltIns
import org.jetbrains.kotlin.descriptors.CallableDescriptor
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.ClassKind
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.descriptorUtil.overriddenTreeUniqueAsSequence
import org.jetbrains.kotlin.utils.DFS
import org.jetbrains.kotlin.utils.SmartSet
import java.util.*

fun <D : CallableDescriptor> D.findTopMostOverriddenDescriptors(): List<D> {
    return DFS.dfs(
        listOf(this),
        { current -> current.overriddenDescriptors },
        object : DFS.CollectingNodeHandler<CallableDescriptor, CallableDescriptor, ArrayList<D>>(ArrayList<D>()) {
            override fun afterChildren(current: CallableDescriptor) {
                if (current.overriddenDescriptors.isEmpty()) {
                    @Suppress("UNCHECKED_CAST")
                    result.add(current as D)
                }
            }
        })
}


fun <D : CallableDescriptor> D.findOriginalTopMostOverriddenDescriptors(): Set<D> {
    return findTopMostOverriddenDescriptors().mapTo(LinkedHashSet<D>()) {
        @Suppress("UNCHECKED_CAST")
        (it.original as D)
    }
}

/**
 * @param <H> is something that handles CallableDescriptor inside
 */
fun <H : Any> Collection<H>.selectMostSpecificInEachOverridableGroup(
    descriptorByHandle: H.() -> CallableDescriptor
): Collection<H> {
    if (size <= 1) return this
    val queue = LinkedList<H>(this)
    val result = SmartSet.create<H>()

    while (queue.isNotEmpty()) {
        val nextHandle: H = queue.first()

        val conflictedHandles = SmartSet.create<H>()

        val overridableGroup =
            OverridingUtil.extractMembersOverridableInBothWays(nextHandle, queue, descriptorByHandle) { conflictedHandles.add(it) }

        if (overridableGroup.size == 1 && conflictedHandles.isEmpty()) {
            result.add(overridableGroup.single())
            continue
        }

        val mostSpecific = OverridingUtil.selectMostSpecificMember(overridableGroup, descriptorByHandle)
        val mostSpecificDescriptor = mostSpecific.descriptorByHandle()

        overridableGroup.filterNotTo(conflictedHandles) {
            OverridingUtil.isMoreSpecific(mostSpecificDescriptor, it.descriptorByHandle())
        }

        if (conflictedHandles.isNotEmpty()) {
            result.addAll(conflictedHandles)
        }

        result.add(mostSpecific)
    }
    return result
}

fun Collection<CallableMemberDescriptor>.allOverridesOfPublicAnyMethodsInInterfaces() = all { it.isAnyOverrideInInterface() }
fun ClassDescriptor.isInterface() = kind == ClassKind.INTERFACE
private fun CallableMemberDescriptor.isAnyOverrideInInterface(): Boolean {
    if ((containingDeclaration as? ClassDescriptor)?.isInterface() != true) return false
    if (!mayBeAnOverrideFromAny()) return false

    return overriddenTreeUniqueAsSequence(useOriginal = true).any {
        val containingClass = it.containingDeclaration as? ClassDescriptor
        containingClass != null && KotlinBuiltIns.isAny(containingClass)
    }
}

private val TO_STRING = Name.identifier("toString")
private val HASHCODE = Name.identifier("hashCode")
private val EQUALS = Name.identifier("equals")

private fun CallableMemberDescriptor.mayBeAnOverrideFromAny() = when (name) {
    TO_STRING, HASHCODE -> valueParameters.isEmpty()
    EQUALS -> valueParameters.size == 1
    else -> false
}
