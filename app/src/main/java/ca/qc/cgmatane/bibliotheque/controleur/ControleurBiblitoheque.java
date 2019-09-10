package ca.qc.cgmatane.bibliotheque.controleur;

import android.content.Context;
import android.content.Intent;

import ca.qc.cgmatane.bibliotheque.donnee.BaseDeDonnees;
import ca.qc.cgmatane.bibliotheque.donnee.LivreDAO;
import ca.qc.cgmatane.bibliotheque.vue.VueBibliotheque;

public class ControleurBiblitoheque implements Controleur {

    static final public int ACTIVITE_AJOUTER_LIVRE = 1;
    static final public int ACTIVITE_MODIFIER_LIVRE = 2;

    private VueBibliotheque vue;
    //private Board model;
    private LivreDAO accesseurLivre;

    private Intent intentionNaviguerAjouterLivre;
    private Intent intentionNaviguerModifierLivre;



    public ControleurBiblitoheque(VueBibliotheque vue) {
        this.vue = vue;
        //this.modele = new Board();
    }

    public void actionNaviguerAjouterLivre(){
        vue.naviguerAjouterLivre();
    }

    public void actionNaviguerModifierLivre(String idLivre){
        vue.naviguerModifierLivre(idLivre);
    }

    @Override
    public void onCreate(Context applicationContext) {
        BaseDeDonnees.getInstance(applicationContext);
        accesseurLivre = LivreDAO.getInstance();
        vue.setListeLivre(accesseurLivre.recupererListeLivre());
        vue.afficherTousLesLivres();
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
        switch(activite)
        {
            case ACTIVITE_AJOUTER_LIVRE:
                vue.setListeLivre(accesseurLivre.recupererListeLivre());
                vue.afficherTousLesLivres();
                break;
            case ACTIVITE_MODIFIER_LIVRE:
                vue.setListeLivre(accesseurLivre.recupererListeLivre());
                vue.afficherTousLesLivres();
                break;
        }
    }
}
