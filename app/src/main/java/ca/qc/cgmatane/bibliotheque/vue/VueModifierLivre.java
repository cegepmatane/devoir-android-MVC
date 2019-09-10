package ca.qc.cgmatane.bibliotheque.vue;

import ca.qc.cgmatane.bibliotheque.modele.Livre;

public interface VueModifierLivre {
    void afficherLivre();
    void setLivre(Livre livre);
    String getIdLivreParametre();
    void naviguerBibliotheque();
    void afficherErreur();
}
