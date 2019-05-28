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
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiseaseFragment extends Fragment {

    AutoCompleteTextView descrip, severty, incident;
    Button save;
    String email;
    DcfDatabaseHelper dcfDatabaseHelper;



    public DiseaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_disease, container, false);
        descrip=view.findViewById(R.id.description);
        severty= view.findViewById(R.id.severity);
        incident= view.findViewById(R.id.incidence);
        save=view.findViewById(R.id.btnSaveDisease);

        dcfDatabaseHelper=new DcfDatabaseHelper(getContext());

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveDiseases();
            }
        });
        return view;
    }
    public void SaveDiseases() {
        if (TextUtils.isEmpty(String.valueOf(descrip.getText()))) {
            descrip.setError("Description Required");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(severty.getText()))) {
            severty.setError("Severeity required");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(incident.getText()))) {
            incident.setError("Input Incident");
            return;
        }

        String desc = descrip.getText().toString();
        String seve = severty.getText().toString();
        String inci = incident.getText().toString();



        Boolean result = dcfDatabaseHelper.insertDisease(desc, seve, inci);

        if (result) {
            Toast.makeText(getContext(), " Details saved Successfully ", Toast.LENGTH_LONG).show();
            clearall();

        } else {
            Toast.makeText(getContext(), "Failed to Save Details", Toast.LENGTH_LONG).show();
        }
    }

    private void clearall() {
        descrip.setText("");
        severty.setText("");
        incident.setText("");

        }

    //do not remove this method
    public String toString(){
        return "Disease";

    }

}
