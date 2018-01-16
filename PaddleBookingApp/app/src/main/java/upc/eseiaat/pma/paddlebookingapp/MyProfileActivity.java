package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class MyProfileActivity extends AppCompatActivity {

    private String user_name;
    private int user_age, user_level;
    private TextView txt_user_name;
    private TextView txt_age;
    private TextView txt_level;

    Intent data;
    int requestCode, resultCode;

    DatabaseReference databaseUsers;
    User user;
    private ValueEventListener eventListener;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // (II)
        Intent intent_menu_to_profile = getIntent();
        id = intent_menu_to_profile.getStringExtra("user_id");
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();


        //Referencias de los elementos del Layout
        txt_user_name = (TextView) findViewById(R.id.txt_user_name);
        txt_age = (TextView) findViewById(R.id.txt_user_age);
        txt_level = (TextView) findViewById(R.id.txt_user_level);
        Button btn_done = (Button) findViewById(R.id.btn_done);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference usersReference = database.getReference(FirebaseReferences.usersReference);
        usersReference.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                user_name = user.getUserName();
                user_age = user.getUserAge();
                user_level = user.getUserLevel();

                txt_user_name.setText(user_name);
                txt_age.setText(Integer.toString(user_age));
                final String [] list_level = getResources().getStringArray(R.array.experience_array);
                txt_level.setText(list_level[user_level]);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                updateUser(id, user_name, user_age, user_level);
                finish();
            }
        });

    }

    private void updateUser(String id, String user_name, int user_age, int user_level) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference userReference = database.getReference(FirebaseReferences.usersReference).child(id);

        User user = new User(id, user_name, user_age, user_level);
        userReference.setValue(user);
    }

    public void editProfile (View view) {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("user_name", user_name);
        intent.putExtra("user_age", user_age);
        intent.putExtra("user_level", user_level);
        startActivityForResult(intent, 6);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 6:
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    user_name = data.getStringExtra("user_name");
                    txt_user_name.setText(user_name);
                    user_age = data.getIntExtra("user_age",0);
                    txt_age.setText(Integer.toString(user_age));
                    user_level = data.getIntExtra("user_level",0);
                    String [] list_level = getResources().getStringArray(R.array.experience_array);
                    txt_level.setText(list_level[user_level]);

                }
        }
    }
}