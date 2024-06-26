package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {

    /**
     * Création du graphe à l'aide d'une carte
     * @param carte la carte fournie
     * @return le graphe créé
     */
    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();

        for (int x = 0; x < carte.getLargeur(); x++) {
            for (int y = 0; y < carte.getHauteur(); y++) {

                Case caseCourante = new Case(carte.getTuile(x, y), x, y);
                graphe.ajouterNoeud(new Noeud<>(caseCourante));

//                ajouterAretesVoisines(graphe, caseCourante, x, y, carte.getLargeur(), carte.getHauteur());
            }
        }

        for (int x = 0; x < carte.getLargeur(); x++) {
            for (int y = 0; y < carte.getHauteur(); y++) {
                Case caseCourante = new Case(carte.getTuile(x, y), x, y);
                ajouterAretesVoisines(graphe, caseCourante, x, y, carte.getLargeur(), carte.getHauteur());
            }
        }

        return graphe;
    }

    /**
     * Ajoute toutes les liaisons (arêtes) d'un noeud et de ses voisins
     * @param graphe le graphe fourni
     * @param caseCourante la case courante
     * @param x abscisse de la case
     * @param y ordonnée de la case
     * @param largeur du graphe
     * @param hauteur du graphe
     */
    private static void ajouterAretesVoisines(Graphe<Case> graphe, Case caseCourante, int x, int y, int largeur, int hauteur) {
//        for (int i = Math.max(0, x - 1); i <= Math.min(x + 1, largeur - 1); i++) {
//            for (int j = Math.max(0, y - 1); j <= Math.min(y + 1, hauteur - 1); j++) {
//                if (i != x || j != y) {
//                    Tuile tuile = carteCourante.getTuile(i, j);
//                    Case caseVoisine = new Case(tuile, i, j);
//                    double cout = calculerCout(caseCourante, caseVoisine);
//
//                    // Cast les cases en Noeud
//                    Noeud<Case> noeudCourant = new Noeud<>(caseCourante);
//                    Noeud<Case> noeudVoisin = new Noeud<>(caseVoisine);
//                    noeudCourant.ajouterVoisins(noeudVoisin);
//                    graphe.ajouterArete(noeudCourant, noeudVoisin, cout);
//                }
//            }
//        }

        Noeud<Case> noeudCourant = null;
        for (Noeud<Case> noeud : graphe.getNoeuds()) {
            Case case1 = noeud.getValeur();
            if (case1.equals(caseCourante)) {
                noeudCourant = noeud;
                break;
            }
        }

        if (noeudCourant != null) {
            int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];

                if (newX >= 0 && newX < largeur && newY >= 0 && newY < hauteur) {
                    Noeud<Case> noeudVoisin = getNoeud(graphe,newX, newY);
                    if (noeudVoisin != null) {
                        Case neighborCase = noeudVoisin.getValeur();
                        double cost = calculerCout(caseCourante, neighborCase);
                        graphe.ajouterArete(noeudCourant, noeudVoisin, cost);
                        noeudCourant.ajouterVoisins(noeudVoisin);
                    }
                }
            }
        }
    }

    /**
     * Calcule le coût pour se déplacer d'une case à une autre
     * @param from case de début
     * @param to case destination
     * @return le coût pour se déplacer
     */
    private static double calculerCout(Case from, Case to) {
        int coutFrom = from.getTuile().getPenalite();
        int coutTo = to.getTuile().getPenalite();
        return coutTo - coutFrom;
    }

    /**
     * Affiche le chemin trouvé dans la console
     * @param chemin chemin reliant les noeuds
     */
    private static void afficherChemin(List<Noeud<Case>> chemin) {
        for (Noeud<Case> noeud : chemin) {
            Case caseActuelle = noeud.getValeur();
            System.out.println("Case: x = " + caseActuelle.getX() + ", y = " + caseActuelle.getY());
            System.out.println(noeud.getValeur());
        }
    }

    /**
     * Récupère le meilleur chemin entre deux noeuds
     * @param algorithme algorithme de recherche
     * @param carte carte fournie
     * @param xDepart abscisse de la case de départ
     * @param yDepart ordonnée de la case de départ
     * @param xArrivee abscisse de la case d'arrivée
     * @param yArrivee ordonnée de la case d'arrivée
     * @return le chemin trouvé par l'algorithme
     */
    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte,
                                       int xDepart, int yDepart, int xArrivee, int yArrivee) {
        Graphe<Case> graphe = creerGraphe(carte);
        Case depart = new Case(carte.getTuile(xDepart, yDepart), xDepart, yDepart);
        Case arrivee = new Case(carte.getTuile(xArrivee, yArrivee), xArrivee, yArrivee);

        Noeud<Case> noeudDepart = getNoeud(graphe, xDepart, yDepart);
        Noeud<Case> noeudArrivee = getNoeud(graphe, xArrivee, yArrivee);

        // Recherche du meilleur chemin
        List<Noeud<Case>> noeuds = algorithme.trouverChemin(graphe, noeudDepart, noeudArrivee);
//        List<Noeud<Case>> noeuds = algorithme.trouverChemin(graphe, new Noeud<>(depart), new Noeud<>(arrivee));

        // Conversion des noeuds en case pour créer le chemin
        List<Case> cases = new ArrayList<>();
        for(Noeud<Case> noeud : noeuds) {
            cases.add(noeud.getValeur());
        }

        return new Chemin(cases);
    }

    private static Noeud<Case> getNoeud(Graphe graphe,int x, int y) {
        for (Object noeud : graphe.getNoeuds()) {
            Case caseCourante = (Case) ((Noeud)noeud).getValeur();
            if (caseCourante.getX() == x && caseCourante.getY() == y) {
                return (Noeud<Case>)noeud;
            }
        }
        return null;
    }
}
