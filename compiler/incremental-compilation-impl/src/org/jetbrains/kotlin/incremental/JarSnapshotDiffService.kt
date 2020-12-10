/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.incremental

import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.serialization.deserialization.getClassId

//TOT Should be in gradle daemon so move it. Only for test here
 class JarSnapshotDiffService() {
//    class Parameters (
//        val protoDataProvider /*= ProtoDataProvider(serializerProtocol)*/
//    ) {}

    companion object {
        //Store list of changed lookups
        val diffCache: MutableMap<Pair<JarSnapshot, JarSnapshot>, DirtyData> = mutableMapOf()

        //TODO for test only
        fun compareJarsInternal(
//            parameters: Parameters,
            snapshotJar: JarSnapshot, newJar: JarSnapshot
        )  = diffCache.computeIfAbsent(Pair(snapshotJar, newJar)) { (snapshot, actual) ->
                val fqNames = mutableListOf<FqName>()
                val symbols = mutableListOf<LookupSymbol>()

                for((fqName, protoData) in snapshot.protos) {
                    val newProtoData = actual.protos[fqName]
                    if (newProtoData == null) {
                        addProtoInfo(protoData, fqNames, fqName, symbols)
//                        parameters.protoDataProvider.
//                    }
                    } else {
                        if (protoData is ClassProtoData && newProtoData is ClassProtoData) {
                            ProtoCompareGenerated(protoData.nameResolver, newProtoData.nameResolver,
                                                  protoData.proto.typeTable, newProtoData.proto.typeTable)
                            val diff = DifferenceCalculatorForClass(protoData, newProtoData).difference()
//                            symbols.addAll(diff.changedMembersNames)

                        }
                    }
                }
//                fqNames.addAll(snapshot.protos.keys.removeAll(actual.protos.keys))

                DirtyData(symbols, fqNames)
            }

        //TODO change to return type
        private fun addProtoInfo(
            protoData: ProtoData,
            fqNames: MutableList<FqName>,
            fqName: FqName,
            symbols: MutableList<LookupSymbol>
        ) {
            if (protoData is ClassProtoData) {
                fqNames.add(fqName)
                symbols.addAll(protoData.proto.getNonPrivateNames(
                    protoData.nameResolver,
                    ProtoBuf.Class::getConstructorList,
                    ProtoBuf.Class::getFunctionList,
                    ProtoBuf.Class::getPropertyList
                ) + protoData.proto.enumEntryList.map { protoData.nameResolver.getString(it.name) }
                )
            }
        }

//            diffCache.computeIfAbsent(Pair(snapshot, newJar)) { (snapshotJar, actualJar) ->
//                //TODO check relative path
//                val symbols = mutableListOf<LookupSymbol>()
//                symbols.addAll(snapshotJar.symbols.minus(actualJar.symbols))
//                symbols.addAll(actualJar.symbols.minus(snapshotJar.symbols))
//                val fqNames = mutableListOf<FqName>()
//                fqNames.addAll(snapshotJar.fqNames.minus(actualJar.fqNames))
//                fqNames.addAll(actualJar.fqNames.minus(snapshotJar.fqNames))
//
//                DirtyData(symbols, fqNames)
//
//                DirtyFilesContainer(parameters.caches, parameters.reporter, parameters.sourceFilesExtensions)
//                    .also {
//                        //calcalute hash
//                        it.addByDirtyClasses(snapshotJar.fqNames.minus(actualJar.fqNames))
//                        it.addByDirtyClasses(actualJar.fqNames.minus(snapshotJar.fqNames))
//                        it.addByDirtySymbols(snapshotJar.symbols.minus(actualJar.symbols))
//                        it.addByDirtySymbols(actualJar.symbols.minus(snapshotJar.symbols))
//                    }
            }
//    }


}