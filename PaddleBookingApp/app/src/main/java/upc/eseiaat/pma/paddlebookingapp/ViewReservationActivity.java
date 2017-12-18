package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Eugenia on 11/12/2017.
 */

public class ViewReservationActivity extends AppCompatActivity {

    //private String title = "Día y hora de la reserva...";
    private TextView date_reservation;
    private TextView player_1;
    private TextView player_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reservation);

        // (II)
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String player_1_data = intent.getStringExtra("player_1_data");
        String player_2_data = intent.getStringExtra("player_2_data");
        date_reservation = (TextView) findViewById(R.id.date_reservation);
        date_reservation.setText(data);
        player_1 = (TextView) findViewById(R.id.player_1);
        player_1.setText(player_1_data);
        player_2 = (TextView) findViewById(R.id.player_2);
        player_2.setText(player_2_data);

    }

    /*public void saveDate(View view) {
        // (III)
        String new_data = date_reservation.getText().toString();
        Intent data = new Intent();
        data.putExtra("data", new_data);
        finish();
    }*/

}
