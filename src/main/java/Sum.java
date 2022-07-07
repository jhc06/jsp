import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class Sum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at:").append(request.getContextPath());
        response.setContentType("text/html");
        PrintWriter uu = response.getWriter();
        int sum = 0;
        for(int i=0; i<10; i++){
            sum = sum +1;
        }
        uu.println("<html>");
        uu.println("<head><title>sum</title></head>");
        uu.println("<body>SUM=");
        uu.println(sum);
        uu.println("</body>");
        uu.println("</html>");

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
