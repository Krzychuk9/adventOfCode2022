fun main() {
    fun part1(input: List<String>): Int {
        return input.sumOf {
            val opponentStrategy = getGameStrategy(it[0])
            val strategy = getGameStrategy(it[2])
            strategy.getResult(opponentStrategy) + strategy.getPoint()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf {
            val opponentStrategy = getGameStrategy(it[0])
            val strategy = getGameStrategy(opponentStrategy, it[2])
            strategy.getResult(opponentStrategy) + strategy.getPoint()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun getGameStrategy(type: Char) = when (type) {
    'A', 'X' -> RockStrategy()
    'B', 'Y' -> PaperStrategy()
    'C', 'Z' -> ScissorsStrategy()
    else -> throw IllegalArgumentException()
}

fun getGameStrategy(opponentStrategy: GameStrategy, result: Char) =
        when (result) {
            'X' -> opponentStrategy.getLose()
            'Y' -> opponentStrategy
            'Z' -> opponentStrategy.getWin()
            else -> throw IllegalArgumentException()

        }

sealed interface GameStrategy {

    fun getResult(opponentStrategy: GameStrategy): Int
    fun getPoint(): Int
    fun getWin(): GameStrategy
    fun getLose(): GameStrategy
}

class RockStrategy() : GameStrategy {

    override fun getResult(opponentStrategy: GameStrategy) = when (opponentStrategy) {
        is ScissorsStrategy -> 6
        is RockStrategy -> 3
        is PaperStrategy -> 0
    }

    override fun getPoint() = 1
    override fun getWin() = PaperStrategy()
    override fun getLose() = ScissorsStrategy()
}

class PaperStrategy() : GameStrategy {

    override fun getResult(opponentStrategy: GameStrategy) = when (opponentStrategy) {
        is RockStrategy -> 6
        is PaperStrategy -> 3
        is ScissorsStrategy -> 0
    }

    override fun getPoint() = 2
    override fun getWin() = ScissorsStrategy()
    override fun getLose() = RockStrategy()
}

class ScissorsStrategy() : GameStrategy {

    override fun getResult(opponentStrategy: GameStrategy) = when (opponentStrategy) {
        is PaperStrategy -> 6
        is ScissorsStrategy -> 3
        is RockStrategy -> 0
    }

    override fun getPoint() = 3
    override fun getWin() = RockStrategy()
    override fun getLose() = PaperStrategy()
}
