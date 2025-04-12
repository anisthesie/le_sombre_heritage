package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class CimetiereAbandonne extends Zone {
    public CimetiereAbandonne(Jeu jeu) {
        super(jeu, "cimetiere_abandonne", "Cimetière Abandonné",
                Fragment.LIGNEE_HERITAGE);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
