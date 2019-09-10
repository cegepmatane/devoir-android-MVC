package ca.qc.cgmatane.bibliotheque.modele;

import java.util.HashMap;

public class Livre {

    public static final String CLE_LIVRE = "livre";
    public static final String CLE_TITRE = "titre";
    public static final String CLE_AUTEUR = "auteur";
    public static final String CLE_ID_LIVRE = "id_livre";

    protected String titre;
    protected String auteur;
    protected int id_livre;

    public Livre(String titre, String auteur, int id_livre) {
        this.titre = titre;
        this.auteur = auteur;
        this.id_livre = id_livre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }

    public HashMap<String, String> obtenirLivrePourAdapteur()
    {
        HashMap<String, String> livrePourAdapteur = new HashMap<String,String>();
        livrePourAdapteur.put(CLE_TITRE, this.titre);
        livrePourAdapteur.put(CLE_AUTEUR, this.auteur);
        livrePourAdapteur.put(CLE_ID_LIVRE, "" + this.id_livre);
        return livrePourAdapteur;
    }

    public boolean isValide(){
        if(this.titre == null || this.titre.isEmpty() ||
            this.auteur == null || this.auteur.isEmpty()) return false;
        return true;

    }

}











