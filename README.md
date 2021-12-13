# :pushpin: SWM - Study With Me
>스터디모임 개설 및 참여 서비스  
>게시판의 기능을 포함하여 제작  
>결제, 지도와 같은 Open API 사용

</br>

## 1. 제작 기간 & 참여 인원
- 2021년 06월 - 08월
- 팀 프로젝트

</br>

## 2. 사용 기술
  - Java 8
  - JavaScript
  - Spring Framework 4.0
  - Maven
  - Oracle
  - HTML
  - CSS
  - jQuery
  - MyBatis

</br>

## 3. ERD 설계
![](https://user-images.githubusercontent.com/87680461/145823346-f97bc969-013e-4317-8a4d-c6265dd9f22c.png)

</br>

## 4. 담당 파트
  - 로그인
  - 회원가입
  - 아이디 저장, 자동 로그인
  - 사용자 마이페이지 ( 정보 수정, 탈퇴 / 개설한 스터디 모임, 작성글 확인 / 1:1 문의 )
 
<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1 회원 가입
<img src="https://user-images.githubusercontent.com/87680461/145826566-9b5dd98e-4f47-40c5-a386-ba949084d9c9.png"  width="500" height="500"/>

- **정규식,  Null 값 체크** :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/c9354604ddf878ceb96cf459b62284b523114f7b/src/main/webapp/WEB-INF/views/login/joinForm.jsp#L149)
  - 화면단에서, 사용자가 입력한 데이터를 정규식 값과 비교합니다.
  - 지정한 정규식과 다르거나 Null 값인 경우, 에러 메세지를 띄웁니다 .
 
- **Axios 비동기 요청** :pushpin: [코드 확인]()
  - URL의 모양새인 경우, 컨텐츠를 등록하는 POST 요청을 비동기로 날립니다.


</div>
</details>

</br>
