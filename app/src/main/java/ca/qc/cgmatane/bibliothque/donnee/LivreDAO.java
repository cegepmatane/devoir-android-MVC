package ca.qc.cgmatane.bibliothque.donnee;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.bibliothque.modele.Livre;

public class LivreDAO {

    private static LivreDAO instance = null;
    //protected List<HashMap<String, String>> listeLivrePourAdapteur ;
    protected List<Livre> listeLivre ;

    private BaseDeDonnees accesseurBaseDeDonnees;

    public static LivreDAO getInstance() {

        if(null == instance){
            instance = new LivreDAO();
        }

        return instance;
    }

    public LivreDAO(){
        this.accesseurBaseDeDonnees = BaseDeDonnees.getInstance();
        // listeLivrePourAdapteur = new ArrayList<HashMap<String, String>>();
        listeLivre = new ArrayList<Livre>();
        //preparerListeLivre();
    }

    //public List<HashMap<String, String>> recupererListeLivre(){
    public List<Livre> recupererListeLivre(){
        return listeLivre;
    }


    public List<Livre> listerLivre() {
        String LISTER_LIVRES = "SELECT * FROM livre";
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_LIVRES,
                null);
        this.listeLivre.clear();
        Livre livre;

        int indexId_livre = curseur.getColumnIndex("id_livre");
        int indexAuteur = curseur.getColumnIndex("auteur");
        int indexTitre = curseur.getColumnIndex("titre");

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()) {
            int id_livre = curseur.getInt(indexId_livre);
            String auteur = curseur.getString(indexAuteur);
            String titre = curseur.getString(indexTitre);
            livre = new Livre(titre, auteur, id_livre);
            this.listeLivre.add(livre);
        }

        return listeLivre;
    }

    public List<HashMap<String, String>> recupererListeLivrePourAdapteur() {

        List<HashMap<String, String>> listeLivrePourAdapteur =
                new ArrayList<HashMap<String, String>>();

        listerLivre();

        for(Livre livre:listeLivre){
            listeLivrePourAdapteur.add(livre.obtenirLivrePourAdapteur());
        }

        return listeLivrePourAdapteur;
    }

    public void ajouterLivre(Livre livre)
    {
        listeLivre.add(livre);

    }

    public Livre chercherLivreParIdLivre(int id_livre){
        for(Livre livreRecherche : this.listeLivre)
        {
            if(livreRecherche.getId_livre() == id_livre) return livreRecherche;
        }
        return null;

    }

    public void modifierLivre(Livre livre){
        for(Livre livreRecherche : this.listeLivre)
        {
            if(livreRecherche.getId_livre() == livre.getId_livre()){
                livreRecherche.setTitre(livre.getTitre());
                livreRecherche.setAuteur(livre.getAuteur());
            }
        }
    }

    private void preparerListeLivre() {

        //HashMap<String,String> livre;
        Livre livre;

        listeLivre.add(new Livre("Android pour les nuls", "Département d'informatique", 1));
        listeLivre.add(new Livre("The Hobbit", "Tolkien", 2));
        listeLivre.add(new Livre("Harry Potter", "J.K.Rowling", 3));

        /*
        livre = new HashMap<String,String>();
        livre.put("titre", "Android pour les nuls");
        livre.put("auteur", "Département d'informatique");
        listeLivrePourAdapteur.add(livre);

        livre = new HashMap<String,String>();
        livre.put("titre", "The Hobbit");
        livre.put("auteur", "Tolkien");
        listeLivrePourAdapteur.add(livre);

        livre = new HashMap<String,String>();
        livre.put("titre", "Harry Potter");
        livre.put("auteur", "J.K.Rowling");
        listeLivrePourAdapteur.add(livre);
        */

    }
}
