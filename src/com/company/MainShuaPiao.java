package com.company;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

public class MainShuaPiao {
    public static HttpClient httpClient = HttpClient.newBuilder().build();
    public static String url = "https://api01.yoreader.com/novel/index.php/zone/create_comment";
    public static String url1= "https://api01.yoreader.com/novel/index.php/user/save_user_chapter_history";
    public static String reqBody="uuid=2009115d921c5faf&token=302196c58dbb5ad711dd47973780ec3e&book_main_id=777&source_id=34&current_chapter=1598969&current_chapter_page=0&current_title=%E7%AC%AC%E4%B8%80%E7%AB%A0%20%E9%99%A8%E8%90%BD%E7%9A%84%E5%A4%A9%E6%89%8D";
    public static String reqBody1="uuid=190707716faeb596&token=c3234f1f53cd14e5a267e0af1ad307e8&book_main_id=777&source_id=34&current_chapter=1598969&current_chapter_page=0&current_title=%E7%AC%AC%E4%B8%80%E7%AB%A0%20%E9%99%A8%E8%90%BD%E7%9A%84%E5%A4%A9%E6%89%8D";

public static void whileSend(){
        while (true){
            new Thread(() -> {
                try {
                    send_request(reqBody);
                    send_request(reqBody1);
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
    public static void send_request(String rBody) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest request = HttpRequest.newBuilder(new URI(url1))
                .header("User-Agent","okhttp/3.6.0" )
                .header("Content-Type", "application/x-www-form-urlencoded")
                //.header("Accept-Encoding","gzip")
                .timeout(Duration.ofSeconds(5))
                .version(HttpClient.Version.HTTP_2) .POST(HttpRequest.BodyPublishers.ofString(rBody, StandardCharsets.UTF_8))
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(unicodeStr2String(response.body()));

    }

    public static String unicodeStr2String(String unicodeStr) {
        int length = unicodeStr.length();
        int count = 0;
        //正则匹配条件，可匹配“\\u”1到4位，一般是4位可直接使用 String regex = "\\\\u[a-f0-9A-F]{4}";
        String regex = "\\\\u[a-f0-9A-F]{1,4}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(unicodeStr);
        StringBuffer sb = new StringBuffer();

        while(matcher.find()) {
            String oldChar = matcher.group();//原本的Unicode字符
            String newChar = unicode2String(oldChar);//转换为普通字符
            // int index = unicodeStr.indexOf(oldChar);
            // 在遇见重复出现的unicode代码的时候会造成从源字符串获取非unicode编码字符的时候截取索引越界等
            int index = matcher.start();

            sb.append(unicodeStr.substring(count, index));//添加前面不是unicode的字符
            sb.append(newChar);//添加转换后的字符
            count = index+oldChar.length();//统计下标移动的位置
        }
        sb.append(unicodeStr.substring(count, length));//添加末尾不是Unicode的字符
        return sb.toString();
    }

    /**
     * 字符串转换unicode
     * @param string
     * @return
     */
    public static String string2Unicode(String string) {
        StringBuffer unicode = new StringBuffer();
        for (int i = 0; i < string.length(); i++) {
            // 取出每一个字符
            char c = string.charAt(i);
            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     * @param unicode 全为 Unicode 的字符串
     * @return
     */
    public static String unicode2String(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }


    public static void main(String[] args) throws InterruptedException, IOException, URISyntaxException {
        whileSend();
    }
}
