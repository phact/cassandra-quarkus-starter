# Quarkus C* starter project

## Dev

To run in dev (including hot loading for both java and javascript) run:

    ./mvnw compile quarkus


Changes to the source code are picked up automatically, it takes javascript changes a few seconds to propagate.

## Build jar

    ./mvnw package

## Build native image

    ./mvnw package -Pnative


Followed by manual native image build when it fails (until https://github.com/quarkusio/quarkus/issues/6598 gets resolved):

    /opt/graalvm-ce-java11-19.3.1/bin/native-image -J-Dsun.nio.ch.maxUpdateArraySize=100 -J-Djava.util.logging.manager=org.jboss.logmanager.LogManager -J-Dvertx.logger-delegate-factory-class-name=io.quarkus.vertx.core.runtime.VertxLogDelegateFactory -J-Dvertx.disableDnsResolver=true -J-Dio.netty.leakDetection.level=DISABLED -J-Dio.netty.allocator.maxOrder=1 -H:ResourceConfigurationFiles=resources-config.json -H:Log=registerResource  -H:ReflectionConfigurationFiles=../../reflection-config/reflect-config.json --initialize-at-build-time= -H:InitialCollectionPolicy='com.oracle.svm.core.genscavenge.CollectionPolicy$BySpaceAndTime' -jar project-1.0.0-SNAPSHOT-runner.jar -J-Djava.util.concurrent.ForkJoinPool.common.parallelism=1 -H:FallbackThreshold=0 -H:+ReportExceptionStackTraces -H:+AddAllCharsets -H:EnableURLProtocols=http -H:NativeLinkerOption=-no-pie -H:+JNI -H:JNIConfigurationFiles=../../reflection-config/jni-config.json --no-server -H:-UseServiceLoaderFeature -H:+StackTrace project-1.0.0-SNAPSHOT-runner

