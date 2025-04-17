package sombreheritage.graphics;

import sombreheritage.Jeu;
import sombreheritage.sauvegarde.GestionnaireSauvegardes;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

public class Gui implements ActionListener {

    private Jeu jeu;

    private boolean partieEnCours = false;

    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;

    public Gui() {
        creerGUI();
        afficherLogo();
    }

    public void afficher(String s) {
        texte.append(s + "\n");
        texte.setCaretPosition(texte.getDocument().getLength());
    }

    public void afficher() {
        afficher("\n");
    }

    public void viderTexte() {
        texte.setText("");
        texte.setCaretPosition(texte.getDocument().getLength());
    }

    public void afficheImage(String cheminImage) {
        URL imageURL = this.getClass().getClassLoader().getResource(cheminImage);
        if (imageURL != null) {
            image.setIcon(new ImageIcon(imageURL));
            fenetre.pack();
        }
    }

    public void afficherLogo() {
        afficheImage("images/sombre_heritage.jpg");
    }

    private void creerGUI() {
        fenetre = new JFrame("Jeu");

        entree = new JTextField(34);
        entree.setToolTipText("Entrez vos commandes ici");

        texte = new JTextArea();
        texte.setEditable(false);
        JScrollPane listScroller = new JScrollPane(texte);
        listScroller.setPreferredSize(new Dimension(200, 200));
        listScroller.setMinimumSize(new Dimension(100, 100));

        JPanel panel = new JPanel();
        image = new JLabel();

        panel.setLayout(new BorderLayout());
        panel.add(image, BorderLayout.NORTH);
        panel.add(listScroller, BorderLayout.CENTER);
        panel.add(entree, BorderLayout.SOUTH);
        fenetre.getContentPane().add(panel, BorderLayout.CENTER);

        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        entree.addActionListener(this);

        fenetre.pack();
        fenetre.setVisible(true);
        entree.requestFocus();
    }

    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        executerCommande();
    }

    private void executerCommande() {
        String commandeLue = entree.getText();
        entree.setText("");
        if (!partieEnCours) {
            traiterLancement(commandeLue);
            return;
        }
        if (jeu != null)
            jeu.traiterCommande(commandeLue);
    }

    private void traiterLancement(String commande) {
        switch (commande) {
            case "1":
                this.jeu = new Jeu(this);
                this.jeu.commencer();
                partieEnCours = true;
                break;
            case "2":
                this.jeu = GestionnaireSauvegardes.depuisSauvegarde(this);
                if (this.jeu != null) {
                    this.jeu.commencer();
                    partieEnCours = true;
                } else {
                    afficher("Erreur lors du chargement de la sauvegarde.");
                }
                break;
        }
    }

    public File getFile() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("."));
        fileChooser.setDialogTitle("Sélectionner une sauvegarde");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Fichiers de sauvegarde", "sauvegarde"));

        int returnValue = fileChooser.showOpenDialog(null);
        if (returnValue != JFileChooser.APPROVE_OPTION)
            return null;
        return fileChooser.getSelectedFile();

    }
}