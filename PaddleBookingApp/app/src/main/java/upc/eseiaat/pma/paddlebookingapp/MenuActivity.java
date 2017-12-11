package upc.eseiaat.pma.paddlebookingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<String> lista_reservas;
    private ArrayAdapter adapter;
    private ListView lista;

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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
}
