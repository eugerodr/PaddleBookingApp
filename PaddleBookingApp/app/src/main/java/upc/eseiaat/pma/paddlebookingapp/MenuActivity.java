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

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<String> reservation_list;
    private ArrayAdapter adapter;
    private ListView list;
    private String data;
    private String player_1_data="Julia";
    private String player_2_data="Marta";
    private int pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        list = (ListView) findViewById(R.id.lista_reservas);

        reservation_list = new ArrayList<>();
        reservation_list.add("Martes, 5 de diciembre");
        reservation_list.add("Miércoles, 6 de diciembre");
        reservation_list.add("Jueves, 7 de diciembre");
        reservation_list.add("Viernes, 8 de diciembre");

        adapter = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, reservation_list);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                pos=position;
                data = reservation_list.get(position);
                // (I)
                Intent intent = new Intent(getApplicationContext(), ViewReservationActivity.class);
                intent.putExtra("data",  data);
                intent.putExtra("player_1_data", player_1_data);
                intent.putExtra("player_2_data", player_2_data);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case 0:
                // (IV)
                // Intento recuperar el valor de "cancel_item" y lo guardo en una nueva variable Boolean item_value
                Intent intent_2 = getIntent();
                Boolean item_value = getIntent().getExtras().getBoolean("cancel_item");

               //Si el valor de item value es true, elimino el item de la lista
                if (item_value) {
                    reservation_list.remove(pos);
                    adapter.notifyDataSetChanged();
                }
        }
    }
}
