package ca.qc.cgmatane.bibliotheque.donnee;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.bibliotheque.modele.Livre;

public class LivreDAO {

    private static LivreDAO instance = null;
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
        listeLivre = new ArrayList<Livre>();
    }


    public List<Livre> recupererListeLivre() {
        String LISTER_LIVRE = "SELECT * FROM " + Livre.CLE_LIVRE;
        Cursor curseur = accesseurBaseDeDonnees.getReadableDatabase().rawQuery(LISTER_LIVRE,
                null);
        this.listeLivre.clear();
        Livre livre;

        int indexId_livre = curseur.getColumnIndex(Livre.CLE_ID_LIVRE);
        int indexAuteur = curseur.getColumnIndex(Livre.CLE_AUTEUR);
        int indexTitre = curseur.getColumnIndex(Livre.CLE_TITRE);

        for(curseur.moveToFirst();!curseur.isAfterLast();curseur.moveToNext()) {
            int id_livre = curseur.getInt(indexId_livre);
            String auteur = curseur.getString(indexAuteur);
            String titre = curseur.getString(indexTitre);
            livre = new Livre(titre, auteur, id_livre);
            this.listeLivre.add(livre);
        }

        return listeLivre;
    }


    public void ajouterLivre(Livre livre)
    {
        SQLiteDatabase accesseurBaseDeDonneesEcriture = accesseurBaseDeDonnees.getWritableDatabase();

        accesseurBaseDeDonneesEcriture.beginTransaction();
        try {

            ContentValues livreEnCleValeur = new ContentValues();
            livreEnCleValeur.put(Livre.CLE_AUTEUR, livre.getAuteur());
            livreEnCleValeur.put(Livre.CLE_TITRE, livre.getTitre());

            accesseurBaseDeDonneesEcriture.insertOrThrow(Livre.CLE_LIVRE, null, livreEnCleValeur);
            accesseurBaseDeDonneesEcriture.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d("LivreDAO", "Erreur en tentant d'ajouter un livre dans la base de données");
        } finally {
            accesseurBaseDeDonneesEcriture.endTransaction();
        }
    }

    public Livre chercherLivreParIdLivre(int id_livre){
        recupererListeLivre();
        for(Livre livreRecherche : this.listeLivre)
        {
            if(livreRecherche.getId_livre() == id_livre) return livreRecherche;
        }
        return null;
    }

    //Ne fonctionne pas avec la BD encore...
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

        Livre livre;

        listeLivre.add(new Livre("Android pour les nuls", "Département d'informatique", 1));
        listeLivre.add(new Livre("The Hobbit", "Tolkien", 2));
        listeLivre.add(new Livre("Harry Potter", "J.K.Rowling", 3));

    }
}
