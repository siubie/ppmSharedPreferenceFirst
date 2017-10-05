package com.putraprima.ppmsharedpreferencefirst;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText firstname,lastname;
    private Button btnSubmit;
    private SharedPreferences mPref;
    private static final String PREF_NAME = TAG+"_prefs";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPref = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastName);

        String storedUsername = mPref.getString("FIRSTNAME","");
        firstname.setText(storedUsername);
        String storedPassword = mPref.getString("LASTNAME","");
        lastname.setText(storedPassword);

        btnSubmit = (Button) findViewById(R.id.button);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = firstname.getText().toString();
                String passwordText = lastname.getText().toString();

                if(TextUtils.isEmpty(usernameText)||TextUtils.isEmpty(passwordText)){
                    Toast.makeText(getApplicationContext(),"Semua Data Harus Diisi",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = mPref.edit();
                    editor.putString("FIRSTNAME",usernameText);
                    editor.putString("LASTNAME",passwordText);
                    editor.apply();
                    firstname.setText("");
                    lastname.setText("");
                }
            }
        });
    }
}
