object day8 {

    tailrec fun runUntilLoop(code: List<String>, accumulator: Int, pc: Int, seen: List<Int>, indexToChange: Int): Int {
        if (seen.contains(pc)) return if (indexToChange >= 0) -1 else accumulator
        if (pc >= code.size) return accumulator
        val (instr, value) = code[pc].split(' ')
        val newInstr = if (pc != indexToChange) instr else when (instr) {
            "jmp" -> "nop"
            "nop" -> "jmp"
            else -> instr
        }
        return when (newInstr) {
            "acc" -> runUntilLoop(code, accumulator + value.toInt(), pc + 1, seen + pc, indexToChange)
            "jmp" -> runUntilLoop(code, accumulator, pc + value.toInt(), seen + pc, indexToChange)
            else -> runUntilLoop(code, accumulator, pc + 1, seen + pc, indexToChange)
        }
    }

    tailrec fun morphUntilWorks(code: List<String>, indexToChange: Int): Int {
        val result = runUntilLoop(code, 0, 0, listOf(), indexToChange)
        if (result != -1) return result
        return morphUntilWorks(code, indexToChange + 1)
    }

    fun pt1(input: List<String>): Int = runUntilLoop(input, 0, 0, listOf(), -1)

    fun pt2(input: List<String>): Int = morphUntilWorks(input, 1)
}