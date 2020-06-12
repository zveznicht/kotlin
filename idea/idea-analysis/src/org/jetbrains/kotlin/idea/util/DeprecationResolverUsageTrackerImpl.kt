/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.util

import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.analyzer.AbstractResolverForProject
import org.jetbrains.kotlin.analyzer.EmptyResolverForProject
import org.jetbrains.kotlin.analyzer.moduleInfo
import org.jetbrains.kotlin.caches.resolve.KotlinCacheService
import org.jetbrains.kotlin.descriptors.ModuleDescriptor
import org.jetbrains.kotlin.idea.caches.project.IdeaModuleInfo
import org.jetbrains.kotlin.resolve.deprecation.DeprecationResolverUsageTracker
import org.jetbrains.kotlin.utils.addToStdlib.safeAs
import java.util.concurrent.ConcurrentHashMap

class DeprecationResolverUsageTrackerImpl(val project: Project) : DeprecationResolverUsageTracker {
    private val traces = ConcurrentHashMap<String, String>()

    override fun registerUsage(componentModule: ModuleDescriptor, descriptorModule: ModuleDescriptor) {
        val componentResolver = componentModule.resolverStash()?.safeAs<AbstractResolverForProject<IdeaModuleInfo>>() ?: return
        val descriptorResolver = descriptorModule.resolverStash()?.safeAs<AbstractResolverForProject<IdeaModuleInfo>>() ?: return

        var me: AbstractResolverForProject<IdeaModuleInfo> = componentResolver
        val resolvers = sequence {
            while (true) {
                yield(me)
                if (me.delegateResolver is EmptyResolverForProject<*>)
                    break
                else
                    me = me.delegateResolver as AbstractResolverForProject<IdeaModuleInfo>
            }
        }.toSet()

        if (descriptorResolver !in resolvers) {
            traces["${componentModule}-${descriptorModule}"] = Throwable().stackTrace.joinToString("\n")
        }
    }
}