package task1

import java.util.*

interface ParenthesisTask {
    fun calculate(numberOfPairs: Int): Iterable<String>
}

class ExhaustiveSolution : ParenthesisTask {

    override fun calculate(numberOfPairs: Int): Iterable<String> {
        val allCombinations = generateAll(numberOfPairs * 2)
        val answer = allCombinations.filter {
            it.isCorrect()
        }
        return answer
    }

}

/* LAZY ?!
class ExhaustiveLessMemorySolution : ParenthesisTask {

    override fun calculate(numberOfPairs: Int): Iterable<String> {
        val allCombinations = generateAll(numberOfPairs * 2)
        val answer = allCombinations.filter {
            it.isCorrect()
        }
        return answer
    }

}*/

class LessExhaustiveSolution : ParenthesisTask {

    override fun calculate(numberOfPairs: Int): Iterable<String> {
        val parenthesisList = mutableListOf<String>().also { list ->
            repeat(numberOfPairs) {
                list.add("(")
            }
            repeat(numberOfPairs) {
                list.add(")")
            }
        }
        val allPermutations = permute(parenthesisList)
        val answer = allPermutations
                .map {
                    it.joinToString(separator = "")
                }
                .filter { it.isCorrect() }
                .toSet()
        return answer
    }

}

fun CharSequence.isCorrect(): Boolean {
    val stack = ArrayDeque<Char>(length)

    for (element in this) {
        if (element == '(') stack.push(element)

        if (element == ')') {
            if (stack.isEmpty()) return false
            if (stack.pop() != '(') return false
        }
    }

    return stack.isEmpty()
}

fun generateAll(n: Int): List<String> {

    val perms = mutableListOf<String>()

    fun combinate(builder: String) {
        if (builder.length == n) perms.add(builder)
        else {
            combinate(builder + "(")
            combinate(builder + ")")
        }
    }

    combinate("")

    return perms

}


fun <T> permute(input: List<T>): List<List<T>> {
    if (input.size == 1) return listOf(input)
    val perms = mutableListOf<List<T>>()
    val toInsert = input[0]
    for (perm in permute(input.drop(1))) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, toInsert)
            perms.add(newPerm)
        }
    }
    return perms
}