package com.company;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

public class MainShuaPiao {
    public static HttpClient httpClient = HttpClient.newBuilder().build();
    public static String url = "https://api01.yoreader.com/novel/index.php/zone/create_comment";
    public static String url1= "https://api01.yoreader.com/novel/index.php/user/save_user_chapter_history";
    public static String reqBody="uuid=190707716faeb596&token=a018db14ba1f90fd09420b31b61dd6db&zone_id=48159&content=冲冲冲！&at_uuid=190707716faeb596&at_comment_id=233317";
    public static String reqBody1="uuid=190707716faeb596&token=c3234f1f53cd14e5a267e0af1ad307e8&book_main_id=777&source_id=34&current_chapter=1598969&current_chapter_page=0&current_title=%E7%AC%AC%E4%B8%80%E7%AB%A0%20%E9%99%A8%E8%90%BD%E7%9A%84%E5%A4%A9%E6%89%8D";
    public static void send_request() throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url1))
                .header("User-Agent","okhttp/3.6.0" )
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding","gzip")
                .timeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2) .POST(HttpRequest.BodyPublishers.ofString(reqBody1, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    public static void whileSend(){
        while (true){
            new Thread(() -> {
                try {
                    send_request();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            try {
                Thread.currentThread().sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        whileSend();
    }
}
