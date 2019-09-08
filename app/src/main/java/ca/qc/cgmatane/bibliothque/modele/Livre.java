package ca.qc.cgmatane.bibliothque.modele;

import java.util.HashMap;

public class Livre {

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
        livrePourAdapteur.put("titre", this.titre);
        livrePourAdapteur.put("auteur", this.auteur);
        livrePourAdapteur.put("id_livre", "" + this.id_livre);
        return livrePourAdapteur;
    }

}











