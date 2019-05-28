package com.example.guzman.dcf;


import android.content.Intent;
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
public class RegionFragment extends Fragment {
    AutoCompleteTextView country, county, sub_county, town, location, sub_location, village;
    Button save;
    String email;
    DcfDatabaseHelper dcfDatabaseHelper;


    public RegionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_region, container, false);
        dcfDatabaseHelper = new DcfDatabaseHelper(getContext());
        country = view.findViewById(R.id.country);
        county = view.findViewById(R.id.county);
        sub_county =view.findViewById(R.id.sub_county);
        town = view.findViewById(R.id.town);
        location =  view.findViewById(R.id.location);
        sub_location = view.findViewById(R.id.sub_location);
        village = view.findViewById(R.id.village);


        save = (Button) view.findViewById(R.id.btnSaveRegion);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveRegion();
            }
        });
        return view;
    }

    public void SaveRegion() {
        if (TextUtils.isEmpty(String.valueOf(country.getText()))) {
            country.setError("Country required");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(county.getText()))) {
            county.setError("County required");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(sub_county.getText()))) {
            sub_county.setError("Sub-County required");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(town.getText()))) {
            town.setError("Town required");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(location.getText()))) {
            location.setError("Location required");
            return;
        }

        if (TextUtils.isEmpty(String.valueOf(sub_location.getText()))) {
            sub_location.setError("Sub-Location required");
            return;
        }
        if (TextUtils.isEmpty(String.valueOf(village.getText()))) {
            village.setError("Village required");
            return;
        }

        String cntry = country.getText().toString();
        String cnty = county.getText().toString();
        String subnty = sub_county.getText().toString();
        String twn = town.getText().toString();
        String loc = location.getText().toString();
        String subloc = sub_location.getText().toString();
        String vill = village.getText().toString();

        Boolean bool = dcfDatabaseHelper.InsertRegion(cntry, cnty, subnty, twn, loc, subloc, vill);
        if (bool) {
            Toast.makeText(getContext(), " Details saved Successfully ", Toast.LENGTH_LONG).show();
            clearall();

        } else {
            Toast.makeText(getContext(), "Failed to Save Details", Toast.LENGTH_LONG).show();
        }
    }

    private void clearall() {
        country.setText("");
        county.setText("");
        sub_county.setText("");
        town.setText("");
        location.setText("");
        sub_location.setText("");
        village.setText("");


    }

    //do not remove this method
    public String toString() {
        return "Region";

    }
}
