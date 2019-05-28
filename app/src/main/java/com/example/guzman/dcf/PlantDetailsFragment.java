package com.example.guzman.dcf;


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
public class PlantDetailsFragment extends Fragment {
    AutoCompleteTextView identity, nem, cultivar;
    Button save;
    DcfDatabaseHelper dcfDatabaseHelper;
    String email;


    public PlantDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_plant_details, container, false);
        identity= view.findViewById(R.id.identity);
        nem= view.findViewById(R.id.plantname);
        cultivar= view.findViewById(R.id.cultivar);
        save=view.findViewById(R.id.btnSavePlant);
        dcfDatabaseHelper=new DcfDatabaseHelper(getContext());
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePlantDetails();

            }
        });
        return view;
    }
    public void SavePlantDetails() {
        if (TextUtils.isEmpty(String.valueOf(identity.getText()))) {
            identity.setError("Identity Required");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(nem.getText()))) {
            nem.setError("Enter Name of crop");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(cultivar.getText()))) {
            cultivar.setError("Input The cultivar");
            return;
        }


        String identy = identity.getText().toString();
        String plntnm = nem.getText().toString();
        String cult = cultivar.getText().toString();


        Boolean bool = dcfDatabaseHelper.insertPlantDetails(identy, plntnm, cult);
        if (bool) {
            Toast.makeText(getContext(), " Details saved Successfully ", Toast.LENGTH_LONG).show();
            clearall();

        } else {
            Toast.makeText(getContext(), "Failed to Save Details", Toast.LENGTH_LONG).show();
        }
    }

    private void clearall() {
        identity.setText("");
        nem.setText("");
        cultivar.setText("");
    }

    //do not remove this method
    public String toString(){
        return "Plant Details";

    }

}
