package com.koonfungyee.food;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText username, password, name, phone;
    Spinner sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }


    public void clickhere(View view) {
        startActivity(new Intent(Register.this, Login.class));

    }

    public void register(View view) {
        username=findViewById(R.id.ETusername);
        password=findViewById(R.id.ETpassword);
        name=findViewById(R.id.ETusername);
        phone=findViewById(R.id.ETphone);
        sex=findViewById(R.id.spinner);

        String username1=username.getText().toString();
        String password1=password.getText().toString();
        String name1=name.getText().toString();
        String phone1=phone.getText().toString();
        String sex1=sex.getSelectedItem().toString();

        if (username1.equals("")||password1.equals("")||name1.equals("")||phone1.equals("")||sex1.equals("")){
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
        }else {
            registerUser(username1,password1,name1,phone1,sex1);
        }

    }

    private void registerUser(final String username, final String password, final String name, final String phone, final String sex) {
        class RegisterUser extends AsyncTask<Void,Void,String>{
            ProgressDialog loading;

            @Override
            protected String doInBackground(Void... voids) {
                HashMap<String,String> hashMap = new HashMap<>();
                hashMap.put("username",username);
                hashMap.put("password",password);
                hashMap.put("name",name);
                hashMap.put("phone",phone);
                hashMap.put("sex",sex);
                RequestHandler rh = new RequestHandler();
                String s = rh.sendPostRequest
                        ("http://studentsumber.com/findfood/php/register.php",hashMap);
                return s;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Register.this,
                        "Registration","...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if (s.equalsIgnoreCase("success")) {
                    Toast.makeText(Register.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, Login.class);
                    Register.this.finish();
                    startActivity(intent);
                }else{
                    Toast.makeText(Register.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                }
            }
        }

        RegisterUser registerUser = new RegisterUser();
        registerUser.execute();
    }
}
