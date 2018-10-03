package stlpapp.finalproject.app.com.appstlp.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StatelessPerson extends Person implements Parcelable {

	private int gender;
	private Date birthDay;
	private Long birthday;
	private String religion;
	private String ethnic;
	private String nationality;
	private String homeid;
	private String idcard;
	private int statusmarry;
	private String nameofspouse;
	private String idcardofspouse;
	private String nationalityofspouse;
	private String dateofmarry;
	private String addressofspouse;
	private int statusplaceofbirth;
	private int statusthaiorabroadbirth;
	private String hospitalofbirth;
	private String addressofvillagebirth;
	private int statuswitness;
	private String countryofbirth;
	private String districtcomethai;
	private String datecomethai;
	private String modecomethai;
	public StatelessPerson(){

	}

	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		birthday = birthDay.getTime();
		this.birthDay = birthDay;
	}

	public Long getBirthday() {
		return birthday;
	}

	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}

	public String getReligion() {
		return religion;
	}
	public void setReligion(String religion) {
		this.religion = religion;
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
	public String getHomeid() {
		return homeid;
	}
	public void setHomeid(String homeid) {
		this.homeid = homeid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getStatusmarry() {
		return statusmarry;
	}
	public void setStatusmarry(int statusmarry) {
		this.statusmarry = statusmarry;
	}
	public String getNameofspouse() {
		try {
			this.nameofspouse = URLDecoder.decode(nameofspouse,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return nameofspouse;
	}
	public void setNameofspouse(String nameofspouse) {
		this.nameofspouse = nameofspouse;
	}
	public String getIdcardofspouse() {
		return idcardofspouse;
	}
	public void setIdcardofspouse(String idcardofspouse) {
		this.idcardofspouse = idcardofspouse;
	}
	public String getNationalityofspouse() {
		return nationalityofspouse;
	}
	public void setNationalityofspouse(String nationalityofspouse) {
		this.nationalityofspouse = nationalityofspouse;
	}
	public String getDateofmarry() {
		try {
			this.dateofmarry = URLDecoder.decode(dateofmarry,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return dateofmarry;
	}
	public void setDateofmarry(String dateofmarry) {
		this.dateofmarry = dateofmarry;
	}
	public String getAddressofspouse() {
		try {
			this.addressofspouse = URLDecoder.decode(addressofspouse,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return addressofspouse;
	}
	public void setAddressofspouse(String addressofspouse) {
		this.addressofspouse = addressofspouse;
	}

	public int getStatusplaceofbirth() {
		return statusplaceofbirth;
	}

	public void setStatusplaceofbirth(int statusplaceofbirth) {
		this.statusplaceofbirth = statusplaceofbirth;
	}

	public int getStatusthaiorabroadbirth() {
		return statusthaiorabroadbirth;
	}
	public void setStatusthaiorabroadbirth(int statusthaiorabroadbirth) {
		this.statusthaiorabroadbirth = statusthaiorabroadbirth;
	}
	public String getHospitalofbirth() {
		return hospitalofbirth;
	}
	public void setHospitalofbirth(String hospitalofbirth) {
		this.hospitalofbirth = hospitalofbirth;
	}
	public String getAddressofvillagebirth() {
		try {
			this.addressofvillagebirth = URLDecoder.decode(addressofvillagebirth,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return addressofvillagebirth;
	}
	public void setAddressofvillagebirth(String addressofvillagebirth) {
		this.addressofvillagebirth = addressofvillagebirth;
	}
	public int getStatuswitness() {
		return statuswitness;
	}
	public void setStatuswitness(int statuswitness) {
		this.statuswitness = statuswitness;
	}
	public String getCountryofbirth() {
		return countryofbirth;
	}
	public void setCountryofbirth(String countryofbirth) {
		this.countryofbirth = countryofbirth;
	}
	public String getDistrictcomethai() {
		try {
			this.districtcomethai = URLDecoder.decode(districtcomethai,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return districtcomethai;
	}
	public void setDistrictcomethai(String districtcomethai) {
		this.districtcomethai = districtcomethai;
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
	public String getModecomethai() {
		return modecomethai;
	}
	public void setModecomethai(String modecomethai) {
		this.modecomethai = modecomethai;
	}

	private IDCardTypeModel.IDCardType idcardtype = new IDCardTypeModel.IDCardType();
	public IDCardTypeModel.IDCardType getIdcardtype() {
		return idcardtype;	
	}
	public void setIdcardtype(IDCardTypeModel.IDCardType idcardtype) {
		this.idcardtype = idcardtype;
	}
	private List<AddressModel.Address> addressList = new ArrayList<>();
	public List<AddressModel.Address> getAddressList() {
		return addressList;
	}
	private List<EducationModel.Education> educationList = new ArrayList<>();

	public List<EducationModel.Education> getEducationList() {
		return educationList;
	}

	public void setEducationList(List<EducationModel.Education> educationList) {
		this.educationList = educationList;
	}

	public void setAddressList(List<AddressModel.Address> addressList) {
		this.addressList = addressList;
	}

	private List<ParentModel.Parent> parentList = new ArrayList<>();

	public List<ParentModel.Parent> getParentList() {
		return parentList;
	}

	public void setParentList(List<ParentModel.Parent> parentList) {
		this.parentList = parentList;
	}
	private List<WitnessModel.Witness> witnessList = new ArrayList<>();

	public List<WitnessModel.Witness> getWitnessList() {
		return witnessList;
	}

	public void setWitnessList(List<WitnessModel.Witness> witnessList) {
		this.witnessList = witnessList;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeInt(gender);
		dest.writeLong(birthDay != null ? birthDay.getTime() : -1);
		dest.writeValue(birthday);
		dest.writeString(religion);
		dest.writeString(ethnic);
		dest.writeString(nationality);
		dest.writeString(homeid);
		dest.writeString(idcard);
		dest.writeInt(statusmarry);
		dest.writeString(nameofspouse);
		dest.writeString(idcardofspouse);
		dest.writeString(nationalityofspouse);
		dest.writeString(dateofmarry);
		dest.writeString(addressofspouse);
		dest.writeInt(statusplaceofbirth);
		dest.writeInt(statusthaiorabroadbirth);
		dest.writeString(hospitalofbirth);
		dest.writeString(addressofvillagebirth);
		dest.writeInt(statuswitness);
		dest.writeString(countryofbirth);
		dest.writeString(districtcomethai);
		dest.writeString(datecomethai);
		dest.writeString(modecomethai);
		dest.writeParcelable(idcardtype,flags);
		dest.writeTypedList(addressList);
		dest.writeTypedList(educationList);
		dest.writeTypedList(parentList);
		dest.writeTypedList(witnessList);

	}

	protected StatelessPerson(Parcel in) {
		super(in);
		gender = in.readInt();
		long tmpBirthDay = in.readLong();
		birthDay = tmpBirthDay == -1 ? null : new Date(tmpBirthDay);
		birthday = (Long) in.readValue(Long.class.getClassLoader());
		religion = in.readString();
		ethnic = in.readString();
		nationality = in.readString();
		homeid = in.readString();
		idcard = in.readString();
		statusmarry = in.readInt();
		nameofspouse = in.readString();
		idcardofspouse = in.readString();
		nationalityofspouse = in.readString();
		dateofmarry = in.readString();
		addressofspouse = in.readString();
		statusplaceofbirth = in.readInt();
		statusthaiorabroadbirth = in.readInt();
		hospitalofbirth = in.readString();
		addressofvillagebirth = in.readString();
		statuswitness = in.readInt();
		countryofbirth = in.readString();
		districtcomethai = in.readString();
		datecomethai = in.readString();
		modecomethai = in.readString();
		idcardtype = in.readParcelable(IDCardTypeModel.IDCardType.class.getClassLoader());
		addressList = in.createTypedArrayList(AddressModel.Address.CREATOR);
		educationList = in.createTypedArrayList(EducationModel.Education.CREATOR);
		parentList = in.createTypedArrayList(ParentModel.Parent.CREATOR);
		witnessList = in.createTypedArrayList(WitnessModel.Witness.CREATOR);
	}
	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<StatelessPerson> CREATOR = new Creator<StatelessPerson>() {
		@Override
		public StatelessPerson createFromParcel(Parcel in) {
			return new StatelessPerson(in);
		}

		@Override
		public StatelessPerson[] newArray(int size) {
			return new StatelessPerson[size];
		}
	};
}
