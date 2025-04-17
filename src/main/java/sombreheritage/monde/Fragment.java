package sombreheritage.monde;

/**
 * Enumération représentant les fragments de l'histoire.
 * Chaque fragment a un nom et une description associés.
 */
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

    /**
     * Constructeur de l'énumération Fragment.
     *
     * @param nom         Le nom du fragment.
     * @param description La description du fragment.
     */
    Fragment(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    /**
     * Retourne le nom du fragment.
     *
     * @return Le nom du fragment.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne la description du fragment.
     *
     * @return La description du fragment.
     */
    public String getDescription() {
        return description;
    }
}
