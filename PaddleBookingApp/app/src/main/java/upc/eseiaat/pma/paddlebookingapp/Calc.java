package upc.eseiaat.pma.paddlebookingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class Calc extends Activity {
    public static final String PREFS_NAME = "MyPrefsFile";

    private String user_name="";
    private int user_age=0;
    private boolean login=false;

    @Override
    protected void onCreate(Bundle state){
        super.onCreate(state);

        // Restore preferences
        SharedPreferences users = getSharedPreferences(PREFS_NAME, 0);
        boolean sign_up = users.getBoolean("user_info", false);
    }


    @Override
    protected void onStop(){
        super.onStop();

        // We need an Editor object to make preference changes.
        // All objects are from android.context.Context
        SharedPreferences users = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = users.edit();
        //editor.putBoolean("silentMode", mSilentMode);

        // Commit the edits!
        editor.commit();
    }


}
