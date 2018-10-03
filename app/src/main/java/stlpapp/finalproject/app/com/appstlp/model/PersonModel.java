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

public class PersonModel {
    private Person person;
    private List<Person> personList;

    public PersonModel(){
        person = new Person();
    }
    public PersonModel(String jsonResponse) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();
        try {
            person = gson.fromJson(jsonResponse, Person.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (person == null) {
                TypeToken<List<Person>> token = new TypeToken<List<Person>>() {
                };
                personList = gson.fromJson(jsonResponse, token.getType());
            }
        }
    }

    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.person);
    }
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Person> getPersonList() {
        return personList;
    }
}
