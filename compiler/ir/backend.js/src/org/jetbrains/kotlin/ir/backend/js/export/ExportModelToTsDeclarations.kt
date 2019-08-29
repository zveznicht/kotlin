/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.ir.backend.js.export

// TODO: Support module kinds other than plain

fun ExportedModule.toTypeScript(): String {
    val prefix = "    type Nullable<T> = T | null | undefined\n"
    val body = declarations.joinToString("\n") { it.toTypeScript("    ") }
    return "declare namespace $name {\n$prefix$body\n}\n"
}

fun List<ExportedDeclaration>.toTypeScript(indent: String): String =
    joinToString("") { it.toTypeScript(indent) + "\n" }

fun ExportedDeclaration.toTypeScript(indent: String): String = indent + when (this) {
    is ErrorDeclaration -> "/* ErrorDeclaration: $message */"

    is ExportedNamespace ->
        "namespace $name {\n" + declarations.toTypeScript("$indent    ") + "$indent}"

    is ExportedFunction -> {
        val keyword: String = when {
            isMember -> when {
                isStatic -> "static "
                isAbstract -> "abstract "
                else -> ""
            }
            else -> "function "
        }

        val renderedParameters = parameters.joinToString(", ") { it.toTypeScript(indent) }

        val renderedTypeParameters =
            if (typeParameters.isNotEmpty())
                "<" + typeParameters.joinToString(", ") + ">"
            else
                ""

        val renderedReturnType = returnType.toTypeScript(indent)

        "$keyword$name$renderedTypeParameters($renderedParameters): $renderedReturnType"
    }
    is ExportedConstructor ->
        "constructor(${parameters.joinToString(", ") { it.toTypeScript(indent) }})"

    is ExportedProperty -> {
        val keyword = when {
            isMember -> (if (isAbstract) "abstract " else "") + (if (!mutable) "readonly " else "")
            else -> if (mutable) "let " else "const "
        }
        keyword + name + ": " + type.toTypeScript(indent) + ";"
    }

    is ExportedClass -> {
        val keyword = if (isInterface) "interface" else "class"
        val superInterfacesKeyword = if (isInterface) "extends" else "implements"

        val superClassClause = superClass?.let { " extends ${it.toTypeScript(indent)}" } ?: ""
        val superInterfacesClause = if (superInterfaces.isNotEmpty()) {
            " $superInterfacesKeyword " + superInterfaces.joinToString(", ") { it.toTypeScript(indent) }
        } else ""

        val membersString = members.joinToString("") { it.toTypeScript("$indent    ") + "\n" }

        val renderedTypeParameters =
            if (typeParameters.isNotEmpty())
                "<" + typeParameters.joinToString(", ") + ">"
            else
                ""

        val modifiers = if (isAbstract && !isInterface) "abstract " else ""

        val klassExport = "$modifiers$keyword $name$renderedTypeParameters$superClassClause$superInterfacesClause {\n$membersString$indent}"
        val staticsExport = if (statics.isNotEmpty()) "\n" + ExportedNamespace(name, statics).toTypeScript(indent) else ""
        klassExport + staticsExport
    }

    is ExportedObject -> {

    }
}

fun ExportedParameter.toTypeScript(indent: String): String =
    "$name: ${type.toTypeScript(indent)}"

fun ExportedType.toTypeScript(indent: String): String = when (this) {
    is ExportedType.Primitive -> typescript
    is ExportedType.Array -> "Array<${elementType.toTypeScript(indent)}>"
    is ExportedType.Function -> "(" + parameterTypes
        .withIndex()
        .joinToString(", ") { (index, type) ->
            "p$index: ${type.toTypeScript(indent)}"
        } + ") => " + returnType.toTypeScript(indent)

    is ExportedType.ClassType ->
        name + if (arguments.isNotEmpty()) "<${arguments.joinToString(", ") { it.toTypeScript(indent) }}>" else ""

    is ExportedType.ErrorType -> "any /*$comment*/"
    is ExportedType.TypeParameter -> name
    is ExportedType.Nullable -> "Nullable<" + baseType.toTypeScript(indent) + ">"
    is ExportedType.ObjectLiteralType -> "{" + members.joinToString("") { "\n$indent" + it.toTypeScript(indent) } + "\n$indent}"
    is ExportedType.IntersectionType -> types.joinToString(" & ") { it.toTypeScript(indent) }
}