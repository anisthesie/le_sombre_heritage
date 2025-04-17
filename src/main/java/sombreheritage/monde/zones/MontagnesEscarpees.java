package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class MontagnesEscarpees extends Zone {

    private boolean trouveTalisman = false;

    public MontagnesEscarpees(Jeu jeu) {
        super(jeu, "montagnes_escarpees", "Montagnes Escarpées",
                Fragment.QUETE_INTERIEURE);
    }

    @Override
    public void entrer() {

        etapeConversation = 0;

        if (!dejaVisite()) {
            getJeu().afficher("Sur ces hauteurs balayées par les vents, la neige fouette votre visage.");
            getJeu().afficher("Des pics abrupts cernent un plateau où se dressent des ruines d’un ancien temple lié au Gardien.");
            getJeu().afficher("La difficulté d’accès éprouve vos capacités physiques et mentales.");
        }
        getJeu().afficher("");
        getJeu().afficher("Les montagnes sont un lieu de méditation et de sacrifice, habitées par des ermites.");
        getJeu().afficher("Vous remarquez à l'horizon un temple ancien, à moitié englouti dans la neige.");
        getJeu().afficher("Un homme vêtu de blanc s'approche de vous, le visage caché sous une capuche : ");
        getJeu().afficher("     « Cela fait longtemps que je n'ai pas vu d'âme errante ici. » dit-il.");
        getJeu().afficher("     « Quelles sont vos intentions ici ? Voulez-vous libérer la forêt ou éxploiter son pouvoir ? »");
        getJeu().afficher("");
        getJeu().afficher("Vos choix sont : ");
        getJeu().afficher("     1) Choisir la voie de la sagesse, de la paix et de la libération.");
        getJeu().afficher("     2) Choisir la voie de la puissance, de la domination et de l'exploitation.");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");

        this.dejaVisite = true;
    }

    @Override
    public void traiterCommande(String commande) {
        switch (etapeConversation) {
            case 0:
                choixIntention(commande);
                break;
            case 1:
                choixEsprit(commande);
                break;
            case 2:
                choixSortie(commande);
                break;
        }
    }

    private void choixIntention(String commande) {
        switch (commande) {
            case "1":
                getJeu().viderTexte();
                getJeu().afficher("Vous choisissez la voie de la sagesse, de la paix et de la libération.");
                getJeu().afficher("Le sage vous guide vers un chemin de méditation et de compréhension.");
                getJeu().afficher("Vous sentez une connexion profonde avec la nature qui vous entoure.");
                getJeu().afficher("L'ermite vous offre un Talisman de Bienveillance en signe de votre choix.");
                trouveTalisman = true;
                break;
            case "2":
                getJeu().viderTexte();
                getJeu().afficher("Vous choisissez la voie de la puissance, de la domination et de l'exploitation.");
                getJeu().afficher("Le sage vous met en garde contre les dangers de cette voie.");
                getJeu().afficher("Consterné, il vous avertit que la nature se défend toujours, puis se perd dans la tempête.");
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez taper 1 ou 2.");
                return;
        }
        etapeConversation = 1;
        getJeu().afficher("");
        getJeu().afficher("Vous continuez votre chemin en direction du temple malgré la tempête.");
        getJeu().afficher("Le chemin est arride et la neige s'accumule autour de vous.");
        getJeu().afficher("En vous approchant du temple, vous ressentez une énergie étrange émanant de l'intérieur.");
        getJeu().afficher("Face à l'entrée du temple, un énorme esprit de la nature nait des ruines et des plantes environnantes.");
        getJeu().afficher("Il vous observe avec curiosité, comme s'il pesait votre âme.");
        getJeu().afficher("     « VOUS ! » rugit-il. « Je vous ai attendu depuis la nuit des temps. »");
        getJeu().afficher("     « Il est temps de vous faire payer pour vos actes. »");
        getJeu().afficher("Les esprits de la nature se rassemblent autour de vous, prêts à obéir au maître esprit.");
        getJeu().afficher("");
        getJeu().afficher("Vous devez maintenant choisir votre voie :");
        getJeu().afficher("     1) Combattre l'esprit de la nature et prouver votre force.");
        getJeu().afficher("     2) Essayer de prouver votre sagesse et votre bienveillance.");
        getJeu().afficher("     2) Rebrousser chemin et fuir le temple.");
        getJeu().afficher("Taper 1, 2 ou 3 pour choisir.");
        getJeu().afficher("");
    }

    private void choixEsprit(String commande) {
        switch (commande) {
            case "1":
                getJeu().viderTexte();
                getJeu().afficher("Vous choisissez de combattre l'esprit de la nature.");
                getJeu().afficher("Votre confiance a été disproportionnée, et vous devez maintenant faire face à ses conséquences.");
                getJeu().afficher("Vous sentez la colère de la nature s'intensifier autour de vous.");
                getJeu().afficher("");
                getJeu().afficher("L'esprit de la nature rugit de colère et vous attaque avec une force incroyable.");
                getJeu().afficher("Vous êtes soudainement entouré par des esprits de la nature qui vous envahissent.");
                getJeu().afficher("Le maître esprit vous prend par la gorge et vous jète du haut de la montagne.");
                getJeu().afficher("");
                getJeu().afficher("Vous tombez dans le vide, blessé, affaibli, mais pas mort.");
                getJeu().afficher("Ce combat est un échec, mais vous avez appris une leçon précieuse.");
                break;
            case "2":
                getJeu().viderTexte();
                if (trouveTalisman) {
                    getJeu().afficher("Vous choisissez de prouver votre sagesse et votre bienveillance.");
                    getJeu().afficher("Vous brandissez le Talisman de Bienveillance et l'esprit de la nature s'arrête.");
                    getJeu().afficher("Vous lui expliquez tout ce que vous avez appris pendant votre voyage.");
                    getJeu().afficher("");
                    getJeu().afficher("L'esprit de la nature vous écoute attentivement et semble apaisé.");
                    getJeu().afficher("Il vous montre le chemin vers l'intérieur du temple, escorté par des esprits de la nature.");
                    getJeu().afficher("Vous entrez dans le temple, où vous découvrez un sanctuaire ancien.");
                    getJeu().afficher("");
                    getJeu().afficher("Dans le temple en ruine, un anneau portant le même glyphe que votre pendentif brisé.");
                    getJeu().afficher("L’effleurer fait resurgir la scène où vous (ou votre ancêtre) avez gravé ce sceau pour sceller l’engagement du Gardien ou sceller votre propre âme.");
                    getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");

                    getJeu().ajouterFragment(getFragment());
                } else {
                    getJeu().afficher("Vous essayez de prouver votre sagesse et votre bienveillance.");
                    getJeu().afficher("Vous peinez à convaincre l'esprit de la nature sans preuve concrète.");
                    getJeu().afficher("Un Talisman de Bienveillance aurait pu vous aider, mais vous avez choisi une autre voie.");
                    getJeu().afficher("");
                    getJeu().afficher("En voyant que vous n'avez pas d'argument solide, l'esprit de la nature se fâche et vous refuse l'entrée.");
                    getJeu().afficher("Vous rebroussez chemin, déçu et frustré.");
                }
                break;
            case "3":
                getJeu().viderTexte();
                getJeu().afficher("Vous choisissez de rebrousser chemin et fuir le temple.");
                getJeu().afficher("L'esprit de la nature rugit de colère, mais vous parvenez à fuir.");
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez taper 1, 2 ou 3.");
                return;
        }
        getJeu().afficher("");
        getJeu().afficher("Vous êtes maintenant de retour au pied de la montagne.");
        getJeu().afficher("Vous ressentez un mélange de soulagement, de tristesse et de confusion.");
        getJeu().afficher("Votre curiosité est piquée, vous ne chercher qu'à poursuivre votre voyage et découvrir la vérité.");
        getJeu().afficher("Vous vous demandez si vous avez pris la bonne décision.");
        getJeu().afficher("Face à un croisement de chemins, vous devez choisir votre prochaine destination : ");
        getJeu().afficher("     1) Descendre vers la Forêt Maudite.");
        getJeu().afficher("     2) Continuer vers les Plaines Désolées.");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");
        etapeConversation = 2;
    }


    private void choixSortie(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous choisissez de descendre vers la Forêt Maudite.");
                getJeu().entrerZone(ForetMaudite.class);
                break;
            case "2":
                getJeu().afficher("Vous choisissez de continuer vers les Plaines Désolées.");
                getJeu().entrerZone(PlainesDesolees.class);
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez taper 1 ou 2.");
        }
    }

}
