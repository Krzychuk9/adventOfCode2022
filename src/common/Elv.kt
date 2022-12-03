package common

data class Elv(val id: Int, val items: MutableList<Item> = mutableListOf()) {

    fun getTotalCalories() = items.sumOf { it.calories }

    fun getTotalItems() = items.size
}

data class Item(val calories: Int)
