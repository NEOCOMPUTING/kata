Bonjour,

Comme precisé dans le cahier des charges

J'ai ajouté une nouvelle méthode dans la classe GildedRose.updateQualityV2
L'ancienne methode reste intacte

Ceci permet de faire un test "before-after" pour assurer que le nouvelle version de code a le même comportement que l'ancienn version

La classe test unitaire GildedRoseTest charge le même jeu de données et lance les tests sur l'ancienne version et la nouvelle version.

il y a 2 tests unitaires à l'intérieur:
1er test : beforeAfterTestOk : test "before after" sur les jeux de données en excluant le dernier nouvel element qui crée le probleme avec l' ancienne version
et beforeAfterTestWithFix : test "before after" sur les jeux de données en incluant le dernier nouvel element qui crée le probleme avec l' ancienne version


Algorithme modifié completement

Afin d'etre plus proche au besoin fonctionnel, j' ai reecrit l'algo en séparant des règles plus lisibles et plus facile a maintenir. (on peut mieux faire encore)
On peut encore optimiser mais c'est juste pour le test kata...
Dans le cas idéal si l'environnement nous permet, on peut utiliser un moteur de regles DROOLS/BRMS pour modeliser ce besoin
Cette derniere facon de faire permet de séparer le code technique et le code métier ainsi assurer une lisibilité parfaite et facile à maintenir les regles sans l aide du développeur.

Je suis expert BRMS chez BNP BP2S et cette technologie permet de simplifier les probleme complexes.
On peut en discuter a vive voix

Bonne lecture
Chau Nguyen

