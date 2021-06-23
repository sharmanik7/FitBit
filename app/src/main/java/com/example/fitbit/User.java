package com.example.fitbit;

import android.util.Log;

public class User {
    public String uName,dob,email;
    public User(){

    }
    public User(String name,String email,String dob)
    {
        this.uName=name;
        this.email=email;
        this.dob=dob;
        Log.d(uName, "User: ");
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getuName() {
        return uName;
    }
}
