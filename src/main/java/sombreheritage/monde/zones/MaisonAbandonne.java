package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class MaisonAbandonne extends Zone {
    public MaisonAbandonne(Jeu jeu) {
        super(jeu, "maison_abandonnee", "Maison Abandonnée",
                Fragment.SCIENCE_SORCELLERIE);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
