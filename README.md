# Séparation de la vue et du contrôleur

Le framework Android basé sur les "Activity" n'est pas MVC au démarrage d'un projet.  Il supporte très bien que tous les aspects du programme soient codés dans les classes qui sont responsables de la présentation.  La division du code par responsabilité distincte incombe au programmeur.  L'exercice Bibliothèque met en évidence un découpage Page/Modèle/DAO.  Page est un amalgame de code de vue et de contrôleur.  Cet exemple final vous présente la manière d’effectuer le travail de découpage des vues et des contrôleurs.

* VueEnParticulier : interface
* Contrôleur : interface
* ContrôleurEnParticulier : une classe qui implémente l'interface contrôleur
* PageEnParticulier : hérite de AppCompatActivity et implémente l'interface VueEnParticulier

La PageEnParticulier est une "Activity", lancée par le framework d'Android.  Cette page implémente une interface du même nom préfixée de "Vue".

Exemple: la page Bibliotheque implémente la vue VueBibliotheque

La page intègre un contrôleur qui se réserve les fonctionnalités suivantes:
* Établir une connexion à la base de données
* Utiliser le ou les DAO pour récupérer les données des modèles
* Utiliser le ou les DAO pour ajouter, modifier, supprimer, rechercher, etc.
* Traiter les actions utilisateur
* Demander à la partie "Activity" de la vue de naviguer vers une autre vue

L'interface Contrôleur décrit les événements généraux d'une vue prévus par le framework Android.
* onCreate
* onPause
* onResume
* onDestroy
* onActivityResult

Un contrôleur spécifique implémente les actions en réponse pour ces événements.  Il ajoute à ces événements génériques, les actions spécifiques que la vue offre à l'utilisateur.

Exemple: le ControleurAjouterLivre implémente actionEnregistrerLivre

Un contrôleur spécifique interagit avec une page grâce à l'interface spécifique de sa vue.

Exemple : le ControleurAjouterLivre appelle la méthode naviguerBibliotheque de l'interface VueAjouterlivre implémenté par la page AjouterLivre