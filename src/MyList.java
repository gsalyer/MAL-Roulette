import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

public class MyList
{
    private JsonElement json;

    public MyList(String user)
    {
        try
        {
            //TODO add more options (e.g. TV/movies, on hold/ rewatch completed, manga)
            String encodedURL = URLEncoder.encode(user, "utf-8");
            String apiURL = "https://api.jikan.moe/v3/user/" + encodedURL + "/animelist/plantowatch";
            URL listURL = new URL(apiURL);

            InputStream is = listURL.openStream();
            InputStreamReader isr = new InputStreamReader(is);

            JsonParser parser = new JsonParser();
            json = parser.parse(isr);
        }
        catch(java.net.MalformedURLException mue)
        {
            System.out.println("Malformed URL");
        }
        catch(java.io.IOException ioe)
        {
            System.out.println("IO Error");
        }
    }

    public static int rand(int min, int max)
    {
        if (min >= max)
            throw new IllegalArgumentException("Invalid range");
        return new Random().nextInt(max - min + 1) + min;
    }

    public JsonArray getList()
    {
        return json.getAsJsonObject().get("anime").getAsJsonArray();
    }

    public JsonElement getRand()
    {
        int last = getList().size();
        return getList().get(rand(0, last)).getAsJsonObject();
    }

    public String getTitle(JsonElement je)
    {
        return je.getAsJsonObject().get("title").getAsString();
    }
}
