package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class MontagnesEscarpees extends Zone {
    public MontagnesEscarpees(Jeu jeu) {
        super(jeu, "montagnes_escarpees", "Montagnes Escarpées",
                Fragment.QUETE_INTERIEURE);
    }

    @Override
    public void traiterCommande(String commande) {

    }
}
