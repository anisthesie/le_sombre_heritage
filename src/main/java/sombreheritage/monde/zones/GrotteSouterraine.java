package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class GrotteSouterraine extends Zone {

    private boolean explored = false;

    public GrotteSouterraine(Jeu jeu) {
        super(jeu, "grotte_souterraine", "Grotte Souterraine",
                Fragment.NAISSANCE);
    }

    @Override
    public void entrer() {
        etapeConversation = 0;
        if (!dejaVisite()) {
            getJeu().afficher("Vous reprenez conscience dans un boyau rocheux, suintant d’humidité.");
            getJeu().afficher("De faibles gouttes tombent du plafond, et une lueur vacillante provient d’un minuscule interstice dans la paroi.");
            getJeu().afficher("Vos premières sensations : froid, confusion, solitude. ");
            getJeu().afficher("Votre mémoire est comme un livre aux pages vierges.");
            getJeu().afficher("");
        }
        getJeu().afficher("Vous êtes maintenant dans: " + getNom() + ".");
        getJeu().afficher("Les choix qui s’offrent à vous sont : ");
        getJeu().afficher("     1) Explorer la grotte");
        getJeu().afficher("     2) Vous enfuir précipitamment pour rejoindre la prochaine zone.");
        getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
        getJeu().afficher("");

        this.dejaVisite = true;
    }

    @Override
    public void traiterCommande(String commande) {
        switch (etapeConversation) {
            case 0:
                choixExploration(commande);
                break;
            case 1:
                choixVoix(commande);
                break;
            case 2:
                choixMurmures(commande);
                break;
        }
    }

    private void choixExploration(String commande) {
        switch (commande) {
            case "1":
                if (explored)
                    getJeu().afficher("Vous avez déjà exploré la grotte.");
                else {
                    getJeu().afficher("Vous avez choisi d'explorer la grotte.");
                    getJeu().afficher("");
                    getJeu().afficher("En explorant la grotte, entendez un écho fantomatique.");
                    getJeu().afficher("La voix murmure : 'Enfin réveillé... Te souviens-tu de ton nom ?'");
                    getJeu().afficher("Deux choix s'offrent à vous :");
                    getJeu().afficher("     1) Avouer que vous avez oublié votre nom.");
                    getJeu().afficher("     2) Ignorer la voix.");
                    getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
                    getJeu().afficher("");

                    etapeConversation = 1;
                    explored = true;
                }
                break;
            case "2":
                getJeu().afficher("Vous avez choisi de fuir la grotte.");
                getJeu().afficher("Vous continuez votre chemin, cherchant une issue pour quitter la grotte.");
                getJeu().afficher("Des murmures résonnent autour de vous, mais vous ne les comprenez pas.");
                getJeu().afficher("Deux choix s'offrent à vous :");
                getJeu().afficher("     1) Suivre les murmures.");
                getJeu().afficher("     2) Ignorer les murmures et continuer l'exploration.");
                getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
                getJeu().afficher("");
                getJeu().afficher("");
                etapeConversation = 2;
                break;
            default:
                getJeu().afficher("Commande invalide! Veuillez entrer 1 ou 2 : ");
                break;
        }
    }

    private void choixVoix(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous avez choisi d'avouer que vous avez oublié votre nom.");
                getJeu().afficher("La voix murmure : 'Suit-moi, j'ai quelque chose à te montrer.'");
                getJeu().afficher("En fouillant un renfoncement derrière un rocher, vous découvrez un pendentif brisé, orné d’un symbole incomplet : une sorte de glyphe évoquant un arbre stylisé.");
                getJeu().afficher("Au contact du pendentif, un bref éclair vous submerge : l’image d’un nourrisson confié à une silhouette encapuchonnée, dans cette grotte même.");
                getJeu().afficher("Vous ne savez pas qui est ce nourrisson, ni cette silhouette.");
                getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");
                getJeu().afficher("");

                removeFragment();
                getJeu().ajouterFragment(getFragment());
                break;
            case "2":
                getJeu().afficher("Vous avez choisi d'ignorer la voix.");
                getJeu().afficher("");
                break;
            default:
                getJeu().afficher("Commande invalide! Veuillez entrer 1 ou 2 : ");
                break;
        }

        getJeu().afficher("Vous continuez votre chemin, cherchant une issue pour quitter la grotte.");
        getJeu().afficher("Des murmures résonnent autour de vous, mais vous ne les comprenez pas.");
        getJeu().afficher("Deux choix s'offrent à vous :");
        getJeu().afficher("     1) Suivre les murmures.");
        getJeu().afficher("     2) Ignorer les murmures et continuer l'exploration.");
        getJeu().afficher("Taper le numero de la commande souhaitée. (1 ou 2) : ");
        getJeu().afficher("");
        etapeConversation = 2;
    }

    private void choixMurmures(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous avez choisi de suivre les murmures.");
                getJeu().afficher("Les murmures deviennent plus forts, vous guidant vers une sortie.");
                getJeu().afficher("");
                getJeu().entrerZone(ZonePlantesCarnivores.class);
                break;
            case "2":
                getJeu().afficher("Vous avez choisi d'ignorer les murmures et de continuer l'exploration.");
                getJeu().afficher("Les murmures s'estompent lentement, laissant place au silence.");
                getJeu().afficher("Vous retrouvez la sortie principale.");
                getJeu().afficher("");
                getJeu().entrerZone(ClairiereEnchantee.class);
                break;
            default:
                getJeu().afficher("Commande invalide! Veuillez entrer 1 ou 2 : ");
                break;
        }
    }
}
