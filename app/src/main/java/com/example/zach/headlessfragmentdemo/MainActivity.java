package com.example.zach.headlessfragmentdemo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends Activity implements HeadLessFragment.HeadlessFragmentListener{

    HeadLessFragment headLessFragment;
    TextView progressTv;
    Button cancle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        headLessFragment = new HeadLessFragment();

        progressTv = (TextView) findViewById(R.id.progress);
        cancle = (Button) findViewById(R.id.cancle);
        getFragmentManager().beginTransaction().add(R.id.content, headLessFragment).commit();


        headLessFragment.myAsynTask.execute();
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    headLessFragment.myAsynTask.cancel(true);
                } catch (Exception e) {
                    Log.d("error", e.toString());
                }

            }
        });

    }

    @Override
    public void sendProgress(int progress) {
        Log.d(MainActivity.class.getSimpleName(), progress + "");
        progressTv.setText(progress + "");
    }
}