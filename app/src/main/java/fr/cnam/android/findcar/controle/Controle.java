package fr.cnam.android.findcar.controle;

import android.content.Context;

import fr.cnam.android.findcar.model.Covoiturage;
import fr.cnam.android.findcar.model.User;
import fr.cnam.android.findcar.model.AccesLocal;

public class Controle {

    private static Controle instance = null;
    private static User user;
    private static Covoiturage covoiturage;
    private static AccesLocal accesLocal;

    /**
     * The Construction Code private
     */
    private Controle(){
        super();
    }

    /**
     * Creation of un Instance
     * @return Instance
     */
    public static final Controle getInstance(Context context){
        if(Controle.instance == null){
            Controle.instance = new Controle();
            accesLocal = new AccesLocal(context);
            user = accesLocal.recupDernier();

        }
        return Controle.instance;
    }


    /**
     * Creation of a User
     * @param nom:lastname
     * @param prenom : firstname
     * @param email : Email
     * @param mobil
     * @param login
     * @param mdp : Password
     * @return User
     */
    public User createUser(String nom, String prenom, String email, Integer mobil, String login, Integer mdp){

        this.user = new User(nom, prenom, email, mobil, login,mdp);
        this.accesLocal.ajout(user);

        return user;
    }

    /**
     * Creation d'une offre de covoiturage
     * @param user
     * @param model
     * @param couleur
     * @param immatriculation
     * @param lieuDepart
     * @param lieuArrivee
     * @param heurRDV
     * @param numPlaces
     * @return covoiturage
     */
    public Covoiturage createCovoiturage(User user, String model, String couleur,String immatriculation, String lieuDepart, String lieuArrivee, int heurRDV, int numPlaces){
        covoiturage = new Covoiturage(user, model, couleur, immatriculation, lieuDepart, lieuArrivee, heurRDV, numPlaces);
        this.accesLocal.ajoutCovoiturage(covoiturage);

        return covoiturage;
    }

    /**
     * Recuper le login de l'utilisateur
     * @return
     */
   public String getLogin(){
        return user.getLogin();
    }

    /**
     * Recuper le mot de passe de l'utilisatuer
     * @return
     */
    public Integer getPassword(){
        return user.getMdp();
    }

    /**
     * Trouver l'utilisateur dans le BD
     * @return
     */
    public User LoginFunction(String login, Integer password){
        User user = accesLocal.findUserByLogin(login, password);
        return user;
    }

}
