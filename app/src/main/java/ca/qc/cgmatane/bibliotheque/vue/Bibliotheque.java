package ca.qc.cgmatane.bibliotheque.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.bibliotheque.R;
import ca.qc.cgmatane.bibliotheque.controleur.ControleurBiblitoheque;
import ca.qc.cgmatane.bibliotheque.modele.Livre;

public class Bibliotheque extends AppCompatActivity implements VueBibliotheque{

    protected List<Livre> listeLivre;
    protected ControleurBiblitoheque controleurBibliotheque = new ControleurBiblitoheque(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_bibliotheque);

        Button vueBibliothequeActionNaviguerAjouterLivre =
                (Button)findViewById(R.id.vue_bibliotheque_action_naviguer_ajouter_livre);

        vueBibliothequeActionNaviguerAjouterLivre.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View arg0) {
                        controleurBibliotheque.actionNaviguerAjouterLivre();
                    }
                }
        );
        controleurBibliotheque.onCreate(getApplicationContext());
    }

    protected void onActivityResult(int activite, int resultat, Intent donnees){
        controleurBibliotheque.onActivityResult(activite);
    }

    private List<HashMap<String, String>> adapterListeLivrePourListView() {

        List<HashMap<String, String>> listeLivrePourListView =
                new ArrayList<HashMap<String, String>>();

        for(Livre livre:listeLivre){
            listeLivrePourListView.add(livre.obtenirLivrePourAdapteur());
        }

        return listeLivrePourListView;
    }


    @Override
    public void afficherTousLesLivres(){
        ListView vueBibliothequeListeLivre = (ListView) findViewById(R.id.vue_bibliotheque_liste_livre);

        SimpleAdapter adapteurVueBibliothequeListeLivre = new SimpleAdapter(
                this,
                adapterListeLivrePourListView(),
                android.R.layout.two_line_list_item,
                new String[] {Livre.CLE_TITRE,Livre.CLE_AUTEUR},
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

                    HashMap<String,String> livre =
                        (HashMap<String, String>)
                            vueBibliothequeListeLivreOnClick.getItemAtPosition((int)positionItem);

                    controleurBibliotheque.actionNaviguerModifierLivre(livre.get(Livre.CLE_ID_LIVRE));
                }
            }
        );


    }

    @Override
    public void setListeLivre(List<Livre> listeLivre) {
        this.listeLivre = listeLivre;
    }

    @Override
    public void naviguerAjouterLivre() {
        Intent intentionNaviguerAjouterLivre = new Intent(this, AjouterLivre.class);
        startActivityForResult(intentionNaviguerAjouterLivre, ControleurBiblitoheque.ACTIVITE_AJOUTER_LIVRE);
    }

    @Override
    public void naviguerModifierLivre(String idLivre) {
        Intent intentionNaviguerModifierLivre = new Intent(
                Bibliotheque.this,
                ModifierLivre.class
        );
        intentionNaviguerModifierLivre.putExtra(Livre.CLE_ID_LIVRE, idLivre);

        startActivityForResult(intentionNaviguerModifierLivre,
                ControleurBiblitoheque.ACTIVITE_MODIFIER_LIVRE);
    }

}


























