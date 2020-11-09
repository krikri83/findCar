package fr.cnam.android.findcar.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import fr.cnam.android.findcar.R;
import fr.cnam.android.findcar.controle.Controle;
import fr.cnam.android.findcar.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    //propriétes
    EditText txtLogin, txtPass;
    TextView tv;
    Controle controle;



    /**
     * Initialisation des liens avec les objets graphiques
     */
    private void init(){
        // On récupère le login et mot de passe :
        txtLogin = (EditText) findViewById(R.id.txtLogIn);
        txtPass=(EditText) findViewById(R.id.txtPassWord);
        tv = (TextView)findViewById(R.id.txtVue);
        this.controle = Controle.getInstance(this);

        ecouteValidation();
        ecouteCreateUser();
        try{
            recupUser();
        }catch (Exception e){

        }


    }

    /**
     * Ecouter le clic sur le bouton Validation
     */
    private void ecouteValidation(){
        ((Button) findViewById(R.id.btnValider)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                //Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show();
                //Log.d("message", "click ok sur le boutton Calcul*****************");
                int pass =  Integer.parseInt(txtPass.getText().toString());
                String login = txtLogin.getText().toString();
                User user = controle.LoginFunction(login, pass);
                if(!user.getLogin().isEmpty()){

                    Intent intent = new Intent(MainActivity.this, Menu.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                } else{
                    tv.setText(R.string.erreur);
                }
            }
        });
    }

    /**
     * Ecoute le clic sur le bouton Cree un Utilisateur
     */
    private void ecouteCreateUser(){
        ((Button) findViewById(R.id.btnCreer)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CreateUser.class);
                startActivity(intent);
            }
        });

    }

    /**
     * Récupère le dernier utilisateur
     */
    private void recupUser(){
        if(controle.getLogin() != null){
            txtLogin.setText(controle.getLogin());
            txtPass.setText(controle.getPassword().toString());
        }
    }

}