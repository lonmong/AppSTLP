package stlpapp.finalproject.app.com.appstlp.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class RequestForHelp implements Parcelable {
	private int requestid;
	private int cerbirth;
	private int tr1;
	private int tr2;
	private int tr3;
	private int tr0310;
	private int tr1front;
	private int tr11part1;
	private int bcerbirth;
	private int bcerplacebirth;
	private int cerregister;
	private int tr14;
	private int tr13;
	private int fmperson;
	private int hfmpersonno2;
	private int trchk;
	private int tr38g;
	private int tr381;
	private int ceridcard;
	private int thaiidcard;
	private int notthaiidcard;
	private int statelessidcard;
	private int residencycer;
	private int refugeeidcardfromwar;
	private int statusrequest;
	public RequestForHelp(){

	}
	public int getRequestid() {
		return requestid;
	}

	public void setRequestid(int requestid) {
		this.requestid = requestid;
	}

	public int getCerbirth() {
		return cerbirth;
	}
	public void setCerbirth(int cerbirth) {
		this.cerbirth = cerbirth;
	}
	public int getTr1() {
		return tr1;
	}
	public void setTr1(int tr1) {
		this.tr1 = tr1;
	}
	public int getTr2() {
		return tr2;
	}
	public void setTr2(int tr2) {
		this.tr2 = tr2;
	}
	public int getTr3() {
		return tr3;
	}
	public void setTr3(int tr3) {
		this.tr3 = tr3;
	}
	public int getTr0310() {
		return tr0310;
	}
	public void setTr0310(int tr0310) {
		this.tr0310 = tr0310;
	}
	public int getTr1front() {
		return tr1front;
	}
	public void setTr1front(int tr1front) {
		this.tr1front = tr1front;
	}
	public int getTr11part1() {
		return tr11part1;
	}
	public void setTr11part1(int tr11part1) {
		this.tr11part1 = tr11part1;
	}
	public int getBcerbirth() {
		return bcerbirth;
	}
	public void setBcerbirth(int bcerbirth) {
		this.bcerbirth = bcerbirth;
	}
	public int getBcerplacebirth() {
		return bcerplacebirth;
	}
	public void setBcerplacebirth(int bcerplacebirth) {
		this.bcerplacebirth = bcerplacebirth;
	}
	public int getCerregister() {
		return cerregister;
	}
	public void setCerregister(int cerregister) {
		this.cerregister = cerregister;
	}
	public int getTr14() {
		return tr14;
	}
	public void setTr14(int tr14) {
		this.tr14 = tr14;
	}
	public int getTr13() {
		return tr13;
	}
	public void setTr13(int tr13) {
		this.tr13 = tr13;
	}
	public int getFmperson() {
		return fmperson;
	}
	public void setFmperson(int fmperson) {
		this.fmperson = fmperson;
	}
	public int getHfmpersonno2() {
		return hfmpersonno2;
	}
	public void setHfmpersonno2(int hfmpersonno2) {
		this.hfmpersonno2 = hfmpersonno2;
	}
	public int getTrchk() {
		return trchk;
	}
	public void setTrchk(int trchk) {
		this.trchk = trchk;
	}
	public int getTr38g() {
		return tr38g;
	}
	public void setTr38g(int tr38g) {
		this.tr38g = tr38g;
	}
	public int getTr381() {
		return tr381;
	}
	public void setTr381(int tr381) {
		this.tr381 = tr381;
	}
	public int getCeridcard() {
		return ceridcard;
	}
	public void setCeridcard(int ceridcard) {
		this.ceridcard = ceridcard;
	}
	public int getThaiidcard() {
		return thaiidcard;
	}
	public void setThaiidcard(int thaiidcard) {
		this.thaiidcard = thaiidcard;
	}
	public int getNotthaiidcard() {
		return notthaiidcard;
	}
	public void setNotthaiidcard(int notthaiidcard) {
		this.notthaiidcard = notthaiidcard;
	}
	public int getStatelessidcard() {
		return statelessidcard;
	}
	public void setStatelessidcard(int statelessidcard) {
		this.statelessidcard = statelessidcard;
	}
	public int getResidencycer() {
		return residencycer;
	}
	public void setResidencycer(int residencycer) {
		this.residencycer = residencycer;
	}
	public int getRefugeeidcardfromwar() {
		return refugeeidcardfromwar;
	}
	public void setRefugeeidcardfromwar(int refugeeidcardfromwar) {
		this.refugeeidcardfromwar = refugeeidcardfromwar;
	}
	public int getStatusrequest() {
		return statusrequest;
	}
	public void setStatusrequest(int statusrequest) {
		this.statusrequest = statusrequest;
	}
	private StatelessPerson statelessperon = new StatelessPerson();
	public StatelessPerson getStatelessperon() {
		return statelessperon;
	}

	public void setStatelessperon(StatelessPerson statelessperon) {
		this.statelessperon = statelessperon;
	}
	private CenterModel.Center center = new CenterModel.Center();
	public CenterModel.Center getCenter() {
		return center;
	}
	public void setCenter(CenterModel.Center center) {
		this.center = center;
	}
	private List<MoreRequestModel.MoreRequest> morerequestSList = new ArrayList<>();
	public List<MoreRequestModel.MoreRequest> getMorerequestSList() {
		return morerequestSList;
	}
	public void setMorerequestSList(List<MoreRequestModel.MoreRequest> morerequestSList) {
		this.morerequestSList = morerequestSList;
	}
	private List<AssignModel.Assign> assigntList = new ArrayList<>() ;
	public List<AssignModel.Assign> getAssigntList() {
		return assigntList;
	}
	public void setAssigntList(List<AssignModel.Assign> assigntList) {
		this.assigntList = assigntList;
	}

	protected RequestForHelp(Parcel in) {
		requestid = in.readInt();
		cerbirth = in.readInt();
		tr1 = in.readInt();
		tr2 = in.readInt();
		tr3 = in.readInt();
		tr0310 = in.readInt();
		tr1front = in.readInt();
		tr11part1 = in.readInt();
		bcerbirth = in.readInt();
		bcerplacebirth = in.readInt();
		cerregister = in.readInt();
		tr14 = in.readInt();
		tr13 = in.readInt();
		fmperson = in.readInt();
		hfmpersonno2 = in.readInt();
		trchk = in.readInt();
		tr38g = in.readInt();
		tr381 = in.readInt();
		ceridcard = in.readInt();
		thaiidcard = in.readInt();
		notthaiidcard = in.readInt();
		statelessidcard = in.readInt();
		residencycer = in.readInt();
		refugeeidcardfromwar = in.readInt();
		statusrequest = in.readInt();
		statelessperon =in.readParcelable(StatelessPerson.class.getClassLoader());
		center = in.readParcelable(CenterModel.Center.class.getClassLoader());
		morerequestSList = in.createTypedArrayList(MoreRequestModel.MoreRequest.CREATOR);
		assigntList = in.createTypedArrayList(AssignModel.Assign.CREATOR);

	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(cerbirth);
		dest.writeInt(tr1);
		dest.writeInt(tr2);
		dest.writeInt(tr3);
		dest.writeInt(tr0310);
		dest.writeInt(tr1front);
		dest.writeInt(tr11part1);
		dest.writeInt(bcerbirth);
		dest.writeInt(bcerplacebirth);
		dest.writeInt(cerregister);
		dest.writeInt(tr14);
		dest.writeInt(tr13);
		dest.writeInt(fmperson);
		dest.writeInt(hfmpersonno2);
		dest.writeInt(trchk);
		dest.writeInt(tr38g);
		dest.writeInt(tr381);
		dest.writeInt(ceridcard);
		dest.writeInt(thaiidcard);
		dest.writeInt(notthaiidcard);
		dest.writeInt(statelessidcard);
		dest.writeInt(residencycer);
		dest.writeInt(refugeeidcardfromwar);
		dest.writeInt(statusrequest);
		dest.writeParcelable(statelessperon, flags);
		dest.writeParcelable(center, flags);
		dest.writeTypedList(morerequestSList);
		dest.writeTypedList(assigntList);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<RequestForHelp> CREATOR = new Creator<RequestForHelp>() {
		@Override
		public RequestForHelp createFromParcel(Parcel in) {
			return new RequestForHelp(in);
		}

		@Override
		public RequestForHelp[] newArray(int size) {
			return new RequestForHelp[size];
		}
	};

}
