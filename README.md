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
  - Ajax를 이용하여 입력된 데이터를 비동기 방식으로 전송합니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/webapp/WEB-INF/views/login/joinForm.jsp#L54)
  - SQL의 Count() 함수를 이용하여, 비교할 데이터의 중복을 확인합니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/resources/mybatis/mappers/member-mapper.xml#L27)

- **이메일 인증** 
  - JavaMailSender 클래스를 활용하며, Random 객체에 의해 생서된 6자리의 랜덤 숫자가 발송됩니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/0e56d2ea9c4beb816738dbc6b687d2c148257aaa/src/main/java/com/ez/swm/login/controller/MemberController.java#L103)
  - 발송 시 생성된 인증번호와 사용자가 입력한 데이터를 비교합니다. 결과에 따라 안내 문구와 색상이 변경됩니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/0e56d2ea9c4beb816738dbc6b687d2c148257aaa/src/main/webapp/WEB-INF/views/login/joinForm.jsp#L105)
  
### 4.2 로그인
<img src="https://user-images.githubusercontent.com/87680461/146746413-36c1dd7a-f6b5-485d-a7b1-e75379b7a35a.png"  width="500" height="500"/>  

  
- **아이디 저장** :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/db2bb6602ba1ca3d2befa296fb3182c0080e3935/src/main/webapp/WEB-INF/views/login/loginForm.jsp#L16)
  - 저장할 사용자의 아이디와 기간을 Cookie에 저장합니다.
  - 아이디 저장을 해제할 경우, 쿠키의 유효기간을 현재 날짜의 전날로 설정하여 쿠키를 소멸시킵니다.

- **자동 로그인**
  - Cookie에 식별이 가능한 사용자의 세션 ID와 자동로그인 유지 기간인 90일을 저장합니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/f9ae05db17569aceae81b8cfce0001693921cb2c/src/main/java/com/ez/swm/login/controller/MemberController.java#L153)
  - Cookie에 저장된 두가지 데이터를 로그인 테이블과 세션에 저장합니다.
  - Interceptor의 Prehandle 메소드를 통해, 세션 ID에 해당하는 사용자의 회원 정보를 가져오는 로직을 로그인과 로그아웃 페이지를 제외한 모든 경로에서 실행되게 합니다.
:pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/f9ae05db17569aceae81b8cfce0001693921cb2c/src/main/java/com/ez/swm/login/interceptor/AuthenticationInterceptor.java#L24)

### 4.3 마이페이지
<img src="https://user-images.githubusercontent.com/87680461/148223451-c5a3d890-3500-4760-ad02-29a55d57c077.JPG"  width="500" height="500"/>  
  </br>
  
- **내가 개설, 가입한 스터디 확인**
  - 사용자 고유 번호에 따른 작성된 글을 보여줍니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/f9ae05db17569aceae81b8cfce0001693921cb2c/src/main/resources/mybatis/mappers/myPage-mapper.xml#L60)
  - 개설 및 참여한 스터디가 존재할 시, 리스트를 가져옵니다.
  - 존재하지 않을 시엔, 존재하지 않다는 화면을 보여줍니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/f9ae05db17569aceae81b8cfce0001693921cb2c/src/main/webapp/WEB-INF/views/myPage/myJoinStudy.jsp#L19)
  
- **1:1 문의**
  - 사용자 고유 번호에 따른 작성된 문의글을 보여줍니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/f9ae05db17569aceae81b8cfce0001693921cb2c/src/main/resources/mybatis/mappers/myPage-mapper.xml#L26)
  - 작성글을 클릭 하면, 상세보기 페이지로 이동합니다. :pushpin: [코드 확인](https://github.com/saltNam/SWM/blob/f9ae05db17569aceae81b8cfce0001693921cb2c/src/main/java/com/ez/swm/myPage/controller/MyPageController.java#L209)
  - 작성한 문의글이 존재할 시, 리스트를 가져옵니다.
  - 존재하지 않을 시엔, 존재하지 않다는 화면을 보여줍니다. 
  
</div>
</details>

</br>
