package com.example.guzman.dcf;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {
    TextView toreg, skip;
    AutoCompleteTextView emaillogin, passlogin;
    Button btnSaveLogin;
    DcfDatabaseHelper dcfDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dcfDatabaseHelper=new DcfDatabaseHelper(this);
        emaillogin=findViewById(R.id.emaillogin);
        passlogin=findViewById(R.id.passlogin);
        toreg=findViewById(R.id.toSignUp);
        skip=findViewById(R.id.textViewSkip);
        btnSaveLogin=findViewById(R.id.btnSaveLogin);
        btnSaveLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckUser();
            }
        });
        
    }

    private void CheckUser() {
        if (TextUtils.isEmpty(String.valueOf(emaillogin.getText()))){
            emaillogin.setError("Email required");
            return;
        }
        if (!emailValidator(emaillogin.getText().toString())){
            emaillogin.setError("Enter Valid Email");
        }

        if (TextUtils.isEmpty(String.valueOf(passlogin.getText()))){
            passlogin.setError("Password required");
            return;
        }

        //process login here
        String email=emaillogin.getText().toString();
        Cursor cursor=dcfDatabaseHelper.GetData(email);

        if (cursor==null){
            Toast.makeText(Login.this, "Email not found", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursor.moveToFirst()){
            String password=cursor.getString(4);
            if (password.equalsIgnoreCase(passlogin.getText().toString())){
                finish();
                Bundle bundle=new Bundle();
                bundle.putString("email",email);

                Intent intent=new Intent(Login.this,Main.class);
//                intent.putExtras(bundle);
                startActivity(intent);
            }else{
                Toast.makeText(Login.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(Login.this, "Email not found", Toast.LENGTH_SHORT).show();
        }

        }
    //Email Validator using Regex
    public boolean emailValidator(String email){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN ="^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern=Pattern.compile(EMAIL_PATTERN);
        matcher=pattern.matcher(email);
        return matcher.matches();

    }
    }

