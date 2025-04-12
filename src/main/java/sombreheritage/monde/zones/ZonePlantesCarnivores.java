package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class ZonePlantesCarnivores extends Zone {

    public ZonePlantesCarnivores(Jeu jeu) {
        super(jeu, "zone_plantes_carnivores", "Zone des Plantes Carnivores",
                Fragment.SURVIE_INSTINCT);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
