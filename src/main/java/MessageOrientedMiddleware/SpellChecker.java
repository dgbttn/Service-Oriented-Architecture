package MessageOrientedMiddleware;

import com.mashape.unirest.http.*;
import com.mashape.unirest.http.exceptions.UnirestException;

public class SpellChecker {

    static String correct(String text) throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get("https://montanaflynn-spellcheck.p.rapidapi.com/check/")
                .queryString("text", text)
                .header("x-rapidapi-host", "montanaflynn-spellcheck.p.rapidapi.com")
                .header("x-rapidapi-key", "a71385bb37msh492367d37b3ce0fp12f888jsnc8b8ac60eb9f")
                .asJson();
        return response.getBody().getObject().getString("suggestion");
    }

    public static void main(String[] args) throws UnirestException {
        System.out.println(correct("This sentnce has some probblems."));
    }
}
