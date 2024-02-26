package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E>{

    private List<Noeud<E>> listeNoeuds;

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {

        graphe.ajouterNoeud(depart);
        graphe.ajouterNoeud(arrivee);

        listeNoeuds = graphe.getNoeuds();

        // depart.setValeur(0);
        // arrivee.setValeur(Integer.MAX_VALUE);

        for (Noeud<E> noeud : listeNoeuds) {
            noeud.ajouterVoisins(null);
        }

        return null;
    }
}
