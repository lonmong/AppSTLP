package stlpapp.finalproject.app.com.appstlp.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Person extends Login implements Parcelable {
	private String nameperson;
	private String emailperson;
	private String telperson;
    public Person(){

    }


    public String getNameperson() {
		try {
			this.nameperson = URLDecoder.decode(nameperson,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return nameperson;
	}
	public void setNameperson(String nameperson) {
		this.nameperson = nameperson;
	}
	public String getEmailperson() {
		return emailperson;
	}
	public void setEmailperson(String emailperson) {
		this.emailperson = emailperson;
	}
	public String getTelperson() {
		return telperson;
	}
	public void setTelperson(String telperson) {
		this.telperson = telperson;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel parcel, int i) {
		parcel.writeString(nameperson);
		parcel.writeString(emailperson);
		parcel.writeString(telperson);
	}
	protected Person(Parcel in) {
        nameperson = in.readString();
		emailperson = in.readString();
		telperson = in.readString();
	}

	public static final Creator<Person> CREATOR = new Creator<Person>() {
		@Override
		public Person createFromParcel(Parcel in) {
			return new Person(in);
		}

		@Override
		public Person[] newArray(int size) {
			return new Person[size];
		}
	};

}
