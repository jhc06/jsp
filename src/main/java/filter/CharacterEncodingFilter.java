package filter;


import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    private String encoding;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        req.setCharacterEncoding(encoding);
        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        encoding = config.getInitParameter("encoding");
        if (encoding == null) {
            encoding = "UTF-8";
        }
    }

    @Override
    public void destroy() {
    }
}
