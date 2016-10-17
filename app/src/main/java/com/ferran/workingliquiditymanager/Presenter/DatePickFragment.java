package com.ferran.workingliquiditymanager.Presenter;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import com.ferran.workingliquiditymanager.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickFragment extends DialogFragment {
    private static final String Extra_Date = "extradate";
    private static final String ARG_Date = "date";

    private Button mDatePickButton;
    private DatePicker mDatePicker;

    public DatePickFragment() {
        // Required empty public constructor
    }

    public static DatePickFragment newInstance(GregorianCalendar date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_Date, date);

        DatePickFragment fragment = new DatePickFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_date_pick, container, false);
        mDatePicker = (DatePicker) v.findViewById(R.id.fragment_date_pick_datePicker);
        GregorianCalendar date = (GregorianCalendar) getArguments().getSerializable(ARG_Date);
        mDatePicker.init(date.get(Calendar.YEAR), date.get(Calendar.MONTH),
                date.get(Calendar.DAY_OF_MONTH), null);

        mDatePickButton = (Button) v.findViewById(R.id.fragment_date_pick_button);
        mDatePickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = mDatePicker.getYear();
                int month = mDatePicker.getMonth();
                int day = mDatePicker.getDayOfMonth();
                GregorianCalendar gc = new GregorianCalendar(year, month, day);
                sendResult(Activity.RESULT_OK, gc);
                getActivity().finish();
            }
        });
        return v;
    }

    private void sendResult(int resultCode, GregorianCalendar gc) {
        Intent intent = new Intent();
        intent.putExtra(Extra_Date, gc);
        getActivity().setResult(resultCode, intent);
    }

    public static GregorianCalendar getDatePickResult(Intent intent) {
        return (GregorianCalendar) intent.getSerializableExtra(Extra_Date);
    }

}
