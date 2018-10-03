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

public class WitnessModel {
	private Witness witness;
	private List<Witness> witnessList;

	public WitnessModel(){
		witness = new Witness();
	}
	public WitnessModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			witness = gson.fromJson(jsonResponse, Witness.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (witness == null) {
				TypeToken<List<Witness>> token = new TypeToken<List<Witness>>() {
				};
				witnessList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.witness);
	}

	public Witness getWitness() {
		return witness;
	}

	public void setWitness(Witness witness) {
		this.witness = witness;
	}

	public List<Witness> getWitnessList() {
		return witnessList;
	}

	public void setWitnessList(List<Witness> witnessList) {
		this.witnessList = witnessList;
	}

	public static class Witness implements Parcelable {
		private int witnessid;
		private String namewitness;
		private String relationship;
		private int statusbealive;
		private String addresswitness;
		public Witness(){

		}

		public int getWitnessid() {
			return witnessid;
		}

		public void setWitnessid(int witnessid) {
			this.witnessid = witnessid;
		}

		public String getNamewitness() {
            try {
                this.namewitness = URLDecoder.decode(namewitness,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
			return namewitness;
		}

		public void setNamewitness(String namewitness) {
			this.namewitness = namewitness;
		}

		public String getRelationship() {
			return relationship;
		}

		public void setRelationship(String relationship) {
			this.relationship = relationship;
		}

		public int getStatusbealive() {
			return statusbealive;
		}

		public void setStatusbealive(int statusbealive) {
			this.statusbealive = statusbealive;
		}

		public String getAddresswitness() {
			try {
				this.addresswitness = URLDecoder.decode(addresswitness,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return addresswitness;
		}

		public void setAddresswitness(String addresswitness) {
			this.addresswitness = addresswitness;
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
			parcel.writeInt(witnessid);
			parcel.writeString(namewitness);
			parcel.writeString(relationship);
			parcel.writeInt(statusbealive);
			parcel.writeString(addresswitness);
			parcel.writeParcelable(this.stateleeeperson, i);
		}
		protected Witness(Parcel in) {
			witnessid = in.readInt();
			namewitness = in.readString();
			relationship = in.readString();
			statusbealive = in.readInt();
			addresswitness = in.readString();
			this.stateleeeperson = in.readParcelable(StatelessPerson.class.getClassLoader());
		}

		public static final Creator<Witness> CREATOR = new Creator<Witness>() {
			@Override
			public Witness createFromParcel(Parcel in) {
				return new Witness(in);
			}

			@Override
			public Witness[] newArray(int size) {
				return new Witness[size];
			}
		};
	}
	
}
