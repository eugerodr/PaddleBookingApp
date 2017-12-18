package romeu.julia.signupactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class EditUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //TODO:Obtener referencias de los elementos del Layout
        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        EditText edit_level = (EditText) findViewById(R.id.edit_level);
        final Spinner spinner_experience = (Spinner) findViewById(R.id.spinner_experience);


        //TODO: Obtener la información del ususario de SignUpActivity
        Intent intent = getIntent();
        String user_name = intent.getStringExtra("user_name");
        int user_age = intent.getIntExtra("user_age");
        int user_level = intent.getIntExtra("user_level");
        edit_name.setText(user_name);
        edit_age.setText(Integer.toString(user_age));
        Array
        edit_level.setText();




        //TODO: Mostrar el spinner si se clica el botón de editar, si no se ha clicado mostrar el nivel en un EditText
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.experience_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_experience.setAdapter(adapter);

        //TODO: Canviar el nombre el botón cuando estés editando los datos por: Guardar
    }
}
