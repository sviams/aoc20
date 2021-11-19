object day13 {

    tailrec fun findNext(after: Int, interval: Int, current: Int = 0): Int = if (current >= after) current else findNext(after, interval, current + interval)

    fun pt1(input: List<String>): Int {
        val earliest = input[0].toInt()
        val buses = input[1].split(",").filter { it != "x" }.map { it.toInt() }
        val nearest = buses.map { it to findNext(earliest, it) - earliest }.minByOrNull { it.second }!!
        return nearest.first * nearest.second
    }

    tailrec fun strideThrough(buses: List<Long>, stride: Long, timestamp: Long, offset: Long): Long {
        val nextBus = buses.first()
        if (nextBus == 1L) return strideThrough(buses.drop(1), stride, timestamp, offset + 1)
        val isMatch = (timestamp + offset) % nextBus == 0L
        val nextStride = if (isMatch) stride * nextBus else stride
        val nextBuses = if (isMatch) buses.drop(1) else buses
        val nextOffset = if (isMatch) offset + 1 else offset
        if (isMatch && buses.size == 1) return timestamp
        return strideThrough(nextBuses, nextStride, timestamp + nextStride, nextOffset)
    }

    fun pt2(input: String): Long {
        val buses2 = input.split(",").map { if (it == "x") 1 else it.toLong() }  //parseIntervals(input.split(','), emptyMap())
        return strideThrough(buses2, 1, 7, 0)//findConvergence(buses, 7, 1, 1)
    }
}
