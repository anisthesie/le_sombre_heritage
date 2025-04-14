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
    public void entrer() {

        etapeConversation = 0;

        if (!dejaVisite()) {
            getJeu().afficher("Une nappe d’eau obscure, à la surface huileuse. Des nappes de brume planent.");
            getJeu().afficher("Un vieux ponton craque sous le moindre pas.");
            getJeu().afficher("Le silence est parfois brisé par un son éthéré, comme un chant lointain.");
        }
        getJeu().afficher("Vous êtes directement affronté par une créature aquatique.");
        getJeu().afficher("C'est un être amphibien qui rôde sous la surface et dévore tout ce qui s'approche.");
        getJeu().afficher("Vous devez l'affronter.");
        getJeu().afficher("Vos choix sont : ");
        getJeu().afficher("     1) Lui donner une part de sang.");
        getJeu().afficher("     2) Sacrifier un objet précieux.");
        getJeu().afficher("     3) L'attaquer frontalement.");
        getJeu().afficher("Taper 1, 2 ou 3 pour choisir.");
        getJeu().afficher("");

        this.dejaVisite = true;
    }
    @Override
    public void traiterCommande(String commande) {
        switch (etapeConversation) {
            case 0:
                choixCreature(commande);
                break;
            case 1:
                choixSacrifice(commande);
                break;
        }
    }

    private void trouverFragment() {

    }

    private void choixCreature(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous lui donnez une part de votre sang.");
                getJeu().afficher("Il semble satisfait et vous laisse fouiller le lac.");

                trouverFragment();
                etapeConversation = 2;
                break;
            case "2":
                getJeu().afficher("Vous décidez de sacrifier un objet précieux.");
                getJeu().afficher("Vous devez choisir un objet parmi ceux que vous possédez : ");
                if(!getJeu().getFragments().isEmpty()) {
                    for(int i = 1; i <= getJeu().getFragments().size(); i++)
                        getJeu().afficher("     " + i + ") " + getJeu().getFragments().get(i - 1).getNom());
                    getJeu().afficher("Taper le numéro de l'objet à sacrifier.");
                    getJeu().afficher("");
                    etapeConversation = 1;
                    return;
                }
                getJeu().afficher("Vous n'avez pas d'objet précieux à sacrifier.");
                getJeu().afficher("La créature vous attaque et vous blesse gravement.");
                break;
            case "3":
                getJeu().afficher("Vous l'attaquez frontalement.");
                getJeu().afficher("La créature vous attaque en retour et vous blesse gravement.");
                break;
            default:
                getJeu().afficher("Choix invalide. Essayez à nouveau.");
        }
    }

    private void choixSacrifice(String commande) {
        int choix = 0;
        try {
            choix = Integer.parseInt(commande);
        } catch (NumberFormatException ignored) {}
        if (choix < 1 || choix > getJeu().getFragments().size()) {
            getJeu().afficher("Choix invalide. Essayez à nouveau.");
            return;
        }
        Fragment fragment = getJeu().getFragments().get(choix - 1);
        getJeu().afficher("Vous sacrifiez " + fragment.getNom() + ".");
        getJeu().afficher("La créature semble satisfaite et vous laisse fouiller le lac.");
        getJeu().retirerFragment(fragment);

        trouverFragment();
        etapeConversation = 2;
    }
}
