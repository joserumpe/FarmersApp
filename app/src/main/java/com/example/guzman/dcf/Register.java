package com.example.guzman.dcf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    AutoCompleteTextView fname, lname, mail, pass;
    Button btnSaveReg;
    TextView alreadreg;
    String email;
    DcfDatabaseHelper dcfDatabaseHelper;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);
        alreadreg = findViewById(R.id.alreadyreg);
        btnSaveReg = findViewById(R.id.btnSaveReg);
        dcfDatabaseHelper = new DcfDatabaseHelper(this);

        progressDialog = new ProgressDialog(this);

        btnSaveReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveRegDetails();
            }
        });
        alreadreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GotoLogin();
            }
        });

    }

    public void SaveRegDetails() {
        if (TextUtils.isEmpty(fname.getText().toString())) {
            fname.setError("First Name Required");
        }
        if (TextUtils.isEmpty(lname.getText().toString())) {
            lname.setError("Last Name Required");
        }
        if (TextUtils.isEmpty(mail.getText().toString())) {
            mail.setError("Email Required");
        }
        if (!emailValidator(mail.getText().toString())) {
            mail.setError("Enter Valid Email");
        }
        if (TextUtils.isEmpty(pass.getText().toString())) {
            pass.setError("Password Required");
        }
        String fnm = fname.getText().toString();
        String lnm = lname.getText().toString();
        String meil = mail.getText().toString();
        String pas = pass.getText().toString();



        Boolean bool = dcfDatabaseHelper.InsertData(fnm, lnm, meil, pas); //new DcfDatabaseHelper(fnm,lnm,meil, pas);
        progressDialog.setMessage("Registering User...");
        progressDialog.show();



        if (bool) {
            Toast.makeText(Register.this, " Details Successfully Saved", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(Register.this, MainActivity.class);
            startActivity(intent);

            progressDialog.dismiss();

        } else {
            Toast.makeText(Register.this, "Failed to Save", Toast.LENGTH_LONG).show();

        }

    }

    public void GotoLogin() {
        Intent intent = new Intent(Register.this, MainActivity.class);
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

//    public void proressBar(){
//        Thread thread= new Thread(){
//            @Override
//            public void run(){
//                super.run();
//                for (int i=0; i<100; ){
//                    try{
////                        Delay of 1 second by sleep
//                        sleep(1000);
//                    }catch (InterruptedException e){
//                        e.printStackTrace();
//                    }
////                    Updating Progress Bar
//                    progbar.setProgress(i);
//                    i=i+10;
//                }
//
//            }
//
//        };
//        thread.start();
//    }
}
