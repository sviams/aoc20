object day17 {

    data class Cube(val x: Int, val y: Int, val z: Int)
    data class HyperCube(val x: Int, val y: Int, val z: Int, val w: Int)

    fun parseGrid(input: List<String>): List<Cube> =
        input.flatMapIndexed { row, lineString ->
            lineString.mapIndexed { col, node ->
                if (node == '#') Cube(col, row, 0) else null
            }.filterNotNull()
        }

    fun parseHyperGrid(input: List<String>): List<HyperCube> =
        input.flatMapIndexed { row, lineString ->
            lineString.mapIndexed { col, node ->
                if (node == '#') HyperCube(col, row, 0, 0) else null
            }.filterNotNull()
        }

    tailrec fun evolve3d(grid: List<Cube>, targetCycles: Int, cycle: Int = 0): List<Cube> {
        if (cycle == targetCycles) return grid
        val xRange = IntRange(grid.minByOrNull { it.x }!!.x - 1, grid.maxByOrNull { it.x }!!.x + 1)
        val yRange = IntRange(grid.minByOrNull { it.y }!!.y - 1, grid.maxByOrNull { it.y }!!.y + 1)
        val zRange = IntRange(grid.minByOrNull { it.z }!!.z - 1, grid.maxByOrNull { it.z }!!.z + 1)
        val newGrid = xRange.flatMap { x ->
            yRange.flatMap { y ->
                zRange.map { z ->
                    val nearX = IntRange(x-1, x+1)
                    val nearY = IntRange(y-1, y+1)
                    val nearZ = IntRange(z-1, z+1)
                    val thisCube = Cube(x,y,z)
                    val neighbors = grid.minus(thisCube).filter { nearX.contains(it.x) && nearY.contains(it.y) && nearZ.contains(it.z) }.count()
                    val isActive = grid.contains(thisCube)
                    if ((isActive && (neighbors == 2 || neighbors == 3)) || !isActive && neighbors == 3) thisCube else null
                }.filterNotNull()
            }
        }
        return evolve3d(newGrid, targetCycles, cycle + 1)
    }

    tailrec fun evolve4d(grid: List<HyperCube>, targetCycles: Int, cycle: Int = 0): List<HyperCube> {
        if (cycle == targetCycles) return grid
        val xRange = IntRange(grid.minByOrNull { it.x }!!.x - 1, grid.maxByOrNull { it.x }!!.x + 1)
        val yRange = IntRange(grid.minByOrNull { it.y }!!.y - 1, grid.maxByOrNull { it.y }!!.y + 1)
        val zRange = IntRange(grid.minByOrNull { it.z }!!.z - 1, grid.maxByOrNull { it.z }!!.z + 1)
        val wRange = IntRange(grid.minByOrNull { it.w }!!.w - 1, grid.maxByOrNull { it.w }!!.w + 1)
        val newGrid = xRange.flatMap { x ->
            yRange.flatMap { y ->
                zRange.flatMap { z ->
                    wRange.map { w ->
                        val nearX = IntRange(x-1, x+1)
                        val nearY = IntRange(y-1, y+1)
                        val nearZ = IntRange(z-1, z+1)
                        val nearW = IntRange(w-1, w+1)
                        val thisCube = HyperCube(x,y,z,w)
                        val neighbors = grid.minus(thisCube).filter { nearX.contains(it.x) && nearY.contains(it.y) && nearZ.contains(it.z) && nearW.contains(it.w) }.count()
                        val isActive = grid.contains(thisCube)
                        if ((isActive && (neighbors == 2 || neighbors == 3)) || !isActive && neighbors == 3) thisCube else null
                    }.filterNotNull()
                }
            }
        }
        return evolve4d(newGrid, targetCycles, cycle + 1)
    }

    fun pt1(input: List<String>): Int = evolve3d(parseGrid(input), 6).count()

    fun pt2(input: List<String>): Int = evolve4d(parseHyperGrid(input), 6).count()
}
