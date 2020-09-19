package com.company;

import static com.company.Main.whileSend;

public class TimerTask extends java.util.TimerTask {

    @Override
    public void run() {
        try {
            whileSend();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
