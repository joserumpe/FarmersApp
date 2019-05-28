package com.example.guzman.dcf;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionaireFragment extends Fragment {
    AutoCompleteTextView gendr, aege, employ, land, crops;
    Button save;
    String email;
    DcfDatabaseHelper dcfDatabaseHelper;
    ProgressDialog progressDialog;

    //    Purpose
    CheckBox incom, consumpt, relats;
    //    extend
    CheckBox smal, ava, larg;
    //    training
    CheckBox yes, no;
    //    mix crop
    CheckBox mix, notmix;
    //    rotate
    CheckBox rotate, notrotate;
    //    virus disease
    CheckBox yess, nooo;

    public QuestionaireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_questionaire, container, false);
        gendr = view.findViewById(R.id.gender);
        aege = view.findViewById(R.id.age);
        employ = view.findViewById(R.id.employment);
        land = view.findViewById(R.id.landsize);
        crops = view.findViewById(R.id.impotantcrops);
        save = view.findViewById(R.id.btnSaveAnwsers);

        incom = view.findViewById(R.id.income);
        consumpt = view.findViewById(R.id.consumption);
        relats = view.findViewById(R.id.relatives);

        smal = view.findViewById(R.id.small);
        ava = view.findViewById(R.id.avarage);
        larg = view.findViewById(R.id.large);

        yes = view.findViewById(R.id.yes1);
        no = view.findViewById(R.id.no1);

        mix = view.findViewById(R.id.yep);
        notmix = view.findViewById(R.id.nop);

        rotate = view.findViewById(R.id.yes2);
        notrotate = view.findViewById(R.id.no2);

        yess = view.findViewById(R.id.yes3);
        nooo = view.findViewById(R.id.no3);
        progressDialog = new ProgressDialog(getContext());


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveQuestionnaire();

            }
        });
        return view;
    }

    public void SaveQuestionnaire() {
        if (TextUtils.isEmpty(String.valueOf(gendr.getText()))) {
            gendr.setError("Enter Gender");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(aege.getText()))) {
            aege.setError("Enter Age");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(employ.getText()))) {
            employ.setError("Employment required");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(land.getText()))) {
            land.setError("land Size required");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(crops.getText()))) {
            crops.setError("Crops required");
            return;
        }
        if (!incom.isChecked() && !consumpt.isChecked() && !relats.isChecked()) {
            Toast.makeText(getContext(), "Please select Purpose", Toast.LENGTH_LONG).show();
        }

        String gdnr = gendr.getText().toString();
        String eg = aege.getText().toString();
        String emply = employ.getText().toString();
        String size = land.getText().toString();
        String crps = crops.getText().toString();
        String purpose = getPurpose();
        String extend = getExtend();
        String training = getTraining();
        String mix = getMix();
        String rotate = getRotation();
        String virus = getVirusdisease();

        progressDialog.setMessage("Saving Contents");
        progressDialog.show();


        Boolean bool = dcfDatabaseHelper.UpdateQuestionnaire(gdnr, eg, emply, size, crps, purpose, extend, training, mix, rotate, virus);
        if (bool) {
            Toast.makeText(getContext(), " Details saved Successfully ", Toast.LENGTH_LONG).show();


        } else {
            Toast.makeText(getContext(), "Failed to Save Details", Toast.LENGTH_LONG).show();
        }
        progressDialog.dismiss();
    }


    //do not remove this method
    public String toString() {
        return "Questionnaire";

    }


    private String getPurpose() {
        String b = "";
//        if (!incom.isChecked() && !consumpt.isChecked() && !relats.isChecked()){
//            b="No purpose is selected";
//        }
        if (incom.isChecked() && consumpt.isChecked() && relats.isChecked()) {
            b = "Income, Consumption and relatives seeds";
        } else if (incom.isChecked() && consumpt.isChecked()) {
            b = "Income and Consumption ";
        } else if (incom.isChecked() && relats.isChecked()) {
            b = "income   and relatives ";
        } else if (consumpt.isChecked() && relats.isChecked()) {
            b = "Commercial and relatives ";
        } else if (consumpt.isChecked()) {
            b = "Consumption ";
        } else if (incom.isChecked()) {
            b = "income";
        } else if (relats.isChecked()) {
            b = "relatives ";
        }
        return b;
    }

    private String getExtend() {
        String b = "";

        if (smal.isChecked()) {
            b = "Small ";
        } else if (ava.isChecked()) {
            b = "Avarage";
        } else if (larg.isChecked()) {
            b = "Large ";
        }
        return b;
    }

    private String getTraining() {
        String b = "";

        if (yes.isChecked()) {
            b = "Yes ";
        } else if (no.isChecked()) {
            b = "No";
        }
        return b;
    }

    private String getMix() {
        String b = "";

        if (mix.isChecked()) {
            b = "Yes ";
        } else if (notmix.isChecked()) {
            b = "No";
        }
        return b;
    }

    private String getRotation() {
        String b = "";

        if (rotate.isChecked()) {
            b = "Yes ";
        } else if (notrotate.isChecked()) {
            b = "No";
        }
        return b;
    }

    private String getVirusdisease() {
        String b = "";

        if (yess.isChecked()) {
            b = "Yes ";
        } else if (nooo.isChecked()) {
            b = "No";
        }
        return b;
    }

}
