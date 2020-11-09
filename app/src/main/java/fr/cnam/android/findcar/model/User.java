package fr.cnam.android.findcar.model;

import java.io.Serializable;

public class User implements Serializable {

    private Integer id;
    private String nom;
    private String prenom;
    private String email;
    private Integer phone;
    private String login;
    private Integer mdp;

    /** Creates a new instance of Utilisateur */
    public User(Integer id, String nom, String prenom, String corri, Integer mobil, String login, Integer mdp){

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email=corri;
        this.phone=mobil;
        this.login = login;
        this.mdp = mdp;

    }
    public User(String nom, String prenom, String corri, Integer mobil, String login, Integer mdp){

        int Min = 1, Max = 4000;
        int id = Min + (int)(Math.random() * ((Max - Min) + 1));

        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email=corri;
        this.phone=mobil;
        this.login = login;
        this.mdp = mdp;

    }

    public User() {
        this.nom = null;
        this.prenom = null;
        this.email= null;
        this.phone=null;
        this.login = null;
        this.mdp = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getMdp() {
        return mdp;
    }

    public void setMdp(Integer mdp) {
        this.mdp = mdp;
    }
}
