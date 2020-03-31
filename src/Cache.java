import java.util.HashMap;
import javax.servlet.http.*;
import javax.servlet.*;

//looking at this more closely it seems I will need to save the fields one at a time
public class Cache {
    private static HashMap<String, HttpServletResponse> data = new HashMap<>();

    public static void addPageToCache(String url, HttpServletResponse response) {
        data.put(url, response);
    }

    public static boolean contains(String url) {
        return data.containsKey(url);
    }

    public static HttpServletResponse getResponse(String url) {
        return data.get(url);
    }
}
