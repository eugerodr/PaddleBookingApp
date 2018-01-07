package baneres.marta.chooseday;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SelectedDayActivity extends AppCompatActivity {

    EditText selected_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_day);

/*
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        selected_date = (EditText)findViewById(R.id.selected_date);
        selected_date.setText(date);
    */
    }

}

