package com.putraprima.ppmsharedpreferencefirst;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class SharedObjectPreference extends AppCompatActivity {
    private Gson gson;
    private GsonBuilder builder;
    private static final String TAG = SharedObjectPreference.class.getSimpleName();
    private EditText firstname,lastname;
    private Button btnSubmit,btnSharedObject;
    private SharedPreferences mPref;
    private static final String PREF_NAME = TAG+"_prefs";
    private Person mPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_object_preference);
        gson = new Gson();
        builder = new GsonBuilder();
        mPref = getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastName);

        String storedPerson = mPref.getString("PERSON","");
        if(storedPerson.length()>0){
            mPerson = convertStringToPersonObject(storedPerson);
            firstname.setText(mPerson.getFirstname());
            lastname.setText(mPerson.getLastname());
        }

        btnSubmit = (Button) findViewById(R.id.button);
        btnSharedObject = (Button) findViewById(R.id.btnSharedObject);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernameText = firstname.getText().toString();
                String passwordText = lastname.getText().toString();

                if(TextUtils.isEmpty(usernameText)||TextUtils.isEmpty(passwordText)){
                    Toast.makeText(getApplicationContext(),"Semua Data Harus Diisi",Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences.Editor editor = mPref.edit();
                    Person newPerson = new Person(usernameText,passwordText);
                    String stringPerson = convertPersonObjectToString(newPerson);
                    editor.putString("PERSON",stringPerson);
                    editor.apply();
                    firstname.setText("");
                    lastname.setText("");
                }
            }
        });
    }

    private String convertPersonObjectToString(Person person){
        return this.gson.toJson(person);
    }

    private Person convertStringToPersonObject(String personString){
        Person person = this.gson.fromJson(personString, Person.class);
        return person;
    }
}
