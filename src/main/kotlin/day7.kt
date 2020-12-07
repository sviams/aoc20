object day7 {

    fun countBagsOneWay(rules: List<String>, target: String): List<String> {
        val containers = rules.filter { it.indexOf(target) > 0 }.map {
            val (cnt) = """^(.*) bags contain.*$""".toRegex().find(it)!!.destructured
            cnt
        }
        return containers + containers.flatMap { countBagsOneWay(rules, it) }
    }

    fun countBagsThatHoldGold(input: List<String>): Int = countBagsOneWay(input, "shiny gold").distinct().size

    fun countBagsTheOtherWay(rules: List<String>, target: String): Int {
        val container = rules.first { it.indexOf(target) == 0 }
        if (container.indexOf("no other") >= 0) return 1
        val children = container.substring(container.indexOf("bags contain ")).split(',').map {
            val (amount, type) = """(\d) (.*) bag.*""".toRegex().find(it)!!.destructured
            Pair(amount.toInt(), type)
        }
        return  1 + children.sumBy { it.first * countBagsTheOtherWay(rules, it.second) }
    }

    fun countBagsThatGoldHolds(input: List<String>): Int = countBagsTheOtherWay(input, "shiny gold") - 1

}