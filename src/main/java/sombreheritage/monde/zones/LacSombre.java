package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class LacSombre extends Zone {
    public LacSombre(Jeu jeu) {
        super(jeu, "lac_sombre", "Lac Sombre",
                Fragment.SACRIFICE);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
