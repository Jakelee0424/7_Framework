
const loginFrm = document.getElementById("loginFrm");

const memberEmail = document.querySelector("#loginFrm input[name='memberEmail']");
const memberPw = document.querySelector("#loginFrm input[name='memberPw']");

if(loginFrm != null){
    // 로그인 시도를 할 때
    loginFrm.addEventListener("submit", e => {

        // 이메일이 입력되지 않은 경우
        // 문자열.trim() : 문자열 좌우 공백 제거
        if(memberEmail.value.trim().length == 0){
            alert("이메일을 입력해주세요.");

            memberEmail.value = ""; // 잘못 입력된 값(공백) 제거
            memberEmail.focus(); // 이메일 input태그에 초점을 맞춤

            e.preventDefault(); // (기본이벤트 제거 : 제출 못하게하기)
            return; 
        }


        // 비밀번호가 입력되지 않은 경우
        if(memberPw.value.trim().length == 0){
            alert("비밀번호를 입력해주세요.");

            memberPw.value = ""; // 잘못 입력된 값(공백) 제거
            memberPw.focus(); // 이메일 input태그에 초점을 맞춤

            e.preventDefault(); // 제출 못하게하기
            return; 
        }


    });
}

// fetch API : 웹 브라우저에서 서버로 HTTP 요청을 하게해주는 최신 인터페이스

/*
 * fetch(url)
 * .then(response => response.json() / response.text())
 * .then(data => console.log(data))
 * .catch(error => console.log(error)) 
 * 
 * 첫번째 .then() 함수는 서버 요청에 대한 응답이 왔을 때 실행됨
 *  -> 응답되는 데이터가 반환되는 값이 단순 자료형 1개면 text(), 객체면 json()으로 파싱 후 두번째 .then으로 넘겨줌
 * 
 * 두번째 .then() 함수는 response.json()/text() 상황에 맞게 파싱되면 실행
 * 
 * => 파싱된 데이터를 전달받으면 로직에 맞게 가공
 * 
 */

// 닉네임이 일치하는 회원의 전화번호 조회

const inputNickname = document.getElementById("inputNickname");
const btn1 = document.getElementById("btn1");
const result1 = document.getElementById("result1");

btn1.addEventListener("click", () =>{

    //fetch api를 이용해서 ajax
    //get방식 요청(쿼리스트링으로 파라미터 전달)

    // promise : 비동기 함수 호출 또는 연산이 완료되었을 때 
    //           이후에 처리할 함수나 에러를 처리하기 위한 함수를 설정하는 모듈
    //          -> 비동기 연산의 최종 결과 객체

    fetch("/selectMemberTel?nickname=" + inputNickname.value)
    .then(resp => resp.text())
    .then(data => {
        // 데이터 가공
        result1.innerText = data;
    })
    .catch(err => console.log(err));

})



// fetch() API를 이용한 post 방식 요청

const inputEmail = document.getElementById("inputEmail");
const btn2 = document.getElementById("btn2");
const result2 = document.getElementById("result2");

btn2.addEventListener("click", () => {

    result2.innerHTML = "";

    // JSON.stringify : JS객체를 JSON으로
    // JSON.parse : JSON을 JS객체로

    // JSON : Javascript 객체 문법으로, 구조화된 데이터를 표현하기 위한 문자기반 표준 포맷
    //        서버 <-> 클라이언트가 데이터를 주고받을때 사용

    //post방식 : 옵션 필요
    fetch("/selectMember", {
        method : "post",  // 방식
        headers : {"Content-Type" : "application/json"}, 
                    // Content-Type : 요청 보내는 자원을 명시 
                    // (js 객체를 json형식으로 만들어 파라미터로 전달)
        body : JSON.stringify({"email" : inputEmail.value})
    })
    .then(resp => resp.json())
    .then(member => {
        result2.innerHTML += "주소 : " + `${member.memberAddress}<br>`
        result2.innerHTML += "유저 닉네임 : " + `${member.memberNickname}<br>` 
        result2.innerHTML += "유저 전화번호 : " + `${member.memberTel}<br>` 
        result2.innerHTML += "가입일 : " + `${member.enrollDate}<br>` 
        console.log(member);
    })
    .catch(err => {console.log(err)})



});


// --------------------------------------------------------------------------------------------------

// 웹소켓 테스트
// 1. sockJS 라이브러리 추가 (CDN으로 jsp에 추가)

// 2. sockJS를 이용하여 클라이언트용 웹소켓 객체 생성
// let testSock = new SockJS("/testSock");

// function sendMessage(name, str) {

//     // 매개변수를 JS 객체에 저장
//     let obj = {}; // 비어있는 객체

//     obj.name = name; // 객체에 일치하는 키가 없으면 자동 추가
//     obj.str = str;

//     console.log(obj);

//     testSock.send(JSON.stringify(obj)); // JS객체 -> JSON 변환 -> send;

// }

// // 3. 웹소켓 객체(testSock)가 서버로부터 전달받은 메세지가 있는 경우
// testSock.onmessage = e => {

//     // e.data : 전달받은 객체

//     obj = JSON.parse(e.data) // JSON객체 -> JS객체

//     console.log(`보낸사람 : ${obj.name}  /  내용 : ${obj.str}`);
// }


//----------------------------------------------------------------------------------
// 자바스크립트로 쿠키 얻어오기

function getcookie(key){  

    const cookies = document.cookie;

    console.log(cookies)

}
