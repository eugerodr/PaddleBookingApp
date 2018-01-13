package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ChooseHourActivity extends AppCompatActivity {

    EditText selected_date;

    private ArrayList <String> hour_list;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_day);

        ListView list = (ListView) findViewById(R.id.hour_list);

        hour_list = new ArrayList<>();
        hour_list.add("9:00-10:00h");
        hour_list.add("10:00-11:00h");
        hour_list.add("11:00-12:00h");
        hour_list.add("12:00-13:00h");
        hour_list.add("13:00-14:00h");
        hour_list.add("14:00-15:00h");
        hour_list.add("15:00-16:00h");
        hour_list.add("16:00-17:00h");
        hour_list.add("17:00-18:00h");
        hour_list.add("18:00-19:00h");
        hour_list.add("19:00-20:00h");
        hour_list.add("20:00-21:00h");
        hour_list.add("21:00-22:00h");


        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hour_list);
        list.setAdapter(adapter);

    }

}

