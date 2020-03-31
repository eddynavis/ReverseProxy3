import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.http.*;
import javax.servlet.*;


public class TestServlet extend HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //check header in request for destination url
        String desinationUrl = request.getRequestURL().toString();
        //get parameterString
        String parameterString = request.getQueryString();
        //append parameters if they exist
        if (parameterString.compareTo("") != 0) {
            desinationUrl = desinationUrl + "?" + parameterString;
        }
        HttpServletResponse webResponse;
        //check if we have cached it
        if (Cache.contains(desinationUrl)) {
            //In cache, set response to saved data
            webResponse = Cache.getResponse(desinationUrl);
        }
        else {
            //Not cached, forward on request
            webResponse = forwardTraffic(desinationUrl, "GET", request);
        }
        //Convert cached or forwarded response to be sent back
        buildResponse(webResponse, response);
    }

    private HttpServletResponse forwardTraffic(String address, String requestMethod, HttpServletRequest request) throws IOException {
        //build url, might have missed some url encoding
        URL url = new URL(address);
        //open connection so we can start building a request
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        //set as GET or POST, etc
        connection.setRequestMethod(requestMethod);

        return null;
    }

    private void buildResponse(HttpServletResponse source, HttpServletResponse destination) {
        //it seems like this needs to be copied over field by field (headers, body, etc)
    }
}
