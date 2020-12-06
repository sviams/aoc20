object day3 {

    tailrec fun countTrees(map: List<String>, currentRow: Int, currentCol: Int, deltaRow: Int, deltaCol: Int, foundTrees: Int): Int {
        val nextRow = (currentRow + deltaRow) % map.size
        if (nextRow < currentRow) return foundTrees
        val nextCol = (currentCol + deltaCol) % map[0].length
        return countTrees(map, nextRow, nextCol, deltaRow, deltaCol, if (map[nextRow][nextCol] == '#') foundTrees+1 else foundTrees)
    }

}