package com.example.observertest;

import android.util.Log;

import java.util.Random;

/**
 *
 * @author puyf
 * @desc 测试类
 */

public class Test {
    static Clown clown=new Clown();
    static int times=0;

    public static void main(String[] args) {
        //来了一个小丑
        Random rnd=new Random();
        //观众入场了
        for (int i = 0; i < 5; i++) {
            Viewer v = new Viewer(i,rnd.nextInt(101),"");
            clown.addObserver(v);
            //Log.d("觀眾",v.getSeatNo()+"/"+v.getLevel());
            System.out.println("座号为"+v.getSeatNo()+"的观众入座,標準為:"+v.getLevel());
        }


        //小丑开始表演
        Thread PT;
        PT=new PerformThread();
        PT.start();
        //小丑表演完毕，退场
//        clown.exit();
    }

    public static class PerformThread extends Thread{

        @Override
        public void run() {
            super.run();
            if (times < 5) {
                try {
                    clown.perform();
                    times++;
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }else{
                clown.exit();
            }

        }
    }
}
