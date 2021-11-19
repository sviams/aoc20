import kotlin.math.pow

object day14 {

    data class State(val memory: Map<Long, Long>, val mask: BitMask)

    data class BitMask(val fromString: String) {

        fun getIndicesOf(c: Char) = fromString.reversed().foldIndexed(listOf()) { index: Int, acc: List<Int>, asd: Char ->
            if (c == asd) acc + index else acc
        }

        fun valueOf(indices: List<Int>) = indices.fold(0L) { acc, index ->  acc + 2.0.pow(index.toDouble()).toLong() }

        val oneIndices: List<Int> by lazy { getIndicesOf('1') }
        val zeroIndicesInv: List<Int> by lazy { (0..36).minus(getIndicesOf('0'))   }
        val exIndices: List<Int> by lazy { getIndicesOf('X') }

        val onesValue: Long by lazy { valueOf(oneIndices) }
        val zeroesInvertedValue: Long by lazy { valueOf(zeroIndicesInv) }

        fun apply(number: Long): Long = number or onesValue and zeroesInvertedValue
        fun apply2(number: Long): Long = number or onesValue

        fun permutations(base: String, exes: List<Int>): List<String> {
            if (exes.isEmpty()) return listOf(base)
            val exIndex = exes.first()
            val strings = (0..1).map { base.reversed().replaceRange(exIndex, exIndex+1, it.toString()).reversed() }
            return strings.flatMap { permutations(it, exes.drop(1)) }
        }
    }

    fun pt1(input: List<String>): Long =
        input.fold(State(mapOf(), BitMask(""))) { state, line ->
            if (line.startsWith("mask")) {
                state.copy(mask = BitMask(line.split(" = ").last()))
            } else {
                val (addrStr, valueStr) = """^mem\[(\d+)\] = (\d+)$""".toRegex().find(line)!!.destructured
                state.copy(memory = state.memory.plus(addrStr.toLong() to state.mask.apply(valueStr.toLong())))
            }
        }.memory.values.sum()

    fun pt2(input: List<String>): Long =
        input.fold(State(mapOf(), BitMask(""))) { state, line ->
            if (line.startsWith("mask")) {
                state.copy(mask = BitMask(line.split(" = ").last()))
            } else {
                val (addrStr, valueStr) = """^mem\[(\d+)\] = (\d+)$""".toRegex().find(line)!!.destructured
                val addrBin = state.mask.apply2(addrStr.toLong()).toString(2)
                val addrBinPadded = (0..36 - addrBin.length).map { "0" }.joinToString("") + addrBin
                val permuts = state.mask.permutations(addrBinPadded, state.mask.exIndices).map { it.toLong(2) }
                state.copy(memory = state.memory.plus(permuts.map { it to valueStr.toLong() }))
            }
        }.memory.values.sum()
}
