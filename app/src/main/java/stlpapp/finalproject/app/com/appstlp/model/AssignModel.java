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

public class AssignModel {
	private Assign assign;
	private List<Assign> assignList;

	public AssignModel(){
		assign = new Assign();
	}
	public AssignModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			assign = gson.fromJson(jsonResponse, Assign.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (assign == null) {
				TypeToken<List<Assign>> token = new TypeToken<List<Assign>>() {
				};
				assignList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.assign);
	}

	public Assign getAssign() {
		return assign;
	}

	public void setAssign(Assign assign) {
		this.assign = assign;
	}

	public List<Assign> getAssignList() {
		return assignList;
	}

	public void setAssignList(List<Assign> assignList) {
		this.assignList = assignList;
	}

	public static class Assign implements Parcelable{
		private int assignid;
		private String factperson;
		private String factfathermother;
		private String forlegalopinion;
		private String personstatus;
		private int statusassign;
		public Assign(){

		}

		public int getAssignid() {
			return assignid;
		}

		public void setAssignid(int assignid) {
			this.assignid = assignid;
		}

		public String getFactperson() {
			try {
				this.factperson = URLDecoder.decode(factperson,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return factperson;
		}

		public void setFactperson(String factperson) {
			this.factperson = factperson;
		}

		public String getFactfathermother() {
			try {
				this.factfathermother = URLDecoder.decode(factfathermother,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return factfathermother;
		}

		public void setFactfathermother(String factfathermother) {
			this.factfathermother = factfathermother;
		}

		public String getForlegalopinion() {
			try {
				this.forlegalopinion = URLDecoder.decode(forlegalopinion,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return forlegalopinion;
		}

		public void setForlegalopinion(String forlegalopinion) {
			this.forlegalopinion = forlegalopinion;
		}

		public String getPersonstatus() {
			try {
				this.personstatus = URLDecoder.decode(personstatus,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return personstatus;
		}

		public void setPersonstatus(String personstatus) {
			this.personstatus = personstatus;
		}

		public int getStatusassign() {
			return statusassign;
		}

		public void setStatusassign(int statusassign) {
			this.statusassign = statusassign;
		}

		private RequestForHelp requestforhelp = new RequestForHelp();

		public RequestForHelp getRequestforhelp() {
			return requestforhelp;
		}

		public void setRequestforhelp(RequestForHelp requestforhelp) {
			this.requestforhelp = requestforhelp;
		}

		private Staff staff = new Staff();

		public Staff getStaff() {
			return staff;
		}

		public void setStaff(Staff staff) {
			this.staff = staff;
		}

		protected Assign(Parcel in) {
			assignid = in.readInt();
			factperson = in.readString();
			factfathermother = in.readString();
			forlegalopinion = in.readString();
			personstatus = in.readString();
			statusassign = in.readInt();
			requestforhelp = in.readParcelable(RequestForHelp.class.getClassLoader());
			staff = in.readParcelable(Staff.class.getClassLoader());
		}

		public static final Creator<Assign> CREATOR = new Creator<Assign>() {
			@Override
			public Assign createFromParcel(Parcel in) {
				return new Assign(in);
			}

			@Override
			public Assign[] newArray(int size) {
				return new Assign[size];
			}
		};

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {
			parcel.writeInt(assignid);
			parcel.writeString(factperson);
			parcel.writeString(factfathermother);
			parcel.writeString(forlegalopinion);
			parcel.writeString(personstatus);
			parcel.writeInt(statusassign);
			parcel.writeParcelable(requestforhelp, i);
			parcel.writeParcelable(staff, i);
		}
	}
	
}
