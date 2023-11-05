import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;

@WebFilter("/time")
public class TimezoneValidateFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String timezoneParameter = request.getParameter("timezone");

        if (timezoneParameter != null && !isValidTimezone(timezoneParameter)) {
            response.setContentType("text/html");
            response.getWriter().write("Invalid timezone");
            ((HttpServletResponse) response).setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            chain.doFilter(request, response);
        }
    }

    private boolean isValidTimezone(String timezone) {
        try {
            ZoneId.of(timezone);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
}
