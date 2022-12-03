import common.Elv
import common.Item

fun main() {
    fun part1(input: List<String>): Int {
        return input.getElves()
                .maxBy { it.getTotalCalories() }
                .getTotalCalories()
    }

    fun part2(input: List<String>): Int {
        return input.getElves()
                .sortedByDescending { it.getTotalCalories() }
                .take(3)
                .sumOf { it.getTotalCalories() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

fun List<String>.getElves(): List<Elv> {
    val elves = mutableListOf(Elv(1))

    this.forEach {
        val lastElv = elves.last()
        if (it.isEmpty()) elves.add(Elv(lastElv.id + 1)) else lastElv.items.add(Item((it.toInt())))
    }

    return elves
}
