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

/**
 * Created by NickyIT14 on 20 มิ.ย. 2561.
 */

public class RequestForHelpModel {
    private RequestForHelp requestForHelp;
    private List<RequestForHelp> requestForHelpList;

    public RequestForHelpModel(){
        requestForHelp = new RequestForHelp();
    }
    public RequestForHelpModel(String jsonResponse) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();
        try {
            requestForHelp = gson.fromJson(jsonResponse, RequestForHelp.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (requestForHelp == null) {
                TypeToken<List<RequestForHelp>> token = new TypeToken<List<RequestForHelp>>() {
                };
                requestForHelpList = gson.fromJson(jsonResponse, token.getType());
            }
        }
    }


    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.requestForHelp);
    }

    public RequestForHelp getRequestForHelp() {
        return requestForHelp;
    }

    public void setRequestForHelp(RequestForHelp requestForHelp) {
        this.requestForHelp = requestForHelp;
    }

    public List<RequestForHelp> getRequestForHelpList() {
        return requestForHelpList;
    }

    public void setRequestForHelpList(List<RequestForHelp> requestForHelpList) {
        this.requestForHelpList = requestForHelpList;
    }
}
