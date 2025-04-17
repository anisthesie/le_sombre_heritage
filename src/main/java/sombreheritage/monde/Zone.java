package sombreheritage.monde;

import sombreheritage.Jeu;

/**
 * Classe abstraite représentant une zone dans le jeu.
 */
public abstract class Zone {

    /**
     * Référence à l'objet Jeu, permettant d'accéder aux fonctionnalités du jeu.
     */
    private Jeu jeu;

    /**
     * Le fragment associé à cette zone.
     */
    private Fragment fragment;

    /**
     * Nom du fichier de l'image représentant la zone.
     */
    private String image;
    /**
     * Nom de la zone.
     */
    private String nom;

    /**
     * Indicateur de si la zone a déjà été visitée.
     */
    protected boolean dejaVisite;

    /**
     * Indicateur de l'étape de la conversation en cours.
     * Utilisé pour gérer les dialogues et les choix du joueur.
     * Chaque question représente une étape de la conversation.
     */
    protected int etapeConversation = 0;

    /**
     * Constructeur de la classe Zone.
     *
     * @param jeu      Référence à l'objet Jeu.
     * @param image    Nom du fichier de l'image représentant la zone.
     * @param nom      Nom de la zone.
     * @param fragment Le fragment associé à cette zone.
     */
    public Zone(Jeu jeu, String image, String nom, Fragment fragment) {
        this.jeu = jeu;
        this.image = image;
        this.nom = nom;
        this.fragment = fragment;
        this.dejaVisite = false;
    }

    /**
     * Méthode abstraite à implémenter par les sous-classes pour gérer les commandes spécifiques à chaque zone.
     * Les entrées de l'utilisateur seront envoyées à la zone dans laquelle il se trouve.
     *
     * @param commande La commande saisie par le joueur.
     */
    public abstract void traiterCommande(String commande);

    /**
     * Méthode appelée lorsque le joueur entre dans la zone.
     * Affiche un message d'accueil et indique si la zone a déjà été visitée.
     * Elle est la plupart du temps surchargée par les sous-classes pour afficher des messages spécifiques.
     */
    public void entrer() {
        getJeu().afficher("");
        if (!dejaVisite())
            getJeu().afficher("   Vous venez de découvrir un nouvel endroit.");
        getJeu().afficher("Vous êtes maintenant dans: " + nom + ".");
        getJeu().afficher("");

        dejaVisite = true;
    }

    /**
     * Méthode pour savoir si la zone a déjà été visitée.
     *
     * @return true si la zone a déjà été visitée, false sinon.
     */
    public boolean dejaVisite() {
        return dejaVisite;
    }

    /**
     * Méthode pour obtenir le fragment associé à cette zone.
     *
     * @return Le fragment associé à cette zone.
     */
    public Fragment getFragment() {
        return fragment;
    }

    /**
     * Méthode pour modifier le fragment associé à cette zone.
     *
     * @param fragment Le nouveau fragment à associer à cette zone.
     */
    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Méthode pour obtenir le nom de la zone.
     *
     * @return Le nom de la zone.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode pour obtenir le chemin de l'image représentant la zone.
     *
     * @return Le chemin de l'image représentant la zone.
     */
    public String getCheminImage() {
        return "images/zones/" + image + ".jpg";
    }

    /**
     * Méthode pour obtenir l'objet Jeu associé à cette zone.
     *
     * @return L'objet Jeu associé à cette zone.
     */
    public Jeu getJeu() {
        return jeu;
    }
}
