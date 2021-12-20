# :pushpin: SWM - Study With Me
>스터디모임 개설 및 참여 서비스  
>게시판의 기능을 포함하여 제작  
>결제, 지도와 같은 Open API 사용

</br>

## 1. 제작 기간 & 참여 인원
- 2021년 06월 - 08월
- 팀 프로젝트 (6명)

</br>

## 2. 사용 기술
  - Java 1.8
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

## 4. 담당 파트 (login / mypage 폴더)
  - 회원가입 
  - 로그인, 로그아웃
  - 아이디 저장, 자동 로그인
  - 사용자 마이페이지 ( 정보 수정, 탈퇴 / 개설한 스터디 모임, 작성글 확인 / 1:1 문의 )
 
<details>
<summary><b>핵심 기능 설명 펼치기</b></summary>
<div markdown="1">

### 4.1 회원 가입
<img src="https://user-images.githubusercontent.com/87680461/145826566-9b5dd98e-4f47-40c5-a386-ba949084d9c9.png"  width="500" height="500"/>

- **정규식,  Null 값 체크** :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/c9354604ddf878ceb96cf459b62284b523114f7b/src/main/webapp/WEB-INF/views/login/joinForm.jsp#L149)
  - 화면단에서, 사용자가 입력한 데이터를 정규식 값과 비교합니다.
  - 지정한 정규식과 다르거나 Null 값인 경우, 에러 메세지를 띄웁니다.
 
- **아이디, 닉네임 중복 확인** 
  - URL의 모양새인 경우, 컨텐츠를 등록하는 POST 요청을 비동기로 날립니다.
  - Ajax를 이용하여 입력된 데이터를 비동기 방식으로 전송합니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/webapp/WEB-INF/views/login/joinForm.jsp#L54)
  - SQL의 Count() 함수를 이용하여, 비교할 데이터의 중복을 확인합니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/resources/mybatis/mappers/member-mapper.xml#L27)

- **이메일 인증** 
  - JavaMailSender 클래스를 활용하며, Random 객체에 의해 생서된 6자리의 랜덤 숫자가 발송됩니다. :pushpin: [코드 확인] (https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/java/com/ez/swm/login/controller/MemberController.java#L102)
  - 발송 시 생성된 인증번호와 사용자가 입력한 데이터를 비교합니다. 결과에 따라 안내 문구와 색상이 변경됩니다. :pushpin: [코드 확인] (https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/webapp/WEB-INF/views/login/joinForm.jsp#L105)
  
### 4.2 로그인
<img src="https://user-images.githubusercontent.com/87680461/146746413-36c1dd7a-f6b5-485d-a7b1-e75379b7a35a.png"  width="500" height="500"/>  
  
  - **아이디 저장** :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/webapp/WEB-INF/views/login/loginForm.jsp#L16)
  - Cookie를 이용하여, 
  - 지정한 정규식과 다르거나 Null 값인 경우, 에러 메세지를 띄웁니다.
  
  
  
  
  
  
  
  
</div>
</details>

</br>
