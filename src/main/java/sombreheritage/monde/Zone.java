package sombreheritage.monde;

import sombreheritage.Jeu;

public abstract class Zone {

    private Jeu jeu;

    private Fragment fragment;

    private String image;
    private String nom;

    protected boolean dejaVisite;

    public Zone(Jeu jeu, String image, String nom, Fragment fragment) {
        this.jeu = jeu;
        this.image = image;
        this.nom = nom;
        this.fragment = fragment;
        this.dejaVisite = false;
    }

    public abstract void traiterCommande(String commande);

    public void entrer() {
        getJeu().afficher("");
        if (!dejaVisite())
            getJeu().afficher("   Vous venez de découvrir un nouvel endroit.");
        getJeu().afficher("Vous êtes maintenant dans: " + nom + ".");
        getJeu().afficher("");

        dejaVisite = true;
    }

    public boolean dejaVisite() {
        return dejaVisite;
    }

    public void removeFragment() {
        this.fragment = null;
    }

    public boolean hasFragment() {
        return fragment != null;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public String getNom() {
        return nom;
    }

    public String getCheminImage() {
        return "images/zones/" + image + ".jpg";
    }

    public String getImage() {
        return image;
    }

    public Jeu getJeu() {
        return jeu;
    }
}
