package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;

import java.util.List;

public class AdaptateurAlgorithme {

    private static Carte carteCourante;

    /**
     * Création du graphe à l'aide d'une carte
     * @param carte
     * @return
     */
    private static Graphe<Case> creerGraphe(Carte carte) {
        carteCourante = carte;
        Graphe<Case> graphe = new Graphe<>();

        for (int x = 0; x < carte.getLargeur(); x++) {
            for (int y = 0; y < carte.getHauteur(); y++) {

                Tuile tuile = carte.getTuile(x, y);
                Case currentCase = new Case(tuile, x, y);
                graphe.ajouterNoeud(currentCase);

                ajouterAretesVoisines(graphe, currentCase, x, y, carte.getLargeur(), carte.getHauteur());
            }
        }
        return graphe;
    }

    /**
     * Ajoute toutes les liaisons (arêtes) d'un noeud et de ses voisins
     * @param graphe
     * @param currentCase
     * @param x
     * @param y
     * @param largeur
     * @param hauteur
     */
    private static void ajouterAretesVoisines(Graphe<Case> graphe, Case currentCase, int x, int y, int largeur, int hauteur) {
        for (int i = Math.max(0, x - 1); i <= Math.min(x + 1, largeur - 1); i++) {
            for (int j = Math.max(0, y - 1); j <= Math.min(y + 1, hauteur - 1); j++) {
                if (i != x || j != y) {
                    Tuile tuile = carteCourante.getTuile(i, j);
                    Case caseVoisine = new Case(tuile, i, j);
                    double cout = calculerCout(currentCase, caseVoisine);
                    graphe.ajouterArete(currentCase, caseVoisine, cout);
                }
            }
        }
    }

    /**
     * Calcule le coût pour se déplacer d'une case à une autre
     * @param from
     * @param to
     * @return
     */
    private static double calculerCout(Case from, Case to) {
        int coutFrom = from.getTuile().getPenalite();
        int coutTo = to.getTuile().getPenalite();
        return coutTo - coutFrom;
    }

    /**
     * Affiche le chemin trouvé dans la console
     * @param chemin
     */
    private static void afficherChemin(List<Noeud<Case>> chemin) {
        for (Noeud<Case> noeud : chemin) {
            System.out.println(noeud);
        }
    }

    public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {

        Graphe graphe = new Graphe();


    }
}
