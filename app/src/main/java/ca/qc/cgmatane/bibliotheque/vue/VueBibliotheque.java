package ca.qc.cgmatane.bibliotheque.vue;

import java.util.List;

import ca.qc.cgmatane.bibliotheque.modele.Livre;

public interface VueBibliotheque {
    void setListeLivre(List<Livre> listeLivre);
    void afficherTousLesLivres();
    void naviguerAjouterLivre();
    void naviguerModifierLivre(String idLivre);
}
