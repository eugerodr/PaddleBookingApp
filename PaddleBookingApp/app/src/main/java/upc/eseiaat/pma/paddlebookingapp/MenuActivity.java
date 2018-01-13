package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private ArrayList<String> reservation_list;
    private ArrayAdapter adapter;
    private String data;
    private String player_1_data="Julia";
    private String player_2_data="Marta";
    private int pos;
    private String id;
    private String hour;
    private String date;
    private Intent intent_go_back;
    private boolean reservation_added=false;

    DatabaseReference databaseReservations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // (II)
        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");

        databaseReservations = FirebaseDatabase.getInstance().getReference("reservations");

        ListView list = (ListView) findViewById(R.id.lista_reservas);
        FloatingActionButton btn_add_reservation = (FloatingActionButton) findViewById(R.id.btn_add_reservation);

        reservation_list = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reservation_list);
        list.setAdapter(adapter);

        btn_add_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseDayActivity.class);
                startActivityForResult(intent,0);
            }
        });

        intent_go_back = getIntent();
        hour = intent_go_back.getStringExtra("hour");
        date = intent_go_back.getStringExtra("date");
        reservation_added = intent.getBooleanExtra("reservation_added", false);

        if (reservation_added) {
        addItem(); }



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

    private void addItem() {
        reservation_list.add(String.format(date) + String.format("   ") + String.format(hour));
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent_menu_to_profile = new Intent(getApplicationContext(), MyProfileActivity.class);
        intent_menu_to_profile.putExtra("user_id", id);
        startActivity(intent_menu_to_profile);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:

                if (resultCode==RESULT_OK) {
                    Boolean item_value = data.getBooleanExtra("cancel_item", false);

                    if (item_value) {
                        reservation_list.remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                }
        }
    }


}