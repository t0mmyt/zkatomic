# zkAtomic

A tool to see how fast an atomic counter in Zookeeper can be incremented with the intent of seeing if it was suitable for use as a global event sequence number (... it wasn't).

## Building

Uses *gradle*

    # Skinny JAR
    gradle build
    
    # Fat JAR
    gradle shadowJar
    
    
## config.properties

    zkConnString = 192.168.50.5

    counterPath = /counterFoo
    counterIterations = 10000

## Running

    java -jar build/lib/zkatomic-1.0-all.jar config.properties