package fr.cnam.android.findcar.model;

import java.io.Serializable;
import java.util.Date;

public class Covoiturage implements Serializable {


    private int idCovoiturage;
    private User user;
    private String model;
    private String couleur;
    private String immatriculation;
    private String lieuDepart;
    private String lieuArrivee;
    private int heurRDV;
    private int numPlaces;


    /**
     * Creates a new instance of Covoiturage whit id
     * @param id
     * @param user
     * @param model
     * @param couleur
     * @param immatriculation
     * @param lieuDepart
     * @param lieuArrivee
     * @param heurRDV
     * @param numPlaces
     */
    public Covoiturage(int id, User user, String model, String couleur,String immatriculation, String lieuDepart, String lieuArrivee, int heurRDV, int numPlaces){
        this.idCovoiturage = id;
        this.user = user;
        this.model=model;
        this.couleur=couleur;
        this.immatriculation= immatriculation;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee=lieuArrivee;
        this.heurRDV=heurRDV;
        this.numPlaces=numPlaces;
    }

    /**
     * Creates a new instance of Covoiturage whitout id
     * @param user
     * @param model
     * @param couleur
     * @param immatriculation
     * @param lieuDepart
     * @param lieuArrivee
     * @param heurRDV
     * @param numPlaces
     */
    public Covoiturage(User user, String model, String couleur,String immatriculation, String lieuDepart, String lieuArrivee, int heurRDV, int numPlaces){

        int Min = 1, Max = 4000;
        int id = Min + (int)(Math.random() * ((Max - Min) + 1));

        this.idCovoiturage = id;
        this.user = user;
        this.model=model;
        this.couleur=couleur;
        this.immatriculation= immatriculation;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee=lieuArrivee;
        this.heurRDV=heurRDV;
        this.numPlaces=numPlaces;
    }


    public int getIdCovoiturage() {
        return idCovoiturage;
    }

    public void setIdCovoiturage(int idCovoiturage) {
        this.idCovoiturage = idCovoiturage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public String getLieuDepart() {
        return lieuDepart;
    }

    public void setLieuDepart(String lieuDepart) {
        this.lieuDepart = lieuDepart;
    }

    public String getLieuArrivee() {
        return lieuArrivee;
    }

    public void setLieuArrivee(String lieuArrivee) {
        this.lieuArrivee = lieuArrivee;
    }

    public int getHeurRDV() {
        return heurRDV;
    }

    public void setHeurRDV(int heurRDV) {
        this.heurRDV = heurRDV;
    }

    public int getNumPlaces() {
        return numPlaces;
    }

    public void setNumPlaces(int numPlaces) {
        this.numPlaces = numPlaces;
    }

    /**
     * Creates a new instance of Covoiturage
     * @param model
     * @param couleur
     * @param immatriculation
     * @param lieuDepart
     * @param lieuArrivee
     * @param heurRDV
     * @param numPlaces
     */
    public Covoiturage(String model, String couleur,String immatriculation, String lieuDepart, String lieuArrivee, int heurRDV, int numPlaces){
        this.user = user;
        this.model=model;
        this.couleur=couleur;
        this.immatriculation= immatriculation;
        this.lieuDepart = lieuDepart;
        this.lieuArrivee=lieuArrivee;
        this.heurRDV=heurRDV;
        this.numPlaces=numPlaces;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
