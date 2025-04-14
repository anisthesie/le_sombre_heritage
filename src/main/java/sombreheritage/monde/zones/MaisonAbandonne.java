package sombreheritage.monde.zones;

import sombreheritage.Jeu;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

public class MaisonAbandonne extends Zone {
    public MaisonAbandonne(Jeu jeu) {
        super(jeu, "maison_abandonnee", "Maison Abandonnée",
                Fragment.SCIENCE_SORCELLERIE);
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

    }
}
