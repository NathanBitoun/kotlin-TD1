// TD1 EXO 1

// author : Nathan Bitoun

import kotlin.math.sqrt
import kotlin.math.pow
import kotlin.system.measureTimeMillis

// Extension pour calculer la moyenne
fun List<Double>.moy(): Double = this.sum() / this.size

// Extension pour calculer les moments centrés
fun List<Double>.moment(order: Int): Double {
    val mean = this.moy()
    return this.sumOf { (it - mean).pow(order) } / this.size
}

// Extension pour calculer la variance
fun List<Double>.variance(): Double = this.moment(2)

// Extension pour calculer l'écart-type
fun List<Double>.standardDeviation(): Double = sqrt(this.variance())

// Extension pour le coefficient d'asymétrie (skewness)
fun List<Double>.skewness(): Double {
    val m3 = this.moment(3)
    val sigma = this.standardDeviation()
    return m3 / sigma.pow(3)
}

// Extension pour le coefficient d'aplatissement (kurtosis)
fun List<Double>.kurtosis(): Double {
    val m4 = this.moment(4)
    val sigma = this.standardDeviation()
    return (m4 / sigma.pow(4)) - 3
}
fun List<Double>.moySumOf(): Double = this.sumOf { it } / this.size

fun List<Double>.varianceSumOf(): Double {
    val mean = this.moySumOf()
    return this.sumOf { (it - mean).pow(2) } / this.size
}

fun List<Double>.moyForLoop(): Double {
    var sum = 0.0
    for (value in this) {
        sum += value
    }
    return sum / this.size
}

fun List<Double>.varianceForLoop(): Double {
    val mean = this.moyForLoop()
    var sum = 0.0
    for (value in this) {
        sum += (value - mean).pow(2)
    }
    return sum / this.size
}
fun main() {
    val rndList = List(1000) { (20..40).random().toDouble() }  // Convertir en Double

    // Calcul des valeurs empiriques
    val moyEmpirique = rndList.moy()
    val varianceEmpirique = rndList.variance()
    val stdEmpirique = rndList.standardDeviation()

    val mean = rndList.moySumOf()
    val stdDev = sqrt(rndList.varianceSumOf())

    // Valeurs théoriques pour une loi uniforme U(20,40)
    val moyenneTheorique = (20 + 40) / 2.0
    val varianceTheorique = ((40 - 20).toDouble().pow(2)) / 12.0
    val stdTheoretical = sqrt(varianceTheorique)

    // Création de l'histogramme
    val histogram = mutableMapOf<Int, Int>()
    for (num in rndList.map { it.toInt() }) {
        histogram[num] = histogram.getOrDefault(num, 0) + 1
    }
    val modalite = histogram.maxByOrNull { it.value }?.key ?: 0
    val modFrequence = histogram[modalite] ?: 0

    // Skewness et Kurtosis
    val skewness = rndList.skewness()
    val kurtosis = rndList.kurtosis()

    // Transformation Z-score
    val zList = rndList.map { (it - moyEmpirique) / stdEmpirique }
    val moyZList = zList.moy()
    val stdZList = zList.standardDeviation()

    // Mesure du temps pour la moyenne
    val timeMoySumOf = measureTimeMillis { zList.moySumOf() }
    val timeMoyForLoop = measureTimeMillis { zList.moyForLoop() }

    // Mesure du temps pour la variance
    val timeVarSumOf = measureTimeMillis { zList.varianceSumOf() }
    val timeVarForLoop = measureTimeMillis { zList.varianceForLoop() }


    // Affichage des résultats
    println("Moyenne empirique      : $moyEmpirique")
    println("Moyenne théorique      : $moyenneTheorique")
    println("Écart-type empirique   : $stdEmpirique")
    println("Écart-type théorique   : $stdTheoretical")
    println("Variance empirique     : $varianceEmpirique")
    println("Variance théorique     : $varianceTheorique")

    println("\nHistogramme des valeurs :")
    histogram.toSortedMap().forEach { (value, count) ->
        print("Valeur $value : $count fois --")
    }

    println("\nModalité (valeur la plus fréquente) : $modalite")
    println("Fréquence de cette modalité        : $modFrequence")

    println("\nMoment centré d'ordre 3 : ${rndList.moment(3)}")
    println("Moment centré d'ordre 4 : ${rndList.moment(4)}")
    println("Coefficient d'asymétrie : $skewness")
    println("Coefficient d'aplatissement : $kurtosis")

    println("\nAprès normalisation (Z-score) :")
    println("Moyenne de zList       : $moyZList")
    println("Écart-type de zList    : $stdZList")

    println("On constate qu'après la normalisation la moyenne est très proche de 0 et que l'écart type de très proche de 1")

    println("Temps pour calculer la moyenne :")
    println("- Avec sumOf     : $timeMoySumOf ms")
    println("- Avec for loop  : $timeMoyForLoop ms")

    println("\nTemps pour calculer la variance :")
    println("- Avec sumOf     : $timeVarSumOf ms")
    println("- Avec for loop  : $timeVarForLoop ms")

    println("On constate qu'avec sumOf c'est plus long qu'avec une boucle for")
}
