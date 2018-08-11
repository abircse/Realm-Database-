package com.studioabir.maptest.realm;


import io.realm.RealmObject;

/**
 * Created by Studio Abir on 2/9/2018.
 */

public class Student extends RealmObject {

    private String roll_no;
    private String name;

    public String getRoll_no() {
        return roll_no;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
