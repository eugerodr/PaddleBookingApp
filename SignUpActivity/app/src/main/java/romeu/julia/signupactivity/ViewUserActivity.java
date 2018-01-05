package romeu.julia.signupactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        String user_name = "James";
        int user_age = 0;
        int user_level = 0;

        //Referencias de los elementos del Layout
        TextView txt_name_surname = (TextView) findViewById(R.id.txt_name_surname);
        TextView txt_age = (TextView) findViewById(R.id.txt_age);
        TextView txt_level = (TextView) findViewById(R.id.txt_level);
        
    }
}
