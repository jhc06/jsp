package JDBC;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.io.StringReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// StringReader 클래스와 StringWriter 클래스는 문자열을 스트림에 기록하거나 읽어낼 때 사용하는 클래스입니다.

@WebListener // 웹 컨테이너 구동 시 contextInitialized를 실행해서 커넥션 풀을 초기화한다.
public class DBCPInitListener  implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String poolConfig =
                sce.getServletContext().getInitParameter("poolConfig");
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
        String driverClass = prop.getProperty("jdbcDriver");
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
            // 커넥션풀의 커넥션 검사 쿼리
            String validationQuery = prop.getProperty("validationQuery");
            if(validationQuery != null&& !validationQuery.isEmpty()){
                poolableConnectionFactory.setValidationQuery(validationQuery);
            }

//            poolableConnectionFactory.setValidationQuery("select 1");
            GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig<>();
            poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
            poolConfig.setTestWhileIdle(true);
            //최소 유휴 커넷션, 최대 크기 를 설정 프로퍼티에서 가져온다.
            int minIdle = getIntProperty(prop, "minIdle", 5);
            poolConfig.setMinIdle(minIdle);
            int maxTotal = getIntProperty(prop, "maxTotal", 50);
            poolConfig.setMaxTotal(maxTotal);

            GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, poolConfig);
            poolableConnectionFactory.setPool(connectionPool);

            Class.forName("org.apache.commons.dbcp2.PoolingDriver");
            PoolingDriver driver = (PoolingDriver) DriverManager.getDriver("jdbc:apache:commons:dbcp:");
            String poolName = prop.getProperty("poolName");
            driver.registerPool(poolName, connectionPool);

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private int getIntProperty(Properties prop, String propName, int defaultValue){
        String value = prop.getProperty(propName);
        if(value == null) return defaultValue;
        return Integer.parseInt(value);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
