object day20 {

    data class Tile(val id: Int, val rows: List<String>) {
        val regularEdges: List<String> by lazy {
            listOf(rows.first(), rightEdge, rows.last(), leftEdge )
        }
        val flippedEdges: List<String> by lazy { regularEdges.map { it.reversed() } }
        val edges: List<String> by lazy { regularEdges + flippedEdges }
        val rightEdge: String by lazy { rows.takeLast(10).map { row -> row.last() }.joinToString("") }
        val leftEdge: String by lazy { rows.map { row -> row.first() }.joinToString("") }
        val bottomEdge: String by lazy { rows.last().take(10) }
        val topEdge: String by lazy { rows.first() }
        fun rotate(): Tile = Tile(id, (rows.indices).map { rowIndex -> rows.reversed().map { it[rowIndex] }.joinToString("") }   )
        fun flip(): Tile = Tile(id, rows.map { it.reversed() })
        fun flipY(): Tile = Tile(id, rows.reversed())

        fun stripBorder(): Tile {
            return Tile(id, rows.map { it.drop(1).dropLast(1) }.drop(1).dropLast(1))
        }

        override fun equals(other: Any?): Boolean {
            return (other!! as Tile).id == this.id
        }
    }

    fun parseTiles(input: List<String>): List<Tile> =
        input.chunked(input.takeWhile { it.isNotEmpty() }.size + 1) { chunk ->
            val (tileId) = """Tile (\d+):""".toRegex().find(chunk.first())!!.destructured
            val rows = chunk.drop(1).takeWhile { it.isNotEmpty() }
            Tile(tileId.toInt(), rows)
        }

    tailrec fun puzzleTogether(todo: List<Tile>, tiles: List<Tile>, result: Map<Tile, List<Int>> = emptyMap()): Map<Tile, List<Int>> {
        if (todo.isEmpty()) return result
        val unpositioned = todo.first()
        val unposEdges = unpositioned.edges
        val matches = tiles.minus(unpositioned).filter { t -> t.edges.any { unposEdges.contains(it) } }
        return puzzleTogether(todo.minus(unpositioned), tiles, result.plus(unpositioned to matches.map { it.id }))
    }

    tailrec fun joinImageTiles(tiles: List<Tile>, result: List<List<Tile>>): List<List<Tile>> {
        if (tiles.isEmpty()) return result
        val rightMatch = tiles.mapNotNull { rotateUntilEdgesMatchOrNull(result.last().last().rightEdge, it, 0) { target, b -> target == b.leftEdge } }.firstOrNull()
        return if (rightMatch == null) {
            val bottomMatch: Tile = tiles.mapNotNull { rotateUntilEdgesMatchOrNull(result.last().first().bottomEdge, it, 0) { target, b -> target == b.topEdge } }.first()
            val newRow: List<Tile> = listOf(bottomMatch)
            joinImageTiles(tiles.minus(bottomMatch), result.plus(listOf(newRow)))
        } else {
            val newLastRow: List<Tile> = result.last() + rightMatch
            joinImageTiles(tiles.minus(rightMatch), result.dropLast(1) + listOf(newLastRow))
        }
    }

    fun pt1(input: List<String>): Long {
        val tiles = parseTiles(input)
        val withNeighbors = puzzleTogether(tiles, tiles)
        return withNeighbors.filter { it.value.size == 2 }.keys.fold(1L) { acc, tile -> acc * tile.id }
    }

    tailrec fun rotateBothUntilMatchesOrNull(left: Tile, right: Tile, leftRotations: Int = 0, rightRotations: Int = 0): Pair<Tile, Tile>? {
        if (left.rightEdge == right.leftEdge) return left to right
        if (leftRotations == 3 && rightRotations == 3) return null
        val nextLeftRotations = if (rightRotations == 3) leftRotations + 1 else leftRotations
        val nextLeft = if (rightRotations == 3) left.rotate() else left
        val nextRightRotations = if (rightRotations == 3) 0 else rightRotations + 1
        return rotateBothUntilMatchesOrNull(nextLeft, right.rotate(), nextLeftRotations, nextRightRotations)
    }

    tailrec fun rotateUntilEdgesMatchOrNull(targetEdge: String, toRotate: Tile, rotations: Int = 0, comparisonFunc: (String, Tile) -> Boolean): Tile? {
        if (comparisonFunc(targetEdge, toRotate)) return toRotate
        return when (rotations) {
            7 -> null
            3 -> rotateUntilEdgesMatchOrNull(targetEdge, toRotate.flip(), rotations+1, comparisonFunc)
            else -> rotateUntilEdgesMatchOrNull(targetEdge, toRotate.rotate(), rotations+1, comparisonFunc)
        }
    }

    fun isThereAMonster(monster: List<List<Int>>, image: List<String>): Boolean =
        monster.mapIndexed { row, monsterLineIndices -> monsterLineIndices.map { monsterLineIndex -> image[row][monsterLineIndex] == '#'} }.flatten().all { it }

    fun monstersInImage(pattern: List<List<Int>>, image: List<String>): Int {
        val rowWindows: List<List<String>> = image.windowed(pattern.size, 1, false)
        val asd = rowWindows.fold(0) { rowAcc, rowWindow ->
            val colWindows: List<List<String>> = rowWindow.map { line -> line.windowed(20, 1, false) }
            val foundInThisRow = (0 until colWindows[0].size).fold(0) { colAcc, index ->  //colWindows.fold(0) { colAcc, colWindow ->
                val colWindow = listOf(colWindows[0][index], colWindows[1][index], colWindows[2][index])
                if (isThereAMonster(pattern, colWindow)) colAcc + 1 else colAcc
            }
            rowAcc + foundInThisRow
        }
        return asd
    }

    fun mergeTiles(tiles: List<List<Tile>>): Tile {
        val withoutBorders = tiles.map { tileRow -> tileRow.map { tile -> tile.stripBorder() } }
        return Tile(0, withoutBorders.flatMap { tileRow ->
            val first = tileRow.first()
            tileRow.minus(first).fold(first.rows) { acc, tile ->
                acc.mapIndexed { index, s -> s + tile.rows[index] }
            }
        })
    }

    fun pt2(input: List<String>, pattern: List<String>): Int {
        val tiles = parseTiles(input)
        val tileMap = tiles.associateBy { it.id }
        val withNeighbors = puzzleTogether(tiles, tiles)
        val cornerTile = withNeighbors.filter { it.value.size == 2 }.keys.toList().get(1)
        val connectingTiles = withNeighbors[cornerTile]!!.map { tileMap[it] }

        val (left, right) = rotateBothUntilMatchesOrNull(cornerTile, connectingTiles.first()!!)!!
        val matchingTop =
            rotateUntilEdgesMatchOrNull(left.topEdge, connectingTiles.last()!!, 0) { target, b -> target == b.bottomEdge }

        val upperLeftTile = if (matchingTop != null) left.flipY() else left
        val mergedTiles = mergeTiles(joinImageTiles(tiles.minus(upperLeftTile), listOf(listOf(upperLeftTile))))

        val monsterIndices: List<List<Int>> =
            pattern.map { line -> line.mapIndexed { index, c -> if (c == '#') index else -1 }.filter { it != -1 } }
        val permutations =
            (0 until 3).fold(listOf(mergedTiles)) { acc, index -> acc + acc.last().rotate() } + (0 until 3).fold(
                listOf(mergedTiles.flip())
            ) { acc, index -> acc + acc.last().rotate() }
        val mostMonsters = permutations.map { monstersInImage(monsterIndices, it.rows) }
        val totalCount = mergedTiles.rows.map { it.count { c -> c == '#' } }.sum()
        val monsterTiles = mostMonsters.maxOrNull()!! * 15
        return totalCount - monsterTiles
    }
}
