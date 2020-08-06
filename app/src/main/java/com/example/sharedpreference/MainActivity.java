package com.example.sharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText usernameText,passwordText;
    private TextView detailsText;
    private Button buttonLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        detailsText = (TextView) findViewById(R.id.textViewId);
        usernameText = (EditText) findViewById(R.id.usernameId);
        passwordText = (EditText) findViewById(R.id.passwordId);
        buttonLogin = (Button) findViewById(R.id.buttonId);

        buttonLogin.setOnClickListener(this);

        SharedPreferences sharedPreferences = getSharedPreferences("details",Context.MODE_PRIVATE);
        if(sharedPreferences.contains("nameKey") && sharedPreferences.contains("passKey")){
            String name = sharedPreferences.getString("nameKey","not found");
            String pass = sharedPreferences.getString("passKey","not found");
            detailsText.setText(name+"  "+pass);
        }

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonId){
            String username = usernameText.getText().toString();
            String password = passwordText.getText().toString();

            if(username.equals("") && password.equals("")){
                Toast.makeText(MainActivity.this,"mara khao",Toast.LENGTH_SHORT).show();
            }
            else{
                //writing
                SharedPreferences sharedPreferences = getSharedPreferences("details", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("nameKey",username);
                editor.putString("passKey",password);
                editor.commit();
                usernameText.setText("");
                passwordText.setText("");
            }
        }
    }
}
