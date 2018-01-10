package romeu.julia.signupactivity;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    private String user_name;
    private int user_age;
    private int user_level;
    int id_user = 0;


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
        user_name = edit_name.getText().toString();

        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        user_age = Integer.parseInt(edit_age.getText().toString());


        //TODO: Obtener el nivel de experiencia seleccionado por el usuario

        spinner_experience.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parentView, View view, int pos, long id) {
                user_level = spinner_experience.getSelectedItemPosition();
                //Toast.makeText(SignUpActivity.this,"Selected:" + pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Button btn_sign_up = (Button) findViewById(R.id.btn_sign_up);
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Write a message to the database

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("USERS");
                ref.setValue("Users id");
                ref.child("alanisawesome").setValue(new User("June 23, 1912", "Alan Turing"));
                DatabaseReference postsRef = ref.child("posts");

                //DatabaseReference newPostRef = postsRef.push();
                //newPostRef.setValue(new Post("gracehop", "Announcing COBOL, a New Programming Language"));
                /*
                myRef.setValue("Name, Age, Level");

                myRef.child(String.format("User %d", id_user)).setValue(id_user);
                myRef.child(String.format("User %d", id_user)).push().setValue(id_user);
                id_user++;*/
            }
        });





    }

    public static class infoUser {

        public String name;
        public int age, level;

        public infoUser (String name, int age, int level) {
            // ...
        }

    }

    public static class Post {

        public String author;
        public String title;

        public Post(String author, String title) {
            // ...
        }

    }


    private class User {
        public User(String s, String s1) {
            
        }
    }
}


