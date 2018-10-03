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

public class StaffModel {
    private Staff staff;
    private List<Staff> staffList;

    public StaffModel(){
        staff = new Staff();
    }
    public StaffModel(String jsonResponse) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                return new Date(json.getAsJsonPrimitive().getAsLong());
            }
        }).create();
        try {
            staff = gson.fromJson(jsonResponse, Staff.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (staff == null) {
                TypeToken<List<Staff>> token = new TypeToken<List<Staff>>() {
                };
                staffList = gson.fromJson(jsonResponse, token.getType());
            }
        }
    }


    public String toJSONString() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this.staff);
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
