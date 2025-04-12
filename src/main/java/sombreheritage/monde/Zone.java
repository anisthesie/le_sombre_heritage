package sombreheritage.monde;

import sombreheritage.Jeu;

public abstract class Zone {

    private Jeu jeu;

    private Fragment fragment;

    private String image;
    private String nom;

    public Zone(Jeu jeu, String image, String nom, Fragment fragment) {
        this.jeu = jeu;
        this.image = image;
        this.nom = nom;
        this.fragment = fragment;
    }

    public abstract void traiterCommande(String commande);

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
