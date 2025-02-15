// TD1 EXO 3
// author : Nathan Bitoun

fun apply(n: Int, m: Int = 0, f: (Int) -> Int): Int {
    var result = n
    repeat(m) {
        result = f(result)
    }
    return result
}

fun main() {
    val result1 = apply(2, 3) { x -> x * x }
    println("Test 1: apply(2, 3, x -> x * x) = $result1") // 256

    /*
       Explication :
     Étapes successives :
     f(2) = 2 * 2 = 4
     f(4) = 4 * 4 = 16
     f(16) = 16 * 16 = 256
    */

    println("Test 2:")
    val result2 = apply(12, 8) { x ->
        println("  ${x % 2}") // Affiche le reste de x divisé par 2
        x / 2
    }
    println("Résultat final: $result2")

    /*
          Explication :
        Affiche le reste de la division par 2 à chaque étape et divise x par 2 :
        12 % 2 = 0 -> 12 / 2 = 6
        6 % 2 = 0 -> 6 / 2 = 3
        3 % 2 = 1 -> 3 / 2 = 1
        1 % 2 = 1 -> 1 / 2 = 0
        Puis tous les résultats sont 0
    */

    // Syracuse
    println("Test 3: Syracuse")
    val m = 10 // Nombre d'itérations
    val result3 = apply(7, m) { x -> if (x % 2 == 0) x / 2 else 3 * x + 1 }
    println("10ème terme de Syracuse à partir de 7 : $result3")

    // Approximation de √2
    println("Test 4: Approximation de √2")
    val sqrt2 = apply(1, 10) { x -> (x + (2 * x) / x) / 2 }
    println("Approximation de √2 après 10 itérations : $sqrt2")
}
