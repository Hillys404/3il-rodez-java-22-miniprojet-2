package fr.ecole3il.rodez2023.carte.elements;

import java.util.List;

public class Noeud<E> {

    private final E valeur;

    private List<Noeud<E>> list;

    public Noeud(E valeur) {
        this.valeur = valeur;
        // initialiser la liste de voisins
    }

    public E getValeur() {
        return valeur;
    }

    public List<Noeud<E>> getVoisins() {
        return this.list;
    }

    public void ajouterVoisins(Noeud<E> voisin) {
        this.list.add(voisin);
    }
}
