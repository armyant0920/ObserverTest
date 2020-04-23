package com.example.observertest;

import android.util.Log;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;

/**
 * @author puyf
 * @desc 观众类
 */
public class Viewer implements Observer {
    private int seatNo;
    private int level;//不同觀眾評價標準不同
    private String reply;


    public void setSeatNo(int seatNo) {
        this.seatNo = seatNo;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public int getSeatNo() {
        return seatNo;
    }

    public Viewer(int seatNo, int level,String reply) {
        this.seatNo = seatNo;
        this.level=level;
        this.reply=reply;
    }

    @Override
    public void update(Observable o, Object arg) {

        Integer state = (Integer) arg;
        if(state!=999) {
            if (state >= level) {
                applause();
            } else {
                CheerBack();
            }
        }else {
            exit();
        }
//        switch (state) {
//            case Clown.PERFORM_GOOD:
//                applause();
//                break;
//            case Clown.PERFORM_BAD:
//                CheerBack();
//                break;
//            case Clown.PERFORM_COMPLETE:
//                exit();
//                break;
//            default:
//                break;
//        }
    }

    /**
     * 鼓掌
     */
    private void applause() {
        System.out.println("座位号" + getSeatNo()+"標準分:"+getLevel() + "的观众鼓掌了！");
        reply="鼓掌";
    }

    /**
     * 倒喝彩
     */
    private void CheerBack() {
        System.out.println("座位号" + getSeatNo() +"標準分:"+getLevel()+ "的观众发出了倒喝彩！");
        reply="倒喝彩";
    }

    /**
     * 退场
     */
    private void exit() {
        System.out.println("座位号" + getSeatNo() + "的观众退场！");
        reply="離場";
    }



}
