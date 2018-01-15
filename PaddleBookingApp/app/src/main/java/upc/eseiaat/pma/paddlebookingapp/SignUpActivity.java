package upc.eseiaat.pma.paddlebookingapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {

    EditText edit_name;
    EditText edit_age;
    Spinner spinner_experience;
    Button btn_sign_up;

    DatabaseReference databaseUsers;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        databaseUsers = FirebaseDatabase.getInstance().getReference(FirebaseReferences.usersReference);

        Intent intent = getIntent();

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_age = (EditText) findViewById(R.id.edit_age);
        btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        spinner_experience = (Spinner) findViewById(R.id.spinner_experience);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.experience_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_experience.setAdapter(adapter);

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();
                // (I)
                /*Intent data = new Intent(getApplicationContext(), MenuActivity.class);
                data.putExtra("user_id",  id);
                setResult(RESULT_OK, data);
                finish();*/
            }
        });

    }

    private void addUser (){

        String user_name = edit_name.getText().toString().trim();
        int user_age = Integer.parseInt(edit_age.getText().toString());
        int user_level = spinner_experience.getSelectedItemPosition();



        if (!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(Integer.toString(user_age))
                && !TextUtils.isEmpty(Integer.toString(user_level))) {

            //Create a unic id for each user
            id = databaseUsers.push().getKey();

            User user = new User(id, user_name, user_age, user_level);

            databaseUsers.child(id).setValue(user);


            Toast.makeText(this,R.string.user_added, Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(this,R.string.enter_value, Toast.LENGTH_LONG).show();
        }

        Intent intent = new Intent();
        intent.putExtra("user_name", user_name);
        intent.putExtra("user_age", user_age);
        intent.putExtra("user_id", id);
        setResult(RESULT_OK, intent);
        finish();

    }
}
