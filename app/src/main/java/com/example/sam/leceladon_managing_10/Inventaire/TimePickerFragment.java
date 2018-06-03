package com.example.sam.leceladon_managing_10.Inventaire;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by SAM on 04/05/2018.
 */

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener{
    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        // passing selected value to the MainActivity through onTimeChangeListener method
        ((OnTimeChangeListenerInterface) getActivity()).onTimeChangeListener(String.valueOf(i) + ":" + String.valueOf(i1));

    }
    public interface OnTimeChangeListenerInterface{
        void onTimeChangeListener(String time);    // this method needs to be override in MainActivity to get the value of selected time
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }
    // callback to the onTimeChangeListener method in MainActivity to pass value of selected time

}
