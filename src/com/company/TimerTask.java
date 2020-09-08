package com.company;

import static com.company.Main.whileSend;

public class TimerTask extends java.util.TimerTask {

    @Override
    public void run() {
        System.out.println("指定时间执行线程任务...");
        while (true){
            whileSend();
            try {
                Thread.currentThread().sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
