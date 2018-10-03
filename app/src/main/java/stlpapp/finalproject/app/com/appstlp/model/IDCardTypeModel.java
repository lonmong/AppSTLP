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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IDCardTypeModel {
	private IDCardType idCardType;
	private List<IDCardType> idCardTypeList;

	public IDCardTypeModel(){
		idCardType = new IDCardType();
	}
	public IDCardTypeModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			idCardType = gson.fromJson(jsonResponse, IDCardType.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (idCardType == null) {
				TypeToken<List<IDCardType>> token = new TypeToken<List<IDCardType>>() {
				};
				idCardTypeList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.idCardType);
	}

	public IDCardType getIdCardType() {
		return idCardType;
	}

	public void setIdCardType(IDCardType idCardType) {
		this.idCardType = idCardType;
	}

	public List<IDCardType> getIdCardTypeList() {
		return idCardTypeList;
	}

	public void setIdCardTypeList(List<IDCardType> idCardTypeList) {
		this.idCardTypeList = idCardTypeList;
	}

	public static class IDCardType implements Parcelable {
		private String idcardno;
		private String idcardcall;
		private String idcardmean;
		private String idcardjob;
		private String benefitsfromgovern;

		public IDCardType(){

		}
		public String getIdcardno() {
			return idcardno;
		}

		public void setIdcardno(String idcardno) {
			this.idcardno = idcardno;
		}

		public String getIdcardcall() {
			return idcardcall;
		}

		public void setIdcardcall(String idcardcall) {
			this.idcardcall = idcardcall;
		}

		public String getIdcardmean() {
			return idcardmean;
		}

		public void setIdcardmean(String idcardmean) {
			this.idcardmean = idcardmean;
		}

		public String getIdcardjob() {
			return idcardjob;
		}

		public void setIdcardjob(String idcardjob) {
			this.idcardjob = idcardjob;
		}

		public String getBenefitsfromgovern() {
			return benefitsfromgovern;
		}

		public void setBenefitsfromgovern(String benefitsfromgovern) {
			this.benefitsfromgovern = benefitsfromgovern;
		}
		private List<StatelessPerson> statelesspersonList = new ArrayList<>();
		public List<StatelessPerson> getStatelesspersonList() {
			return statelesspersonList;
		}
		public void setStatelesspersonList(List<StatelessPerson> statelesspersonList) {
			this.statelesspersonList = statelesspersonList;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {
			parcel.writeString(idcardno);
			parcel.writeString(idcardcall);
			parcel.writeString(idcardmean);
			parcel.writeString(idcardjob);
			parcel.writeString(benefitsfromgovern);
			parcel.writeTypedList(statelesspersonList);
		}
		protected IDCardType(Parcel in) {
			idcardno = in.readString();
			idcardcall = in.readString();
			idcardmean = in.readString();
			idcardjob = in.readString();
			benefitsfromgovern = in.readString();
			statelesspersonList = in.createTypedArrayList(StatelessPerson.CREATOR);
		}

		public static final Creator<IDCardType> CREATOR = new Creator<IDCardType>() {
			@Override
			public IDCardType createFromParcel(Parcel in) {
				return new IDCardType(in);
			}

			@Override
			public IDCardType[] newArray(int size) {
				return new IDCardType[size];
			}
		};

	}
}
