/*
 * Copyright 2010-2017 JetBrains s.r.o.
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

package org.jetbrains.kotlin.testFramework

import org.jetbrains.org.objectweb.asm.ClassReader
import org.jetbrains.org.objectweb.asm.ClassVisitor
import org.jetbrains.org.objectweb.asm.MethodVisitor
import org.jetbrains.org.objectweb.asm.Opcodes
import java.lang.instrument.ClassFileTransformer
import java.security.ProtectionDomain

class PsiSubstitutorLoadingTransformer(private val debugInfo: Boolean) : ClassFileTransformer {
    class FieldReWriter(cv: ClassVisitor?) : ClassVisitor(Opcodes.ASM4, cv) {
        init {
            this.cv = cv
        }

        override fun visitMethod(
            access: Int, name: String,
            desc: String?, signature: String?, exceptions: Array<String?>?
        ): MethodVisitor? {
            var r: MethodVisitor? = super.visitMethod(access, name, desc, signature, exceptions)
            if ("<clinit>" == name) {
                r = MyMethodAdapter(r)
            }
            return r
        }

        internal class MyMethodAdapter(delegate: MethodVisitor?) : MethodVisitor(Opcodes.ASM4) {
            override fun visitInsn(opcode: Int) {
                println(opcode)
                super.visitInsn(opcode)
            }

            override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
                println("$opcode $owner $name $descriptor")
                super.visitFieldInsn(opcode, owner, name, descriptor)
            }

            override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                println("$opcode $owner $name $descriptor $isInterface")
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
            }

            override fun visitCode() {
                println("HELLO")
                super.visitCode()
                // build my static initializer by calling
                // visitFieldInsn(int opcode, String owner, String name, String desc)
                // or the like
            }
        }
    }


    override fun transform(
        loader: ClassLoader?,
        className: String?,
        classBeingRedefined: Class<*>?,
        protectionDomain: ProtectionDomain?,
        classfileBuffer: ByteArray
    ): ByteArray? {
        if (loader == null) return null

        if (className == "com/intellij/psi/PsiSubstitutor") {
            println("AHHHHHHHHHHHHA")

            try {
                val reader = ClassReader(classfileBuffer)
                reader.accept(FieldReWriter(null), 0)

                val donorClass = loader.loadClass("com.intellij.psi.KotlinPsiSubstitutor")

                println(donorClass)
            } catch (e: ClassNotFoundException) {
            }
        }

        return null
    }
}
