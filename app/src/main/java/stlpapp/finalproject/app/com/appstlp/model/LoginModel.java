package stlpapp.finalproject.app.com.appstlp.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;


public class LoginModel {
    private Login login;
    private List<Login> loginList;

    public LoginModel(){
        login = new Login();
    }
    public LoginModel(String jsonResponse) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();
        try {
            login = gson.fromJson(jsonResponse, Login.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (login == null) {
                TypeToken<List<Login>> token = new TypeToken<List<Login>>() {
                };
                loginList = gson.fromJson(jsonResponse, token.getType());
            }
        }
    }


    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.login);
    }
    public Login getLogin() {
        return login;
    }

    public List<Login> getLoginList() {
        return loginList;
    }
}
