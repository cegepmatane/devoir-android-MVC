package ca.qc.cgmatane.bibliotheque.controleur;

import android.content.Context;

import ca.qc.cgmatane.bibliotheque.donnee.BaseDeDonnees;
import ca.qc.cgmatane.bibliotheque.donnee.LivreDAO;
import ca.qc.cgmatane.bibliotheque.modele.Livre;
import ca.qc.cgmatane.bibliotheque.vue.VueModifierLivre;

public class ControleurModifierLivre implements Controleur {

    private VueModifierLivre vue;
    private Livre livre;
    private LivreDAO accesseurLivre;

    public ControleurModifierLivre(VueModifierLivre vue) {
        this.vue = vue;
    }

    public void actionEnregistrerLivre(Livre livre){
        if(livre.isValide()) {
            accesseurLivre = LivreDAO.getInstance();
            accesseurLivre.modifierLivre(livre);
            vue.naviguerBibliotheque();
        }else{
            vue.afficherErreur();
        }
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        int idLivre = Integer.parseInt(vue.getIdLivreParametre());
        this.accesseurLivre = LivreDAO.getInstance();
        livre = accesseurLivre.chercherLivreParIdLivre(idLivre);
        vue.setLivre(livre);
        vue.afficherLivre();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onActivityResult(int activite) {
    }
}
