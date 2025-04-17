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
        getJeu().afficher("");
        getJeu().afficher("Soudain, la statue ouvre lentement un œil de pierre. Un frisson vous parcourt.");
        getJeu().afficher("Un souffle invisible vous enveloppe, et votre esprit est projeté ailleurs...");
        getJeu().afficher("");
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
                getJeu().viderTexte();
                getJeu().afficher("Vous êtes entrain de sacrifier tous vos fragments pour libérer la forêt.");
                if (!getJeu().possedeFragment(Fragment.SCIENCE_SORCELLERIE)) {
                    if (getJeu().getFragments().size() >= 7) {
                        finLiberation();
                        break;
                    } else {
                        getJeu().afficher("Malgré vos efforts, la forêt ne peut être complètement guérie, car vous n'avez pas trouvé tous les fragments de mémoire.");
                        finUnification();
                        break;
                    }
                }

                getJeu().afficher("Vous avez précédemment choisi de garder le Journal ésotérique, ce qui empêche la guérison complète de la forêt.");
                finBloque();
                break;
            case "2":
                getJeu().viderTexte();
                getJeu().afficher("Vous décidez de garder les fragments de mémoire et essayez de devenir un Gardien éternel.");
                if (getJeu().getFragments().size() >= 8) {
                    finAscension();
                    break;
                }
                getJeu().afficher("Pour devenir un Gardien éternel, vous devez rassembler tous les fragments de mémoire.");
                getJeu().afficher("Vous n'avez pas trouvé tous les fragments lors de votre voyage, vous ne pouvez pas devenir un Gardien éternel.");
                getJeu().afficher("Votre soif de pouvoir vous a aveuglé, et vous avez perdu la chance de sauver la forêt.");
                finBloque();
                break;
        }
        getJeu().afficher("");
        etapeConversation = 1;
    }

    private void finBloque() {
        getJeu().afficher("");
        getJeu().afficher("La statue se fige, son œil de pierre se refermant lentement.");
        getJeu().afficher("La forêt demeure dans l'ombre, ses couleurs ternies par la malédiction.");
        getJeu().afficher("");
        getJeu().afficher("Vous n'avez pas réussi à libérer la forêt, et vous êtes désormais piégé dans ce lieu désolé.");
        getJeu().afficher("La statue, témoin silencieux de votre échec, vous observe avec une tristesse infinie.");
        getJeu().afficher("");
        getJeu().afficher("Il ne vous reste plus qu'à errer dans ces plaines désolées, hanté par vos choix.");
        getJeu().afficher("Vous venez de débloquer le fragment : " + getFragment().getNom());
        finJeu();
    }

    private void finLiberation() {
        getJeu().afficher("");
        getJeu().afficher("La forêt reprend vie, ses couleurs éclatantes illuminant le paysage.");
        getJeu().afficher("Les souvenirs de votre voyage s'estompent, mais la paix intérieure vous envahit.");
        getJeu().afficher("");
        getJeu().afficher("Vous avez choisi de libérer la forêt, et en retour, elle vous a libéré de votre fardeau.");
        getJeu().afficher("Vous êtes désormais un Gardien de la forêt, un protecteur éternel.");
        getJeu().afficher("");
        getJeu().afficher("Vous venez de débloquer le fragment : " + getFragment().getNom());
        finJeu();
    }

    private void finUnification() {
        getJeu().afficher("");
        getJeu().afficher("La forêt s'épanouit, mais une partie de vous reste à jamais liée à elle.");
        getJeu().afficher("Vous êtes désormais un Gardien de la forêt, mais votre quête n'est pas encore terminée.");
        getJeu().afficher("");
        getJeu().afficher("Vous avez choisi de préserver vos souvenirs et vous fusionnez avec l’âme sylvestre.");
        getJeu().afficher("La forêt n’est ni totalement guérie ni perdue : elle demeure un lieu mystique, sous votre gouverne éternelle.");
        getJeu().afficher("L’aventurier que vous étiez disparaît, un Gardien naît, aux yeux brillants, veillant sur la forêt à jamais.");
        getJeu().afficher("");
        getJeu().afficher("Vous venez de débloquer le fragment : " + getFragment().getNom());
        finJeu();
    }

    private void finAscension() {
        getJeu().afficher("");
        getJeu().afficher("Vous détruisez la statue, aspirant l’essence maléfique, devenant un être imprégné de puissance.");
        getJeu().afficher("La forêt ne guérit pas, mais se plie à votre volonté.");
        getJeu().afficher("");
        getJeu().afficher("Vous régnez désormais sur la région, la malédiction devenant votre outil, imposant une terreur ou un ordre absolu.");
        getJeu().afficher("Vous êtes devenu un Gardien éternel, mais à quel prix ?");
        getJeu().afficher("");
        getJeu().afficher("Vous venez de débloquer le fragment : " + getFragment().getNom());
        finJeu();
    }

    private void finJeu() {
        etapeConversation = 3;
        getJeu().afficher("");
        getJeu().afficher("Vous avez terminé le jeu.");
        getJeu().afficher("Merci d'avoir joué !");
    }
}
