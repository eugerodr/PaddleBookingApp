package romeu.julia.signupactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        final Spinner spinner_experience = (Spinner) findViewById(R.id.spinner_experience);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.experience_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_experience.setAdapter(adapter);

        //TODO: Obtener la información del usuario

        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        String user_name = edit_name.getText().toString();

        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        final int user_age = Integer.parseInt(edit_age.getText().toString());


        //TODO: Obtener el nivel de experiencia seleccionado por el usuario

        spinner_experience.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {

                int user_level = (int) parent.getItemAtPosition(pos);

                //Toast.makeText(getApplicationContext(),"Selected:" + selected_level, Toast.LENGTH_SHORT).show();

            }
        });




    }
}


