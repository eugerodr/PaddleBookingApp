package upc.eseiaat.pma.paddlebookingapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class ChooseDayActivity extends AppCompatActivity {

    EditText date;
    DatePickerDialog datePickerDialog;
    Button btn_next;
    TextView txt_select_day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_day);

        Intent intent = getIntent();

        date = (EditText) findViewById(R.id.date);
        btn_next = (Button) findViewById(R.id.btn_next);

        btn_next.setVisibility(View.INVISIBLE);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(ChooseDayActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                date.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

                btn_next.setVisibility(View.VISIBLE);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    selectedDay();
            }
        });
    }

    private void selectedDay() {
        // (I)
        Intent intent = new Intent(this, ChooseHourActivity.class);
        // 2. Afegir paràmetres (dades extra) a la crida a l'activitat
        /*intent.putExtra("date", date);*/
        // 3. Passar l'intent a Android perquè obri l'activitat
        startActivityForResult (intent,0);
    }

}



