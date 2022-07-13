package filter.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

public class SomeRequestWrapper extends HttpServletRequestWrapper {
    private Map<String, String[]> parameterMap = null;


    // 만드는 이유는?. 없으면 syntex 에러가 뜨는 이유는?
    public SomeRequestWrapper(HttpServletRequest request) {
        super(request);
        parameterMap = new HashMap<>(request.getParameterMap());
    }

}
