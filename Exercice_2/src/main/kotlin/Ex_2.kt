// TD1 EXO 2
// author : Nathan Bitoun

import kotlin.math.sqrt

// il existe déjà une fonction sqrt

fun realRoots(a: Double, b: Double, c: Double): List<Double> {
    val delta = b * b - 4 * a * c

    return when {
        delta < 0 -> emptyList() // Aucune racine réelle
        delta == 0.0 -> listOf(-b / (2 * a)) // Une racine double
        else -> {
            val sqrtDelta = sqrt(delta)
            listOf((-b - sqrtDelta) / (2 * a), (-b + sqrtDelta) / (2 * a)) // Deux racines réelles
        }
    }
}

fun main() {
    val testCases = listOf(
        Triple(1.0, -2.0, 1.0), // x^2 - 2x + 1
        Triple(1.0, -2.0, 2.0), // x^2 - 2x + 2
        Triple(1.0, 0.0, -2.0), // x^2 - 2
        Triple(1.0, -3.0, 2.0)  // x^2 - 3x + 2
    )

    for ((a, b, c) in testCases) {
        println("Polynôme ${a}x² + ${b}x + ${c} : Racines = ${realRoots(a, b, c)}")
    }
}
