package sombreheritage.monde;

public enum Fragment {

    SURVIE_INSTINCT("Survie et instinct", "Racine runique"),
    CHOIX_ULTIME("Choix ultime", "Une lueur autour de la statue du Gardien"),
    QUETE_INTERIEURE("Quête intérieure", "Anneau marqué du sceau du Gardien"),
    SCIENCE_SORCELLERIE("Science ou sorcellerie", "Chapitres du journal ésotérique"),
    SACRIFICE("Sacrifice ou pacte oublié", "Médaillon en argent"),
    NAISSANCE("Naissance", "Un pendentif brisé"),
    CORRUPTION("Corruption", "Cristal sombre dans le chêne creux"),
    MEMOIRE_RADIEUSE("Mémoire radieuse", "Pierre-feuille scintillante"),
    LIGNEE_HERITAGE("Lignée ou héritage", "Dague cérémonielle");

    private String nom;
    private String description;

    Fragment(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }
}
