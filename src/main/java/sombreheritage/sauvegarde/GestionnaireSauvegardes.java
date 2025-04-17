package sombreheritage.sauvegarde;

import sombreheritage.Jeu;
import sombreheritage.graphics.Gui;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Classe gérant la sauvegarde et le chargement des parties du jeu.
 */
public class GestionnaireSauvegardes {

    /**
     * Sauvegarde l'état du jeu dans un fichier.
     * Le nom du fichier est généré automatiquement en fonction du temps actuel.
     *
     * @param jeu L'objet Jeu à sauvegarder.
     * @return Le nom du fichier de sauvegarde créé.
     */
    public static String sauvegarder(Jeu jeu) {
        String nomSauvegarde = "sombre_heritage_" + System.currentTimeMillis() + ".sauvegarde";

        try (PrintWriter writer = new PrintWriter(nomSauvegarde)) {
            writer.println("#DebutSauvegarde");
            writer.println("ZoneCourante=" + jeu.getZoneCourante().getClass().getSimpleName());
            writer.print("Fragments=");

            for (int i = 0; i < jeu.getFragments().size(); i++) {
                Fragment fragment = jeu.getFragments().get(i);
                writer.print(fragment.toString());
                System.out.println(fragment);
                if (i < jeu.getFragments().size() - 1)
                    writer.print(",");
            }
            writer.println();
            writer.println("#FinSauvegarde");

        } catch (Exception e) {
            jeu.afficher("Erreur lors de la sauvegarde : " + e.getMessage());
            return "";
        }

        return nomSauvegarde;
    }

    /**
     * Charge l'état du jeu à partir d'un fichier de sauvegarde.
     *
     * @param gui L'interface graphique pour afficher les messages.
     * @return L'objet Jeu chargé à partir de la sauvegarde, ou null en cas d'erreur.
     */
    public static Jeu depuisSauvegarde(Gui gui) {
        File file = gui.getFile();

        if (file == null || !file.exists()) {
            gui.afficher("Le fichier de sauvegarde n'existe pas.");
            return null;
        }

        Jeu jeu = new Jeu(gui);
        Zone zoneCourante = null;
        boolean premiereLigne = true;


        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                if (premiereLigne && !ligne.startsWith("#DebutSauvegarde")) {
                    gui.afficher("Le fichier n'est pas une sauvegarde de jeu.");
                    return null;
                }
                premiereLigne = false;
                if (ligne.startsWith("ZoneCourante=")) {
                    String nomZone = ligne.substring("ZoneCourante=".length());
                    zoneCourante = jeu.getZone(nomZone);

                } else if (ligne.startsWith("Fragments=")) {
                    String[] fragmentsStr = ligne.substring("Fragments=".length()).split(",");
                    for (String fragmentStr : fragmentsStr) {
                        if (fragmentStr.trim().isEmpty())
                            continue;
                        try {
                            Fragment fragment = Fragment.valueOf(fragmentStr.trim());
                            jeu.ajouterFragment(fragment);
                        } catch (IllegalArgumentException e) {
                            gui.afficher("Fragment inconnu : " + fragmentStr);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            gui.afficher("Le fichier de sauvegarde n'existe pas.");
            return null;
        }

        if (zoneCourante == null)
            gui.afficher("Zone courante introuvable dans la sauvegarde. Début de la partie dans la zone par défaut.");

        jeu.setZoneDepart(zoneCourante);
        jeu.commencer();
        return jeu;
    }

}
