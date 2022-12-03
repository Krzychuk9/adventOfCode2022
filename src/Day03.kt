fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val midPoint = it.length / 2
            val itemsInFirstCompartment = it.substring(0, midPoint).toSet()
            val itemsInSecondCompartment = it.substring(midPoint).toSet()
            val item = itemsInFirstCompartment.intersect(itemsInSecondCompartment).first()
            getItemPriority(item)
        }
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3)
                .map {
                    it.reduce { l1, l2 -> l1.toSet().intersect(l2.toSet()).joinToString() }
                }
                .sumOf { getItemPriority(it.first()) }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun getItemPriority(char: Char) = char.code - getCharOffset(char)

fun getCharOffset(char: Char) = if (char.isUpperCase()) 38 else 96
