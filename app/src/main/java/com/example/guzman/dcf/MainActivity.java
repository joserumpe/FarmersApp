package com.example.guzman.dcf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    EditText edtName, edtPass;
    Button btnSignIn;
    TextView skip, txtViewSignUp;
    private static final int TIME_LIMIT = 1500;
    private static long backPressed;
    //    ProgressBar progressBar;
    private ProgressDialog progressDialog;


    DcfDatabaseHelper dcfDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dcfDatabaseHelper = new DcfDatabaseHelper(MainActivity.this);

        edtName = findViewById(R.id.email);
        edtPass = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.btnSignIn);
        skip = findViewById(R.id.skip);
        txtViewSignUp = findViewById(R.id.textViewSignup);
//        progressBar=findViewById(R.id.progressbar);
        progressDialog=new ProgressDialog(this);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(String.valueOf(edtName.getText()))) {
                    edtName.setError("Email required");
                    return;
                }

                if (TextUtils.isEmpty(String.valueOf(edtPass.getText()))) {
                    edtPass.setError("Password required");
                    return;
                }
                if (!emailValidator(edtName.getText().toString())) {
                    edtName.setError("Enter valid email");
                }

                //process login here
                String email = edtName.getText().toString();
                Cursor cursor = dcfDatabaseHelper.GetData(email);

                if (cursor == null) {
                    Toast.makeText(MainActivity.this, "Email not found", Toast.LENGTH_SHORT).show();
                    return;
                }


                progressDialog.setMessage("Logging in...");
                progressDialog.show();

                if (cursor.moveToFirst()) {
                    String password = cursor.getString(4);
                    String mail = cursor.getString(3);
                    if (email.equalsIgnoreCase(mail)) {

                        if (password.equalsIgnoreCase(edtPass.getText().toString())) {
                            finish();
                            Bundle bundle = new Bundle();
                            bundle.putString("email", email);

                            Intent intent = new Intent(MainActivity.this, Main.class);
                            intent.putExtras(bundle);
                            startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Incorrect Credentials", Toast.LENGTH_LONG).show();
                        }

                    } else {
                        Toast.makeText(MainActivity.this, "Email not found", Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();
                }


            }
        });

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToSkipLogin();
            }
        });
        txtViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LaunchRegisterScreen(v);
            }
        });


    }



    private void ToSkipLogin() {
        finish();
        Intent intent = new Intent(MainActivity.this, Main.class);
        startActivity(intent);

    }


    public void LaunchRegisterScreen(View view) {
        finish();
        Intent intent = new Intent(MainActivity.this, Register.class);
        startActivity(intent);
    }

    //Email Validator using Regex
    public boolean emailValidator(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();

    }

    public void onBackPressed() {
        if (TIME_LIMIT + backPressed > System.currentTimeMillis()) {
            super.onBackPressed();
        } else {
            Toast.makeText(getApplicationContext(), "Press back again t exit", Toast.LENGTH_SHORT).show();
        }
        backPressed = System.currentTimeMillis();
    }
}
