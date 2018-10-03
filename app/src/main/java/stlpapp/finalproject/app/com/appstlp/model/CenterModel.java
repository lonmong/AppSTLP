package stlpapp.finalproject.app.com.appstlp.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CenterModel {
	private Center center;
	private List<Center> centerList;

	public CenterModel(){
		center = new Center();
	}
	public CenterModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try{
			center = gson.fromJson(jsonResponse, Center.class);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			TypeToken<List<Center>> token = new TypeToken<List<Center>>(){};
			centerList = gson.fromJson(jsonResponse, token.getType());
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.center);
	}

	public Center getCenter() {
		return center;
	}

	public void setCenter(Center center) {
		this.center = center;
	}

	public List<Center> getCenterList() {
		return centerList;
	}

	public void setCenterList(List<Center> centerList) {
		this.centerList = centerList;
	}

	public static class Center implements Parcelable {

		private String telcenter;
		private String namecenter;
		private String emailcenter;
		private String addresscenter;

		public Center(){

		}

		public String getTelcenter() {
			return telcenter;
		}

		public void setTelcenter(String telcenter) {
			this.telcenter = telcenter;
		}

		public String getNamecenter() {
            try {
                this.namecenter = URLDecoder.decode(namecenter,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
			return namecenter;
		}

		public void setNamecenter(String namecenter) {
			this.namecenter = namecenter;
		}

		public String getEmailcenter() {
			return emailcenter;
		}

		public void setEmailcenter(String emailcenter) {
			this.emailcenter = emailcenter;
		}

		public String getAddresscenter() {
			return addresscenter;
		}

		public void setAddresscenter(String addresscenter) {
			this.addresscenter = addresscenter;
		}
		private List<Staff> staffList = new ArrayList<>();
		public List<Staff> getStaffList() {
			return staffList;
		}
		public void setStaffList(List<Staff> staffList) {
			this.staffList = staffList;
		}
		private List<RequestForHelp> requestForHelpsList = new ArrayList<>() ;
		public List<RequestForHelp> getRequestForHelpsList() {
			return requestForHelpsList;
		}

		public void setRequestForHelpsList(List<RequestForHelp> requestForHelpsList) {
			this.requestForHelpsList = requestForHelpsList;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {
			parcel.writeString(telcenter);
			parcel.writeString(namecenter);
			parcel.writeString(emailcenter);
			parcel.writeString(addresscenter);
			parcel.writeList(staffList);
			parcel.writeList(requestForHelpsList);
		}
		protected Center(Parcel in) {
			telcenter = in.readString();
			namecenter = in.readString();
			emailcenter = in.readString();
			addresscenter = in.readString();
			staffList = in.createTypedArrayList(Staff.CREATOR);
			requestForHelpsList = in.createTypedArrayList(RequestForHelp.CREATOR);
		}

		public static final Creator<Center> CREATOR = new Creator<Center>() {
			@Override
			public Center createFromParcel(Parcel in) {
				return new Center(in);
			}

			@Override
			public Center[] newArray(int size) {
				return new Center[size];
			}
		};
	}

}
