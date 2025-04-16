package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class ZonePlantesCarnivores extends Zone {


    private boolean outilsTrouves = false;
    private boolean animalTrouve = false;

    public ZonePlantesCarnivores(Jeu jeu) {
        super(jeu, "zone_plantes_carnivores", "Zone des Plantes Carnivores",
                Fragment.SURVIE_INSTINCT);
    }

    @Override
    public void entrer() {
        etapeConversation = 0;
        if (!dejaVisite()) {
            getJeu().afficher("Vous venez de pénétrer une zone marécageuse, où de gigantesques plantes se meuvent comme des prédateurs à l’affût.");
            getJeu().afficher("Le sol spongieux dégage une odeur de putréfaction.");
            getJeu().afficher("L’atmosphère est oppressante, chaque bruit de succion ou de tiges froissées donne l’alerte que la vie végétale n’est plus inoffensive.");
            getJeu().afficher("");
        }
        getJeu().afficher("Vous êtes maintenant dans: " + getNom() + ".");
        getJeu().afficher("Vous remarquez qu'une fleur-œil géante veille sur le marécage.");
        getJeu().afficher("Si vous passez à découvert, elle déploie des lianes strangulates, vous étouffant.");
        getJeu().afficher("Vous devez d'abord trouver de quoi vous défendre.");
        getJeu().afficher("Vous remarquez un campement abandonné, avec des outils de jardinage.");
        getJeu().afficher("Les choix qui s’offrent à vous sont : ");
        getJeu().afficher("     1) Explorer le campement abandonné.");
        getJeu().afficher("     2) Trouver un animal et l'utiliser comme appât.");
        getJeu().afficher("     3) Regagner la Claireière.");
        getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
        getJeu().afficher("");

        this.dejaVisite = true;
    }

    @Override
    public void traiterCommande(String commande) {

        switch (etapeConversation) {
            case 0:
                choixMethode(commande);
                break;
            case 1:
                choixSortie(commande);
                break;
        }

    }

    private void choixMethode(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous avez choisi d'explorer le campement abandonné.");
                getJeu().afficher("En fouillant le campement, vous trouvez des outils de jardinage.");
                getJeu().afficher("Vous pouvez les utiliser pour vous défendre contre la fleur-œil géante.");
                getJeu().afficher("");
                outilsTrouves = true;
                break;
            case "2":
                getJeu().afficher("Vous avez choisi de trouver un animal.");
                getJeu().afficher("En cherchant dans le marécage, vous trouvez une grenouille que vous utiliserez comme appât.");
                getJeu().afficher("");
                animalTrouve = true;
                break;
            case "3":
                getJeu().afficher("Vous avez choisi de regagner la Claireière.");
                getJeu().afficher("Vous quittez la zone des plantes carnivores et retournez à la Claireière.");
                getJeu().afficher("");
                getJeu().entrerZone(ClairiereEnchantee.class);
                return;
            default:
                getJeu().afficher("Commande non reconnue. Veuillez entrer 1, 2 ou 3.");
                return;
        }

        getJeu().afficher("Vous êtes maintenant prêt à affronter la fleur-œil géante.");
        if (outilsTrouves) {
            getJeu().afficher("Vous utilisez les outils de jardinage pour vous défendre.");
            getJeu().afficher("Vous arrivez à couper les lianes strangulates de la fleur.");
        }
        if (animalTrouve) {
            getJeu().afficher("Vous utilisez la grenouille comme appât pour distraire la fleur-œil géante.");
            getJeu().afficher("La fleur se concentre sur la grenouille, vous permettant de passer sans encombre.");
        }
        getJeu().afficher("");
        getJeu().afficher("Vous avez réussi à battre la fleur-œil géante et à explorer la zone des plantes carnivores.");
        getJeu().afficher("Dans le bulbe d’une énorme plante endormie, une racine runique est enfoncée.");
        getJeu().afficher("La prendre réveille un flash : vous ou un ancêtre, luttant contre des créatures végétales pour protéger la forêt d’envahisseurs autrefois.");
        getJeu().afficher("Ironiquement, c’est aujourd’hui cette végétation qui menace tout être vivant.");
        getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");
        getJeu().afficher("");

        getJeu().ajouterFragment(getFragment());

        getJeu().afficher("Au bout de la zone, se trouvent deux chemins : ");
        getJeu().afficher("     1) Un chemin qui mène au Lac Sombre, via des passerelles.");
        getJeu().afficher("     2) Un chemin qui mène à la Claireière.");
        getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
        getJeu().afficher("");
        etapeConversation = 1;
    }

    private void choixSortie(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous avez choisi de suivre le chemin qui mène au Lac Sombre.");
                getJeu().afficher("Vous traversez les passerelles et arrivez au bord du lac.");
                getJeu().afficher("");
                getJeu().entrerZone(LacSombre.class);
                break;
            case "2":
                getJeu().afficher("Vous avez choisi de suivre le chemin qui mène à la Claireière.");
                getJeu().afficher("Vous quittez la zone des plantes carnivores et retournez à la Claireière.");
                getJeu().afficher("");
                getJeu().entrerZone(ClairiereEnchantee.class);
                break;
            default:
                getJeu().afficher("Commande non reconnue. Veuillez entrer 1 ou 2.");
                break;
        }
    }
}
