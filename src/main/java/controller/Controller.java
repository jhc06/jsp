package controller;

import handler.CommandHandler;
import handler.NullHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class Controller  extends HttpServlet {
    // <커맨드, 핸들러인스턴스> 매핑 정보 저장
    private Map<String, CommandHandler> commandHandlerMap =
            new HashMap<>();

    // ***** getInitParameter()
    // - 초기화 파라미터 파일 로드.
    // ***** getServletContext()
    // - Returns a reference to the ServletContext in which the caller is executing.
    // ***** getRealpath(String path)
    // - Returns a String containing the real path for a given virtual path.
    // ***** hasNext()
    // - 가져올 객체가 있으면 true 리넡 없으면 false 리턴
    // ***** getRealPath(String path)
    // - 서버상 물리적파일경로 리턴. 이하는 c://dev/project/jsp/web/command.properties 를 리턴.
    // ***** The ServletContext.getRealPath() is intented to convert a web content path
    // (the path in the expanded WAR folder structure on the server's disk file system)
    // to an absolute disk file system path.
    public void init() throws ServletException {
        // 초기화 파라미터 중 web.xml에 매핑된 초기 파라미터 값으로  command.properties의 path를 받아옴
        String configFile = getInitParameter("configFile"); //
        Properties prop = new Properties();
        String configFilePath = getServletContext().getRealPath(configFile); //
        try (FileReader fis = new FileReader(configFilePath)) {
            prop.load(fis);
        } catch (IOException e) {
            throw new ServletException(e);
        }
        // 프로퍼티 내의 키값으로 set 생성 후 iterator 생성
        // ***** properties.keySet()
        // - set클래스를 이용해서 key값만 저장한다.
        // ***** set.iterator()
        // - set 내용물의 반복자를 리턴한다.
        Iterator keyIter = prop.keySet().iterator(); // Iterator inteface
        // iterator를 사용하여 매핑값을 얻어서 인스턴스객체화 시킨 후 MAP에 value 값으로 넣는다.
        while (keyIter.hasNext()) { // .hasNext -> iterator가 element를 가지고 있을 때는 true.
            // 키값으로 command 변수에 대입 후 MAP에 key값으로 넣는다.
            String command = (String) keyIter.next();
            String handlerClassName = prop.getProperty(command); //프로퍼티 내에 key(command)값에 매핑되는 value 값을 리턴한다.
            try {
                Class<?> handlerClass = Class.forName(handlerClassName); // class 명에 따르는 object를 리턴.
                CommandHandler handlerInstance =
                        (CommandHandler) handlerClass.newInstance(); // class object를 인스턴스화시켜준다.
                commandHandlerMap.put(command, handlerInstance); // 프로퍼티 내 key값과 value에 해당하는 인스턴스 객체를 맵에 넣는다.
            } catch (ClassNotFoundException | InstantiationException
                     | IllegalAccessException e) {
                throw new ServletException(e);
            }
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    // ***** getRequestURI()
    // - Returns the part of this request's URL from the protocol name up to the query string
    // in the first line of the HTTP request. The web container does not decode this String.
    // ***** getContextPath()
    // - Returns the portion of the request URI that indicates the context of the request.
    // The context path always comes first in a request URI.
    // The path starts with a "/" character but does not end with a "/" character.
    // For servlets in the default (root) context, this method returns "".
    // The container does not decode this string.
    // ***** indexOf(String str, int fromIndex)
    // - Returns the index within this string of the first occurrence of
    // the specified substring.
    // ***** getRequestDispatcher(String path);
    // - Returns a RequestDispatcher object that acts as a wrapper for
    // the resource located at the given path.
    // A RequestDispatcher object can be used to forward a request to the resource
    // or to include the resource in a response. The resource can be dynamic or static.
    // ***** dispatcher.forward(request, response)
    // - Forwards a request from a servlet to another resource (servlet, JSP file,
    // or HTML file) on the server.
    // For a RequestDispatcher obtained via getRequestDispatcher(), the ServletRequest object
    // has its path elements and parameters adjusted to match the path of the target resource.

    private void process(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String command = request.getRequestURI();
        if (command.indexOf(request.getContextPath()) == 0) {
            command = command.substring(request.getContextPath().length()); // contextPath 경로 문자 수만큼 앞에서 제외한 나머지 문자열을 리턴한다.
        }
        CommandHandler handler = commandHandlerMap.get(command); //주소와 매핑한 handler객체값 리턴.
        if (handler == null) {
            handler = new NullHandler();
        }
        String viewPage = null;
        try {
            viewPage = handler.process(request, response); // handler로 요청한 기능을 수행한다.
        } catch (Throwable e) {
            throw new ServletException(e);
        }
        if (viewPage != null) {
            RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
            dispatcher.forward(request, response); // req와rep를 viewPage로 포워딩;
        }
    }
    // ************ 입력된 주소값에서 contextPath를 제외한 String값과 프로퍼티의 키값과 비교 후
    // 일치하는 CommandHandler 객체값을 가져와서 객체 값 내 process 메소드를 실행 후 viewpage 주소를 리턴한다. 이를 RequestDispatcher로
}
