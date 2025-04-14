package sombreheritage.graphics;

import sombreheritage.Jeu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Gui implements ActionListener {

    private Jeu jeu;

    private JFrame fenetre;
    private JTextField entree;
    private JTextArea texte;
    private JLabel image;

    public Gui() {
        creerGUI();
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

    public void enable(boolean ok) {
        entree.setEditable(ok);
        if (!ok)
            entree.getCaret().setBlinkRate(0);
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
        if (jeu != null)
            jeu.traiterCommande(commandeLue);
    }
}