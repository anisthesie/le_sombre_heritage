package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class ForetMaudite extends Zone {
    public ForetMaudite(Jeu jeu) {
        super(jeu, "foret_maudite", "Forêt Maudite",
                Fragment.CORRUPTION);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
