package com.example.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

//localhost:8088/list -> localhost는 내아이피  , 포트번호 는 8088
@Controller // 웹브라우저랑 통신할수있게해줌

public class TestController {
    //함수(메서드)는 호출을 해야만 사용가능  , @RequestMapping-> 스프링이 호출해주는 함수
    @RequestMapping("/list")  //("/list")-> 스프링한테 호출하고 싶은 함수를 브라우저를 통해 알려주기 위해 필요. 실행할 함수 식별자라고생각하면댐
    @ResponseBody //  @ResponseBody 리턴값을 브라우저에 문자열(json 이라는형태)로 보여줘라 라는뜻
    public String list() {
        return "게시물 목록";

    }


    @RequestMapping("/test1")  // 함수이름은 상관이없다. 일치시켜주는게 좋다.
    @ResponseBody
    public Integer test1() {
        return 100;

    }

    @RequestMapping("/test2")
    @ResponseBody

    public ArrayList<String> test2() {
        ArrayList<String> list = new ArrayList<>();
        list.add("바나나");
        list.add("딸기");
        list.add("오렌지");
        return list;
    }


    @RequestMapping("/test3")
    @ResponseBody
    public Person test3() {
        return new Person("홍길동", 20);  //롬복 깔기
    }

    @RequestMapping("/test4")
    @ResponseBody
    public ArrayList<Person> test4() {

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("홍길동", 20));
        list.add(new Person("김철수", 30));
        list.add(new Person("이영희", 40));

        return list;
    }

    @RequestMapping("/test5")
    @ResponseBody
    public String test5() {
//        String html = "<h1>hello</h1>";
////        html+="<ul>";
////        html+="<li>apple</li>";
////        html+="<li>banana</li>";
////        html+="<ul>";
////        return html;

        //혹시라도 내가 문자열을 이어붙이고 싶을때는  위에 방법 보다 StringBuilder 라는 객체만들어주고 append 해서 추가할거만들어주면 객체에 쌓임
        StringBuilder sb = new StringBuilder();
        sb.append("<h1>hello</h1>"); // 큰제목 숫자커질수록 작아짐
        sb.append("<ul>"); // 마크업된 형태로 나옴
        sb.append("<li>apple</li>"); //목록
        sb.append("<li>banana</li>");
        sb.append("</ul>");
        return sb.toString();
    }


    @RequestMapping("/test6")
    @ResponseBody
    public String test6() {
        StringBuilder sb = new StringBuilder();
        // 구구단 2 단 출력
        for (int i = 1; i <= 9; i++) {
            sb.append(2 + " X " + i + " = " + (2 * i) + "<br>"); //-> /n 대신 html 에는 br 줄바꿈
        }

        return sb.toString();


    }

    @RequestMapping("/test7")
    @ResponseBody
    public String test7(@RequestParam("num1") int num1, @RequestParam("num2") int num2) {
        int result = num1 + num2;
        return "result : " + result;

    }

    @RequestMapping("/test8")
    @ResponseBody
    public String test8(@RequestParam("name") String name) {
        return name + "님 반갑습니다 !";
    }


    @RequestMapping("/test9")
    @ResponseBody
    public String test9(@RequestParam("dan") int dan) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 9; i++) {
            sb.append(dan + " X " + i + " = " + (dan * i) + "<br>");
        }


        return sb.toString();
    }

    @RequestMapping("/test10")
    @ResponseBody
    public String test10(@RequestParam("dan") int dan, @RequestParam("limit") int limit) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= limit; i++) {
            sb.append(dan + " X " + i + " = " + (dan * i) + "<br>");
        }

        return sb.toString();
    }

    @RequestMapping("/test11")
    @ResponseBody
    public String test11(@RequestParam("name") String name, @RequestParam("age") int age) {
        return age + "살 " + name + "입니다.";
    }

    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam("loginId") String loginId, @RequestParam("password") String password) {
        String userId = "hong";
        String userPw = "1234";

        if (loginId.equals(userId) && password.equals(userPw)) {
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }

    }

    @RequestMapping("/loginView")
    @ResponseBody
    public String loginView() { //여러줄입력할땐 """ 세번
        return """ 
                <form action="http://localhost:8088/login">
                    <div>아이디<input type="text" name="loginId"></div>
                    <div>비밀번호<input type="password" name="password"></div>
                    <div><input type="submit" value="로그인"></div>
                </form>
                """;
    }

    @RequestMapping("/loginView2")
    // ResponseBody를 붙이지 않으면 template으로 view를 대체한다는 의미
    // 타임리프 라이브러리 추가해야함.
    public String loginView2() {
        return "login";
    }

    @RequestMapping("/html-test")
    public String htmlTest() {
        return "test";
    }


    @RequestMapping("/data")
    public String dataSend(Model model) {
        ArrayList<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("orange");

        Person person = new Person("홍길동" , 20);
        model.addAttribute("myNumber", 19);
        model.addAttribute("myName0", "홍길동");
        model.addAttribute("fruitList", list);
        model.addAttribute("person" , person);  //"person" 이라는 이름으로 넘어감
        return "test";



    }
}

@Getter //, 우린이제 롬복사용한다 그러므로 이렇게@getter ,setter
@Setter
@AllArgsConstructor //앞으로 class 만들땐 private 해주기  ,모든매개변수를 갖는 생성자를 만들수있음
class Person {
    private String name;
    private int age;

}
