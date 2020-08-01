package com.example.omduggineni.addition;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Om Duggineni on 12/30/2017.
 */

public class Timer extends Thread{
    public int rightbutton, score, num1, num2, sum;
    public Button[] buttons = new Button[4];
    public ProgressBar bar;
    public boolean isrunning, correctresponse, responsedone, answasright, isDone;
    public long starttime;
    public TextView text;

    public Timer(ProgressBar Bar, TextView Text, Button[] Buttons){
        score = 0;
        bar = Bar;
        text = Text;
        buttons = Buttons;
    }

    public void setup(){
        isDone = false;
        isrunning = true;
        num1 = getRandom(10);
        num2 = getRandom(10);
        text.setText(String.valueOf(num1) + "+" + String.valueOf(num2));
        sum = num1+num2;
        rightbutton = getRandom(4);
        buttons[rightbutton].setText(String.valueOf(sum));
        int[] possiblevalues = {0, 1, 2, 3};
        remove(possiblevalues, rightbutton);
        for(int i = 0; i < possiblevalues.length; i++){
            buttons[i].setText(String.valueOf(getRandom(18)));
        }
        starttime = System.nanoTime();
    }

    public boolean check(int button){
        return button == rightbutton;
    }

    public void remove(int[] arr, int removedIdx) {
        System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
    }

    public static int getRandom(int max){ return (int) (Math.random()*max); }

    public void run(String[] args){
        while(isrunning) {
            bar.setProgress(1000000 - ((int) (System.nanoTime() - starttime)) / (500));
            System.out.println(System.nanoTime());
            isrunning = (System.nanoTime() - starttime) < 500000000;
            if (responsedone) {
                isrunning = false;
            }
            try{
                wait(100);
            }catch(InterruptedException e) {
                //do nothing
            }
        }
        if(responsedone){
            if(correctresponse){
                answasright = true;
                score += 5;
            }else{
                answasright = false;
            }
        }else{
            answasright = false;
        }
        isDone = true;
    }
}
