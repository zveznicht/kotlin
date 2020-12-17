#!/usr/bin/env bash

#
# Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
# Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
#

set -e

rm -rf out out.jar

# Specify path to the built async-profiler: https://github.com/jvm-profiling-tools/async-profiler

ASYNCPROF=/home/mike/devel/tools/async-profiler-1.8.2-linux-x64/build/

# Specify path to the Kotlin compiler home

K=/home/mike/devel/projects/kotlin/dist/kotlinc

# Specify path where to dump captured snapshots

OUT=$HOME/Snapshots/ijtc

CP=/home/mike/devel/projects/kotlin/compiler/backend.common.jvm/build/libs/backend.common.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/backend-common/build/libs/backend-common-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/frontend.java/build/libs/frontend.java-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/serialization/build/libs/serialization-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/frontend/build/libs/frontend-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/config.jvm/build/libs/config.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/descriptors.jvm/build/libs/descriptors.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/deserialization/build/libs/deserialization-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/resolution/build/libs/resolution-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/resolution.common.jvm/build/libs/resolution.common.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/psi/build/libs/psi-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/descriptors/build/libs/descriptors-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/compiler.common.jvm/build/libs/compiler.common.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/frontend.common/build/libs/frontend.common-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/config/build/libs/config-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/deserialization.common.jvm/build/libs/deserialization.common.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/deserialization.common/build/libs/deserialization.common-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/resolution.common/build/libs/resolution.common-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/compiler.common/build/libs/compiler.common-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/libraries/tools/kotlin-annotations-jvm/build/libs/kotlin-annotations-jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/util/build/libs/util-1.4.255-SNAPSHOT.jar:/home/mike/.gradle/kotlin-build-dependencies/repo/kotlin.build/intellij-core/202.7660.26/artifacts/intellij-core.jar:/home/mike/.gradle/kotlin-build-dependencies/repo/kotlin.build/intellij-core/202.7660.26/artifacts/asm-all-8.0.1.jar:/home/mike/.gradle/kotlin-build-dependencies/repo/kotlin.build/intellij-core/202.7660.26/artifacts/guava-29.0-jre.jar:/home/mike/.gradle/kotlin-build-dependencies/repo/kotlin.build/ideaIC/202.7660.26/artifacts/lib/trove4j.jar:/home/mike/devel/projects/kotlin/core/metadata.jvm/build/libs/metadata.jvm-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/core/metadata/build/libs/metadata-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/libraries/stdlib/jvm/build/libs/kotlin-stdlib-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/compiler.version/build/libs/compiler.version-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/compiler/container/build/libs/container-1.4.255-SNAPSHOT.jar:/home/mike/devel/projects/kotlin/libraries/tools/script-runtime/build/libs/kotlin-script-runtime-1.4.255-SNAPSHOT.jar:/home/mike/.gradle/caches/modules-2/files-2.1/io.javaslang/javaslang/2.0.6/415b0d40db4890849270c2a5cb50050fc6ee7636/javaslang-2.0.6.jar:/home/mike/.gradle/caches/modules-2/files-2.1/javax.annotation/jsr250-api/1.0/5025422767732a1ab45d93abfea846513d742dcf/jsr250-api-1.0.jar:/home/mike/devel/projects/kotlin/libraries/stdlib/common/build/libs/kotlin-stdlib-common-1.4.255-SNAPSHOT.jar:/home/mike/.gradle/caches/modules-2/files-2.1/org.jetbrains/annotations/13.0/919f0dfe192fb4e063e7dacadee7f8bb9a2672a9/annotations-13.0.jar:/home/mike/devel/projects/kotlin/core/util.runtime/build/libs/util.runtime-1.4.255-SNAPSHOT.jar:/home/mike/.gradle/caches/modules-2/files-2.1/javax.inject/javax.inject/1/6975da39a7040257bd51d21a231b76c915872d38/javax.inject-1.jar:/home/mike/.gradle/caches/modules-2/files-2.1/io.javaslang/javaslang-match/2.0.6/d57a666939103b659813de52102d3ff0baa8ad5f/javaslang-match-2.0.6.jar:/home/mike/.gradle/caches/modules-2/files-2.1/org.jetbrains.kotlin/protobuf-lite/2.6.1/9af39e6d6cbd4404d06ae2ae63505b6247e6760b/protobuf-lite-2.6.1.jar

#IR="-Xuse-fir"
IR=

#PROFILE="-Xrepeat=4 -Xprofile=$ASYNCPROF/libasyncProfiler.so:event=cpu,interval=1ms,threads,start,framebuf=50000000:$OUT"
PROFILE="-agentpath:/home/mike/devel/tools/YourKit-JavaProfiler-2020.9/bin/linux-x86-64/libyjpagent.so"

JVM_OPTS="-XX:+PreserveFramePointer"
MODULE="compiler/backend/src"
#MODULE="compiler/fir/tree"
echo $JVM_OPTS $K $IR $PROFILE $MODULE

#time java -Xmx12G $JVM_OPTS -cp $K/lib/kotlin-compiler.jar:$ASYNCPROF/async-profiler.jar org.jetbrains.kotlin.cli.jvm.K2JVMCompiler -Xjvm-default=compatibility $IR -jvm-target 1.8 -module-name backend -no-reflect -no-stdlib -Xuse-ir -api-version 1.4 -language-version 1.4 -verbose -cp $CP $MODULE -d out -Xreport-perf $PROFILE
time java $PROFILE -Xmx12G $JVM_OPTS -cp $K/lib/kotlin-compiler.jar org.jetbrains.kotlin.cli.jvm.K2JVMCompiler -Xjvm-default=compatibility $IR -jvm-target 1.8 -module-name backend -no-reflect -no-stdlib -Xuse-ir -api-version 1.4 -language-version 1.4 -verbose -cp $CP $MODULE -d out -Xreport-perf -Xrepeat=4