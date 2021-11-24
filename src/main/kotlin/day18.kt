object day18 {

    fun OpPrec1(c: Char): Int = if (c == '+' || c == '*') 1 else 0
    fun OpPrec2(c: Char): Int = if (c == '+') 2 else if (c == '*') 1 else 0

    tailrec fun toPostfix(expression: String, operators: String, output: String, precedence: (Char) -> Int): String {
        if (expression.isEmpty()) return output + operators.reversed()
        val nextToken = expression.first()
        val nextExpression = expression.drop(1)
        return when {
            nextToken.isDigit() -> toPostfix(nextExpression, operators, output + nextToken, precedence)
            nextToken == '(' -> toPostfix(nextExpression, operators + nextToken, output, precedence)
            nextToken == ')' -> {
                val popped = operators.reversed().takeWhile { it != '(' }
                toPostfix(nextExpression, operators.dropLast(popped.length+1), output + popped, precedence)
            }
            else -> {
                val popped = operators.reversed().takeWhile { precedence(nextToken) <= precedence(it) }
                toPostfix(nextExpression, operators.dropLast(popped.length) + nextToken, output + popped, precedence)
            }
        }
    }

    tailrec fun calculate(postFix: String, operands: List<Long> = emptyList()): Long {
        if (postFix.isEmpty()) return operands.first()
        val next: Char = postFix.first()
        return when {
            next.isDigit() -> calculate(postFix.drop(1), operands.plus(next.code.toLong() - 48))
            else -> {
                val left = operands.reversed()[0]
                val right = operands.reversed()[1]
                val newOps = if (next == '+') operands.dropLast(2).plus(left + right) else if (next == '*') operands.dropLast(2).plus(left * right) else operands
                calculate(postFix.drop(1), newOps)
            }
        }
    }

    fun pt1(input: List<String>): Long = input.sumOf { calculate(toPostfix(it.replace(" ", ""), "", "", ::OpPrec1)) }

    fun pt2(input: List<String>): Long = input.sumOf { calculate(toPostfix(it.replace(" ", ""), "", "", ::OpPrec2)) }
}
