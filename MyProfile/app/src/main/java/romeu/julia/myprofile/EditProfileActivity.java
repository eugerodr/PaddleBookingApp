package romeu.julia.myprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class EditProfileActivity extends AppCompatActivity {

    private int new_user_level;
    private EditText edit_name;
    private EditText edit_age;
    private Spinner spinner_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        //Referencias de los elementos del Layout
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_age = (EditText) findViewById(R.id.edit_age);
        Button btn_save = (Button) findViewById(R.id.btn_save);

        //Configuración Spinner
        spinner_experience = (Spinner) findViewById(R.id.spinner_experience);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.experience_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_experience.setAdapter(adapter);


        Intent intent = getIntent();

        String user_name = intent.getStringExtra("user_name");
        edit_name.setText(user_name);

        int user_age = intent.getIntExtra("user_age", 0);
        edit_age.setText(Integer.toString(user_age));

        int user_level = intent.getIntExtra("user_level", 0);
        spinner_experience.setSelection(user_level);
    }



    public void saveProfile (View view) {
        String new_user_name = edit_name.getText().toString();
        int new_user_age = Integer.parseInt(edit_age.getText().toString());
        spinner_experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int pos, long id) {
                new_user_level = spinner_experience.getSelectedItemPosition();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //Do nothing
            }

        });

        Intent data = new Intent();
        data.putExtra("user_name", new_user_name);
        data.putExtra("user age", new_user_age);
        data.putExtra("user_level", new_user_level);
        setResult(RESULT_OK, data);
        finish();
    }
}