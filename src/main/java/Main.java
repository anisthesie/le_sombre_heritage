import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class Main {

    public static void main(String[] args) throws Exception {
        Jeu jeu = new Jeu();
        for(Zone zone : jeu.getZones()) {
            Fragment fragment = zone.getFragment();
            System.out.println(fragment.getNom());
            System.out.println(fragment.getDescription());
            jeu.afficher(fragment.getDescription());
            jeu.afficher(fragment.getNom());
            jeu.afficher("");
        }
    }

}
