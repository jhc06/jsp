package JDBC;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnectionFactory;

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
            prop.load(new StringReader(poolConfig)); //web.xml에 있는 poolConfig 초기파라미터 내용을 로드.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        loadJDBCDriver(prop); // properties 내 jdbcdirver value 값을 클래스로 만들어놈.
        initConnectionPool(prop);
    }

    private void loadJDBCDriver(Properties prop){
        String driverClass = prop.getProperty("jdbcdriver");
        try{
            Class.forName(driverClass);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException("fail to load JDBC Driver", ex); // RuntimeException(message, cause)
        }
    }
    private void initConnectionPool(Properties prop){
        try{
            String jdbcUrl = prop.getProperty("jdbcUrl");
            String username = prop.getProperty("dbUser");
            String pw = prop.getProperty("dbPass");

            ConnectionFactory connectionFactory =
                    new DriverManagerConnectionFactory(jdbcUrl, username, pw);
            PoolableConnectionFactory poolableConnectionFactory =
                    new PoolableConnectionFactory(connectionFactory, null);
            String validationQuery = prop.getProperty("validationQuery");
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
