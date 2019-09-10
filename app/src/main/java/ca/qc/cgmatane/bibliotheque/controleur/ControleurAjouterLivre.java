package ca.qc.cgmatane.bibliotheque.controleur;

import android.content.Context;

import ca.qc.cgmatane.bibliotheque.donnee.BaseDeDonnees;
import ca.qc.cgmatane.bibliotheque.donnee.LivreDAO;
import ca.qc.cgmatane.bibliotheque.modele.Livre;
import ca.qc.cgmatane.bibliotheque.vue.VueAjouterLivre;

public class ControleurAjouterLivre implements Controleur {

    private VueAjouterLivre vue;
    private LivreDAO accesseurLivre;


    public ControleurAjouterLivre(VueAjouterLivre vue) {
        this.vue = vue;
    }

    public void actionEnregistrerLivre(Livre livre){
        if(livre.isValide()) {
            accesseurLivre = LivreDAO.getInstance();
            accesseurLivre.ajouterLivre(livre);
            vue.naviguerBibliotheque();
        }else{
            vue.afficherErreur();
        }
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
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
