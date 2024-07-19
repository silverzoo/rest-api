## Java REST API 통신
Java 코드를 작성하여 Open Weather Map API에 제공하는 "서울"의 날씨 데이터를 JSON 포맷으로 불러오는 실습 ➟ 
[Open Weather Map API](https://openweathermap.org/api)

### Socket 사용
1. SSL 연결(https 통신)을 위한 소켓 생성
2. HTTP 요청 헤더 작성
3. 응답 읽기 및 데이터 출력

### URLConnection 사용
1. URL 객체 생성 및 HttpURLConnection 획득
2. HTTP 헤더 설정
3. HTTP 상태 코드 받아오기
4. 입출력 스트림 생성 및 데이터 읽어오기
