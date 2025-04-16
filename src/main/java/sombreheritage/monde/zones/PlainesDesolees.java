package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class PlainesDesolees extends Zone {
    public PlainesDesolees(Jeu jeu) {
        super(jeu, "plaines_desolees", "Plaines Désolées",
                Fragment.CHOIX_ULTIME);
    }

    @Override
    public void entrer() {
        etapeConversation = 0;
        if (!dejaVisite()) {
            getJeu().afficher("Des étendues de terre craquelée, grises et infertiles, s’étendent à l’horizon.");
            getJeu().afficher("Au centre, une colossale statue représentant un Gardien courbé, ses mains tendues vers le ciel.");
            getJeu().afficher("Le vent soulève des volutes de sable, et un sentiment d’inévitabilité vous envahit. Votre quête touche à son terme.");
            getJeu().afficher("Vous êtes désormais bloqué ici, votre déstin ne dépend que des fragments que vous avez rassemblés.");
            getJeu().afficher("");
        }
        getJeu().afficher("Vous êtes maintenant dans: " + getNom() + ".");
        getJeu().afficher("Un cercle de pierres noires, partiellement enseveli sous la poussière, se dessine à vos pieds.");
        getJeu().afficher("Une pulsation sourde en émane, comme si la terre elle-même vous reconnaissait.");
        getJeu().afficher("Instinctivement, vous placez les fragments un à un dans les creux du cercle. Chacun réagit en libérant une faible lueur.");

        getJeu().afficher("Soudain, la statue ouvre lentement un œil de pierre. Un frisson vous parcourt.");
        getJeu().afficher("Un souffle invisible vous enveloppe, et votre esprit est projeté ailleurs...");

        getJeu().afficher("Des souvenirs affluent : les visages que vous avez croisés, les choix que vous avez faits.");
        getJeu().afficher("Puis une voix résonne, puissante et ancienne :");
        getJeu().afficher("     « Mortel. Tu es à la porte de ton dernier acte. Mais avant que je t'ouvre le passage... tu dois te juger toi-même. »");

        getJeu().afficher("");
        getJeu().afficher("Deux visions s’offrent à vous :");
        getJeu().afficher("     1) Libération et unification : sacrifier les fragments pour rendre à la forêt son éclat, au prix de vos souvenirs.");
        getJeu().afficher("     2) Ascension : conserver les fragments, mais abandonner la forêt à son sort, et devenir un Gardien éternel.");
        getJeu().afficher("Taper 1 ou 2 pour choisir l'option ultime, le destin de la forêt et de votre âme : ");
        getJeu().afficher("");
        this.dejaVisite = true;
    }

    @Override
    public void traiterCommande(String commande) {
        switch (etapeConversation) {
            case 0:
                choixUltime(commande);
                break;
            case 1:
                break;
        }
    }

    private void choixUltime(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous êtes entrain de sacrifier tous vos fragments pour libérer la forêt.");
                if (getJeu().getFragments().size() >= 7) {
                    if (!getJeu().possedeFragment(Fragment.SCIENCE_SORCELLERIE)) {
                        finLiberation();
                        break;
                    } else {
                        getJeu().afficher("Vous avez précédemment choisi de garder le Journal ésotérique, ce qui empêche la guérison complète de la forêt.");
                        finBloque();
                        break;
                    }
                }
                getJeu().afficher("Malgré vos efforts, la forêt ne peut être complètement guérie, car vous n'avez pas trouvé tous les fragments de mémoire.");
                finUnification();
                break;
            case "2":
                getJeu().afficher("Vous décidez de garder les fragments de mémoire et essayez de devenir un Gardien éternel.");
                if(getJeu().getFragments().size() >= 8) {
                    finAscension();
                    break;
                }
                getJeu().afficher("Pour devenir un Gardien éternel, vous devez rassembler tous les fragments de mémoire.");
                getJeu().afficher("Vous n'avez pas trouvé tous les fragments de mémoire, vous ne pouvez pas devenir un Gardien éternel.");
                getJeu().afficher("Votre soif de pouvoir vous a aveuglé, et vous avez perdu la chance de sauver la forêt.");
                finBloque();
                break;
        }
        etapeConversation = 1;
    }

    private void finBloque() {

    }

    private void finLiberation() {

    }

    private void finUnification() {

    }

    private void finAscension() {
    }
}
