package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class PlainesDesolees extends Zone {
    public PlainesDesolees(Jeu jeu) {
        super(jeu, "plaines_desolees", "Plaines Désolées",
                Fragment.CHOIX_ULTIME);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
