package socket;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;

public class HttpRequestBySocket {
    private static final String HOST = "api.openweathermap.org";
    private static final int PORT = 443;

    public static void main(String[] args) {
        //1. SSL 연결(https 통신)을 위한 소켓 생성
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();

        // try with resource 구문으로 자원에 문제 발생시 자동 해제
        try (Socket socket = factory.createSocket(HOST, PORT);

             OutputStream outputStream = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(outputStream, true);

             InputStream inputStream = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            //2. HTTP 요청 헤더 작성

            //발급받은 api key: 77e0c6a8e1b2afb8fab5dbcbe18bf360
            writer.println("GET /data/2.5/weather?q=seoul&appid=77e0c6a8e1b2afb8fab5dbcbe18bf360 HTTP/1.1");
            writer.println(STR."Host: \{HOST}");
            writer.println("User-Agent: Chrome");
            writer.println("Connection: close");
            writer.println();

            //3. 응답 읽기 및 데이터 출력
            StringBuilder response = new StringBuilder();
            String line;
            boolean isContent = false;
            while ((line = reader.readLine()) != null) {
                if(!isContent) {
                    if(line.isEmpty()) {
                        isContent = true;
                    }
                    continue;
                }
                response.append(line);
            }
            System.out.println(response);

        } catch (IOException e) {
            System.out.println(STR."IO Exception: \{e.getMessage()}");
        }
    }
}
