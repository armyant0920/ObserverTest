package com.example.observertest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    private Random rnd=new Random();
    private TextView ClownTV[]=new TextView[2];
    private TextView seats[]=new TextView[5];
    private TextView Levels[]=new TextView[5];
    private TextView responses[]=new TextView[5];
    private Button btn_perform;
    private Clown clown;
    private TextView screen;
    private Viewer viewers[]=new Viewer[5];
    private int times=0;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        }

    @Override
    protected void onPause() {
        super.onPause();
        if(handler.hasCallbacks(r1)) {
            handler.removeCallbacks(r1);
        }

    }

    public void init() {

        handler=new Handler();
        screen=findViewById(R.id.screen);
        btn_perform=findViewById(R.id.btn_perform);
        ClownTV[0]=findViewById(R.id.ClownTV);
        ClownTV[1]=findViewById(R.id.ClownScore);
        seats[0]=findViewById(R.id.p1);
        seats[1]=findViewById(R.id.p2);
        seats[2]=findViewById(R.id.p3);
        seats[3]=findViewById(R.id.p4);
        seats[4]=findViewById(R.id.p5);
        for(int i=0;i<seats.length;i++){
            seats[i].setText("p"+(i+1));
        }
        Levels[0]=findViewById(R.id.lv1);
        Levels[1]=findViewById(R.id.lv2);
        Levels[2]=findViewById(R.id.lv3);
        Levels[3]=findViewById(R.id.lv4);
        Levels[4]=findViewById(R.id.lv5);

        responses[0]=findViewById(R.id.rs1);
        responses[1]=findViewById(R.id.rs2);
        responses[2]=findViewById(R.id.rs3);
        responses[3]=findViewById(R.id.rs4);
        responses[4]=findViewById(R.id.rs5);

        for(int i=0;i<responses.length;i++){
            responses[i].setText("");
        }

    }

    public void performStart(View view){
        times=0;
        btn_perform.setVisibility(View.GONE);
        clown = new Clown();
//        PerformThread t=new PerformThread();
        ClownTV[0].setText("小丑上場");
        screen.setText("表演中");

        for(int i=0;i<5;i++){
            viewers[i]= new Viewer(i, rnd.nextInt(101),"");//随机评分标准
            clown.addObserver(viewers[i]);
            System.out.println("座号为" + viewers[i].getSeatNo() + "的观众入座,標準為:" +  viewers[i].getLevel());
            Levels[i].setText(String.valueOf( viewers[i].getLevel()));
        }

        handler.post(r1);
    }

//    public class PerformThread implements Runnable{
//
//        private int times=0;
//        @Override
//        public void run() {
//
//
//        }
//    }

    private Runnable r1=new Runnable() {
        @Override
        public void run() {
            if (times < 5) {

                    clown.perform();
                    ClownTV[1].setText(String.valueOf(clown.getScore()));
                    for(int i=0;i<5;i++){
                    responses[i].setText(viewers[i].getReply());
                        if(clown.getScore()<viewers[i].getLevel()){
                            responses[i].setTextColor(Color.RED);
                        }else{
                            responses[i].setTextColor(Color.BLACK);
                        }
                    }
                    screen.setText("第"+(times+1)+"個表演");
                    times++;
                    handler.postDelayed(r1,3000);

            }else{
                clown.exit();
                clearText();
                ClownTV[0].setText("leave");
                ClownTV[1].setText("");
                btn_perform.setVisibility(View.VISIBLE);
                handler.removeCallbacks(r1);

            }


        }
    };

    public void clearText(){
        for(TextView t:Levels){
            t.setText("");
        }
        for(TextView t:responses){
            t.setText("");
        }
         screen.setText("");
    }
}
