package fr.cnam.android.findcar.outils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import fr.cnam.android.findcar.model.User;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    //Propriétes
    private String creation="create table user ("
            +"id INTEGER PRIMARY KEY,"
            +"nom TEXT NOT NULL,"
            + "prenom TEXT NOT NULL,"
            + "email TEXT NOT NULL,"
            + "mobil INTEGER NOT NULL,"
            + "login TEXT NOT NULL,"
            + "password INTEGER NOT NULL);";

    //Propriétes
    private String creationCovoiturage="create table covoiturage ("
            +"idCovoiturage INTEGER PRIMARY KEY,"
            +"user INTEGER NOT NULL,"
            + "model TEXT NOT NULL,"
            + "couleur TEXT NOT NULL,"
            + "immatriculation TEXT NOT NULL,"
            + "lieuDepart TEXT NOT NULL,"
            + "lieuArrivee TEXT NOT NULL,"
            + "heurRDV INTEGER NOT NULL,"
            + "numPlaces INTEGER NOT NULL,"
            + "FOREIGN KEY(user) REFERENCES user(id));";

    /**
     * Constructeur
     * @param context
     * @param name
     * @param factory
     * @param version
     */
    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    /**
     *si un changement de BD
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
        db.execSQL(creationCovoiturage);
    }

    /**
     *si changement de version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
