[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-2972f46106e565e64193e422d61a12cf1da4916b45550586e14ef0a7c637dd04.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=18039742)
# L3 MIAGE - IHM et Prog Mobile - 2024-2025

## Leo Donati

### TD1 : introduction à Kotlin

Dans IntelliJ IDEA, vous devez créer un projet appelé `TD1` basé sur **kotlin** qui utilise `Maven` comme outil de build. 
- Comme répertoire de création, choisissez le répertoire que vous avez cloné à partir du repository du github classroom
- Dans les options étendues mettez l'identifiant de vore package
- Ne cochez pas la case pour ajouter du code exemple
- Ne cochez pas la case pour créer un repository git (car vous avez déjà un repository git)
- Pour le JDK visez une version récente (openjdk-22 pour moi).

Une fois le projet créé, vous allez créer un **module** pour chaque exercice, avec clic-droit **New** puis **Modules...**.

**Faites des `commit` et des `push` régulièrement !**

#### Exercice 1

1. Utiliser la méthode `random` du type `Range` pour générer une liste `rndList` de 1000 nombres aléatoires entre 20 et 40.
2. Pour calculer la moyenne des valeurs de cette liste on peut utiliser la méthode `SumOf` qui prend une expression lambda en argument. Ce qui donne :
```kotlin
val avg = rndList.sumOf { it } / rndList.size.toDouble()
```
- Calculer de la même manière la *variance* de cette liste, et déduisez-en l'écart-type.
3. Comparer la moyenne et la l'écart type empiriques calculés sur ce tirage avec les valeurs théoriques d'une loi uniforme sur l'intervalle [[20, 40]].
4. Générer un histogramme des valeurs de la liste `rndList` en utilisant le type `mutableMapOf<Int, Int>()`. Quelle est la modalité de la liste ? Quelle est la fréquence de cette modalité ?
5. Avec la même méthode que pour la variance calculer les moments centrés d'ordre 3 et 4 de la liste `rndList`. Puis déduisez-en le coefficient d'asymétrie et le coefficient d'aplatissement de la distribution.
6. Centrage et réduction de la liste `rndList` : pour chaque élément de la liste, soustrayez la moyenne et divisez par l'écart-type en utilisant la méthode `map` et une **expression lambda**. Stockez le résultat dans une nouvelle liste `zList`. Calculez la moyenne et l'écart-type de cette nouvelle liste. Que constatez-vous ?
7. Comparer les performances de la méthode `sumOf` avec une boucle `for` pour calculer la moyenne et la variance de la liste. Pour cela, utilisez la méthode `measureTimeMillis` de la bibliothèque `kotlin.system` pour mesurer le temps d'exécution de chaque méthode. Que constatez-vous ?

#### Exercice 2

1. Créez une fonction `mySqrt` qui prend un nombre réel `x` en argument et renvoie la racine carrée de `x` si `x` est positif ou nul, et `null` sinon.
2. Utilisez cette fonction pour écrire une fonction `realRoots` qui calcule les racines réelles d'un polynôme de degré 2 défini par 3 coefficients `a`, `b` et `c`. La fonction doit renvoyer une liste de 0, 1 ou 2 racines réelles.
3. Testez cette fonction avec les polynômes suivants :
- `x^2 - 2x + 1` : 1 racine double
- `x^2 - 2x + 2` : 0 racine réelle
- `x^2  - 2` : 2 racines réelles
- `x^2 - 3x + 2` : 2 racines réelles

#### Exercice 3

1. Créez une fonction `apply` qui prend en argument un entier `n`, un entier optionnel `m` initialisé à 0 et une expression lambda `f` qui prend un entier en argument et renvoie un entier. La fonction `apply` doit renvoyer la valeur de `f(f(...f(n)))` où `f` est appliquée `m` fois.
2. Testez cette fonction avec `n = 2`, `m = 3` et `f = { x -> x * x }`. Que renvoie la fonction `apply` ? Expliquez.
3. Testez cette fonction avec `n = 12`, `m = 8` et comme lambda l'expression suivante :
```kotlin
{ x ->
    println("  ${x % 2}")
    x / 2
}
```
- Que fait la fonction `apply` ?
4. Comment utiliser `apply` pour afficher les `m` premiers termes de la suite de Syracuse et de renvoyer le `m`-ième terme de cette suite ?
5. Comment utiliser `apply` pour calculer une approximation de $\sqrt 2$ en utilisant la suite $u_0 = 1$ et $u_{n+1} = \frac{1}{2}(u_n + \frac{2}{u_n}) $
