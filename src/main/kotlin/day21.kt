object day21 {

    data class Food(val ingredients: List<String>, val allergens: List<String>)

    fun parseFoods(input: List<String>): List<Food> = input.fold(emptyList()) { acc, line ->
        val (ingredientsString, allergensString) = line.split(" (contains ")
        val ingredients = ingredientsString.split(" ")
        val allergens = allergensString.dropLast(1).split(", ")
        acc + Food(ingredients, allergens)
    }

    fun mapAllergensToIngredients(foods: List<Food>): Map<String, String> {
        val allergens = foods.flatMap { it.allergens }.distinct()
        val foodsPerAllergen: Map<String, List<Food>> = allergens.map { allergen -> allergen to foods.filter { food -> food.allergens.contains(allergen) } }.toMap()
        val allergenicIngredients: Map<String, Set<String>> = foodsPerAllergen.map { (allergen: String, foodList: List<Food>) -> allergen to foodList.drop(1).fold(foodList.first().ingredients.toSet()) { acc, food -> food.ingredients.intersect(acc) } }.toMap()
        return reduceAllergenics(allergenicIngredients)
    }

    tailrec fun reduceAllergenics(possibles: Map<String, Set<String>>, result: Map<String, String> = emptyMap()): Map<String, String> {
        if (possibles.isEmpty()) return result
        val (allergen, ingredient) = possibles.toList().sortedBy { it.second.size }.first()
        val newResult = result.plus(allergen to ingredient.first())
        val newPossibles = possibles.minus(allergen).map { (allergen, ingredients) -> allergen to ingredients.minus(ingredient) }.toMap()
        return reduceAllergenics(newPossibles, newResult)
    }

    fun pt1(input: List<String>): Int {
        val foods = parseFoods(input)
        val allergenicIngredients = mapAllergensToIngredients(foods).values
        return (foods.flatMap { it.ingredients }.distinct() - allergenicIngredients).fold(0) { acc, ingredient -> acc + foods.count { food -> food.ingredients.contains(ingredient) } }
    }

    fun pt2(input: List<String>): String =
        mapAllergensToIngredients(parseFoods(input)).toSortedMap().values.joinToString(",")
}
