package fr.cnam.android.findcar.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fr.cnam.android.findcar.R;
import fr.cnam.android.findcar.model.User;

public class ValidationOffre extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation_offre);
        ecouteButtonOk();
    }

    User user;

    /**
     * Ecouter le clic sur le bouton Annuler
     */
    public void ecouteButtonOk(){
        //Récupère l'utilisateur et initialiser les TextViews (Nom et Prenom).
        Intent intent = getIntent();
        if (intent != null){
            user = (User)getIntent().getSerializableExtra("user");

        }
        ((Button) findViewById(R.id.btnOK)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValidationOffre.this, Menu.class);
                intent.putExtra("user", user);
                startActivity(intent);
            }
        });

    }

}