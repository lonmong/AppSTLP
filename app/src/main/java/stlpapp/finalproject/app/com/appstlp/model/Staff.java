package stlpapp.finalproject.app.com.appstlp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Person implements Parcelable {
	private String position;
	private String address;
	private int statusstaff;
	public Staff(){

	}

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
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

	public int getStatusstaff() {
		return statusstaff;
	}

	public void setStatusstaff(int statusstaff) {
		this.statusstaff = statusstaff;
	}

	private CenterModel.Center center;

	public CenterModel.Center getCenter() {
		return center;
	}
	public void setCenter(CenterModel.Center center) {
		this.center = center;
	}
	private List<AssignModel.Assign> assignList = new ArrayList<>();
	public List<AssignModel.Assign> getAssignList() {
		return assignList;
	}
	public void setAssignList(List<AssignModel.Assign> assignList) {
		this.assignList = assignList;
	}
	protected Staff(Parcel in) {
		super(in);
		position = in.readString();
		address = in.readString();
		statusstaff = in.readInt();
		center = in.readParcelable(CenterModel.Center.class.getClassLoader());
		assignList = in.createTypedArrayList(AssignModel.Assign.CREATOR);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(position);
		dest.writeString(address);
		dest.writeInt(statusstaff);
		dest.writeParcelable(center, flags);
		dest.writeTypedList(assignList);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<Staff> CREATOR = new Creator<Staff>() {
		@Override
		public Staff createFromParcel(Parcel in) {
			return new Staff(in);
		}

		@Override
		public Staff[] newArray(int size) {
			return new Staff[size];
		}
	};


}
