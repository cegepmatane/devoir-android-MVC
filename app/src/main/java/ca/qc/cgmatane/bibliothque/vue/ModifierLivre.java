package ca.qc.cgmatane.bibliothque.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.bibliothque.R;
import ca.qc.cgmatane.bibliothque.donnee.LivreDAO;
import ca.qc.cgmatane.bibliothque.modele.Livre;

public class ModifierLivre extends AppCompatActivity {

    protected LivreDAO accesseurLivre;
    protected Livre livre;
    protected EditText vueModifierLivreChampTitre;
    protected EditText vueModifierLivreChampAuteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_livre);

        Bundle parametres = this.getIntent().getExtras();
        String parametreId_livre = (String) parametres.get("id_livre");
        int idLivre = Integer.parseInt(parametreId_livre);

        this.accesseurLivre = LivreDAO.getInstance();

        livre = accesseurLivre.chercherLivreParIdLivre(idLivre);

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
    private void enregisterLivre() {
        /*
        Toast message = Toast.makeText(getApplicationContext(),
                "Titre "+ vueAjouterLivreChampTitre.getText().toString(),
                Toast.LENGTH_SHORT);

        message.show();
        */

        livre.setAuteur(vueModifierLivreChampAuteur.getText().toString());
        livre.setTitre(vueModifierLivreChampTitre.getText().toString());

        accesseurLivre.modifierLivre(livre);

        naviguerRetourBibliotheque();
    }

    private void naviguerRetourBibliotheque() {
        this.finish();
    }

}





















