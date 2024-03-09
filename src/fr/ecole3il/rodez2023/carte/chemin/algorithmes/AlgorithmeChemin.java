package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;

public interface AlgorithmeChemin<E> {

    /**
     * Trouve le chemin (le plus court) entre un noeud départ
     * et un noeud arrivé dans le graphe donné
     * @param graphe graphe avec lequel on travaille
     * @param depart noeud de départ
     * @param arrivee noeud d'arrivée
     * @return liste de noeuds qui constitue le chemin entre les deux noeuds
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}
