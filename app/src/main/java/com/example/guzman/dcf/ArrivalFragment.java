package com.example.guzman.dcf;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */

@SuppressWarnings("ALL")
public class ArrivalFragment extends Fragment {
    EditText dte, tyme;
    String email;
    Button save;
    ImageView imagedate, imagetime;
    DcfDatabaseHelper dcfDatabaseHelper;


    public ArrivalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_arrival, container, false);

        dte= view.findViewById(R.id.date);
        tyme= view.findViewById(R.id.time);
        save=view.findViewById(R.id.btnSaveArrival);

        dcfDatabaseHelper=new DcfDatabaseHelper(getContext());

        imagedate=view.findViewById(R.id.imagedate) ;
        imagetime=view.findViewById(R.id.imagtime) ;

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveArrival();
            }

        });
        imagedate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdate(v);
            }
        });
        imagetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettime(v);
            }
        });


      return view;
    }
        private void SaveArrival() {
        if (TextUtils.isEmpty(dte.getText().toString())){
            dte.setError("Pick a date");
        }
        if (TextUtils.isEmpty(tyme.getText().toString())){
            tyme.setError("Pick the Time");
        }
            String dyte = dte.getText().toString();
            String tym = tyme.getText().toString();

            Boolean bool = dcfDatabaseHelper.insertArrival(dyte, tym);

            if (bool) {
                Toast.makeText(getContext(), " Arrival Successfully Saved", Toast.LENGTH_LONG).show();

                clearall();

            } else {
                Toast.makeText(getContext(), "Failed to Save", Toast.LENGTH_LONG).show();

            }
        }

    private void clearall() {
        dte.setText("");
        tyme.setText("");


    }

    public void getdate(View view) {
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            final int day, month, year;
            day = gregorianCalendar.get(Calendar.DAY_OF_MONTH);
            month = gregorianCalendar.get(Calendar.MONTH);
            year = gregorianCalendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    dte.setText(dayOfMonth+"/" +(month+1)+ "/"+year);
                }
            }, year,month,day);
            datePickerDialog.show();
        }
        public void gettime(View view){
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            final int  hours, minutes;
            hours=gregorianCalendar.get(Calendar.HOUR_OF_DAY);
            minutes= gregorianCalendar.get(Calendar.MINUTE);


            TimePickerDialog timePickerDialog= new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                    tyme.setText(""+hourOfDay+":"+ minute);
                    tyme.setText(hourOfDay+ ":" + minute);

                }
            }, hours, minutes, true);
            timePickerDialog.show();
        }


    //do not remove this method
    public String toString(){

        return "Arrival";
        }
}
