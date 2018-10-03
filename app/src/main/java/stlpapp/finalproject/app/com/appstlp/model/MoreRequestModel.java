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
import java.util.Date;
import java.util.List;

public class MoreRequestModel {
	private MoreRequest moreRequest;
	private List<MoreRequest> moreRequestList;

	public MoreRequestModel(){
		moreRequest = new MoreRequest();
	}
	public MoreRequestModel(String jsonResponse) {
		Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
			public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
					throws JsonParseException {
				return new Date(json.getAsJsonPrimitive().getAsLong());
			}
		}).create();
		try {
			moreRequest = gson.fromJson(jsonResponse, MoreRequest.class);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (moreRequest == null) {
				TypeToken<List<MoreRequest>> token = new TypeToken<List<MoreRequest>>() {
				};
				moreRequestList = gson.fromJson(jsonResponse, token.getType());
			}
		}
	}
	public String toJSONString() {
		Gson gson = new GsonBuilder().create();
		return gson.toJson(this.moreRequest);
	}

	public MoreRequest getMoreRequest() {
		return moreRequest;
	}

	public void setMoreRequest(MoreRequest moreRequest) {
		this.moreRequest = moreRequest;
	}

	public List<MoreRequest> getMoreRequestList() {
		return moreRequestList;
	}

	public void setMoreRequestList(List<MoreRequest> moreRequestList) {
		this.moreRequestList = moreRequestList;
	}

	public static class MoreRequest implements Parcelable {
		private int morerequestid;
		private String whatstoryforrequest;
		private String answer;
		private int statusmrequest;

		public MoreRequest(){

		}

		public int getMorerequestid() {
			return morerequestid;
		}

		public void setMorerequestid(int morerequestid) {
			this.morerequestid = morerequestid;
		}

		public String getWhatstoryforrequest() {
			return whatstoryforrequest;
		}

		public void setWhatstoryforrequest(String whatstoryforrequest) {
			this.whatstoryforrequest = whatstoryforrequest;
		}
		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public int getStatusmrequest() {
			return statusmrequest;
		}

		public void setStatusmrequest(int statusmrequest) {
			this.statusmrequest = statusmrequest;
		}

		private RequestForHelp requestforhelp = new RequestForHelp();

		public RequestForHelp getRequestforhelp() {
			return requestforhelp;
		}

		public void setRequestforhelp(RequestForHelp requestforhelp) {
			this.requestforhelp = requestforhelp;
		}

		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel parcel, int i) {
			parcel.writeInt(morerequestid);
			parcel.writeString(whatstoryforrequest);
			parcel.writeString(answer);
			parcel.writeInt(statusmrequest);
			parcel.writeParcelable(requestforhelp, i);
		}
		protected MoreRequest(Parcel in) {
			morerequestid = in.readInt();
			whatstoryforrequest = in.readString();
			answer = in.readString();
			statusmrequest = in.readInt();
			requestforhelp = in.readParcelable(RequestForHelp.class.getClassLoader());
		}

		public static final Creator<MoreRequest> CREATOR = new Creator<MoreRequest>() {
			@Override
			public MoreRequest createFromParcel(Parcel in) {
				return new MoreRequest(in);
			}

			@Override
			public MoreRequest[] newArray(int size) {
				return new MoreRequest[size];
			}
		};
	}
	
}
