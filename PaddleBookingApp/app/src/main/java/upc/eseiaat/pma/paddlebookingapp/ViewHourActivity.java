package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ViewHourActivity extends AppCompatActivity {

    private String date;
    private String hour;
    private String user;
    private boolean reservation_added;

    DatabaseReference databaseReservations;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hour);

        Intent intent = getIntent();

        databaseReservations = FirebaseDatabase.getInstance().getReference("reservations");
        databaseUsers = FirebaseDatabase.getInstance().getReference("user");

        TextView reservation_hour = (TextView) findViewById(R.id.reservation_hour);
        TextView reservation_date = (TextView) findViewById(R.id.reservation_date);
        TextView reservarion_player1 = (TextView) findViewById(R.id.reservation_player1);

        Button btn_join = (Button) findViewById(R.id.btn_join);

        hour = intent.getStringExtra("selected_hour");
        reservation_hour.setText(hour);

        date = intent.getStringExtra("selected_date");
        reservation_date.setText(date);

        //user = databaseUsers.child(userName);



        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReservation();
            }
        });

    }

    private void addReservation() {

        String [] parts = date.split("/");

        String day = parts[0];
        String month = parts[1];
        String id = databaseReservations.push().getKey();
        //String user1_id = databaseUsers.getDatabase(userName);
        String user1_id = "Whatever";
        String user2_id = "Whatever";

        if (!hour.isEmpty() && !day.isEmpty() && !month.isEmpty()) {

            Reservations reservation = new Reservations(id, hour, day, month, user1_id, user2_id);
            databaseReservations.child(id).setValue(reservation);

            Toast.makeText(this, R.string.added_reservation, Toast.LENGTH_SHORT).show();

            reservation_added=true;

            Intent intent_go_back = new Intent(getApplicationContext(), MenuActivity.class);
            intent_go_back.putExtra("hour", hour);
            intent_go_back.putExtra("date", date);
            intent_go_back.putExtra("reservation_added", true);
            startActivity(intent_go_back);
        }

        else {
            Toast.makeText(this, R.string.missing_data, Toast.LENGTH_SHORT).show();
        }
    }
}