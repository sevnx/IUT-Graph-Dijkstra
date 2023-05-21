# BUT_SAE_2_02

### **Groupe 106**

#### Membres de l'équipe

* Seweryn CZYKINOWSKI
* Kevin JIANG
* Clément GALIBARDY-SEFRIN
* Clément SALLIOT

#### Détails

* Toutes les classes de graphes ont été codées
* Dijkstra passe pour tout les graphes fournies dans les tests, en fonction de l'implémentation :
    * Liste d'adjacence :
      * `DorogovtsevMendes ` : Passe pour tout les graphes
      * `Barabasi` : Passe pour tout les graphes
      * `Full` : Passe pour tout les graphes
    * Matrice d'adjacence :
      * `DorogovtsevMendes ` : Jusqu'à 10000 sommets, trop long au delà (mémoire insuffisante)
      * `Barabasi` : Justqu'à 10002 sommets, impossible au delà (mémoire insuffisante)
      * `Full` : Passe pour tout les graphes
    * Table de hachage : 
      * `DorogovtsevMendes ` : Passe pour tout les graphes
      * `Barabasi` : Passe pour tout les graphes
      * `Full` : Passe pour tout les graphes
    * Liste d'arcs :
      * `DorogovtsevMendes ` : Jusqu'à 10000 sommets, trop long au delà
      * `Barabasi` : Justqu'à 10002 sommets, trop long au delà
      * `Full` : Jusqu'à 501 sommets, trop long au delà