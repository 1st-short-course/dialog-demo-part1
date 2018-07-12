package com.example.rany.dialogdemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SingleAndMultiChoiceDialog extends AppCompatActivity {

    private Button btnRadioSingle, btnMultiChoice;
    private TextView tvResult;
    private ListView lvResult;
    private String[] university;
    private String[] courses;
    private int item_choose = -1;
    private ArrayList<String> selected_course;
    private boolean[] checked_course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_and_multi_choice_dialog);

        initView();
        // set event to all view
        setEvent();

    }

    private void setEvent() {
        btnRadioSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRadioSingle();
            }
        });
        btnMultiChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMultiChoice();
            }
        });
    }

    private void showMultiChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.m_title)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setCancelable(false)
                .setMultiChoiceItems(courses, checked_course, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            selected_course.add(courses[position]);
                        }
                        else if(selected_course.contains(courses[position])){
                            selected_course.remove(courses[position]);
                        }
//                        Toast.makeText(SingleAndMultiChoiceDialog.this,
//                                selected_course.toString(), Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // set result from multichoice dialog into text view
//                        String result = "";
//                        for(int i = 0; i < selected_course.size(); i++){
//                            result += selected_course.get(i);
                        // set comma (,) into textview in order to separate item
//                            if(i < selected_course.size()-1){
//                                result += " , ";
//                            }
//                        }
//                        tvResult.setText(result);
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                                (SingleAndMultiChoiceDialog.this,
                                        android.R.layout.simple_list_item_1,
                                        selected_course);
                        lvResult.setAdapter(adapter);
                    }
                })
                .show();
    }

    private void showRadioSingle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.r_title)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setCancelable(false)
                .setSingleChoiceItems(university, item_choose, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        item_choose = position;
                    }
                })
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position) {
                        tvResult.setText(university[item_choose]);
                    }
                })
                .show();

    }


    private void initView() {
        btnRadioSingle = findViewById(R.id.btnSingleRadio);
        btnMultiChoice = findViewById(R.id.btnMultiChoice);
        tvResult = findViewById(R.id.tvResult2);
        lvResult = findViewById(R.id.lvResult);

        university = getResources().getStringArray(R.array.university);
        courses = getResources().getStringArray(R.array.courses);

        selected_course = new ArrayList<>();
        checked_course = new boolean[courses.length];

    }
}
