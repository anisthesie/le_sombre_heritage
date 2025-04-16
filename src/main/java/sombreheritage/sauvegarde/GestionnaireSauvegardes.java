package sombreheritage.sauvegarde;

import sombreheritage.Jeu;
import sombreheritage.graphics.Gui;
import sombreheritage.monde.Fragment;
import sombreheritage.monde.Zone;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionnaireSauvegardes {

    public static String sauvegarder(Jeu jeu) {
        String nomSauvegarde = "sombre_heritage_" + System.currentTimeMillis() + ".sauvegarde";
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(nomSauvegarde);

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
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        return nomSauvegarde;
    }

    public static Jeu depuisSauvegarde(String chemin, Gui gui) {
        File file = new File(chemin);
        if (!file.exists()) {
            if (gui != null)
                gui.afficher("Le fichier de sauvegarde n'existe pas.");
            return null;
        }
        Jeu jeu = new Jeu(gui);
        Zone zoneCourante = null;

        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            if (gui != null)
                gui.afficher("Le fichier de sauvegarde n'existe pas.");
            return null;
        }

        while (scanner.hasNextLine()) {
            String ligne = scanner.nextLine();
            if (ligne.startsWith("ZoneCourante=")) {
                String nomZone = ligne.substring("ZoneCourante=".length());
                zoneCourante = jeu.getZone(nomZone);
            } else if (ligne.startsWith("Fragments=")) {
                String[] fragmentsStr = ligne.substring("Fragments=".length()).split(",");
                for (String fragmentStr : fragmentsStr) {
                    Fragment fragment = Fragment.valueOf(fragmentStr.trim());
                    jeu.ajouterFragment(fragment);
                }
            }
        }
        jeu.setZoneDepart(zoneCourante);
        jeu.commencer();
        return jeu;
    }

}
