package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Noeud<E> {

    private E valeur;
    private List<Noeud<E>> voisins;

    /**
     * Crée le noeud avec sa valeur et initialise
     * la liste de ses noeuds voisins
     * @param valeur
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur du noeud
     * @return
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Modifie la valeur du noeud
     * @return
     */

    /**
     * Récupère les noeuds voisins du noeud courant
     * @return
     */
    public List<Noeud<E>> getVoisins() {
        return this.voisins;
    }

    /**
     * Ajoute un nouveau noeud voisin
     * @param voisin
     */
    public void ajouterVoisins(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}
