package sombreheritage;

import sombreheritage.graphics.Gui;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;
import sombreheritage.monde.zones.*;
import sombreheritage.sauvegarde.GestionnaireSauvegardes;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe principale du jeu représentant une partie.
 * Elle gère les zones et les fragments.
 */
public class Jeu {

    /**
     * L'interface graphique du jeu.
     */
    private Gui gui;
    /**
     * La zone de départ du jeu.
     */
    private Zone zoneDepart;

    /**
     * La zone courante du jeu.
     */
    private Zone zoneCourante;
    /**
     * Le tableau contenant toutes les zones du jeu.
     */
    private Zone[] zones;

    /**
     * La liste des fragments trouvés par le joueur.
     */
    private List<Fragment> fragments;


    /**
     * Constructeur de la classe Jeu.
     */
    public Jeu() {
        this(new Gui());
    }

    /**
     * Constructeur de la classe Jeu.
     *
     * @param gui L'interface graphique à utiliser.
     */
    public Jeu(Gui gui) {
        this(GrotteSouterraine.class, gui);
    }

    /**
     * Constructeur de la classe Jeu.
     *
     * @param zoneDepart La classe de la zone de départ.
     */
    public <T extends Zone> Jeu(Class<T> zoneDepart) {
        this(zoneDepart, new Gui());
    }

    /**
     * Constructeur de la classe Jeu.
     *
     * @param zoneDepart La classe de la zone de départ.
     * @param gui        L'interface graphique à utiliser.
     */
    public <T extends Zone> Jeu(Class<T> zoneDepart, Gui gui) {

        initialiserZones();

        this.fragments = new ArrayList<>();

        this.gui = gui;
        gui.setJeu(this);
        gui.afficher();

        this.zoneDepart = getZone(zoneDepart);
    }


    /**
     * Méthode pour commencer le jeu.
     */
    public void commencer() {
        entrerZone(zoneDepart);
    }

    /**
     * Méthode pour traiter la commande entrée par l'utilisateur.
     * La commande "save" est traitée prioritairement pour sauvegarder l'état du jeu.
     *
     * @param entree La commande entrée par l'utilisateur.
     */
    public void traiterCommande(String entree) {
        if (entree.equalsIgnoreCase("save")) {
            String fichier = GestionnaireSauvegardes.sauvegarder(this);
            afficher("");
            afficher("Sauvegarde effectuée dans : " + fichier);
            afficher("");
            return;
        }
        if (zoneCourante != null) zoneCourante.traiterCommande(entree);
    }

    /**
     * Méthode pour initialiser les zones du jeu.
     */
    private void initialiserZones() {
        zones = new Zone[9];
        zones[0] = new MontagnesEscarpees(this);
        zones[1] = new PlainesDesolees(this);
        zones[2] = new GrotteSouterraine(this);
        zones[3] = new LacSombre(this);
        zones[4] = new ForetMaudite(this);
        zones[5] = new CimetiereAbandonne(this);
        zones[6] = new ClairiereEnchantee(this);
        zones[7] = new MaisonAbandonne(this);
        zones[8] = new ZonePlantesCarnivores(this);

    }

    /**
     * Méthode pour obtenir la liste des fragments.
     *
     * @return La liste des fragments.
     */
    public List<Fragment> getFragments() {
        return fragments;
    }


    /**
     * Méthode pour vérifier si un fragment a été trouvé.
     *
     * @param fragment Le fragment à vérifier.
     * @return true si le fragment a été trouvé, false sinon.
     */
    public boolean possedeFragment(Fragment fragment) {
        return fragments.contains(fragment);
    }

    /**
     * Méthode pour ajouter un fragment à la liste des fragments.
     *
     * @param fragment Le fragment à ajouter.
     */
    public void ajouterFragment(Fragment fragment) {
        if (fragment == null) return;
        fragments.add(fragment);
    }

    /**
     * Méthode pour retirer un fragment de la liste des fragments.
     *
     * @param fragment Le fragment à retirer.
     */
    public void retirerFragment(Fragment fragment) {
        if (fragment == null) return;
        fragments.remove(fragment);
    }

    /**
     * Méthode pour entrer dans une zone.
     *
     * @param zone La zone dans laquelle entrer.
     */
    public void entrerZone(Zone zone) {
        if (zone == null || zone == this.zoneCourante) return;

        gui.viderTexte();
        setZoneCourante(zone);
        zone.entrer();
        gui.afficheImage(zone.getCheminImage());
    }

    /**
     * Méthode pour entrer dans une zone.
     *
     * @param zone La classe de la zone dans laquelle entrer.
     */
    public <T extends Zone> void entrerZone(Class<T> zone) {
        entrerZone(getZone(zone));
    }

    /**
     * Méthode pour changer la zone de départ.*
     */
    public void setZoneDepart(Zone zone) {
        if (zone == null) return;
        this.zoneDepart = zone;
    }

    /**
     * Méthode pour obtenir la zone actuelle.
     *
     * @return La zone actuelle.
     */
    public Zone getZoneCourante() {
        return zoneCourante;
    }

    /**
     * Affiche un message dans l'interface graphique.
     */
    public void afficher(String message) {
        gui.afficher(message);
    }

    /**
     * Vider le texte de l'interface graphique.
     */
    public void viderTexte() {
        gui.viderTexte();
    }

    /**
     * Changer la zone courante.
     */
    public void setZoneCourante(Zone zoneCourante) {
        this.zoneCourante = zoneCourante;
    }

    /**
     * Obtenir l'intégralité des zones du jeu.
     *
     * @return Un tableau contenant toutes les zones.
     */
    public Zone[] getZones() {
        return zones;
    }

    /**
     * Obtenir une zone spécifique par son nom.
     *
     * @param nom Le nom de la zone à obtenir.
     * @return La zone correspondante, ou null si elle n'existe pas.
     */
    public <T extends Zone> T getZone(String nom) {
        for (Zone zone : zones) {
            if (zone.getClass().getSimpleName().equalsIgnoreCase(nom))
                return (T) zone;

        }
        return null;
    }

    /**
     * Obtenir une zone spécifique par sa classe.
     *
     * @param type La classe de la zone à obtenir.
     * @return La zone correspondante, ou null si elle n'existe pas.
     */
    public <T extends Zone> T getZone(Class<T> type) {
        for (Zone zone : zones) {
            if (type.isInstance(zone))
                return type.cast(zone);
        }
        return null;
    }

}
