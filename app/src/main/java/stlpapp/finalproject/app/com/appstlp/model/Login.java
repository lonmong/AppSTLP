package stlpapp.finalproject.app.com.appstlp.model;


import android.os.Parcel;
import android.os.Parcelable;

public class Login implements Parcelable {

	private String username;
	private String password;
	private int type;
	public Login(){

	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(username);
		parcel.writeString(password);
		parcel.writeInt(type);
	}
	protected Login(Parcel in) {
		username = in.readString();
		password = in.readString();
		type = in.readInt();
	}

	public static final Creator<Login> CREATOR = new Creator<Login>() {
		@Override
		public Login createFromParcel(Parcel in) {
			return new Login(in);
		}

		@Override
		public Login[] newArray(int size) {
			return new Login[size];
		}
	};
}
