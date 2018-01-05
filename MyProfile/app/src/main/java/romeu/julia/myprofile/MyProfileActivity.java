package romeu.julia.myprofile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MyProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        String user_name = "James";
        int user_age = 0;
        int user_level = 0;

        //Referencias de los elementos del Layout
        TextView txt_name_surname = (TextView) findViewById(R.id.txt_name_surname);
        TextView txt_age = (TextView) findViewById(R.id.txt_age);
        TextView txt_level = (TextView) findViewById(R.id.txt_level);

        Intent intent = new Intent(this, EditProfileActivity.class);
        intent.putExtra("user_name", user_name );
        intent.putExtra("user_age", user_age );
        intent.putExtra("user_level", user_level );
        startActivityForResult(intent, 0);

        /*
        onActivityResult(int requestCode, int resultCode, Intent data);

        @Override
        prote
        onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case 0:
                    // (IV)
                    if (resultCode == AppCompatActivity.RESULT_OK) {
                        title = data.getStringExtra("title");
                        title_text.setText(title);
                    }
            }
        }
        */
    }
}
