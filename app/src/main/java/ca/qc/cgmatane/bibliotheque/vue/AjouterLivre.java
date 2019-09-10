package ca.qc.cgmatane.bibliotheque.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cgmatane.bibliotheque.R;
import ca.qc.cgmatane.bibliotheque.controleur.ControleurAjouterLivre;
import ca.qc.cgmatane.bibliotheque.modele.Livre;

public class AjouterLivre extends AppCompatActivity implements VueAjouterLivre{

    protected EditText vueAjouterLivreChampTitre;
    protected EditText vueAjouterLivreChampAuteur;

    protected ControleurAjouterLivre controleurAjouterLivre = new ControleurAjouterLivre(this);


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
        controleurAjouterLivre.onCreate(getApplicationContext());
    }

    private void enregisterLivre() {
        Livre livre = new Livre(vueAjouterLivreChampTitre.getText().toString(),
                vueAjouterLivreChampAuteur.getText().toString(),0);

        controleurAjouterLivre.actionEnregistrerLivre(livre);
    }

    @Override
    public void naviguerBibliotheque() {
        this.finish();
    }

    @Override
    public void afficherErreur() {
        Toast message = Toast.makeText(this.getApplicationContext(),
                "Le livre n'est pas valide.",
                Toast.LENGTH_SHORT);
        message.show();
    }
}























