package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class MaisonAbandonne extends Zone {

    private boolean trouveFormule = false;

    public MaisonAbandonne(Jeu jeu) {
        super(jeu, "maison_abandonnee", "Maison Abandonnée", Fragment.SCIENCE_SORCELLERIE);
    }

    @Override
    public void entrer() {
        etapeConversation = 0;

        if (!dejaVisite()) {
            getJeu().afficher("Une demeure lézardée, aux vitres brisées, laisse filtrer un vent froid.");
            getJeu().afficher("Des livres et parchemins sont éparpillés sur le sol poussiéreux.");
            getJeu().afficher("Un laboratoire au sous-sol recèle des fioles brisées, d’étranges instruments.");
            getJeu().afficher("On devine qu’une recherche malsaine ou interdite s’y est déroulée.");
        }
        getJeu().afficher("La maison est structurée en plusieurs pièces ainsi qu'une cave.");
        getJeu().afficher("Les pièces ont l'air calmes et silencieuses.");
        getJeu().afficher("La cave est particulièrement effrayante, mais peut contenir des trésors.");
        getJeu().afficher("Vos choix sont :");
        getJeu().afficher("     1) Explorer les pièces de la maison");
        getJeu().afficher("     2. Explorer la cave");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
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
                choixJournal(commande);
                break;
            case 2:
                choixSortie(commande);
                break;
        }
    }

    private void choixExploration(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous explorez la maison.");
                if (!trouveFormule) {
                    getJeu().afficher("Vous trouvez un livre ancien sur la sorcellerie.");
                    getJeu().afficher("Vous apprenez une formule secrète !");
                    getJeu().afficher("Elle pourrait vous être utile par la suite.");
                    getJeu().afficher("");
                    trouveFormule = true;
                } else getJeu().afficher("Vous avez déjà trouvé la formule secrète. Il n'y a rien de nouveau ici.");
                getJeu().afficher("");
                getJeu().afficher("Vous entendez un bruit venant de la cave.");
                break;
            case "2":
                getJeu().afficher("Vous descendez dans la cave.");
                getJeu().afficher("Un bruit étrange résonne dans l'obscurité, les meubles sont en levitation.");
                getJeu().afficher("Vous êtes confronté à un esprit agressif qui hante les lieux.");
                getJeu().afficher("");
                if (trouveFormule) {
                    getJeu().afficher("Vous utilisez la formule secrète que vous avez trouvé dans la maison.");
                    getJeu().afficher("L'esprit est apaisé et disparaît dans un souffle de vent laissant derrière lui quelque chose.");
                    getJeu().afficher("Un journal ésotérique partiellement illisible contient des comptes-rendus d’expériences :");
                    getJeu().afficher("L’érudit tentait de manipuler l’âme de la forêt pour accroître son pouvoir.");
                    getJeu().afficher("Y figure le nom de votre ancêtre ou le vôtre, laissant entendre votre complicité ancienne.");
                    getJeu().afficher("");
                    getJeu().afficher("Après avoir ramassé le journal, votre moralité est mise à l'épreuve.");
                    getJeu().afficher("Vous devez choisir entre deux options :");
                    getJeu().afficher("     1) Brûler le journal : Vous détruisez la connaissance, allégez le fardeau maléfique qui pèse sur la forêt, facilitant un chemin vers la \"Libération\".");
                    getJeu().afficher("     2) Conserver le journal : Vous gagnez l’option de rituels puissants pour \"Ascension\" ou \"Unification\".");
                    getJeu().afficher("Tapez 1 ou 2 pour choisir.");
                    getJeu().afficher("");
                    removeFragment();


                    etapeConversation = 1;
                    return;
                }
                getJeu().afficher("Vous n'avez pas de formule pour apaiser l'esprit.");
                getJeu().afficher("L'esprit vous attaque ! Vous essayez de contre-attaquer mais l'esprit est bien plus fort.");
                getJeu().afficher("Vous vous enfuyez et revenez à l'entrée de la maison.");
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez taper 1 ou 2.");
                return;
        }
        getJeu().afficher("Vous êtes de retour à l'entrée de la maison.");
        getJeu().afficher("Vos choix sont :");
        getJeu().afficher("     1) Continuer à explorer les pièces de la maison");
        getJeu().afficher("     2. Explorer la cave");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");
    }

    private void choixJournal(String commande){
        switch (commande){
            case "1":
                getJeu().afficher("Vous brûlez le journal.");
                getJeu().afficher("Le feu consume les pages, libérant une énergie sombre.");
                getJeu().afficher("Vous sentez un soulagement, comme si un poids avait été levé.");
                getJeu().afficher("Vous avez choisi la \"Libération\".");
                getJeu().afficher("");
                break;
            case "2":
                getJeu().afficher("Vous conservez le journal.");
                getJeu().afficher("Il contient des secrets puissants, mais aussi un lourd fardeau.");
                getJeu().afficher("Vous avez choisi l'\"Ascension\" ou l'\"Unification\".");
                getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");
                getJeu().afficher("");

                getJeu().ajouterFragment(getFragment());
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez taper 1 ou 2.");
                return;
        }

        getJeu().afficher("Vous êtes de retour à l'entrée de la maison.");
        getJeu().afficher("Vous pouvez maintenant quitter la zone, vos choix sont : ");
        getJeu().afficher("     1) Sortir vers la forêt maudite.");
        etapeConversation = 2;
    }

    private void choixSortie(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous rejoignez la forêt maudite.");
                getJeu().entrerZone(ForetMaudite.class);
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez taper 1 ou 2.");
                return;
        }
    }
}
