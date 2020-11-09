package fr.cnam.android.findcar.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fr.cnam.android.findcar.R;
import fr.cnam.android.findcar.controle.Controle;
import fr.cnam.android.findcar.model.User;

public class CreateUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        init();
    }

    //propriétes
    EditText nom, prenom, email, tele, password, confirmPass, login;
    Controle controller;

    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init(){

        nom = (EditText) findViewById(R.id.txtName);
        prenom = (EditText) findViewById(R.id.txtPrenomUser);
        email = (EditText) findViewById(R.id.txtEmail);
        tele = (EditText) findViewById(R.id.txtPhone);
        password =(EditText) findViewById(R.id.txtPassword2);
        confirmPass=(EditText) findViewById(R.id.txtPasswordConfirm);
        login = (EditText) findViewById(R.id.txtLogIn);
        this.controller = Controle.getInstance(this);
        ecouteValidation();
        ecouteAnnuler();
    }

    /**
     * Ecouter le blic sur le bouton Validation
     */
    public void ecouteValidation(){
        ((Button) findViewById(R.id.btnCreation)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CreateUser.this, "Test", Toast.LENGTH_SHORT).show();
                //Log.d("message", "click ok sur le bouton*****");

                Integer reg_mobil=0, reg_password=0, reg_conformPassword=0;
                String reg_lastname ="" , reg_firstname = "", reg_mail = "" , reg_login = "";
                //Récupération des données saisie
                try{
                    reg_mobil = Integer.parseInt(tele.getText().toString());
                    reg_lastname = nom.getText().toString();
                    reg_firstname = prenom.getText().toString();
                    reg_mail = email.getText().toString();
                    reg_password = Integer.parseInt(password.getText().toString());
                    reg_login = login.getText().toString();
                    reg_conformPassword = Integer.parseInt(confirmPass.getText().toString());
                }
                catch (Exception e){
                    e.printStackTrace();
                }

                if(reg_login.equals("")|| reg_login.equals(null)){
                    Toast.makeText(CreateUser.this, "Login ne peux pas être vide", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!checkMail(reg_mail)) {
                    Toast.makeText(CreateUser.this, "Courriel n\'est pas bien remplir", Toast.LENGTH_SHORT).show();
                    return;

                } else if(!checkPassword(reg_password, reg_conformPassword)){
                    Toast.makeText( CreateUser.this,"Mot de passe ne corresponse pas au Mot de passe (Confirmation) ou n\'est pas bien remplir",Toast.LENGTH_LONG).show();
                    return;
                } else{
                    afficheMenu(reg_lastname, reg_firstname, reg_mobil, reg_mail, reg_login, reg_password);
                }
            }
        });
    }

    /**
     * Affichage de la menu aprés un contrôle de saisir
     * @param nom
     * @param prenom
     * @param telephone
     * @param email
     * @param login
     * @param password
     */
    public void afficheMenu(String nom, String prenom, Integer telephone, String email, String login, Integer password){
        User user = this.controller.createUser(nom, prenom, email, telephone, login, password);
        Intent intent = new Intent(CreateUser.this, MainActivity.class);

        startActivity(intent);
    }

    /**
     * Ecouter le clic sur le bouton Annuler
     */
    public void ecouteAnnuler(){
        ((Button) findViewById(R.id.btnDeconnecter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateUser.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    /**
     * Contrôle de la email
     * @param mail
     * @return
     */
    public boolean checkMail(String mail){
        boolean ok = true;
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (mail.equals("") || mail.equals(null)||!mail.matches(emailPattern)){
            ok = false;
        }
        return ok;
    }

    /**
     * Contrôle de la mots de passe est egale de mots de passe (confirmation)
     * @param password
     * @param conformPassword
     * @return
     */
    public boolean checkPassword(Integer password, Integer conformPassword){
        boolean ok = true;
        if(!password.equals(conformPassword)){
            ok = false;
        }
        if(password.toString().length() < 4){
            ok =false;
        }
        return ok;
    }
}