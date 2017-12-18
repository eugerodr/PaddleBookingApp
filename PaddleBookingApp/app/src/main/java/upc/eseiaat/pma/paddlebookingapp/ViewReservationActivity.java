package upc.eseiaat.pma.paddlebookingapp;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ViewReservationActivity extends AppCompatActivity {

    private Boolean cancel_item = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_reservation);

        // (II)
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String player_1_data = intent.getStringExtra("player_1_data");
        String player_2_data = intent.getStringExtra("player_2_data");

        TextView date_reservation = (TextView) findViewById(R.id.date_reservation);
        date_reservation.setText(data);

        TextView player_1 = (TextView) findViewById(R.id.player_1);
        player_1.setText(player_1_data);

        TextView player_2 = (TextView) findViewById(R.id.player_2);
        player_2.setText(player_2_data);

        Button cancel_reserv = (Button) findViewById(R.id.cancel);
        cancel_reserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelReservation();
            }
        });

    }

    private void cancelReservation(){
        //Si le doy a cancelar reserva, el valor de cancel_item es true y se implementa finish(); para volver a MenuActivity
        //Si no se cancela la reserva, cancel_item sigue teniendo siendo false e implemento finish(); para volver a MenuActivity

        AlertDialog.Builder builder = new AlertDialog.Builder(ViewReservationActivity.this);
        builder.setTitle(R.string.confirm);
        builder.setMessage(R.string.message);
        builder.setPositiveButton(R.string.erase, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ViewReservationActivity.this, R.string.cancelled,
                        Toast.LENGTH_LONG).show();
                cancel_item=true;
                Intent intent = new Intent();
                intent.putExtra("cancel_item", true);
                finish();
                    }
                });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.putExtra("cancel_item", false);
                finish();
            }
        });

        builder.create().show();

            }


    /*public void saveCancel(View view) {
        // (III)
        Intent intent = new Intent();

        if (cancel_item) {
            intent.putExtra("cancel_item", true);
        }
        else {
            intent.putExtra("cancel_item", false);
        }
        finish();
    }*/

}


