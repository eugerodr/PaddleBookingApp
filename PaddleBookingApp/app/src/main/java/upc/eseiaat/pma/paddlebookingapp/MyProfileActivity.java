package upc.eseiaat.pma.paddlebookingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

    private String user_name;
    private int user_age, user_level;
    private TextView txt_user_name;
    private TextView txt_age;
    private TextView txt_level;

    Intent data;
    int requestCode, resultCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        // (II)
        Intent intent_menu_to_profile = getIntent();

        //Referencias de los elementos del Layout
        txt_user_name = (TextView) findViewById(R.id.txt_user_name);
        txt_age = (TextView) findViewById(R.id.txt_user_age);
        txt_level = (TextView) findViewById(R.id.txt_user_level);
        Button btn_done = (Button) findViewById(R.id.btn_done);

        txt_user_name.setText(user_name);
        txt_age.setText(Integer.toString(user_age));
        String [] list_level = getResources().getStringArray(R.array.experience_array);
        txt_level.setText(list_level[user_level]);

        btn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });
    }

    public void editProfile (View view) {
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