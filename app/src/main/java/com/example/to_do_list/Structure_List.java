package com.example.to_do_list;

import android.widget.EditText;

public class Structure_List {
    int image;
    String name,number;
    public Structure_List(int image,String name,String number){
        this.image=image;
        this.name=name;
        this.number=number;
    }
    public Structure_List(String name, String number){
        this.name=name;
        this.number=number;
    }
}
