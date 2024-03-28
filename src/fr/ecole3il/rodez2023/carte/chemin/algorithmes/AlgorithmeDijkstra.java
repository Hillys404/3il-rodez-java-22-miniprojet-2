package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E>{

    /**
     * Implémentation de la méthode trouverChemin adaptée à l'algorithme de Dijkstra
     * @param graphe graphe avec lequel on travaille
     * @param depart noeud de départ
     * @param arrivee noeud d'arrivée
     * @return
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {

        Map<Noeud<E>, Noeud<E>> listeNoeuds = new HashMap<>();
        Map<Noeud<E>, Double> listeCouts = new HashMap<>();

        /*
         * Initialisation du coût de chaque noeud
         * et initialisation de leur prédécesseurs
         */
        for (Noeud<E> noeud : graphe.getNoeuds()) {
            listeCouts.put(noeud, Double.MAX_VALUE);
            listeNoeuds.put(noeud, null);
        }

        // Coût du noeud de départ mis à zéro
        listeCouts.put(depart, 0.0);

        // File de priorité décidée par le coût de chaque noeud
        PriorityQueue<Noeud<E>> priorityQueue = new PriorityQueue<>((n1, n2) -> (int) (listeCouts.get(n1) - listeCouts.get(n2)));

        priorityQueue.add(depart);

        while(!priorityQueue.isEmpty()) {
            Noeud<E> noeud = priorityQueue.poll();
            // Si le noeud supprimé est le noeud d'arrivée, l'algo s'arrête
            if (noeud.equals(arrivee)) break;

            /*
             * Sinon, pour chaque voisin du nœud actuel on calcule le coût
             * pour atteindre ce voisin
             */
            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double coutTotal = listeCouts.get(noeud) + graphe.getCoutArete(noeud, voisin);

                /*
                 * Si le coût est inférieur au coût du noeud voisin,
                 * on met à jour le coût du voisin et son prédécesseur
                 */
                if (coutTotal < listeCouts.get(voisin)) {
                    listeCouts.put(voisin, coutTotal);
                    listeNoeuds.put(voisin, noeud);

                    // Et on l'ajoute à la file de priorité
                    priorityQueue.add(voisin);
                }
            }
        }

        LinkedList<Noeud<E>> chemin = new LinkedList<>();

        // Reconstruction du chemin le plus court
        Noeud<E> noeudCourant = arrivee;

        while(noeudCourant != null) {
            chemin.add(0, noeudCourant);
            noeudCourant = listeNoeuds.get(noeudCourant);
        }
        Collections.reverse(chemin);
        return chemin;
    }
}
