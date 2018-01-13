package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_hour);

        Intent intent = getIntent();

        TextView reservation_hour = (TextView) findViewById(R.id.reservation_hour);
        TextView reservation_date = (TextView) findViewById(R.id.reservation_date);
        TextView reservarion_player1 = (TextView) findViewById(R.id.reservation_player1);

        Button btn_join = (Button) findViewById(R.id.btn_join);

        String hour = intent.getStringExtra("selected_hour");
        reservation_hour.setText(hour);

        String date = intent.getStringExtra("selected_date");
        reservation_date.setText(date);

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //addReservation();
            }
        });

    }

    /*private void addReservation() {

        String hour = "15:00h";
        String day = "Monday";
        String month = "May";
        String id = databaseReservations.push().getKey();
        String user1_id = "Whatever";
        String user2_id = "Whatever";

        if (!hour.isEmpty() && !day.isEmpty() && !month.isEmpty()) {

            Reservations reservation = new Reservations(id, hour, day, month, user1_id, user2_id);
            databaseReservations.child(id).setValue(reservation);

            Toast.makeText(this, R.string.added_reservation, Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(this, R.string.missing_data, Toast.LENGTH_SHORT).show();
        }


    }*/
}
