package sombreheritage;

import sombreheritage.graphics.Gui;
import sombreheritage.monde.Zone;
import sombreheritage.monde.zones.*;

public class Jeu {

    private Gui gui;

    private Zone zoneCourante;
    private Zone[] zones;

    public Jeu() {
        this(new Gui());
    }

    public Jeu(Gui gui) {

        initialiserZones();
        this.zoneCourante = getZoneParType(GrotteSouterraine.class);

        this.gui = gui;
        gui.setJeu(this);
        gui.afficher();
        gui.afficheImage(zoneCourante.getCheminImage());
    }

    public void traiterCommande(String entree) {
        String commande = entree.toUpperCase().replace(" ", "");
        gui.afficher("vous avez écrit : " + commande);
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
        zones[8] = new PlainesDesolees(this);

    }

    public void entrerZone(Zone zone) {
        if (zone == null) return;

        setZoneCourante(zone);
        gui.afficheImage(zone.getCheminImage());
        gui.afficher("Vous entrez dans " + zone.getNom() + ".");

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

    public <T extends Zone> T getZoneParType(Class<T> type) {
        for (Zone zone : zones) {
            if (type.isInstance(zone))
                return type.cast(zone);
        }
        return null;
    }

}
