import sombreheritage.graphics.Gui;

public class Main {

    public static void main(String[] args) {

        Gui gui = new Gui();
        gui.afficher("Bienvenue dans le jeu Sombre Héritage !");
        gui.afficher("Vous pouvez taper 'save' à n'importe quel moment pour sauvegarder votre partie.");
        gui.afficher("");
        gui.afficher("Pour commencer, vous devez choisir une option.");
        gui.afficher("      1) Lancer une nouvelle partie.");
        gui.afficher("      2) Charger depuis une sauvegarde.");
        gui.afficher("Taper 1 ou 2 pour choisir une option.");
        gui.afficher("");

    }

}
