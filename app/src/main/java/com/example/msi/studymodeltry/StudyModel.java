package com.example.msi.studymodeltry;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class StudyModel extends AppCompatActivity {
    Button start0,end0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_model);
        start0=(Button)findViewById(R.id.start);
        end0=(Button)findViewById(R.id.end);
        final Intent intent = new Intent(this,MyService.class);
        start0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(intent);
            }
        });
        end0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                这个好像不行
//                MyService de = new MyService();
//                de.onDestroy();
                stopService(intent);
            }
        });

    }

}
