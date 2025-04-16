package sombreheritage;

import sombreheritage.graphics.Gui;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;
import sombreheritage.monde.zones.*;
import sombreheritage.sauvegarde.GestionnaireSauvegardes;

import java.util.ArrayList;
import java.util.List;

public class Jeu {

    private Gui gui;
    private Zone zoneDepart;

    private Zone zoneCourante;
    private Zone[] zones;

    private List<Fragment> fragments;

    public Jeu() {
        this(new Gui());
    }

    public Jeu(Gui gui) {
        this(GrotteSouterraine.class, gui);
    }

    public <T extends Zone> Jeu(Class<T> zoneDepart) {
        this(zoneDepart, new Gui());
    }

    public <T extends Zone> Jeu(Class<T> zoneDepart, Gui gui) {

        initialiserZones();

        this.fragments = new ArrayList<>();

        this.gui = gui;
        gui.setJeu(this);
        gui.afficher();

        this.zoneDepart = getZone(zoneDepart);
    }

    public void commencer() {
        entrerZone(zoneDepart);
    }

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

    public List<Fragment> getFragments() {
        return fragments;
    }

    public boolean possedeFragment(Fragment fragment) {
        return fragments.contains(fragment);
    }

    public void ajouterFragment(Fragment fragment) {
        if (fragment == null) return;
        fragments.add(fragment);
    }

    public void retirerFragment(Fragment fragment) {
        if (fragment == null) return;
        fragments.remove(fragment);
    }

    public void entrerZone(Zone zone) {
        if (zone == null || zone == this.zoneCourante) return;

        gui.viderTexte();
        setZoneCourante(zone);
        zone.entrer();
        gui.afficheImage(zone.getCheminImage());
    }

    public <T extends Zone> void entrerZone(Class<T> zone) {
        entrerZone(getZone(zone));
    }

    public void setZoneDepart(Zone zone) {
        if (zone == null) return;
        this.zoneDepart = zone;
    }

    public Zone getZoneCourante() {
        return zoneCourante;
    }

    public void afficher(String message) {
        gui.afficher(message);
    }

    public void setZoneCourante(Zone zoneCourante) {
        this.zoneCourante = zoneCourante;
    }

    public Zone[] getZones() {
        return zones;
    }

    public <T extends Zone> T getZone(String nom) {
        for (Zone zone : zones) {
            if (zone.getClass().getSimpleName().equalsIgnoreCase(nom))
                return (T) zone;

        }
        return null;
    }

    public <T extends Zone> T getZone(Class<T> type) {
        for (Zone zone : zones) {
            if (type.isInstance(zone))
                return type.cast(zone);
        }
        return null;
    }

}
