package ca.qc.cgmatane.bibliotheque.donnee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ca.qc.cgmatane.bibliotheque.modele.Livre;

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static synchronized BaseDeDonnees getInstance(Context contexte)
    {
        instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }


    public BaseDeDonnees(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "bibliotheque", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //String CREATE_TABLE = "create table livre(id_livre INTEGER PRIMARY KEY, titre TEXT, auteur TEXT)";
        String CREATE_TABLE = "create table "+ Livre.CLE_LIVRE + "(" + Livre.CLE_ID_LIVRE +
                " INTEGER PRIMARY KEY, " + Livre.CLE_TITRE + " TEXT, " + Livre.CLE_AUTEUR + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

        //String DELETE = "delete from livre where 1 = 1";
        String DELETE = "delete from "+ Livre.CLE_LIVRE + " where 1 = 1";
        db.execSQL(DELETE);

        // ...
        String INSERT_1 = "insert into livre(titre, auteur) VALUES('Le Hobbit', 'Tolkien')";
        String INSERT_2 = "insert into livre(titre, auteur) VALUES('Rénover sa maison', 'Auteurs variés')";
        String INSERT_3 = "insert into livre(titre, auteur) VALUES('Enders Games', 'Orson Scott Card')";

        db.execSQL(INSERT_1);
        db.execSQL(INSERT_2);
        db.execSQL(INSERT_3);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        //String DETRUIRE_TABLE = "drop table livre";
        //db.execSQL(DETRUIRE_TABLE);
        String CREER_TABLE = "create table livre(id_livre INTEGER PRIMARY KEY, titre TEXT, auteur TEXT)";
        db.execSQL(CREER_TABLE);
    }


}
