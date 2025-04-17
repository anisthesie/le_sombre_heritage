package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class ForetMaudite extends Zone {

    public ForetMaudite(Jeu jeu) {
        super(jeu, "foret_maudite", "Forêt Maudite",
                Fragment.CORRUPTION);
    }

    @Override
    public void entrer() {
        etapeConversation = 0;

        if (!dejaVisite()) {
            getJeu().afficher("Le cœur sombre du royaume. Les arbres sont noueux, difformes, exsudant une sève noire.");
            getJeu().afficher("Un chemin boueux s’enfonce dans la pénombre.");
            getJeu().afficher("Des murmures constants suggèrent que la forêt est \"vivante\", attentive à chacun de vos pas.");
            getJeu().afficher("");
        }
        getJeu().afficher("Vous êtes submergé par une brume illusoire.");
        getJeu().afficher("Les hallucinations vous prennent la tête : cris lointains, des murmures, des rires et des pleurs.");
        getJeu().afficher("Vous êtes pris d'un vertige, vous ne différenciez plus le réel de l'irréel.");

        getJeu().afficher("Les seuls choix qui se present à vous sont :");
        getJeu().afficher("     1) Ignorer les hallucinations et avancer aveuglement.");
        getJeu().afficher("     2. Attendre que la brume se dissipe.");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");

        this.dejaVisite = true;
    }

    @Override
    public void traiterCommande(String commande) {

        switch (etapeConversation) {
            case 0:
                choixBrume(commande);
                break;
            case 1:
                choixJournal(commande);
                break;
            case 2:
                choixSortie(commande);
                break;
        }
    }

    private void choixBrume(String commande) {
        switch (commande) {
            case "1":
                getJeu().viderTexte();
                getJeu().afficher("Vous avancez dans la brume aveuglement, vous ne savez pas où vous allez.");
                getJeu().afficher("Les cris se font de plus en plus forts, vous ne pouvez plus les ignorer.");
                getJeu().afficher("Vous êtes pris de panique, et vous courez de plus en plus vite.");
                getJeu().afficher("Après de longues minutes de course, vous vous retrouvez face à un croisement.");
                getJeu().afficher("Vous ne savez pas où mènent ces chemins, mais c'est votre seule option.");
                getJeu().afficher("");
                getJeu().afficher("Vous devez choisir entre deux chemins :");
                getJeu().afficher("     1) Un chemin rocheux, sombre et étroit.");
                getJeu().afficher("     2) Un vieux passage rocailleux pris de végétation.");
                getJeu().afficher("Taper 1 ou 2 pour choisir.");
                getJeu().afficher("");
                etapeConversation = 2;
                break;
            case "2":
                getJeu().viderTexte();
                getJeu().afficher("Vous attendez que la brume se dissipe.");
                getJeu().afficher("Les hallucinations s'estompent peu à peu et vous continuez à avancer.");
                getJeu().afficher("Un chêne noir particulier attire votre attention, vous décidez de vous en approcher.");
                getJeu().afficher("");
                getJeu().afficher("Vous êtes soudainement confronté à un arbre vivant.");
                getJeu().afficher("Il vous regarde avec des yeux vides, puis vous attrape avec ses branches et commence à vous étrangler.");
                getJeu().afficher("Après un moment de silence, l'arbre se met à parler : ");
                getJeu().afficher("     « Je suis l'arbre de l'honnêteté, je suis ici pour te juger. »");
                getJeu().afficher("     « Qu'as-tu fait du Journal ésotérique ? »");
                getJeu().afficher("");
                getJeu().afficher("Vos réponses possibles sont :");
                getJeu().afficher("     1) Je l'ai brûlé.");
                getJeu().afficher("     2) Je l'ai gardé.");
                getJeu().afficher("Taper 1 ou 2 pour choisir.");
                getJeu().afficher("");
                etapeConversation = 1;
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez entrer 1 ou 2.");
                break;
        }
    }

    private void choixJournal(String commande) {
        switch (commande) {
            case "1":
                getJeu().viderTexte();
                getJeu().afficher("     « Quel honnête homme ! » s'écrie l'arbre en relâchant son étreinte.");
                getJeu().afficher("     « Ce journal était longtemps la source de tous nos problèmes, vous êtes le bienvenu ici. » ");
                getJeu().afficher("     « Vous trouverez derrière moi un chêne noir, je pense qu'il vous intéressera. »");
                getJeu().afficher("L'arbre vous relâche et vous inspectez derrière lui.");
                getJeu().afficher("");
                break;
            case "2":
                getJeu().viderTexte();
                getJeu().afficher("     « DIABLE ! » s'écrie l'arbre en vous balançant au sol.");
                getJeu().afficher("     « Ce journal est la source de la corruption, il est maudit. »");
                getJeu().afficher("     « Quand tu bruleras ce journal, alors tu seras le bienvenu ici. » s'enfuit l'arbre d'une voix effrayée.");
                getJeu().afficher("L'arbre s'enfuit dans la forêt, vous inspectez derrière lui le chêne qui attire votre attention.");
                getJeu().afficher("");
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez entrer 1 ou 2.");
                return;
        }
        getJeu().afficher("Dans le tronc creux du vieux chêne noir, un cristal sombre palpite.");
        getJeu().afficher("Le saisir provoque une vision : vous (ou votre ancêtre) avez scellé l’âme du Gardien ici, pensant sauver la forêt.");
        getJeu().afficher("Mais cet esprit enchaîné s’est nourri de rancune, gangrenant la nature.");
        getJeu().afficher("Vous avez acquis le fragment : " + getFragment().getNom() + ".");
        getJeu().afficher("");

        getJeu().ajouterFragment(getFragment());

        getJeu().afficher("Vous vous mettez désormais à la recherche d'un moyen de quitter cette forêt.");
        getJeu().afficher("Après de longues minutes de course, vous vous retrouvez face à un croisement.");
        getJeu().afficher("Vous devez choisir entre deux chemins :");
        getJeu().afficher("     1) Un sentier rocheux sombre et étroit qui mène vers les Montagnes Escarpées.");
        getJeu().afficher("     2) Un vieux passage rocailleux pris de végétation menant vers les Plaines Desolées.");
        getJeu().afficher("Taper 1 ou 2 pour choisir.");
        getJeu().afficher("");
        etapeConversation = 2;

    }

    private void choixSortie(String commande) {
        switch (commande) {
            case "1":
                getJeu().afficher("Vous empruntez le sentier rocheux sombre et étroit.");
                getJeu().entrerZone(MontagnesEscarpees.class);
                break;
            case "2":
                getJeu().afficher("Vous empruntez le vieux passage rocailleux pris de végétation.");
                getJeu().entrerZone(PlainesDesolees.class);
                break;
            default:
                getJeu().afficher("Choix invalide. Veuillez entrer 1 ou 2.");
                break;
        }
    }
}
