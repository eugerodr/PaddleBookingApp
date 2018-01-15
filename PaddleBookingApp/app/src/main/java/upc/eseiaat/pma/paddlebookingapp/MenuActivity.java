package upc.eseiaat.pma.paddlebookingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class MenuActivity extends AppCompatActivity {

    private static final String PREFS_NAME = "LoginInfo";
    private ArrayList<String> reservation_list;
    private ArrayAdapter adapter;
    private String data;
    private String player_1_data="Julia";
    private String player_2_data="Marta";
    private int pos;
    private String id;
    private String hour;
    private String date;


    DatabaseReference databaseReservations;
    private boolean login=false;
    private String login_name;
    private int login_age;

    //TODO: arreglar los intents de julia


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Mirem si ja hem fet el login -- Pau
        SharedPreferences users = getSharedPreferences(PREFS_NAME, 0);
        login = users.getBoolean("login", false);

        if (!login) {
            SignUp();

        }

        // (II)
        Intent intent = getIntent();
        id = intent.getStringExtra("user_id");

        databaseReservations = FirebaseDatabase.getInstance().getReference("reservations");

        ListView list = (ListView) findViewById(R.id.lista_reservas);
        FloatingActionButton btn_add_reservation = (FloatingActionButton) findViewById(R.id.btn_add_reservation);

        reservation_list = new ArrayList<>();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, reservation_list);
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

        btn_add_reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_1 = new Intent(getApplicationContext(), ChooseDayActivity.class);
                startActivityForResult(intent_1,1);
            }
        });



    }

    private void SignUp() {
        Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivityForResult(intent, 4);
    }

    @Override
    protected void onStop(){
        super.onStop();

        // Guardem si hem fet login (FALTA POSAR EL NOM d'USUARI o el ID) -- Pau
        SharedPreferences users = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = users.edit();
        editor.putBoolean("login", login);
        editor.apply();
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

        switch (item.getItemId()) {
            case R.id.edit_profile:
                Intent intent_menu_to_profile = new Intent(getApplicationContext(), MyProfileActivity.class);
                intent_menu_to_profile.putExtra("user_id", id);
                startActivityForResult(intent_menu_to_profile, 5);
                return super.onOptionsItemSelected(item);

            case R.id.log_out:
                login=false;
                onStop();
                SignUp();
                return super.onOptionsItemSelected(item);

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

            if (requestCode==0) {

                    if (resultCode == RESULT_OK) {
                        Boolean item_value = data.getBooleanExtra("cancel_item", false);

                        if (item_value) {
                            reservation_list.remove(pos);
                            adapter.notifyDataSetChanged();
                        }
                    }
            }

            if (requestCode==1) {

                    if (resultCode == RESULT_OK) {
                        date = data.getStringExtra("selected_date");

                        Intent intent_2 = new Intent(getApplicationContext(), ChooseHourActivity.class);
                        //intent_2.putExtra("selected_date", selected_date);
                        startActivityForResult(intent_2, 2);
                    }
            }

            if (requestCode == 2) {

                if (resultCode == RESULT_OK) {
                    hour = data.getStringExtra("selected_hour");

                    Intent intent_3 = new Intent(getApplicationContext(), ViewHourActivity.class);
                    intent_3.putExtra("date", date);
                    intent_3.putExtra("hour", hour);
                    startActivityForResult(intent_3, 3);
                }
            }


            if (requestCode==3) {
                if (resultCode == RESULT_OK) {
                    boolean reservation_added = data.getBooleanExtra("reservation_added", false);

                    if (reservation_added) {
                        addItem();
                    }
                }
            }

            if (requestCode == 4) {

                if (resultCode == RESULT_OK) {
                    login_name = data.getStringExtra("user_name");
                    login_age = data.getIntExtra("user_age", 0);
                    login = true;
                }
            }
        }
}