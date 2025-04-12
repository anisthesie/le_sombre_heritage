package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class GrotteSouterraine extends Zone {


    public GrotteSouterraine(Jeu jeu) {
        super(jeu, "grotte_souterraine", "Grotte Souterraine",
                Fragment.NAISSANCE);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
