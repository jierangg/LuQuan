package com.company;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.company.Main.send_request;

public class HttpThread extends Thread{
    User user;
    String reqBody;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public HttpThread(User user, String reqBody) {
        this.user = user;
        this.reqBody = reqBody;
    }

    @Override
    public void run() {
        try {
            send_request(user,reqBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
