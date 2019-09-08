package ca.qc.cgmatane.bibliothque.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cgmatane.bibliothque.R;
import ca.qc.cgmatane.bibliothque.donnee.LivreDAO;
import ca.qc.cgmatane.bibliothque.modele.Livre;

public class AjouterLivre extends AppCompatActivity {

    protected EditText vueAjouterLivreChampTitre;
    protected EditText vueAjouterLivreChampAuteur;

    protected LivreDAO accesseurLivre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_livre);

        vueAjouterLivreChampTitre = (EditText)findViewById(R.id.vue_ajouter_livre_champ_titre);
        vueAjouterLivreChampAuteur = (EditText)findViewById(R.id.vue_ajouter_livre_champ_auteur);

        Button vueAjouterLivreActionEnregistrer =
                (Button)findViewById(R.id.vue_ajouter_livre_action_enregistrer);

        vueAjouterLivreActionEnregistrer.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        enregisterLivre();
                    }
                }
        );

    }

    private void enregisterLivre() {
        /*
        Toast message = Toast.makeText(getApplicationContext(),
                "Titre "+ vueAjouterLivreChampTitre.getText().toString(),
                Toast.LENGTH_SHORT);

        message.show();
        */

        accesseurLivre = LivreDAO.getInstance();

        Livre livre = new Livre(vueAjouterLivreChampTitre.getText().toString(),
                vueAjouterLivreChampAuteur.getText().toString(),0);

        accesseurLivre.ajouterLivre(livre);

        naviguerRetourBibliotheque();
    }

    private void naviguerRetourBibliotheque() {
        this.finish();
    }

}























