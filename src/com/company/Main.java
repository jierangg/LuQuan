package com.company;
import com.alibaba.fastjson.JSON;

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
    public static String goodIdList[]={"8a29ac8973e8807e0174058dea5c0ab5","8a29ac8973e8807e017405894eaa0a70"};
    public static String goodIdList1[]={"8a29ac8972a48dc10172bb4eebaf0ce7"};
    //url
    public static String url = "https://m.client.10010.com/welfare-mall-front/mobile/api/bj2402/v1";
    //定时
    public static Timer timer;

    public static void timerTaskCall(){
        Date time = getTime();
        System.out.println("指定时间time=" + time);
        timer = new Timer();
        timer.schedule(new TimerTask(), time);
    }

    public static Date getTime(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 15);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 58);
        Date time = calendar.getTime();

        return time;
    }
    //userList
    public static ArrayList<User> userList=new ArrayList<>();
    //UserSetter
    public static void userSetter(){
        //联通
        userList.add(new User("Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:15602968825};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}","_n3fa_cid=748f34ec87e34b6ef8bd9454ab6858de; _n3fa_ext=ft=1593322142; _n3fa_lvt_a9e72dfe4a54a20c3d6e671b3bad01d9=1593322142; UID=ijaHndDVoSFdbPBS70iO8miUfDdz99mw; zg_did=%7B%22did%22%3A%20%2217435351a4e1cb-0ddf59cb058c58-2720540f-38400-17435351a4f102%22%7D; zg_a59132f133104d7ab2ae3427e4d17684=%7B%22sid%22%3A%201598620506718%2C%22updated%22%3A%201598620508592%2C%22info%22%3A%201598620506729%2C%22superProperty%22%3A%20%22%7B%5C%22st_timestamp%5C%22%3A%20%5C%2220200828211505%5C%22%2C%5C%22st_position%5C%22%3A%20%5C%22STSYQB001%5C%22%7D%22%2C%22platform%22%3A%20%22%7B%7D%22%2C%22utm%22%3A%20%22%7B%7D%22%2C%22referrerDomain%22%3A%20%22%22%2C%22cuid%22%3A%20%22%22%7D; tianjincity=11|110; tianjin_ip=0; clientid=98|0; WT_FPC=id=2b1a34c72990e0b6c0e1593322192777:lv=1599062578527:ss=1599062578484; MUT_S=android9; login_type=06; u_account=15602968825; ecs_acc=bAo4DcevUzIJ1zWCPt9uL0zCME6jCe2T98/k17CGh/C2hpuzPcnCFTcSNRrXtlV1LAz3Q6KzWWB7HME22Tc1tz/xpnzIOAj3O6KS0bRz0iZMk14AkT2g77r0/6lvdfXqi+AV+7DGc8LUk30Vsq2SU36W3wrb9AurcWMBsA0cUuw=; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32174fc1e297e76168e140c26396069181bc19f39ca8ece5b80e2cfadbc45627c3d5e42f6a298bcd425afb9013620d49508; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NDc0ZGY4ZDI2OTNhNjAyMjQ1ZDQ0ODQzYmI0ZjQ2NzEzZDE3ZTMzYmRmZDJhZjViODcxZmViYjNlZmIyMjNmNDUyYWYzZjQ5YWM2ZjZiZDY4OGZlMGFmZWQ5OWJjMTA5NTcyYmRmMmIzOGMwOGM1Njk3YjQ5ODdkNWFhMWIxMDIzIiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxNTYwMjk2ODgyNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6ImY3YzkwYTQyOTM5YjZlYjkyZjQ3NzQ5OWNiOTIwNjU0In0.gAuhrTrKUsmkK5Xxx7kJe4VaF_SUnIbzYm9QeSq1Yg8; city=051|540|90488771|-99; acw_tc=7169ab2115991545298372324eee2ae73fe596c156f93f5e33dd823bbc; lbscitycode=570; SHAREJSESSIONID=1EBECA78A5286DB0515406255DEBDA10; _pk_ref.1.183e=%5B%22%22%2C%22%22%2C1599154533%2C%22https%3A%2F%2Fm.client.10010.com%2FmobileService%2Fquery%2FtickeListNew.htm%22%5D; _pk_ses.1.183e=1; BIGipServerPOOL_SJYYT2_KHDJTZY_80=252512778.16927.0000; _pk_id.1.183e=720a5b787e36fe11.1598602814.15.1599154596.1599154533.1598677930","自己联通"));
        //bkf联通
        userList.add(new User("Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:17620426435};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}","MUT_S=android9; login_type=06; u_account=17620426435; city=051|540|90157638|-99; ecs_acc=XLSi/RWeuJGWfbOOk+pUeygQFule+gh6dFI5f2TBM8j0Os7uqFtktPoEcct+yd+nsZ+Z+2euHQyLJGPxgmOiQMCMO85ESeU1LSlAWK0BzxhwzaVCJw+/A9cviaUCCTMSZjEO2X0G0s2E7lYr43i+VPrW3eHoG7gITlZ2fwBVsnM=; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32174fc1e297e76168e140c26396069181bba0a41c2470944d3db93b2e4c74222a19028673ded8136ccec13bf96da8d9faa; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NDE2NDJkNzgxZDc0ZTk3YjIyZjQ0ZGFlMzRlMzA3YTY4M2MzMDIzZWJmMzQwOWZiN2Q2OTNlY2EzYWIwODdhOGM2NDM2ODI2N2Y4NmZjOWVkYjY2MzNlODdlNDc4OGJhZDZiNDk0Zjg5YzJjYjBjMTY5NzE5Yzc1MWJkNjZmZGFhIiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxNzYyMDQyNjQzNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6Ijg3ZmEyOTk4MmZjMzdjNDE5MTMxY2Q5MDIwZjUwODRhIn0.ycmHKsofUa6xFS3-E1vp6U05mOm8D54N-Hnk2AjXwqc; _uop_id=npfauth_fgt4n5dj0babbdb9274f94778d379e9b03c36d8fomeuiler; MUT_V=android%406.002","bkf联通"));
        //pwj联通
        userList.add(new User("Mozilla/5.0 (Linux; Android 9; MI 6 Build/PKQ1.190118.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@6.002,desmobile:13172466596};devicetype{deviceBrand:Xiaomi,deviceModel:MI 6}","MUT_S=android9; login_type=06; u_account=13172466596; city=051|540|90488771|-99; ecs_acc=QPUvGEpmU9sDb1kVJyk/4qf5WaStShpgbr4qzGE1uaWWoiGLl1A4peUTGcK0RypL2rP2pxf1c4jnRAEeCGfNqspHRoe5MfV+9LMWq4T75G8o7lBbxnQQJYOnl8QBH+eRtqYGuDwLQNbi1dIL42Iu/8pC2mCbcNlBMUZVDrBVn94=; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32174fc1e297e76168e140c26396069181b05a98b6a4ab7a6972f2d539a1178f72d3ac020a9ac2f706315bb5ebddac998e2; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc2M2Q4ZWU2M2U4ZjAxYTk5OGEzNTQ2NDcwMDFmNzI3NGI0ZjJhNzMyNTBjNjcwNTI5YzA1NjEyMmM3MzcyMWIwNTI0YTA3NWZjYmZkYzUyMDdmMzgyMzY5NzJmYmI1YTUxMWQ2YWM0MGFkNDY2OGM3NWU0MDE2YWJmMzJlZjE4M2MzZDk0MWM3ZjJjNTRlMDRhNzhiYzEyMmRmZDY0ZDY0IiwidmVyc2lvbiI6IjAwIn0=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxMzE3MjQ2NjU5NiIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6ImQ0YzY3NjY3ODQ4ZGVmNjczMTlhNzI2YmVlM2FmZWY5In0.AUifSUOpqvZptIszGOLk21jZN6j2KPuqM19WF2mJb80","pwj联通"));
        //电信副卡1
        //userList.add(new User("Mozilla/5.0 (Linux; Android 9; Redmi 6 Pro Build/PKQ1.180917.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@7.0500,desmobile:19107525015};devicetype{deviceBrand:Xiaomi,deviceModel:Redmi 6 Pro};{yw_code:0}","MUT_S=android9; devicedId=868585041206351; login_type=06; u_account=19107525015; city=051|570|-99|-99; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32182d7341bbf9a6268b3bd12762d4142a950ce43ec238c5f7a4f0598a3632f3db1cd2343c5e7db42876fcd324c7c772bba; gipgeo=51|510; SHOP_PROV_CITY=; tianjincity=11|110; tianjin_ip=0; mallcity=51|540; MUT_V=android%407.0500; MUT=88914307-7dfa-4558-879d-fe3a7a241fc3; UID=BiH7kCx26pA0IY7pdZ8zDefjBIZWY2Ym; yw_login=7137e77933058957d0d04aaabf7311caf05592677ea5d099ac579f1fa7375b5998135238b1022952566fb2fc77c08563d31285821de468912b17ae6de2a4847d5b703343f0e1f7efccedecf321268aeb; clientid=98|0; WT_FPC=id=26545516ee7ae58d9471599155806581:lv=1599156021591:ss=1599155806581; acw_tc=0ed737a715991598520624430ef043fb8e3533bcaf1873ddb1c2761b67; ecs_acc=aBRQu7/rfMTD0zZA51EFhLqiKaSqnUfyDIUic/LIKZDuy+iLdOhBL6EPoAufPwUzmZWDS5tFzEmHEzQQdEDAKx7Aqqj6DszCY5r75N4XnlVjkghIuYs5VhekmYs/Pd01TN6FiJeqrFPgkfMvGgfQN6UcIptXpGXkCb3JTKQqlJU=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxOTEwNzUyNTAxNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NzAiLCJpZCI6IjYwZTgzYjQzMzJhZTgwNzExZGEwMzBjMzkwNGEyNjQ5In0.Tar4AdeJKFlGDazP21bnRFMuP-9ppA8zsnXGXf57c_4; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc1YzI0M2IwNGY1ZTcxYjY1YmE3ZDMwY2U4OWIyZGQ4ZjBhMDFlM2YwNzUxNmFlODhkYTE0NWE1ZTQ4OTg0NjBhZThhY2Q0ZTJjMDBiNjc5MzVkNjM4Y2QxNTQ0ZjhhMTZmOTkxMGMxZTVmMTUzMWZjNTg3N2Q5Y2RlMDliNTVlOGZjNTA0NjMyM2QwMjI5MWNhMjUzNTVmZmFmNWEyNDhmOTk5MjNlZmFiZmMwYjEzNzkwMGU5YjY1NjRhZWNkNzYiLCJ2ZXJzaW9uIjoiMDAifQ==","自己电信副卡1"));
        //电信主卡
        //userList.add(new User("Mozilla/5.0 (Linux; Android 9; Redmi 6 Pro Build/PKQ1.180917.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@7.0500,desmobile:19107525015};devicetype{deviceBrand:Xiaomi,deviceModel:Redmi 6 Pro}","MUT_S=android9; devicedId=868585041206351; login_type=06; u_account=19107525015; city=051|570|-99|-99; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32182d7341bbf9a6268b3bd12762d4142a950ce43ec238c5f7a4f0598a3632f3db1cd2343c5e7db42876fcd324c7c772bba; gipgeo=51|510; SHOP_PROV_CITY=; tianjincity=11|110; tianjin_ip=0; mallcity=51|540; MUT_V=android%407.0500; MUT=88914307-7dfa-4558-879d-fe3a7a241fc3; UID=BiH7kCx26pA0IY7pdZ8zDefjBIZWY2Ym; yw_login=7137e77933058957d0d04aaabf7311caf05592677ea5d099ac579f1fa7375b5998135238b1022952566fb2fc77c08563d31285821de468912b17ae6de2a4847d5b703343f0e1f7efccedecf321268aeb; clientid=98|0; WT_FPC=id=26545516ee7ae58d9471599155806581:lv=1599156021591:ss=1599155806581; acw_tc=0ed737a715991598520624430ef043fb8e3533bcaf1873ddb1c2761b67; ecs_acc=aBRQu7/rfMTD0zZA51EFhLqiKaSqnUfyDIUic/LIKZDuy+iLdOhBL6EPoAufPwUzmZWDS5tFzEmHEzQQdEDAKx7Aqqj6DszCY5r75N4XnlVjkghIuYs5VhekmYs/Pd01TN6FiJeqrFPgkfMvGgfQN6UcIptXpGXkCb3JTKQqlJU=; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxOTEwNzUyNTAxNSIsInBybyI6IjA1MSIsImNpdHkiOiI1NzAiLCJpZCI6IjYwZTgzYjQzMzJhZTgwNzExZGEwMzBjMzkwNGEyNjQ5In0.Tar4AdeJKFlGDazP21bnRFMuP-9ppA8zsnXGXf57c_4; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc1YzI0M2IwNGY1ZTcxYjY1YmE3ZDMwY2U4OWIyZGQ4ZjBhMDFlM2YwNzUxNmFlODhkYTE0NWE1ZTQ4OTg0NjBhZThhY2Q0ZTJjMDBiNjc5MzVkNjM4Y2QxNTQ0ZjhhMTZmOTkxMGMxZTVmMTUzMWZjNTg3N2Q5Y2RlMDliNTVlOGZjNTA0NjMyM2QwMjI5MWNhMjUzNTVmZmFmNWEyNDhmOTk5MjNlZmFiZmMwYjEzNzkwMGU5YjY1NjRhZWNkNzYiLCJ2ZXJzaW9uIjoiMDAifQ==","自己电信主卡2"));
        //爸移动
        //userList.add(new User("Mozilla/5.0 (Linux; Android 9; Redmi 6 Pro Build/PKQ1.180917.001; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/84.0.4147.125 Mobile Safari/537.36; unicom{version:android@7.0500,desmobile:19107525015};devicetype{deviceBrand:Xiaomi,deviceModel:Redmi 6 Pro}","MUT_S=android9; devicedId=5b71337544b242e6a574663e05048510; login_type=06; u_account=13728857366; city=051|540|-99|-99; ecs_acc=f8e8H4Nk8y09LGtMpZ1FsYY7TLjvzOkG/RKnTjyhDEcurzHJ4zEMI8OtcG4hkyy46Mxp1l62Kj/EhwSX7L/EeUmdQkdGLhlQ8lQBXiofuVwLdK9nv86spqzUduegKt/T7VVOes0VlOqesOhv5AvV014JO6hpUk5DtWviyNvQk6E=; cw_mutual=6ff66a046d4cb9a67af6f2af5f74c32182d7341bbf9a6268b3bd12762d4142a932538682bb99955b1f26fff14c710439d2898ebf71cac0ea69863ce3a060d234; jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJtb2JpbGUiOiIxMzcyODg1NzM2NiIsInBybyI6IjA1MSIsImNpdHkiOiI1NDAiLCJpZCI6ImU1ZDIyNWY3ZTY4Y2VmZjI2MzQwNWY0Y2YzZWNlN2I4In0.Zt60Z_UeHV9-nKfkUxMLpIWFVy6fUsysZgpLWlq68E8; yw_login=7137e77933058957d0d04aaabf7311ca933ce03eccb244d253862c44e73790539276be5d53fc1d79c5f83505387a2cc7; ecs_token=eyJkYXRhIjoiNWVjMzc1MzNjZDhiYmJhZTEwYWQ1NDMzYjIyNDJkODc1YzI0M2IwNGY1ZTcxYjY1YmE3ZDMwY2U4OWIyZGQ4ZjI4NjIzMmYxYzZlZjJjMjBlMTEwODQ4MTlmNzJhOGNiMmI4OGYwOWVhMTZlZDY1OTY4ZmEzNzRhYTQzM2NmYWQ1ZDIxNTczNWZlYjBlMGY2N2U5YzcwOGM1ZmRkZmMxZjU5N2I1NzBlODg0YjhjNTM0OWIzZDFmZDcwMmNkNDViODQyZWY1NDFiNmY3NDMxMDZiODc2MDk2NmVjZTFkMTUiLCJ2ZXJzaW9uIjoiMDAifQ==; clientid=98|0","爸移动"));

    }
    public static HttpClient httpClient = HttpClient.newBuilder().build();
    public static void main(String[] args) throws Exception {
        userSetter();
        //定时
        //timerTaskCall();

        //主程序：
        whileSend();



    }
    public static void whileSend(){
        for (String good:goodIdList1) {
            String body= "reqdata="+JSON.toJSONString(new reqdata(good));
            //System.out.println(good);
            for (User u:userList) {
                try {
                    new HttpThread(u,body).start();
                    //send_request(u);
                }catch (Exception e){
                    e.printStackTrace();
                }
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
}
