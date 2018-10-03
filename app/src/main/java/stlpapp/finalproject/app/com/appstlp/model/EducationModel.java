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
import java.util.Date;
import java.util.List;

public class EducationModel {
	private Education education;
	private List<Education> educationList;

	public EducationModel(){
		education = new Education();
	}
	public EducationModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			education = gson.fromJson(jsonResponse, Education.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (education == null) {
				TypeToken<List<Education>> token = new TypeToken<List<Education>>() {
				};
				educationList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.education);
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

	public List<Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}

	public static class Education implements Parcelable {
		private int educationid;
		private String educationdetails;

		public Education(){

		}
		public int getEducationid() {
			return educationid;
		}

		public void setEducationid(int educationid) {
			this.educationid = educationid;
		}

		public String getEducationdetails() {
			try {
				this.educationdetails = URLDecoder.decode(educationdetails,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return educationdetails;
		}

		public void setEducationdetails(String educationdetails) {
			this.educationdetails = educationdetails;
		}

		private StatelessPerson stateleeeperson = new StatelessPerson();

		public StatelessPerson getStateleeeperson() {
			return stateleeeperson;
		}
		public void setStateleeeperson(StatelessPerson stateleeeperson) {
			this.stateleeeperson = stateleeeperson;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {
			parcel.writeInt(educationid);
			parcel.writeString(educationdetails);
			parcel.writeParcelable(this.stateleeeperson, i);
		}
		protected Education(Parcel in) {
			educationid = in.readInt();
			educationdetails = in.readString();
			this.stateleeeperson = in.readParcelable(StatelessPerson.class.getClassLoader());
		}

		public static final Creator<Education> CREATOR = new Creator<Education>() {
			@Override
			public Education createFromParcel(Parcel in) {
				return new Education(in);
			}

			@Override
			public Education[] newArray(int size) {
				return new Education[size];
			}
		};
	}
	
}
