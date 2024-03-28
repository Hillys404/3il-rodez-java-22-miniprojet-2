package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graphe<E> {

    private Map<Noeud<E>, Map<Noeud<E>, Double>> listeNoeuds;

    /**
     * Crée le graphique et initialise sa liste de noeud
     */
    public Graphe() {
        this.listeNoeuds = new HashMap<>();
    }

    /**
     * Ajoute un noeud au graphique
     * @param noeud
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        // Ajoute le noeud s'il n'existe pas déjà
        if(!this.listeNoeuds.containsKey(noeud)) {
            this.listeNoeuds.put(noeud, new HashMap<>());
        }
    }

    /**
     * Ajoute une connexion (arête) entre deux noeuds
     * @param depart noeud de départ
     * @param arrivee noeud d'arrivée
     * @param cout coût de l'arête
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        /*
         * Vérification de l'existence des noeuds depart et arrivee
         * Ajout des noeuds s'ils n'existent pas
         */
        if (!listeNoeuds.containsKey(depart)) ajouterNoeud(depart);
        if (!listeNoeuds.containsKey(arrivee)) ajouterNoeud(arrivee);

        // Liaison des deux noeuds
        this.listeNoeuds.get(depart).put(arrivee, cout);
    }

    /**
     * Récupère le coût de l'arête entre deux noeuds
     * @param depart noeud de départ
     * @param arrivee noeud d'arrivée
     * @return coût de l'arête (-1 si au moins un des deux noeuds n'existe pas)
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        if (listeNoeuds.containsKey(depart) && listeNoeuds.get(depart).containsKey(arrivee)) {
            return this.listeNoeuds.get(depart).get(arrivee);
        }
        return Double.MAX_VALUE;
    }

    /**
     * Récupère la liste de tous les noeuds du graphique
     * @return
     */
    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<>(this.listeNoeuds.keySet());
    }

    /**
     * Récupère la liste des noeuds voisins du noeud fourni en paramètre
     * @param noeud noeud pour lequel on cherche ses voisins
     * @return liste des noeuds voisins du noeud donné
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (listeNoeuds.containsKey(noeud)) {
            return new ArrayList<>(listeNoeuds.get(noeud).keySet());
//            return noeud.getVoisins();
        }
        return new ArrayList<>();
    }
}
