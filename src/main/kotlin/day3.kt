object day3 {

    tailrec fun countTrees(map: List<String>, currentRow: Int, currentCol: Int, deltaRow: Int, deltaCol: Int, foundTrees: Int): Int {
        val nextRow = (currentRow + deltaRow) % map.size
        if (nextRow < currentRow) return foundTrees
        val nextCol = (currentCol + deltaCol) % map[0].length
        val hasTree = map[nextRow][nextCol] == '#'
        val newTrees = if (hasTree) foundTrees+1 else foundTrees
        return countTrees(map, nextRow, nextCol, deltaRow, deltaCol, newTrees)
    }

}