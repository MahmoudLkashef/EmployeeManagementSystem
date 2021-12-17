package com.example.employeemanagementsystem;

import android.content.Context;
import android.widget.EditText;


public class CheckEditTextInput {
Context context;

    public CheckEditTextInput(Context context) {
        this.context = context;
    }
    public boolean isEmpty(EditText editText)
    {
        if((editText.getText().toString().trim()).equals(""))return true;
        return false;
    }
    public boolean validInput(EditText editText)
    {
        if(isEmpty(editText))
        {
            editText.setError("please fill the field");
            return false;
        }
        return true;
    }

}
