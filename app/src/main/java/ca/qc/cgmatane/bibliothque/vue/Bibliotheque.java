package ca.qc.cgmatane.bibliothque.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.bibliothque.R;
import ca.qc.cgmatane.bibliothque.donnee.BaseDeDonnees;
import ca.qc.cgmatane.bibliothque.donnee.LivreDAO;

public class Bibliotheque extends AppCompatActivity {

    static final public int ACTIVITE_AJOUTER_LIVRE = 1;
    static final public int ACTIVITE_MODIFIER_LIVRE = 2;

    protected ListView vueBibliothequeListeLivre;
    protected List<HashMap<String, String>> listeLivrePourAdapteur;

    protected LivreDAO accesseurLivre;

    protected Intent intentionNaviguerAjouterLivre;
    protected Intent intentionNaviguerModifierLivre;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_bibliotheque);

        BaseDeDonnees.getInstance(getApplicationContext());

        afficherTousLesLivres();



        intentionNaviguerAjouterLivre = new Intent(this, AjouterLivre.class);

        Button vueBibliothequeActionNaviguerAjouterLivre =
                (Button)findViewById(R.id.vue_bibliotheque_action_naviguer_ajouter_livre);

        vueBibliothequeActionNaviguerAjouterLivre.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View arg0) {
                        //startActivity(intentionNaviguerAjouterLivre);
                        startActivityForResult(intentionNaviguerAjouterLivre, Bibliotheque.ACTIVITE_AJOUTER_LIVRE);
                    }

                }

        );

    }




    protected void onActivityResult(int activite, int resultat, Intent donnees){
        switch(activite)
        {
            case ACTIVITE_AJOUTER_LIVRE:
                afficherTousLesLivres();
                break;
            case ACTIVITE_MODIFIER_LIVRE:
                afficherTousLesLivres();
                break;
        }


    }

    protected void afficherTousLesLivres(){
        accesseurLivre = LivreDAO.getInstance();

        vueBibliothequeListeLivre = (ListView) findViewById(R.id.vue_bibliotheque_liste_livre);

        listeLivrePourAdapteur = accesseurLivre.recupererListeLivrePourAdapteur();

        SimpleAdapter adapteurVueBibliothequeListeLivre = new SimpleAdapter(
                this,
                listeLivrePourAdapteur,
                android.R.layout.two_line_list_item,
                new String[] {"titre","auteur"},
                new int[] {android.R.id.text1, android.R.id.text2});

        vueBibliothequeListeLivre.setAdapter(adapteurVueBibliothequeListeLivre);

        vueBibliothequeListeLivre.setOnItemClickListener(
            new AdapterView.OnItemClickListener(){

                @Override
                public void onItemClick(AdapterView<?> parent,
                                        View vue,
                                        int positionDansAdapteur,
                                        long positionItem) {
                    Log.d("Bibliotheque", "onItemClick");
                    ListView vueBibliothequeListeLivreOnClick = (ListView)vue.getParent();

                    @SuppressWarnings("unchecked")
                    HashMap<String,String> livre =
                            (HashMap<String, String>)
                                    vueBibliothequeListeLivreOnClick.getItemAtPosition((int)positionItem);
                    /*
                    Toast message = Toast.makeText(getApplicationContext(),
                            "Position " +
                                    positionItem +
                                    " titre " +
                                    livre.get("titre"),
                            Toast.LENGTH_SHORT);

                    Log.d("Bibliotheque", "onItemClick Position:"+positionItem);
                    Log.d("Bibliotheque", "onItemClick Titre:"+livre.get("titre"));

                    message.show();
                    */

                intentionNaviguerModifierLivre = new Intent(
                        Bibliotheque.this,
                        ModifierLivre.class
                );
                intentionNaviguerModifierLivre.putExtra("id_livre",
                        livre.get("id_livre"));

                //startActivity(intentionNaviguerModiferLivre);
                startActivityForResult(intentionNaviguerModifierLivre,
                        ACTIVITE_MODIFIER_LIVRE);

                }
            }
        );


    }

}


























