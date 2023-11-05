import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

    @WebServlet("/time")
    public class TimeServlet extends HttpServlet {
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html");             // Встановлюємо тип вмісту та кодування для відповіді
            response.setHeader("Refresh", "1");   // для оновлення сторінки
            PrintWriter out = response.getWriter();           // для відповіді

            String timezoneParameter = request.getParameter("timezone");                // параметтер
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // форматер
            out.println("<html><body>");                                                      // початок відповіді
            ZoneId utcZone;                                   //https://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html

            if (timezoneParameter == null || timezoneParameter.isEmpty()) {
                utcZone = ZoneId.of("UTC");
                LocalDateTime utcTime = LocalDateTime.now(utcZone);
                String formattedUtcTime = utcTime.format(formatter);
                out.println("<h2>" + formattedUtcTime + " UTC</h2>");
            } else{
                out.println("<h2>Value parameter 'timezone'= " + timezoneParameter + "</h2>");
                utcZone = ZoneId.of(timezoneParameter);
                LocalDateTime utcTime = LocalDateTime.now(utcZone);
                String formattedUtcTime = utcTime.format(formatter);
                out.println("<h2>Time with parameter: " + formattedUtcTime +" "+ utcZone + "</h2>");
            }

            out.println("</body></html>");
        }

    }

