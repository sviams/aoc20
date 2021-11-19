object day12 {

    val dirs = listOf(1, 0, -1, 0)

    tailrec fun navigate(input: List<String>, x: Int, y: Int, dX: Int, dY: Int) : Pair<Int, Int> {
        if (input.isEmpty()) return x to y
        val next = input.first()
        val dir = next.first()
        val amount = next.drop(1).toInt()
        val (nextX, nextY) = when (dir) {
            'N' -> x to y+amount
            'S' -> x to y-amount
            'E' -> x+amount to y
            'W' -> x-amount to y
            'F' -> x+(dirs[dX]*amount) to y+(dirs[dY]*amount)
            else -> x to y
        }
        val (nextDx, nextDy) = when (dir) {
            'L' -> (dX + 4 - amount/90) % 4 to (dY + 4 - amount/90) % 4
            'R' -> (dX + amount/90) % 4 to (dY + amount/90) % 4
            else -> dX to dY
        }
        return navigate(input.drop(1), nextX, nextY, nextDx, nextDy)
    }

    val turns = listOf(-1, -1, 1, 1)
    val directions = listOf('E', 'S', 'W', 'N')
/*
    tailrec fun rotateRight90(x: Int, y: Int, dirX: Int, dirY: Int, times: Int): Pair<Int, Int> {
        if (times == 0) return x to y
        val xDir = dirs[dirX]
        val yDir = dirs[dirY]
        val newX = when (dirX) {
            1 -> y
            0 -> 0
            else -> 1
        }
    }
*/
    fun turn(turn: String, x: Int, y: Int): Pair<Int, Int> {
        return when (turn) {
            "R90", "L270" -> y to -x
            "L90", "R270" -> -y to x
            "L180", "R180" -> -x to -y
            else -> x to y
        }
    }

    tailrec fun moveWaypoint(input: List<String>, wpX: Int, wpY: Int, shipX: Int, shipY: Int) : Pair<Int, Int> {
        if (input.isEmpty()) return shipX to shipY
        val next = input.first()
        val dir = next.first()
        val amount = next.drop(1).toInt()
        /*
        val nextDirIndex = when (dir) {
            'L' -> (dirIndex + 4 - amount/90) % 4
            'R' -> (dirIndex + amount/90) % 4
            else -> dirIndex
        }
        */



        val (nextWpX, nextWpY) = when (dir) {
            'N' -> wpX to wpY+amount
            'S' -> wpX to wpY-amount
            'E' -> wpX+amount to wpY
            'W' -> wpX-amount to wpY
            'L' -> turn(next, wpX, wpY)//-wpY to -wpX
            'R' -> turn(next, wpX, wpY)
            else -> wpX to wpY
        }

        val (nextShipX, nextShipY) = if (dir == 'F') shipX + amount * wpX to shipY + amount * wpY else shipX to shipY

        return moveWaypoint(input.drop(1), nextWpX, nextWpY, nextShipX, nextShipY)
    }

    fun pt1(input: List<String>): Int {
        val asd = navigate(input, 0, 0, 0, 1)
        return Math.abs(asd.first) + Math.abs(asd.second)
    }

    fun pt2(input: List<String>): Int {
        val asd = moveWaypoint(input, 10, 1, 0, 0)
        return Math.abs(asd.first) + Math.abs(asd.second)
    }
}
