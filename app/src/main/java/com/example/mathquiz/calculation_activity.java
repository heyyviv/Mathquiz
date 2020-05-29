package com.example.mathquiz;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.Random;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calculation_activity extends AppCompatActivity {
    public static TextView first_num;
    public static TextView second_num;
    public static TextView operator;
    public static TextView countdown;
    public static EditText answer;
    public Button submit;
    public int digit;
    public static String[] symbol={"*","+","-","/"};
    Button next;
    public static CountDownTimer stopwatch;
    public static String hightxt="HIGHSCORE  ";
    public static int score;
    public static TextView high_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_activity);
        Intent intent=getIntent();
        digit=intent.getIntExtra("level",1);
        first_num=findViewById(R.id.first_num);
        second_num=findViewById(R.id.second_num);
        operator=findViewById(R.id.operator);
        countdown=findViewById(R.id.timer_text);
        answer=findViewById(R.id.answer_edit);
        submit=findViewById(R.id.submit_btn);
        high_txt=findViewById(R.id.score);
        calculations(digit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopwatch.cancel();
                completed();
            }
        });
        next=findViewById(R.id.next_btn);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //answer.setText("0");
                answer.getText().clear();
                stopwatch.cancel();


                calculations(digit);

            }
        });
    }
    public static void calculations(int level){
        high_txt.setText(hightxt+String.valueOf(score));
        generating(level);
        stopwatch=new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {

                countdown.setText(String.valueOf( millisUntilFinished / 1000));
            }

            public void onFinish() {
                completed();
            }
        }.start();



    }
    public static void generating(int level){
        Random generator=new Random();
        int example=0;
        for(int i=0;i<level;i++){
            int fnum=generator.nextInt(10);
            example=(example*10)+fnum;
        }
        first_num.setText(String.valueOf(example));
        example=0;
        for(int i=0;i<level;i++){
            int fnum=generator.nextInt(9)+1;
            example=(example*10)+fnum;
        }
        second_num.setText(String.valueOf(example));
        int ope=generator.nextInt(4);
        operator.setText(symbol[ope]);
    }
    public static void completed(){
        int fnum=Integer.parseInt(String.valueOf(first_num.getText()));
        int snum=Integer.parseInt(String.valueOf(second_num.getText()));
        int tnum;
        try {
             tnum = Integer.parseInt(String.valueOf(answer.getText()));
        }catch (Exception e){
             tnum=-20993;
        }
        String sym=String.valueOf(operator.getText());
        if(sym=="+"){
            if(tnum==(fnum+snum)){
                answer.setText("Correct");
                score++;
            }else{
                int aa=fnum+snum;
                answer.setText(String.valueOf(aa));
            }
        }else if(sym=="*"){
            if(tnum==(fnum*snum)){
                answer.setText("Correct");
                score++;
            }else{
                int aa=fnum*snum;
                answer.setText(String.valueOf(aa));
            }
        }else if(sym=="-"){
            if(tnum==(fnum-snum)){
                answer.setText(String.valueOf(tnum));
                score++;
            }else{
                int aa=fnum-snum;
                answer.setText(String.valueOf(aa));
            }
        }if(sym=="/"){
            if(tnum==(fnum/snum)){
                answer.setText("Correct");
                score++;
            }else{
                int aa=fnum/snum;
                answer.setText(String.valueOf(aa));
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        score=0;
        high_txt.setText("Highscore 0");
    }
}
