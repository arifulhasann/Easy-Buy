package com.example.easybuy

import android.widget.EditText

    //Extension Function
    fun EditText.isEmpty() : Boolean {

        if(this.text.toString().isEmpty()){
            this.error = "This place need to be fill up"
            return true
        }else{
            return false
        }
    }
