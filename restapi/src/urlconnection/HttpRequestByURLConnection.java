package urlconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class HttpRequestByURLConnection {
    public static void main(String[] args) throws IOException{
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=77e0c6a8e1b2afb8fab5dbcbe18bf360");
            //1. HttpURLConnection 타입으로 강제 형변환을 통한 URL 객체 생성
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //2. HTTP 요청 헤더 작성
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setReadTimeout(30000);

            //3. HTTP 상태코드 받아오기
            int responseCode = connection.getResponseCode();
            System.out.println(STR."HTTP 상태코드: \{responseCode}");

            if(responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("커넥션 에러 발생");
                System.out.println(connection.getResponseMessage());
                return;
            }

            //4. 입출력 스트림 생성 및 데이터 읽어오기
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();

        } catch (MalformedURLException e) {
            System.out.println(STR."URL Malformed: \{e.getMessage()}");
        }
    }
}
