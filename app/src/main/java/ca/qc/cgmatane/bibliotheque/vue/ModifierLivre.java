package ca.qc.cgmatane.bibliotheque.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ca.qc.cgmatane.bibliotheque.R;
import ca.qc.cgmatane.bibliotheque.controleur.ControleurModifierLivre;
import ca.qc.cgmatane.bibliotheque.modele.Livre;

public class ModifierLivre extends AppCompatActivity implements VueModifierLivre{

    protected Livre livre;
    protected EditText vueModifierLivreChampTitre;
    protected EditText vueModifierLivreChampAuteur;
    protected ControleurModifierLivre controleurModifierLivre = new ControleurModifierLivre(this);
    protected String idLivreParametre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_livre);

        Bundle parametres = this.getIntent().getExtras();
        idLivreParametre = (String) parametres.get(Livre.CLE_ID_LIVRE);

        controleurModifierLivre.onCreate(getApplicationContext());
    }


    private void enregisterLivre() {
        livre.setAuteur(vueModifierLivreChampAuteur.getText().toString());
        livre.setTitre(vueModifierLivreChampTitre.getText().toString());
        controleurModifierLivre.actionEnregistrerLivre(livre);
    }


    @Override
    public void afficherLivre() {
        vueModifierLivreChampTitre = (EditText)findViewById(R.id.vue_modifier_livre_champ_titre);
        vueModifierLivreChampAuteur = (EditText)findViewById(R.id.vue_modifier_livre_champ_auteur);
        vueModifierLivreChampTitre.setText(livre.getTitre());
        vueModifierLivreChampAuteur.setText(livre.getAuteur());

        Button vueModifierLivreActionEnregistrer =
                (Button)findViewById(R.id.vue_modifier_livre_action_enregistrer);

        vueModifierLivreActionEnregistrer.setOnClickListener(

                new View.OnClickListener()
                {
                    public void onClick(View arg0) {
                        enregisterLivre();
                    }
                }
        );

    }

    @Override
    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String getIdLivreParametre() {
        return idLivreParametre;
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





















