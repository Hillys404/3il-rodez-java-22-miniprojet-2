## Questions :
# Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?
Une map avec pour clé le noeud courant et pour valeur une map de ses noeuds voisins (et leur coût)

# Pourquoi pensez-vous que les classes Noeud et Graphe ont été définies avec des paramètres génériques ?
Liberté de réutiliser les classes pour d'autres graphes qui n'ont pas les mêmes types de paramètres

# Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?
L'interface permet de regrouper et utiliser plusieurs algorithmes de recherche 