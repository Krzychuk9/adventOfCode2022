fun main() {
    fun part1(input: List<String>): Int {
        return input.toRanges().count {
            val firstGroup = it[0]
            val secondGroup = it[1]
            firstGroup.subtract(secondGroup).isEmpty() || secondGroup.subtract(firstGroup).isEmpty()
        }
    }

    fun part2(input: List<String>): Int {
        return input.toRanges().count {
            val firstGroup = it[0]
            val secondGroup = it[1]
            firstGroup.subtract(secondGroup).size != firstGroup.toSet().size
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))//582
    println(part2(input))//893
}

fun List<String>.toRanges() = this.map {
    it.split(",")
            .map { range -> range.toRange() }
}

fun String.toRange(): IntRange {
    val split = this.split("-")
    return split[0].toInt()..split[1].toInt()
}

