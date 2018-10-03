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

public class AddressModel{
	private Address address;
	private List<Address> addressList;

	public AddressModel(){
		address = new Address();
	}
	public AddressModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			address = gson.fromJson(jsonResponse, Address.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (address == null) {
				TypeToken<List<Address>> token = new TypeToken<List<Address>>() {
				};
				addressList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.address);
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public static class Address implements Parcelable {
		private int addressid;
		private String detailaddress;
		private String fromyears;
		private String toyears;
		private int homestatus;
		private int statusaddress;
		public Address(){

		}


		public int getAddressid() {
			return addressid;
		}

		public void setAddressid(int addressid) {
			this.addressid = addressid;
		}

		public String getDetailaddress() {
			try {
				this.detailaddress = URLDecoder.decode(detailaddress,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return detailaddress;
		}

		public void setDetailaddress(String detailaddress) {
			this.detailaddress = detailaddress;
		}

		public String getFromyears() {
			return fromyears;
		}

		public void setFromyears(String fromyears) {
			this.fromyears = fromyears;
		}

		public String getToyears() {
			return toyears;
		}

		public void setToyears(String toyears) {
			this.toyears = toyears;
		}

		public int getHomestatus() {
			return homestatus;
		}

		public void setHomestatus(int homestatus) {
			this.homestatus = homestatus;
		}

		public int getStatusaddress() {
			return statusaddress;
		}

		public void setStatusaddress(int statusaddress) {
			this.statusaddress = statusaddress;
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
			parcel.writeInt(addressid);
			parcel.writeString(detailaddress);
			parcel.writeString(fromyears);
			parcel.writeString(toyears);
			parcel.writeInt(homestatus);
			parcel.writeInt(statusaddress);
			parcel.writeParcelable(this.stateleeeperson, i);
		}
		protected Address(Parcel in) {
			addressid = in.readInt();
			detailaddress = in.readString();
			fromyears = in.readString();
			toyears = in.readString();
			homestatus = in.readInt();
			statusaddress = in.readInt();
			this.stateleeeperson = in.readParcelable(StatelessPerson.class.getClassLoader());
		}

		public static final Creator<Address> CREATOR = new Creator<Address>() {
			@Override
			public Address createFromParcel(Parcel in) {
				return new Address(in);
			}

			@Override
			public Address[] newArray(int size) {
				return new Address[size];
			}
		};
	}
}
