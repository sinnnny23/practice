package practice.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.*;
import java.util.List;
import java.util.Map;

public class HttpServerCon {
    private static CookieManager cookieManager;

    public HttpServerCon() {
        cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);
    }
    public static void main(String[] args) {
        HttpServerCon serverCon = new HttpServerCon();

        try {
            URL url = new URL("https://member.swipepay.co.kr/Command");

            //Http 연결 생성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            //json 데이터 생성
            String data = "{\"command\":\"SPASwipeLogin\", \"usid\":\"sinnnny23\", \"passwd\":\"tlsltlsl@23\"}";

            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            int status = conn.getResponseCode();


            //서버로부터 획득한 쿠키 정보를 출력한다.
            Map<String, List<String>> headerFields = conn.getHeaderFields();
            List<String> cookiesHeader = headerFields.get("Set-Cookie");
            if (cookiesHeader != null && !cookiesHeader.isEmpty()) {
                for (String cookie : cookiesHeader) {
                    if (!cookie.equals("")) {
                        HttpCookie parsedCookie = HttpCookie.parse(cookie).get(0);
                        cookieManager.getCookieStore().add(url.toURI(),parsedCookie);
                        System.out.println("추가된 쿠키헤더 값: " + parsedCookie);
                    }
                }
            }

            //쿠키 저장소 가져오기
            CookieStore cookieStore = cookieManager.getCookieStore();
            //저장소에 있는 모든 쿠키 가져오기
            List<HttpCookie> cookies = cookieStore.getCookies();
            //가져온 쿠키 출력
            for (HttpCookie cookie : cookies) {
                System.out.println("쿠키 이름: " + cookie.getName());
                System.out.println("쿠키 값: " + cookie.getValue());
                System.out.println("도메인: " + cookie.getDomain());
                System.out.println("경로: " + cookie.getPath());
                //필요한 경우 쿠키의 기타 속성도 출력할 수 있습니다.
                //예: 만료일자, 보안여부 등
            }

            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Failed : HTTP error code : " + status);
            }
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));


            String output;
            System.out.println("Output from Server ... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            br.close();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
