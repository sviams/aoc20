object day15 {

    tailrec fun sayNumbers(memory: HashMap<Int, Int>, current: Int, lastNumber: Int, isNew: Boolean, limit: Int): Int {
        if (current > limit) return lastNumber
        val newNumber = if (isNew) 0 else current-1 - memory[lastNumber]!!
        memory[lastNumber] = current-1
        return sayNumbers(memory, current + 1, newNumber, !memory.containsKey(newNumber), limit)
    }

    fun pt1(input: List<Int>): Int {
        val asd = hashMapOf<Int, Int>()
        input.mapIndexed { index, i -> asd[i] = index + 1 }
        return sayNumbers(asd, input.size+1, input.last(), true, 2020)
    }

    fun pt2(input: List<Int>): Int {
        val asd = hashMapOf<Int, Int>()
        input.mapIndexed { index, i -> asd[i] = index + 1 }
        val result = sayNumbers(asd, input.size+1, input.last(), true, 30000000)
        return result
    }
}
