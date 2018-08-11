package com.studioabir.maptest.realm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {


    ///------------Variable Declearation---------------///
    Button add, view, update, delete;
    EditText roll_no, name;
    TextView text;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //-------------Initialize all component from xml-----------//
        add = (Button)findViewById(R.id.add);
        view = (Button)findViewById(R.id.view);
        update = (Button)findViewById(R.id.update);
        delete = (Button)findViewById(R.id.delete);
        roll_no = (EditText)findViewById(R.id.roll_no);
        name = (EditText)findViewById(R.id.name);
        text = (TextView)findViewById(R.id.text);

        //----Realm initialization for launch activity-----------//
        Realm.init(this);
        //-------To get realm instance for use in project--------//
        realm = Realm.getDefaultInstance();

    }
    //////--------------Click action method for Click on All button by id-----------///
    public void clickAction(View view){
        switch (view.getId())
        {
            case R.id.add:
                addRecord();
                break;

            case R.id.view:
                viewRecord();
                break;

            case R.id.update:
                updateRecord();
                break;

            case R.id.delete:
                deleteRecord();
                break;
        }
    }

    //////------------Methord for Add datainto Database-----------------////
    public void addRecord(){
        realm.beginTransaction();
        Student student = realm.createObject(Student.class);
        student.setRoll_no(roll_no.getText().toString());
        student.setName(name.getText().toString());
        realm.commitTransaction();
    }

    //////------------Methord for View all From Database-----------------////
    public void viewRecord(){
        RealmResults<Student> results = realm.where(Student.class).findAll();
        text.setText("");
        for(Student student : results){
            text.append(student.getRoll_no() + " " + student.getName() + "\n");
        }
    }

    //////------------Methord for Update Data  From Database by roll_no-----------------////
    public void updateRecord(){
        RealmResults<Student> results = realm.where(Student.class).equalTo("roll_no",roll_no.getText().toString()).findAll();
        realm.beginTransaction();

        for(Student student : results){
            student.setName(name.getText().toString());
        }

        realm.commitTransaction();
    }

    //////------------Methord for Delete Data From Database by roll_no-----------------////
    public void deleteRecord(){
        RealmResults<Student> results = realm.where(Student.class).equalTo("roll_no",roll_no.getText().toString()).findAll();
        realm.beginTransaction();
        results.deleteAllFromRealm();
        realm.commitTransaction();
    }

    //------OnDestroy Methord for Close Realm Database after Your/ Close App
    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}