package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E>{

    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {

        Map<Noeud<E>, Noeud<E>> listeNoeuds = new HashMap<>();
        Map<Noeud<E>, Double> coutEstime = new HashMap<>();
        Map<Noeud<E>, Double> coutReel = new HashMap<>();

        for (Noeud<E> noeud : graphe.getNoeuds()) {
            coutEstime.put(noeud, Double.POSITIVE_INFINITY);
            coutReel.put(noeud, Double.POSITIVE_INFINITY);
            listeNoeuds.put(noeud, null);
        }

        coutEstime.put(depart, 0.0);
        coutReel.put(depart, 0.0);

        // Initialisation d'une file de prioritée triée par le coût total estimé
        PriorityQueue<Noeud<E>> priorityQueue = new PriorityQueue<>(Comparator.comparingDouble(coutEstime::get));
        priorityQueue.add(depart);

        while(!priorityQueue.isEmpty()) {
            Noeud<E> noeud = priorityQueue.poll();

            // Si le noeud supprimé est le noeud d'arrivée, l'algo s'arrête
            if (noeud.equals(arrivee)) break;

            for (Noeud<E> voisin : graphe.getVoisins(noeud)) {
                double coutTotal = coutReel.get(noeud) + graphe.getCoutArete(noeud, voisin);
                /*
                 * Si le coût est inférieur au coût du noeud voisin,
                 * on met à jour le coût du voisin et son prédécesseur
                 */
                if (coutTotal < coutReel.get(voisin)) {
                    coutReel.put(voisin, coutTotal);
                    listeNoeuds.put(voisin, noeud);

                    // Et on l'ajoute à la file de priorité
                    priorityQueue.add(voisin);

                    double coutEstimeVoisin = coutTotal + heuristique(voisin, arrivee);

                    if (coutEstimeVoisin < coutEstime.get(voisin)) {
                        coutEstime.put(voisin, coutEstimeVoisin);
                    }
                }
            }
        }

        LinkedList<Noeud<E>> chemin = new LinkedList<>();

        // Reconstruction du chemin le plus court
        Noeud<E> noeudCourant = arrivee;

        while(noeudCourant != null) {
            chemin.addFirst(noeudCourant);
            noeudCourant = listeNoeuds.get(noeudCourant);
        }
        return chemin;
    }

    /**
     * Calcule le coût estimé pour arriver au noeud d'arrivée
     * @param noeud noeud courant
     * @param arrivee noeud d'arrivée
     * @return coût estimé
     */
    private double heuristique(Noeud<E> noeud, Noeud<E> arrivee) {
        // TODO Choisir une heuristique
        return 0.0;
    }
}
