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

public class StatelessPersonModel {
    private StatelessPerson statelessPerson;
    private List<StatelessPerson> statelessPersonList;

    public StatelessPersonModel(){
        statelessPerson = new StatelessPerson();
    }
    public StatelessPersonModel(String jsonResponse) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();
        statelessPerson = gson.fromJson(jsonResponse, StatelessPerson.class);
        if (statelessPerson == null) {
            TypeToken<List<StatelessPerson>> token = new TypeToken<List<StatelessPerson>>() {
            };
            statelessPersonList = gson.fromJson(jsonResponse, token.getType());
        }

    }


    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.statelessPerson);
    }

    public StatelessPerson getStatelessPerson() {
        return statelessPerson;
    }

    public void setStatelessPerson(StatelessPerson statelessPerson) {
        this.statelessPerson = statelessPerson;
    }

    public List<StatelessPerson> getStatelessPersonList() {
        return statelessPersonList;
    }

    public void setStatelessPersonList(List<StatelessPerson> statelessPersonList) {
        this.statelessPersonList = statelessPersonList;
    }
}
