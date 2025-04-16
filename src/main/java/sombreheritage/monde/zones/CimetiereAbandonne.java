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
    public void entrer() {

        etapeConversation = 0;

        if (!dejaVisite()) {
            getJeu().afficher("Un espace lugubre : tombes délabrées, statues d’anges brisés, brume stagnante. Vous entendez parfois des gémissements ténus.");
            getJeu().afficher("L’air est glacial, un silence morbide pèse.");
            getJeu().afficher("");
        }
        getJeu().afficher("Un fantôme surgit du mausolée. Il interroge vos motivations :");
        getJeu().afficher("Les choix que vous ferez ici détermineront votre destin.");
        getJeu().afficher("Vous pouvez soit : ");
        getJeu().afficher("     1) Essayer de convaincre le fantôme de vos bonnes intentions.");
        getJeu().afficher("     2) L’affronter.");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");

        this.dejaVisite = true;
    }


    @Override
    public void traiterCommande(String commande) {
        switch (etapeConversation) {
            case 0:
                choixFantome(commande);
                break;
            case 1:
                choixSortie(commande);
                break;
        }

    }

    private void choixFantome(String commande) {
        switch (commande) {
                case "1":
                    getJeu().afficher("Vous tentez de convaincre le fantôme de vos bonnes intentions.");
                    getJeu().afficher("Le fantôme semble hésiter, mais il finit par vous faire confiance et vous laisse passer.");
                    getJeu().afficher("Au fond du cimetière, votre regard est attiré par une lumière étrange.");
                    getJeu().afficher("Une dague cérémonielle plantée devant une tombe portant votre nom ou celui de votre ancêtre.");
                    getJeu().afficher("En la ramassant, un souvenir étrange se déclanche où l’on vous surnomme \"Dernier gardien\".");
                    getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");
                    getJeu().afficher("");

                    getJeu().ajouterFragment(getFragment());
                    break;
            case "2":
                    getJeu().afficher("Vous décidez d'affronter le fantôme.");
                    getJeu().afficher("Le combat est intense, mais le fantôme est d'une puissance incroyable.");
                    getJeu().afficher("Vous réalisez que vous ne pouvez pas le vaincre.");
                    getJeu().afficher("Finalement, vous êtes contraint de fuir le cimetière.");
                    getJeu().afficher("");
                    break;
            default:
                getJeu().afficher("Choix invalide. Veuillez entrer 1 ou 2.");
                return;
        }
        getJeu().afficher("Vous quittez le cimetière, le cœur lourd de mystères non résolus.");
        getJeu().afficher("Il n'y a que deux sorties :");
        getJeu().afficher("     1) Un chemin forestier qui mène vers une maison abandonnée.");
        getJeu().afficher("     2) Un sentier qui descend vers une forêt maudite.");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");
        etapeConversation = 1;

    }

    private void choixSortie(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous empruntez le chemin forestier.");
                getJeu().entrerZone(MaisonAbandonne.class);
                break;
            case "2":
                getJeu().afficher("Vous descendez vers la forêt maudite.");
                getJeu().entrerZone(ForetMaudite.class);
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez entrer 1 ou 2.");
                break;
        }
    }
}
