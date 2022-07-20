package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandHandler {
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception;
}
// process 메소드를 구현 클래스를 이용
// 어떤 구현 클래스를 이용할 것인지는 properties 문서를 load해서 Controller가 결정/배분한다.