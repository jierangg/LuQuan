package com.company;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.*;
import java.net.http.HttpClient.Version;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

public class Main {
    //商品ID
    public static String goodIdList[]={"8a29ac8972a48dc10172bb4eebaf0ce7","8a29ac8a7459ce7701746696a94a03ac","8a29ac8972a48dc10172bb4b994e0cc5","8a29ac8a72a48dbe0172bb4885430d81","8a29ac8973e8807e017409f378340d51","8a29ac8a73e88062017409dde2c20db6","8a29ac8a7459ce770174668bbc1b0341","8a29ac8a73a10a640173c7fa389f0a7a","8a29ac89744fa266017453230dcb0424"};
    public static String goodIdList1[]={"8a29ac8972a48dc10172bb4eebaf0ce7"};
    public static ArrayList<Good> GoodList;
    
    //url
    public static String url = "https://m.client.10010.com/welfare-mall-front/mobile/api/bj2402/v1";
    public static String Price_url="https://m.client.10010.com/welfare-mall-front-activity/mobile/activity/getGoodsTradePrice/v1";
    //定时
    public static Timer timer;
    //userList
    public static ArrayList<User> userList=new ArrayList<>();

    public static HttpClient httpClient = HttpClient.newBuilder().build();

    public static void timerTaskCall(){
        Date time = getTime();
        System.out.println("指定时间time=" + time);
        timer = new Timer();
        timer.schedule(new TimerTask(), time);
    }

    public static Date getTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 10);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 01);
        Date time = calendar.getTime();

        return time;
    }

    public static void setGoodList() throws InterruptedException, IOException, URISyntaxException {
        JSONObject goodJson=JSONObject.parseObject(getGoodsPrice());
        System.out.println(goodJson.get("resdata"));
    }
    //UserSetter
    public static void userSetter(){
        //联通
        userList.add(new User("Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:15602968825};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}","mobileServiceroute=1600394029.225.144842.817951; SHAREJSESSIONID=4C66EF0D128AC4545180AB1B1A37F94E; ecs_acc=ui9aTaV8tqkB53fDQZgKlW/y/soycH/ZDRZEMYFO1CYKfQzCA6bSZbBtWKpAsFs7bv5JTiIO+YaiuJ+x35Shc1tKoH9pzI7kQYWy9+vZR3J9Y0u9xBuhHJPe8Ju+1DxRHBHNmNb/j3sSoySf5QZfcXC37S/+HQSCDVHqHZAsUdg=; mobileServiceAll=8f6c1a12a2e9736bce0a5d0f293885c0; mobileService1=_lSe6uQKVUIjHDCgkRPmEwJrEZrdMdyno1vCpTJ1Z9-bq8joolsp!198622810; logHostIP=null; c_sfbm=234g_00; wmf=51993f17be1efcb85f8fea736b81a0f7; a_token=eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDA5OTg4MjcsInRva2VuIjp7ImxvZ2luVXNlciI6IjE1NjAyOTY4ODI1IiwicmFuZG9tU3RyIjoieWgxYTA1NjUxNjAwMzk0MDI3In0sImlhdCI6MTYwMDM5NDAyN30.iSfDx6OW-TMy4CjEvKeniCgNvR9b_s6Nn4oYyZKLoSFHtwytQC9TtKoifN4TuSk6QTpMSzvk-MTUgAdCvz6ffA; c_id=55ee97e6f978ada6c0355cce5156ef56f77cd89672fd4250073d2a5d8ff746a2; u_type=11; login_type=06; u_account=15602968825; city=051|540|90488771|-99; c_version=android@5.91; d_deviceCode=863114008639374; enc_acc=ui9aTaV8tqkB53fDQZgKlW/y/soycH/ZDRZEMYFO1CYKfQzCA6bSZbBtWKpAsFs7bv5JTiIO+YaiuJ+x35Shc1tKoH9pzI7kQYWy9+vZR3J9Y0u9xBuhHJPe8Ju+1DxRHBHNmNb/j3sSoySf5QZfcXC37S/+HQSCDVHqHZAsUdg=; random_login=1; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32173f46e7b33cddd06e905f7f51fd509dd636f344021046b47c0743ce227f7ed570422420c199dee7612e7d3ef846dd8fa; t3_token=6732ed25dc75e89aa39f97ef4da6acc4; invalid_at=61bc7f4afd0d65a7c0760c4349323c0d0571f3e998e7fbac228d04eee1a5d3eb; c_mobile=15602968825; wo_family=0; u_areaCode=540; third_token=eyJkYXRhIjoiMTMyYzJlNGFmOTFiOWU0ZTRmMmMyMDQwOWVkNWU5NDI2YWQ2OWNkZGE0YThhNzJhMjIxNjgxNmM0ZWUzOGU5ZjJiMDg3YmE0NmIxNDVmNjg2MGZhNmZiN2E5NWEwYWFkMDlhNjg5MTUxMzFlNzU3Y2Y2YmYyZTc0MWI3MjJmN2VhNTdlMTRkNzE3NDcwOWIzMjcwMGE2YzQyMTMwN2IyOSIsInZlcnNpb24iOiIwMCJ9; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NDZhZjc4MTJjZmRhZmVmZjk4OWNhYTQyNzJjNzU2MWRkNDM0ODU0ZjNmNDMzMTE4NDU1ZTM5YzE1YmM2ZTczZTFiZjY0NDIzNzEzZDU1ZTk4ZTEwYzgxYjMyMmRhNTIxZTE5NjFmYmU3YjVjMTE2MzI4ZTEwM2NhYzhjMGUzZjgxIiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxNTYwMjk2ODgyNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6ImJiODg1Y2EzZDJhZGEyNWFkMTlmYzI2ODBjY2VlNjZjIn0.-fce5QrHDzUSBb4P9qA8segtLDWGcyyapjM7GD4U1Bw","自己联通"));
        //bkf联通
        //userList.add(new User("Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:17620426435};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}","MUT_S=android9; login_type=06; u_account=17620426435; city=051|540|90157638|-99; ecs_acc=XLSi/RWeuJGWfbOOk+pUeygQFule+gh6dFI5f2TBM8j0Os7uqFtktPoEcct+yd+nsZ+Z+2euHQyLJGPxgmOiQMCMO85ESeU1LSlAWK0BzxhwzaVCJw+/A9cviaUCCTMSZjEO2X0G0s2E7lYr43i+VPrW3eHoG7gITlZ2fwBVsnM=; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32174fc1e297e76168e140c26396069181bba0a41c2470944d3db93b2e4c74222a19028673ded8136ccec13bf96da8d9faa; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NDE2NDJkNzgxZDc0ZTk3YjIyZjQ0ZGFlMzRlMzA3YTY4M2MzMDIzZWJmMzQwOWZiN2Q2OTNlY2EzYWIwODdhOGM2NDM2ODI2N2Y4NmZjOWVkYjY2MzNlODdlNDc4OGJhZDZiNDk0Zjg5YzJjYjBjMTY5NzE5Yzc1MWJkNjZmZGFhIiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxNzYyMDQyNjQzNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6Ijg3ZmEyOTk4MmZjMzdjNDE5MTMxY2Q5MDIwZjUwODRhIn0.ycmHKsofUa6xFS3-E1vp6U05mOm8D54N-Hnk2AjXwqc; _uop_id=npfauth_fgt4n5dj0babbdb9274f94778d379e9b03c36d8fomeuiler; MUT_V=android%406.002","bkf联通"));
        //pwj联通
        //userList.add(new User("Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:13172466596};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}","MUT_S=android9; login_type=06; u_account=13172466596; city=051|540|90488771|-99; ecs_acc=QPUvGEpmU9sDb1kVJyk/4qf5WaStShpgbr4qzGE1uaWWoiGLl1A4peUTGcK0RypL2rP2pxf1c4jnRAEeCGfNqspHRoe5MfV+9LMWq4T75G8o7lBbxnQQJYOnl8QBH+eRtqYGuDwLQNbi1dIL42Iu/8pC2mCbcNlBMUZVDrBVn94=; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32174fc1e297e76168e140c26396069181b05a98b6a4ab7a6972f2d539a1178f72d3ac020a9ac2f706315bb5ebddac998e2; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NGI0ZjJhNzMyNTBjNjcwNTI5YzA1NjEyMmM3MzcyMWIwNTI0YTA3NWZjYmZkYzUyMDdmMzgyMzY5NzJmYmI1YTUxMWQ2YWM0MGFkNDY2OGM3NWU0MDE2YWJmMzJlZjE4M2MzZDk0MWM3ZjJjNTRlMDRhNzhiYzEyMmRmZDY0ZDY0IiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxMzE3MjQ2NjU5NiIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6ImQ0YzY3NjY3ODQ4ZGVmNjczMTlhNzI2YmVlM2FmZWY5In0.AUifSUOpqvZptIszGOLk21jZN6j2KPuqM19WF2mJb80","pwj联通"));
 
    }

    public static void main(String[] args) throws Exception {
        userSetter();
        //定时
        timerTaskCall();

        //主程序：
        //whileSend();

        //查产品
        //setGoodList();

    }
    public static void whileSend() throws InterruptedException {
        for (String good:goodIdList1) {
            String body= "reqdata="+JSON.toJSONString(new reqdata(good));
            System.out.println(good);
            for (User u:userList) {
                try {
                    new HttpThread(u,body).start();
                    //send_request(u);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            try {
                Thread.currentThread().sleep(5500);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void send_request(User user,String reqBody) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url))
                .header("User-Agent", user.User_Agent)
                .header("Cookie", user.Cookie)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .timeout(Duration.ofSeconds(5))
                .version(Version.HTTP_2) .POST(HttpRequest.BodyPublishers.ofString(reqBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body()+"\n"+user.getRemark());
    }
    public static String getGoodsPrice() throws IOException, InterruptedException, URISyntaxException {
        String url = "https://www.sina.com.cn/";
        HttpRequest request = HttpRequest.newBuilder(new URI(Price_url))
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:15602968825};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}")
                .header("Cookie", "mobileServiceroute=1600394029.225.144842.817951; SHAREJSESSIONID=4C66EF0D128AC4545180AB1B1A37F94E; ecs_acc=ui9aTaV8tqkB53fDQZgKlW/y/soycH/ZDRZEMYFO1CYKfQzCA6bSZbBtWKpAsFs7bv5JTiIO+YaiuJ+x35Shc1tKoH9pzI7kQYWy9+vZR3J9Y0u9xBuhHJPe8Ju+1DxRHBHNmNb/j3sSoySf5QZfcXC37S/+HQSCDVHqHZAsUdg=; mobileServiceAll=8f6c1a12a2e9736bce0a5d0f293885c0; mobileService1=_lSe6uQKVUIjHDCgkRPmEwJrEZrdMdyno1vCpTJ1Z9-bq8joolsp!198622810; logHostIP=null; c_sfbm=234g_00; wmf=51993f17be1efcb85f8fea736b81a0f7; a_token=eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MDA5OTg4MjcsInRva2VuIjp7ImxvZ2luVXNlciI6IjE1NjAyOTY4ODI1IiwicmFuZG9tU3RyIjoieWgxYTA1NjUxNjAwMzk0MDI3In0sImlhdCI6MTYwMDM5NDAyN30.iSfDx6OW-TMy4CjEvKeniCgNvR9b_s6Nn4oYyZKLoSFHtwytQC9TtKoifN4TuSk6QTpMSzvk-MTUgAdCvz6ffA; c_id=55ee97e6f978ada6c0355cce5156ef56f77cd89672fd4250073d2a5d8ff746a2; u_type=11; login_type=06; u_account=15602968825; city=051|540|90488771|-99; c_version=android@5.91; d_deviceCode=863114008639374; enc_acc=ui9aTaV8tqkB53fDQZgKlW/y/soycH/ZDRZEMYFO1CYKfQzCA6bSZbBtWKpAsFs7bv5JTiIO+YaiuJ+x35Shc1tKoH9pzI7kQYWy9+vZR3J9Y0u9xBuhHJPe8Ju+1DxRHBHNmNb/j3sSoySf5QZfcXC37S/+HQSCDVHqHZAsUdg=; random_login=1; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32173f46e7b33cddd06e905f7f51fd509dd636f344021046b47c0743ce227f7ed570422420c199dee7612e7d3ef846dd8fa; t3_token=6732ed25dc75e89aa39f97ef4da6acc4; invalid_at=61bc7f4afd0d65a7c0760c4349323c0d0571f3e998e7fbac228d04eee1a5d3eb; c_mobile=15602968825; wo_family=0; u_areaCode=540; third_token=eyJkYXRhIjoiMTMyYzJlNGFmOTFiOWU0ZTRmMmMyMDQwOWVkNWU5NDI2YWQ2OWNkZGE0YThhNzJhMjIxNjgxNmM0ZWUzOGU5ZjJiMDg3YmE0NmIxNDVmNjg2MGZhNmZiN2E5NWEwYWFkMDlhNjg5MTUxMzFlNzU3Y2Y2YmYyZTc0MWI3MjJmN2VhNTdlMTRkNzE3NDcwOWIzMjcwMGE2YzQyMTMwN2IyOSIsInZlcnNpb24iOiIwMCJ9; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NDZhZjc4MTJjZmRhZmVmZjk4OWNhYTQyNzJjNzU2MWRkNDM0ODU0ZjNmNDMzMTE4NDU1ZTM5YzE1YmM2ZTczZTFiZjY0NDIzNzEzZDU1ZTk4ZTEwYzgxYjMyMmRhNTIxZTE5NjFmYmU3YjVjMTE2MzI4ZTEwM2NhYzhjMGUzZjgxIiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxNTYwMjk2ODgyNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6ImJiODg1Y2EzZDJhZGEyNWFkMTlmYzI2ODBjY2VlNjZjIn0.-fce5QrHDzUSBb4P9qA8segtLDWGcyyapjM7GD4U1Bw")
                .timeout(Duration.ofSeconds(5))
                .version(Version.HTTP_2).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        return response.body();
    }
}
