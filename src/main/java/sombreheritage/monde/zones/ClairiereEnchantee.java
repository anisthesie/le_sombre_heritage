package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class ClairiereEnchantee extends Zone {

    public ClairiereEnchantee(Jeu jeu) {
        super(jeu, "clairiere_enchantee", "Claire Enchantée",
                Fragment.MEMOIRE_RADIEUSE);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
