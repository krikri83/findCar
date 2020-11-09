package fr.cnam.android.findcar.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.cnam.android.findcar.R;
import fr.cnam.android.findcar.model.User;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        init();
    }
    //propriétes
    TextView txtBonjour;
    User user;


    private void init(){
        Intent intent = getIntent();
        if (intent != null){
            user = (User)getIntent().getSerializableExtra("user");

            if (user != null){
                txtBonjour = (TextView) findViewById(R.id.txtBonjour);
                txtBonjour.setText("Bonjour " + user.getPrenom()+" !");
            }
        }

        ecouteDeconnecter();
        ecouteProposerOffre();
        ecouteConsulter();
    }

    /**
     * Ecouter le clic sur le bouton Se Deconnecter
     */
    public void ecouteDeconnecter(){
        ((Button) findViewById(R.id.btnDeconnecter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MainActivity.class);
                startActivity(intent);

            }
        });

    }

    /**
     * Ecouter le clic sur le bouton Proposer une offre de covoiturage
     */
    public void ecouteProposerOffre(){
        ((Button) findViewById(R.id.btnProposerOffre)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Proposer.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }

    /**
     * Ecouter le clic sur le bouton Consulter les offres à proximité
     */
    public void ecouteConsulter(){
        ((Button) findViewById(R.id.btnConsulter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, Consulter.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }
}