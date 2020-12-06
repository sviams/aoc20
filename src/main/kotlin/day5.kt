object day5 {

    tailrec fun bsp(chars: CharSequence, result: IntRange, lowerChar: Char): Int {
        if (chars.isBlank()) return result.first
        val diff = result.last - result.first
        val newResult = if (chars.first() == lowerChar) result.first..result.first + (diff)/2 else (result.first + (diff+1)/2)..result.last
        return bsp(chars.drop(1), newResult, lowerChar)
    }

    data class BoardingCard(val spec: String) {

        val row: Int by lazy { bsp(spec.take(7), 0..127, 'F') }

        val col: Int by lazy { bsp(spec.takeLast(3), 0..7, 'L') }

        val id: Int by lazy { row * 8 + col }

    }

    fun missingSeat(input: List<String>): Int {
        val rowWithMissing = input.map { BoardingCard(it) }.groupBy { it.row }.minus(0).minus(127).filter { it.value.size != 8 }
        val row = rowWithMissing.keys.first()
        val takenCols = rowWithMissing.entries.first().value.map { it.col }
        val missingCol = (0..7).first { !takenCols.contains(it) }
        return (row * 8) + missingCol
    }

}