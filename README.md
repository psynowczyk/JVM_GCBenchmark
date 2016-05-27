# Porównanie wydajności algorytmów GC

Uruchomienie:
```sh
mvn exec:exec
```

Detale:
```sh
Rozmiar sterty: 512MB
Ilość alokacji na test: 1000000 (milion)
Rozgrzewka w postaci testu 1
```

Parametry jvm:
```sh
-Xmx512m
-verbose:gc
-XX:+UseConcMarkSweepGC | -XX:+UseParallelOldGC | -XX:+UseG1GC
-XX:+PrintGCDetails
-XX:+PrintGCTimeStamps
-XX:+PrintTenuringDistribution
-XX:+PrintGCApplicationConcurrentTime
-XX:+PrintGCApplicationStoppedTime
-Xloggc:gc.log
```

Szczegóły testów:
```sh
Test 1:
  - alokacje w 1 wątku
  - obiekty o stałym rozmiarze 15Mb

Test 2:
  - alokacje w 10 wątkach
  - obiekty o stałym rozmiarze 15Mb

Test 3:
  - alokacje w 1 wątku
  - obiekty o losowym rozmiarze 5Mb-15Mb

Test 4:
  - alokacje w 10 wątkach
  - obiekty o losowym rozmiarze 5Mb-15Mb
```

Resultat:
![alt text](https://github.com/psynowczyk/JVM_GCBenchmark/blob/master/graph.jpg?raw=true "")
