package romeu.julia.myprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

    private String user_name = "James";
    private int user_age = 0, user_level = 0;
    private Button btn_edit_profile;
    private TextView txt_name_surname;
    private TextView txt_age;
    private TextView txt_level;

    Intent data;
    int requestCode, resultCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        //Referencias de los elementos del Layout
        txt_name_surname = (TextView) findViewById(R.id.txt_name_surname);
        txt_age = (TextView) findViewById(R.id.txt_age);
        txt_level = (TextView) findViewById(R.id.txt_level);
        btn_edit_profile = (Button) findViewById(R.id.btn_edit_profile);

        btn_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfile();
            }
        });
    }

    private void editProfile() {
        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("user_name", user_name);
        intent.putExtra("user_age", user_age);
        intent.putExtra("user_level", user_level);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (resultCode == AppCompatActivity.RESULT_OK) {
                    user_name = data.getStringExtra("user_name");
                    txt_name_surname.setText(user_name);
                    user_age = data.getIntExtra("user_age",0);
                    txt_age.setText(user_age);
                    user_level = data.getIntExtra("user_level",0);
                    String [] list_level = getResources().getStringArray(R.array.experience_array);
                    txt_level.setText(list_level[user_level]);
                }
        }
    }
    }
