package JDBC;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.StringReader;
import java.util.Properties;

// StringReader 클래스와 StringWriter 클래스는 문자열을 스트림에 기록하거나 읽어낼 때 사용하는 클래스입니다.
public class DBCPInitListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String poolConfig = sce.getServletContext().getInitParameter("poolConfig");
        Properties prop = new Properties();
        try {
            prop.load(new StringReader(poolConfig));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadJDBCDriver(prop);
        initConnectionPool(prop);
    }

    private void

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
