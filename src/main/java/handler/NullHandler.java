package handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NullHandler implements CommandHandler{
    // response.sendError(int errorStatusCode)
    // - Sends an error response to the client using the specified status code
    // and clearing the buffer.
    // SC_NOT_FOUND
    // - Status code (404) indicating that the requested resource is not available.
    @Override
    public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
        res.sendError(HttpServletResponse.SC_NOT_FOUND);
        return null;
    }
}
