object day22 {

    data class State(val p1: List<Int>, val p2: List<Int>) {
        fun isFinished() = p1.isEmpty() || p2.isEmpty()
        fun winState() = p1.ifEmpty { p2 }
        fun winner() = if (p2.isEmpty()) 1 else 2
        fun score() = winState().reversed().foldIndexed(0) { index, acc, i -> acc + (index+1) * i }
        fun hash() = "$p1-$p2"
    }

    tailrec fun combat(state: State): State {
        if (state.isFinished()) return state
        val newP1 = if (state.p1.first() > state.p2.first()) state.p1.drop(1).plus(state.p1.first()).plus(state.p2.first()) else state.p1.drop(1)
        val newP2 = if (state.p2.first() > state.p1.first()) state.p2.drop(1).plus(state.p2.first()).plus(state.p1.first()) else state.p2.drop(1)
        return combat(State(newP1, newP2))
    }

    tailrec fun recursiveCombat(state: State, history: List<String> = emptyList()): State {
        if (state.isFinished()) return state
        if (history.contains(state.hash())) return State(state.p1, emptyList())
        val p1draw = state.p1.first()
        val p2draw = state.p2.first()
        val p1enoughToRecurse = state.p1.drop(1).size >= p1draw
        val p2enoughToRecurse = state.p2.drop(1).size >= p2draw
        val winner = if (p1enoughToRecurse && p2enoughToRecurse) recursiveCombat(State(state.p1.drop(1).take(p1draw), state.p2.drop(1).take(p2draw))).winner() else if (p1draw > p2draw) 1 else 2
        val newP1 = if (winner == 1) state.p1.drop(1).plus(p1draw).plus(p2draw) else state.p1.drop(1)
        val newP2 = if (winner == 2) state.p2.drop(1).plus(p2draw).plus(p1draw) else state.p2.drop(1)
        return recursiveCombat(State(newP1, newP2), history.plus(state.hash()))
    }

    fun pt1(input: List<String>): Int {
        val p1 = input.drop(1).takeWhile { it.isNotEmpty() }.map { it.toInt() }
        val p2 = input.dropWhile { it != "Player 2:" }.drop(1).map { it.toInt() }
        return combat(State(p1, p2)).score()
    }

    fun pt2(input: List<String>): Int {
        val p1 = input.drop(1).takeWhile { it.isNotEmpty() }.map { it.toInt() }
        val p2 = input.dropWhile { it != "Player 2:" }.drop(1).map { it.toInt() }
        return recursiveCombat(State(p1, p2)).score()
    }
}
