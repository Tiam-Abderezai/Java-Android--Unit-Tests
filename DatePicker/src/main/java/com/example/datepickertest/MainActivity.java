package com.example.datepickertest;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity extends Activity {

    private TextView textDateStart;
    private TextView textDateEnd;
    private Button buttonDateStart;
    private Button buttonDateEnd;
    private Calendar calendarStartDate;
    private Calendar calendarEndDate;

    static final int DATE_DIALOG_ID = 0;

    private TextView textDateActive;
    private Calendar calendarDateActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*  capture our View elements for the start date function   */
        textDateStart = (TextView) findViewById(R.id.textStartDate);
        buttonDateStart = (Button) findViewById(R.id.buttonStartDate);

        /* get the current date */
        calendarStartDate = Calendar.getInstance();

        /* add a click listener to the button   */
        buttonDateStart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(textDateStart, calendarStartDate);
            }
        });

        /* capture our View elements for the end date function */
        textDateEnd = (TextView) findViewById(R.id.textEndDate);
        buttonDateEnd = (Button) findViewById(R.id.buttonEndDate);

        /* get the current date */
        calendarEndDate = Calendar.getInstance();

        /* add a click listener to the button   */
        buttonDateEnd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDateDialog(textDateEnd, calendarEndDate);
            }
        });

        /* display the current date (this method is below)  */
        updateDisplay(textDateStart, calendarStartDate);
        updateDisplay(textDateEnd, calendarEndDate);
    }

    private void updateDisplay(TextView dateDisplay, Calendar date) {
        dateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append(date.get(Calendar.MONTH) + 1).append("-")
                        .append(date.get(Calendar.DAY_OF_MONTH)).append("-")
                        .append(date.get(Calendar.YEAR)).append(" "));

    }

    public void showDateDialog(TextView dateDisplay, Calendar date) {
        textDateActive = dateDisplay;
        calendarDateActive = date;
        showDialog(DATE_DIALOG_ID);
    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            calendarDateActive.set(Calendar.YEAR, year);
            calendarDateActive.set(Calendar.MONTH, monthOfYear);
            calendarDateActive.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateDisplay(textDateActive, calendarDateActive);
            unregisterDateDisplay();
        }
    };

    private void unregisterDateDisplay() {
        textDateActive = null;
        calendarDateActive = null;
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, calendarDateActive.get(Calendar.YEAR), calendarDateActive.get(Calendar.MONTH), calendarDateActive.get(Calendar.DAY_OF_MONTH));
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        switch (id) {
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(calendarDateActive.get(Calendar.YEAR), calendarDateActive.get(Calendar.MONTH), calendarDateActive.get(Calendar.DAY_OF_MONTH));
                break;
        }
    }
}





