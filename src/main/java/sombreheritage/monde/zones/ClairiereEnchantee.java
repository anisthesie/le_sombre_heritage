package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class ClairiereEnchantee extends Zone {

    private boolean apprisRituel = false;

    public ClairiereEnchantee(Jeu jeu) {
        super(jeu, "clairiere_enchantee", "Claire Enchantée",
                Fragment.MEMOIRE_RADIEUSE);
    }

    @Override
    public void entrer() {

        etapeConversation = 0;
        if (!dejaVisite()) {
            getJeu().afficher("Après la pénombre, vous débouchez sur une clairière d’une beauté surnaturelle.");
            getJeu().afficher("L’herbe scintille sous la lumière d’un soleil doux, une fontaine d’eau cristalline trône au centre, et une brise parfumée caresse les feuillages.");
            getJeu().afficher("Vous ressentez un élan d’espoir ou de paix, contraste brutal avec la grotte lugubre.");
            getJeu().afficher("");
        }
        getJeu().afficher("Vous êtes maintenant dans: " + getNom() + ".");
        getJeu().afficher("Vous apercevez un Esprit de la Clairière, silhouette évanescente et lumineuse.");
        getJeu().afficher("Il vous dit : « Je sens sur toi l’odeur de la caverne… Cherches-tu la rédemption ou l’oubli ? »");
        getJeu().afficher("");
        getJeu().afficher("Les choix qui s’offrent à vous sont : ");
        getJeu().afficher("     1) Répondre gentiment et montrer le pendentif brisé de la grotte.");
        getJeu().afficher("     2) Repousser l'esprit.");
        getJeu().afficher("Taper le numéro de la commande souhaitée. (1 ou 2) : ");
        getJeu().afficher("");

        this.dejaVisite = true;
    }

    @Override
    public void traiterCommande(String commande) {

        switch (etapeConversation) {
            case 0:
                choixEsprit(commande);
                break;
            case 1:
                choixFontaine(commande);
                break;
            case 2:
                choixChemin(commande);
                break;
        }

    }

    private void choixEsprit(String commande) {
        switch (commande) {
            case "1":
                if(!getJeu().possedeFragment(Fragment.NAISSANCE)) {
                    getJeu().afficher("Vous n'avez pas le pendentif brisé de la grotte.");
                    getJeu().afficher("");
                    return;
                }
                getJeu().afficher("Vous montrez le pendentif brisé à l'esprit.");
                getJeu().afficher("L’Esprit semble heureux, murmure une prière : vous apprenez qu’un rituel est nécessaire.");
                getJeu().afficher("");

                apprisRituel = true;
                break;
            case "2":
                getJeu().afficher("Vous repoussez l'esprit.");
                getJeu().afficher("L'esprit s'éloigne, déçu, disparaît dans la brume.");
                getJeu().afficher("");
                break;
            default:
                getJeu().afficher("Commande invalide. Veuillez entrer 1 ou 2.");
                getJeu().afficher("");
                return;
        }
        getJeu().afficher("En explorant la clairière, vous trouvez une fontaine d'eau fraiche.");
        getJeu().afficher("Deux choix s'offrent à vous :");
        getJeu().afficher("     1) Boire l'eau de la fontaine.");
        getJeu().afficher("     2) Continuer votre chemin.");
        getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
        getJeu().afficher("");
        etapeConversation = 1;
    }

    private void choixFontaine(String commande) {
        switch (commande) {
            case "1":
                if (apprisRituel) {
                    getJeu().afficher("Avant de boire, vous récitez les paroles du rituel.");
                    getJeu().afficher("Vous buvez l'eau de la fontaine.");
                    getJeu().afficher("L’eau est douce et apaisante, vous ressentez une énergie nouvelle.");
                    getJeu().afficher("Près de la fontaine, une petite pierre en forme de feuille repose, baignant dans un halo de lumière.");
                    getJeu().afficher("La toucher évoque le souvenir d’une cérémonie paisible, où un être bienveillant vous bénissait dans cette clairière, vous conférant une protection éphémère à l’époque.");
                    getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");
                    getJeu().afficher("");

                    getJeu().ajouterFragment(getFragment());
                } else {
                    getJeu().afficher("Vous buvez l'eau de la fontaine.");
                    getJeu().afficher("L’eau est douce et apaisante, mais rien ne se passe sans le récit d'un certain rituel.");
                    break;
                }
                break;
            case "2":
                getJeu().afficher("Vous décidez de continuer votre chemin.");
                break;
            default:
                getJeu().afficher("Commande invalide. Veuillez entrer 1 ou 2.");
                return;
        }

        getJeu().afficher("Vous vous éloignez enfin de la fontaine et continuez votre chemin.");
        getJeu().afficher("Vous arrivez à un croisement dans la forêt.");
        getJeu().afficher("Deux chemins s'offrent à vous :");
        getJeu().afficher("     1) Un sentier menant à la Forêt Maudite.");
        getJeu().afficher("     2) Un chemin longeant une arche de lierre, qui mène au Cimetière Abandonné.");
        getJeu().afficher("     3) Retourner à la grotte.");
        getJeu().afficher("Taper le numero de la commande souhaitée. (1, 2 ou 3) : ");
        getJeu().afficher("");
        etapeConversation = 2;
    }

    private void choixChemin(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous décidez de prendre le sentier menant à la Forêt Maudite.");
                getJeu().afficher("");
                getJeu().entrerZone(ForetMaudite.class);
                break;
            case "2":
                getJeu().afficher("Vous choisissez de suivre le chemin longeant l'arche de lierre.");
                getJeu().afficher("");
                getJeu().entrerZone(CimetiereAbandonne.class);
                break;
            case "3":
                getJeu().afficher("Vous retournez à la grotte.");
                getJeu().afficher("");
                getJeu().entrerZone(GrotteSouterraine.class);
                break;
            default:
                getJeu().afficher("Commande invalide. Veuillez entrer 1, 2 ou 3.");
        }
    }
}
