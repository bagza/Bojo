import task1.ExhaustiveSolution

class Utils {

    static def withBenchmark = { Closure method ->
        def runtime = Runtime.getRuntime()
        def start = System.currentTimeMillis()
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory()

        method()

        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory()
        def now = System.currentTimeMillis()
        println("Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1000000 + " MB")
        println("Time: " + (now - start) / 1000 + " s")
    }

}
