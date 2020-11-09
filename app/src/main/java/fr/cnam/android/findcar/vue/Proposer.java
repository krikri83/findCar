package fr.cnam.android.findcar.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Date;

import fr.cnam.android.findcar.R;
import fr.cnam.android.findcar.controle.Controle;
import fr.cnam.android.findcar.model.User;


public class Proposer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proposer);
        init();
    }

    //propriétes
    TextView txtNom, txtPrenom;
    EditText txtModel, txtCouleur, txtImmatriculation, txtLieuDepar, txtLieuArriveet, txtHeurRDV, txtNumPlaces;
    User user;
    Controle controller;


    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init(){

        //Récupère l'utilisateur et initialiser les TextViews (Nom et Prenom).
        Intent intent = getIntent();
        if (intent != null){
            user = (User)getIntent().getSerializableExtra("user");

            if (user != null){
                txtNom = (TextView) findViewById(R.id.txtNomUser);
                txtPrenom = (TextView) findViewById(R.id.txtPrenomUser);
                txtNom.setText(user.getNom());
                txtPrenom.setText(user.getPrenom());

            }
        }

        //Récupères les editTexts
        txtModel = (EditText) findViewById(R.id.txtModel);
        txtCouleur = (EditText) findViewById(R.id.txtCouleur);
        txtImmatriculation = (EditText) findViewById(R.id.txtImmatriculation);
        txtLieuDepar = (EditText) findViewById(R.id.txtLieuDepart);
        txtLieuArriveet = (EditText) findViewById(R.id.txtLieuArrivee);
        txtHeurRDV = (EditText) findViewById(R.id.txtHeurRDV);
        txtNumPlaces = (EditText) findViewById(R.id.txtNomPersonnes);

        this.controller = Controle.getInstance(this);
        ecouteValidation();
        ecouteAnnulerOffre();

    }


    /**
     * Ecouter le blic sur le bouton Validation
     */
    public void ecouteValidation(){
        ((Button) findViewById(R.id.btnValiderOffre)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer reg_Heure=0, reg_NumPlaces=0;
                String reg_model ="" , reg_imma = "", reg_Couleur = "" , reg_LieuDepart = "", reg_LieuArrivee = "";
                //Récupération des données saisie
                try{
                    reg_model = txtModel.getText().toString();
                    reg_Couleur = txtCouleur.getText().toString();
                    reg_imma = txtImmatriculation.getText().toString();
                    reg_LieuDepart = txtLieuDepar.getText().toString();
                    reg_LieuArrivee = txtLieuArriveet.getText().toString();
                    reg_Heure = Integer.parseInt(txtHeurRDV.getText().toString());
                    reg_NumPlaces = Integer.parseInt(txtNumPlaces.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                //Toast.makeText( Proposer.this,"Test",Toast.LENGTH_LONG).show();
                if (!checkCouleur(reg_Couleur)) {
                    Toast.makeText(Proposer.this, "Couleur n'exist pas dans la liste de couleurs. Les couleur sont Noir, Blanc, Rouge, Vert, Bleu, Jeune, Rose, Gris ou Violet ", Toast.LENGTH_LONG).show();
                    return;

                } else if(reg_LieuArrivee.equals("") || reg_LieuArrivee.equals(null)){
                    Toast.makeText( Proposer.this,"Le Lieu d'arrivée doit être remplir !",Toast.LENGTH_LONG).show();
                    return;
                } else if(reg_LieuDepart.equals("") || reg_LieuDepart.equals(null)){
                    Toast.makeText( Proposer.this,"Le Lieu du depart doit être remplir !",Toast.LENGTH_LONG).show();
                    return;
                }  else if(!checkImmatriculation(reg_imma)){
                    Toast.makeText( Proposer.this,"L'immatriculation n'est pas correct saisir ! 2 lettre - 3 chiffres - 2 lettre",Toast.LENGTH_LONG).show();
                    return;
                } else{
                    //Toast.makeText(Proposer.this, "Test réussi!", Toast.LENGTH_SHORT).show();
                    afficheConfirmation(user, reg_model, reg_Couleur, reg_imma, reg_LieuDepart, reg_LieuArrivee, reg_Heure, reg_NumPlaces);
                }
            }
        });
    }

    /**
     * Afficher le Confirmation de la création d'une nouvelle offre de covoiturage
     * @param user
     * @param reg_model
     * @param reg_Couleur
     * @param reg_imma
     * @param reg_LieuDepart
     * @param reg_LieuArrivee
     * @param reg_Heure
     * @param reg_NumPlaces
     */
    public void  afficheConfirmation(User user, String reg_model, String reg_Couleur, String reg_imma,
                                     String reg_LieuDepart,String reg_LieuArrivee, Integer reg_Heure, Integer reg_NumPlaces){

        controller.createCovoiturage(user, reg_model, reg_Couleur, reg_imma, reg_LieuDepart, reg_LieuArrivee, reg_Heure, reg_NumPlaces);
        Intent intent = new Intent(Proposer.this, ValidationOffre.class);
        intent.putExtra("user", user);
        startActivity(intent);

    }
    /**
     * Ecouter le clic sur le bouton Annuler
     */
    public void ecouteAnnulerOffre(){
        ((Button) findViewById(R.id.btnAnnulerOffre)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Proposer.this, Menu.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }

    /**
     * Vérifier que le couleur est dans la liste de présélectionner
     * @param couleur
     * @return boolean
     */
    public boolean checkCouleur(String couleur){
        boolean ok = false;
        // Créer un ArrayList<String>
        ArrayList<String> arraylist = new ArrayList<String>();

        //remplir ArrayList
        arraylist.add("Noir");
        arraylist.add("Blanc");
        arraylist.add("Rouge");
        arraylist.add("Vert");
        arraylist.add("Bleu");
        arraylist.add("Jeune");
        arraylist.add("Rose");
        arraylist.add("Gris");
        arraylist.add("Violet");

       if(arraylist.contains(couleur)){
           ok=true;
       }


        return ok;
    }

    /**
     * Vérifier que l'immatricullation est correct saisir - 2 lettre, 3 chiffres et 2 lettre
     * @param imma
     * @return
     */
    public boolean checkImmatriculation(String imma){
        boolean ok= true;
        String immaPattern = "[A-Za-z]{2}-[0-9]{3}-[A-Za-z]{2}";
        if (imma.equals("") || imma.equals(null)||!imma.matches(immaPattern)){
            ok = false;
        }

        return ok;
    }
}