package com.bs.demo.widget;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Description: 日期选择器
 */

public class CDatePickerDialog {
    private Context context;
    private DatePickerDialog datePickerDialog;
    private OnDatePicked call;
    public interface OnDatePicked {
        void callback(DatePicker view, String year, String month, String day);
    }
    public CDatePickerDialog(Context context, OnDatePicked  onDatePicked) {
        this.context = context;
        call = onDatePicked;
        init();
    }
    private void init(){
        final Calendar c = Calendar.getInstance();

       datePickerDialog =new DatePickerDialog(context, (datePicker, i, i1, i2) -> {
           c.set(datePicker.getYear(), datePicker.getMonth(),datePicker.getDayOfMonth());
           //补零
           DecimalFormat df = new DecimalFormat("00");
           call.callback(datePicker,df.format(i),df.format(i1+1),df.format(i2));
       },c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH));
    }
    public void show(){
        datePickerDialog.show();
    }
}
