package com.example.guzman.dcf;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SeedsFragment extends Fragment {
    CheckBox own, comm, rel;
    Button btnSaveSeed;
    DcfDatabaseHelper dcfDatabaseHelper;
    String email;


    public SeedsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_seeds, container, false);
        own=view.findViewById(R.id.ownseed);
        comm=view.findViewById(R.id.commercial);
        rel=view.findViewById(R.id.relatives);
        btnSaveSeed=view.findViewById(R.id.btnSaveSeed);
        dcfDatabaseHelper=new DcfDatabaseHelper(getContext());

        btnSaveSeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveSeeds();
            }
        });

        return view;
    }

    private void SaveSeeds() {
        if (!own.isChecked() && !comm.isChecked() && !rel.isChecked()){
            Toast.makeText(getContext(), "Please select a seed", Toast.LENGTH_SHORT).show();
        }else{
            String oonseed=getSeeds();
//            Toast.makeText(getContext(), ""+oonseed, Toast.LENGTH_SHORT).show();
            Boolean bool = dcfDatabaseHelper.insertSeeds(oonseed);
            if (bool) {
        Toast.makeText(getContext(), " Details saved Successfully ", Toast.LENGTH_LONG).show();

    } else {
        Toast.makeText(getContext(), "Failed to Save Details", Toast.LENGTH_LONG).show();
    }
}

        }

private String getSeeds() {
        String a="";
        if (own.isChecked() && comm.isChecked() && rel.isChecked()){
            a="Own, Commercial and relatives seeds";
        }
        else if (own.isChecked() && comm.isChecked()){
            a="Own and Commercial seeds";
        }
        else if (own.isChecked() && rel.isChecked()){
            a="Own,  and relatives seeds";
        }else if (comm.isChecked() && rel.isChecked()){
            a="Commercial and relatives seeds";
        }else if (comm.isChecked()){
            a="Commercial seeds";
        }else if (own.isChecked()){
            a="Ownseeds";
        }else if (rel.isChecked()){
            a="relatives seeds";
        }
        return a;
    }



    //do not remove this method
    public String toString(){
        return "Seeds";

    }
}
