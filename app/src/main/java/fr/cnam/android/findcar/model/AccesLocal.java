package fr.cnam.android.findcar.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fr.cnam.android.findcar.outils.MySQLiteOpenHelper;

public class AccesLocal {
    //Proprétés
    private String nomBase ="dbFindCar.sqlite";
    private Integer versionBase =1;
    private MySQLiteOpenHelper accesBD;
    private SQLiteDatabase db;


    /**
     * Constructeur
     * @param context
     */
    public AccesLocal(Context context){
        accesBD = new MySQLiteOpenHelper(context, nomBase, null, versionBase);
    }

    /**
     * Ajout d'un User dans le base de donnée
     * @param user
     */
    public void ajout(User user){
        db = accesBD.getWritableDatabase();
        String req ="insert into user (id, nom, prenom, email, mobil, login, password) values ";
        req += "("+user.getId()+",\"" +user.getNom() +"\",\"" +user.getPrenom() +"\",\"" +user.getEmail() +"\"," +user.getPhone()+ ",\"" +user.getLogin()+ "\"," +user.getMdp() +")";
        db.execSQL(req);
    }

    public void ajoutCovoiturage(Covoiturage covoiturage){
        db = accesBD.getWritableDatabase();
        String req ="insert into covoiturage (idCovoiturage, user, model, couleur, immatriculation, lieuDepart, lieuArrivee, heurRDV, numPlaces) values";
        req += "("+covoiturage.getIdCovoiturage()+","+covoiturage.getUser().getId()+",\""+covoiturage.getModel()+"\",\""+covoiturage.getCouleur()
                +"\",\""+covoiturage.getImmatriculation()+"\",\""+covoiturage.getLieuDepart()+"\",\""+covoiturage.getLieuArrivee()+"\","+covoiturage.getHeurRDV()+","+covoiturage.getNumPlaces()+")";
        db.execSQL(req);
    }
    /**
     * Recupération de la dernier User de la DB
     * @return
     */
    public User recupDernier(){
        db = accesBD.getReadableDatabase();
        User user =null;
        String req = "Select * from user";
        Cursor cursor = db.rawQuery(req, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            Integer id = cursor.getInt(0);
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String email = cursor.getString(3);
            Integer mobil = cursor.getInt(4);
            String login = cursor.getString(5);
            Integer password = cursor.getInt(6);
            user = new User(id, nom, prenom, email, mobil, login, password);
        }

        cursor.close();
        return user;
    }

    /**
     * Récupère l'utilisateur par son Id.
     * @param login
     * @return
     */
    public User findUserByLogin(String login, Integer password){
        db = accesBD.getReadableDatabase();
        User user = null;
        String req = "Select * from user where login = \""+ login +"\" and password = " + password;
        Cursor cursor = db.rawQuery(req, null);

        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            Integer Id = cursor.getInt(0);
            String nom = cursor.getString(1);
            String prenom = cursor.getString(2);
            String email = cursor.getString(3);
            Integer mobil = cursor.getInt(4);
            user = new User(Id, nom, prenom, email, mobil, login, password);
        }

        cursor.close();
        return user;
    }


}
