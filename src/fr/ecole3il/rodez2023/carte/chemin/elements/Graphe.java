package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

public class Graphe<E> {

    private List<Noeud<E>> listeNoeuds;
    private List<Noeud<E>> listeVoisins;
    private double matrice;

    /**
     * Crée le graphique et initialise sa liste de noeud
     */
    public Graphe() {
        this.listeNoeuds = new ArrayList<>();
    }

    /**
     * Ajoute un noeud au graphique
     * @param noeud
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        this.listeNoeuds.add(noeud);
    }

    /**
     * Ajoute une connexion (arête) entre deux noeuds
     * @param depart
     * @param arrivee
     * @param cout
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        /*
         * Vérification de l'existence des noeuds depart et arrivee
         * Ajout des noeuds s'ils n'existent pas
         */
        if (!listeNoeuds.contains(depart)) ajouterNoeud(depart);
        if (!listeNoeuds.contains(arrivee)) ajouterNoeud(arrivee);

        // Ajout de l'arête dans la matrice d'adjacence associée au noeud de départ
        // todo appel matrice
    }

    /**
     * Récupère le coût de l'arête entre deux noeuds
     * @param depart
     * @param arrivee
     * @return
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        // Utilisation de la matrice du noeud de depart pour récupérer le cout de l'arête
        // Si il n'y a pas de liaison entre les deux noeuds, renvoi 0



        // TODO coder
        return 0;
    }

    /**
     * Récupère la liste de tous les noeuds du graphique
     * @return
     */
    public List<Noeud<E>> getNoeuds() {
        return listeNoeuds;
    }

    /**
     * Récupère la liste des noeuds voisins du noeud fourni en paramètre
     * @param noeud
     * @return
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        return noeud.getVoisins();
    }
}
