package com.example.sweet_xue.daojihi;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by sweet_xue on 11/1/16.
 */
public class DaoJiShi {

    private static DaoJiShi daoJiShi;
    private Timer timer;
    private int number;

    public static DaoJiShi getDaoJiShi() {
        if (daoJiShi == null) {
            daoJiShi = new DaoJiShi();
        }
        return daoJiShi;
    }

    public int getNumber() {
        return number;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public void daoJiShi(final TimeInterface timeInterface) {
        if (number <= 0) {
            number = 60;
        }

        if (timer == null) {
            timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    number--;
                    if (number <= 0) {
                        timer.cancel();
                        timer = null;
                    }
                    timeInterface.callback(number);
                }
            }, 1000, 1000);
        }
    }


}
