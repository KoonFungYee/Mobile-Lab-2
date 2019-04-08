package com.koonfungyee.food;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


public class Login extends AppCompatActivity {
    private SharedPreferenceConfig preferenceConfig;
    EditText edusername1,edpassword1;
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        preferenceConfig=new SharedPreferenceConfig(getApplicationContext());
        if (preferenceConfig.readLoginStatus()){
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }

        edusername1 = findViewById(R.id.ETusername);
        edpassword1 = findViewById(R.id.ETpassword);
        btnlogin = findViewById(R.id.BTNlogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edusername1.getText().toString();
                String password = edpassword1.getText().toString();

                loginUser(username,password);
            }
        });

    }

    private void loginUser(final String username, final String pass) {
        class LoginUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,
                        "Login user","...",false,false);
            }
            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("username",username);
                hashMap.put("password",pass);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest
                        ("http://studentsumber.com/findfood/php/login.php",hashMap);
                return s;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equalsIgnoreCase("success")){
                    Toast.makeText(Login.this, "Welcome", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,MainActivity.class));
                    preferenceConfig.writeLoginStatus(true);
                    finish();
                }else {
                    Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }

            }
        }
        LoginUser loginUser = new LoginUser();
        loginUser.execute();
    }

    public void clickhere(View view) {
        startActivity(new Intent(Login.this, Register.class));
    }
}
