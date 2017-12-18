package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<String> lista_reservas;
    private ArrayAdapter adapter;
    private ListView lista;
    private String data;
    private String player_1_data="Julia";
    private String player_2_data="Marta";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        lista = (ListView) findViewById(R.id.lista_reservas);

        lista_reservas = new ArrayList<>();
        lista_reservas.add("Martes, 5 de diciembre");
        lista_reservas.add("Miércoles, 6 de diciembre");
        lista_reservas.add("Jueves, 7 de diciembre");
        lista_reservas.add("Viernes, 8 de diciembre");

        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, lista_reservas);
        lista.setAdapter(adapter);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //cojo los datos en la posicion del item
                data = lista_reservas.get(position);
                // (I)
                // 1. Crear un 'intent'
                Intent intent = new Intent(getApplicationContext(), ViewReservationActivity.class);
                // 2. Afegir paràmetres (dades extra) a la crida a l'activitat
                intent.putExtra("data",  data);
                intent.putExtra("player_1_data", player_1_data);
                intent.putExtra("player_2_data", player_2_data);
                // 3. Passar l'intent a Android perquè obri l'activitat
                startActivityForResult(intent, 0);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                // (IV)
                if (resultCode == AppCompatActivity.RESULT_OK) {
                     data = data.getStringExtra("data");
                    data.setText(data);
                }
        }
    }*/
}
