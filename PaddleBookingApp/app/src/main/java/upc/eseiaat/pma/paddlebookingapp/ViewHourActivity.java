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
    //private String user;
    private boolean reservation_added;

    DatabaseReference databaseReservations;
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hour);

        Intent intent_3 = getIntent();
        date = intent_3.getStringExtra("date");
        hour = intent_3.getStringExtra("hour");

        databaseReservations = FirebaseDatabase.getInstance().getReference("reservations");
        databaseUsers = FirebaseDatabase.getInstance().getReference("user");

        TextView reservation_hour = (TextView) findViewById(R.id.reservation_hour);
        reservation_hour.setText(hour);

        TextView reservation_date = (TextView) findViewById(R.id.reservation_date);
        reservation_date.setText(date);
        //TextView reservarion_player1 = (TextView) findViewById(R.id.reservation_player1);

        Button btn_join = (Button) findViewById(R.id.btn_join);


        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addReservation();
            }
        });

    }

    private void addReservation() {

        String id = databaseReservations.push().getKey();
        //String user1_id = databaseUsers.getDatabase(userName);
        String user1_id = "Whatever";
        String user2_id = "Whatever";

        if (!hour.isEmpty() && !date.isEmpty()) {

            Reservations reservation = new Reservations(id, hour, date, user1_id, user2_id);
            databaseReservations.child(id).setValue(reservation);

            Toast.makeText(this, R.string.added_reservation, Toast.LENGTH_SHORT).show();

            reservation_added=true;

            Intent intent_3 = new Intent();
            intent_3.putExtra("reservation_added", true);
            setResult(RESULT_OK, intent_3);
            finish();
        }

        else {
            Toast.makeText(this, R.string.missing_data, Toast.LENGTH_SHORT).show();
        }
    }
}
