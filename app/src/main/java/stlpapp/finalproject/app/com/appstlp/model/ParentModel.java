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

public class ParentModel {
	private Parent parent;
	private List<Parent> parentList;

	public ParentModel(){
		parent = new Parent();
	}
	public ParentModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			parent = gson.fromJson(jsonResponse, Parent.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (parent == null) {
				TypeToken<List<Parent>> token = new TypeToken<List<Parent>>() {
				};
				parentList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.parent);
	}

	public Parent getParent() {
		return parent;
	}

	public void setParent(Parent parent) {
		this.parent = parent;
	}

	public List<Parent> getParentList() {
		return parentList;
	}

	public void setParentList(List<Parent> parentList) {
		this.parentList = parentList;
	}

	public static class Parent implements Parcelable {
		private int parentid;
		private String name;
		private int statusbealive;
		private String birthday;
		private String idcard;
		private String ethnic;
		private String nationality;
		private String datecomethai;
		private String address;
		private int statusparent;

		public Parent(){

		}

		public int getParentid() {
			return parentid;
		}

		public void setParentid(int parentid) {
			this.parentid = parentid;
		}

		public String getName() {
			try {
				this.name = URLDecoder.decode(name,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getStatusbealive() {
			return statusbealive;
		}

		public void setStatusbealive(int statusbealive) {
			this.statusbealive = statusbealive;
		}

		public String getBirthday() {
			try {
				this.birthday = URLDecoder.decode(birthday,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getIdcard() {
			return idcard;
		}

		public void setIdcard(String idcard) {
			this.idcard = idcard;
		}

		public String getEthnic() {
			return ethnic;
		}

		public void setEthnic(String ethnic) {
			this.ethnic = ethnic;
		}

		public String getNationality() {
			return nationality;
		}

		public void setNationality(String nationality) {
			this.nationality = nationality;
		}

		public String getDatecomethai() {
			try {
				this.datecomethai = URLDecoder.decode(datecomethai,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return datecomethai;
		}

		public void setDatecomethai(String datecomethai) {
			this.datecomethai = datecomethai;
		}

		public String getAddress() {
			try {
				this.address = URLDecoder.decode(address,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public int getStatusparent() {
			return statusparent;
		}

		public void setStatusparent(int statusparent) {
			this.statusparent = statusparent;
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
			parcel.writeInt(parentid);
			parcel.writeString(name);
			parcel.writeInt(statusbealive);
			parcel.writeString(birthday);
			parcel.writeString(idcard);
			parcel.writeString(ethnic);
			parcel.writeString(nationality);
			parcel.writeString(datecomethai);
			parcel.writeString(address);
			parcel.writeInt(statusparent);
			parcel.writeParcelable(this.stateleeeperson, i);
		}

		protected Parent(Parcel in) {
			parentid = in.readInt();
			name = in.readString();
			statusbealive = in.readInt();
			birthday = in.readString();
			idcard = in.readString();
			ethnic = in.readString();
			nationality = in.readString();
			datecomethai = in.readString();
			address = in.readString();
			statusparent = in.readInt();
			this.stateleeeperson = in.readParcelable(StatelessPerson.class.getClassLoader());
		}
		public static final Creator<Parent> CREATOR = new Creator<Parent>() {
			@Override
			public Parent createFromParcel(Parcel in) {
				return new Parent(in);
			}

			@Override
			public Parent[] newArray(int size) {
				return new Parent[size];
			}
		};
	}
	
}
