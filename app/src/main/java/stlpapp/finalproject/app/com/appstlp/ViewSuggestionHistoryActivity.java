package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import stlpapp.finalproject.app.com.appstlp.controller.ViewSuggestionHistoryController;
import stlpapp.finalproject.app.com.appstlp.controller.WSManager;
import stlpapp.finalproject.app.com.appstlp.model.AddressModel;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.EducationModel;
import stlpapp.finalproject.app.com.appstlp.model.ParentModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelp;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPerson;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.model.WitnessModel;

public class ViewSuggestionHistoryActivity extends AppCompatActivity {
    private int requestid;
    private int assignid;
    private String username = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_suggestion_history);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        requestid = intent.getIntExtra("requestid",0);
        assignid = intent.getIntExtra("assignid",0);
        detailRequestandSuggestion();
    }

    public void detailRequestandSuggestion() {
        final LinearLayout Listdetailrequest = (LinearLayout)findViewById(R.id.detailrequest);
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);
        final DateFormat dfm = DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("th", "TH"));
        ViewSuggestionHistoryController manager = ViewSuggestionHistoryController.getWsManager(ViewSuggestionHistoryActivity.this);
        final RequestForHelpModel requestForHelpModel = new RequestForHelpModel();
        final RequestForHelp requestForHelp = new RequestForHelp();
        requestForHelp.setRequestid(requestid);
        requestForHelpModel.setRequestForHelp(requestForHelp);

        manager.detailRequestByidRequest(requestForHelpModel, new ViewSuggestionHistoryController.ViewSuggestionHistoryControllerListener() {
            @Override
            public void onComplete(Object response) {
                if (((response instanceof RequestForHelpModel))) {
                    RequestForHelpModel requestForHelpModel1 = (RequestForHelpModel) response;
                    RequestForHelp requestForHelp1 = requestForHelpModel1.getRequestForHelp();
                    requestForHelpModel.setRequestForHelp(requestForHelp1);
                }
                progress.dismiss();

                TextView textViewNameperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewNameperson.setText("ชื่อ : "+ requestForHelpModel.getRequestForHelp().getStatelessperon().getNameperson().toString());
                Listdetailrequest.addView(textViewNameperson);

                TextView textViewEmailperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewEmailperson.setText("อีเมลล์ : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getEmailperson().toString());
                Listdetailrequest.addView(textViewEmailperson);

                TextView textViewTelperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewTelperson.setText("เบอร์โทร : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getTelperson().toString());
                Listdetailrequest.addView(textViewTelperson);

                TextView textViewBirthdayperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewBirthdayperson.setText("วันเกิด : "+dfm.format(requestForHelpModel.getRequestForHelp().getStatelessperon().getBirthday()));
                Listdetailrequest.addView(textViewBirthdayperson);

                TextView textViewReligionperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewReligionperson.setText("ศาสนา : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getReligion().toString());
                Listdetailrequest.addView(textViewReligionperson);

                TextView textViewEthnicperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewEthnicperson.setText("ชาติพันธุ์ : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getEthnic().toString());
                Listdetailrequest.addView(textViewEthnicperson);

                TextView textViewNationalityperson = new TextView(ViewSuggestionHistoryActivity.this);
                textViewNationalityperson.setText("สัญชาติ : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getNationality().toString());
                Listdetailrequest.addView(textViewNationalityperson);

                TextView textViewHomeid = new TextView(ViewSuggestionHistoryActivity.this);
                textViewHomeid.setText("รหัสประจำบ้าน : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getHomeid().toString());
                Listdetailrequest.addView(textViewHomeid);

                TextView textViewIdcard = new TextView(ViewSuggestionHistoryActivity.this);
                textViewIdcard.setText("เลขประจำตัวประชาชน : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getIdcard().toString());
                Listdetailrequest.addView(textViewIdcard);

                TextView textViewStatusMarryperson = new TextView(ViewSuggestionHistoryActivity.this);
                if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==1){
                    textViewStatusMarryperson.setText("สถานะ : โสด");
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==2){
                    textViewStatusMarryperson.setText("สถานะ : หย่า");
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==3){
                    textViewStatusMarryperson.setText("สถานะ : หม้าย");
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==4){
                    textViewStatusMarryperson.setText("สถานะ : ไม่จดทะเบียนสมรส");
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==5){
                    textViewStatusMarryperson.setText("สถานะ : จดทะเบียนสมรส");
                }
                Listdetailrequest.addView(textViewStatusMarryperson);

                if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==2||requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==3||requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==4||requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry()==5){
                    TextView textViewDetailSpouse = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewDetailSpouse.setText("=ข้อมูลคู่สมรส=");
                    textViewDetailSpouse.setTypeface(null, Typeface.BOLD);
                    textViewDetailSpouse.setTextColor(Color.parseColor("#00189D"));
                    TextView textViewNamespouse = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewNamespouse.setText("ชื่อคู่สมรส : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getNameofspouse().toString());

                    TextView textViewIdcardSpouse = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewIdcardSpouse.setText("รหัสประจำตัวคู่สมรส : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getIdcardofspouse().toString());

                    TextView textViewNationalityspouse = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewNationalityspouse.setText("สัญชาติคู่สมรส : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getNationalityofspouse().toString());

                    TextView textViewDateMarry = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewDateMarry.setText("วันที่สมรส : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getDateofmarry().toString());

                    TextView textViewAddressspouse = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewAddressspouse.setText("ที่อยู่คู่สมรส : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getAddressofspouse().toString());

                    Listdetailrequest.addView(textViewDetailSpouse);
                    Listdetailrequest.addView(textViewNamespouse);
                    Listdetailrequest.addView(textViewIdcardSpouse);
                    Listdetailrequest.addView(textViewNationalityspouse);
                    Listdetailrequest.addView(textViewDateMarry);
                    Listdetailrequest.addView(textViewAddressspouse);
                }
                TextView textViewStatusPlaceBirth = new TextView(ViewSuggestionHistoryActivity.this);
                if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusplaceofbirth()==1){
                    TextView textViewBirth = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewBirth.setText("=ข้อมูลการเกิด=");
                    textViewBirth.setTypeface(null, Typeface.BOLD);
                    textViewBirth.setTextColor(Color.parseColor("#00189D"));
                    textViewStatusPlaceBirth.setText("สถานะการทราบสถานที่เกิด : ทราบสถานที่เกิด");
                    Listdetailrequest.addView(textViewBirth);
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusplaceofbirth()==2){
                    TextView textViewBirth = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewBirth.setText("=ข้อมูลการเกิด=");
                    textViewBirth.setTypeface(null, Typeface.BOLD);
                    textViewBirth.setTextColor(Color.parseColor("#00189D"));
                    textViewStatusPlaceBirth.setText("สถานะการทราบสถานที่เกิด : ไม่ทราบสถานที่เกิด");
                    Listdetailrequest.addView(textViewBirth);
                }
                Listdetailrequest.addView(textViewStatusPlaceBirth);

                TextView textViewStatusThaiOrAbroadBirth = new TextView(ViewSuggestionHistoryActivity.this);
                if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth()==1){
                    textViewStatusThaiOrAbroadBirth.setText("-เกิดที่ไทย");
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth()==2){
                    textViewStatusThaiOrAbroadBirth.setText("-เกิดนอกไทย");
                }
                Listdetailrequest.addView(textViewStatusThaiOrAbroadBirth);

                if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth()==1) {
                    TextView textViewHospitalbirth = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewHospitalbirth.setText("ชื่อสถานพยาบาลที่เกิด : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getHospitalofbirth().toString());

                    TextView textViewVillagebirth = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewVillagebirth.setText("ที่อยู่หมู่บ้านที่เกิด : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getAddressofvillagebirth().toString());

                    Listdetailrequest.addView(textViewHospitalbirth);
                    Listdetailrequest.addView(textViewVillagebirth);

                    TextView textViewStatuswitness = new TextView(ViewSuggestionHistoryActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatuswitness() == 1) {
                        textViewStatuswitness.setText("มีพยานรู้เห็นการเกิด");
                        textViewStatuswitness.setTextColor(Color.parseColor("#009D1D"));
                    } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatuswitness() == 2) {
                        textViewStatuswitness.setText("ไม่มีพยานรู้เห็นการเกิด");
                        textViewStatuswitness.setTextColor(Color.parseColor("#D81101"));
                    }
                    Listdetailrequest.addView(textViewStatuswitness);

                    if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatuswitness() == 1) {
                        final WSManager manager = new WSManager(ViewSuggestionHistoryActivity.this);
                        final ProgressDialog progress = ProgressDialog.show(ViewSuggestionHistoryActivity.this, getString(R.string.please_wait),
                                getString(R.string.please_wait), true);
                        StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
                        StatelessPerson statelessPerson = new StatelessPerson();
                        statelessPerson.setUsername(username);
                        statelessPersonModel.setStatelessPerson(statelessPerson);

                        manager.getListWitnessByUsername(statelessPersonModel, new WSManager.WSManagerListener() {
                            @Override
                            public void onComplete(Object response) {
                                progress.dismiss();
                                WitnessModel witnessModel = (WitnessModel) response;
                                List<WitnessModel.Witness> witnesses = witnessModel.getWitnessList();
                                TextView textViewDetailWitness = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewDetailWitness.setText("=ข้อมูลพยานรู้เห็นการเกิด=");
                                textViewDetailWitness.setTypeface(null, Typeface.BOLD);
                                textViewDetailWitness.setTextColor(Color.parseColor("#00189D"));
                                Listdetailrequest.addView(textViewDetailWitness);

                                for (int i = 0; i < witnesses.size(); i++) {
                                    TextView textViewWitnessName = new TextView(ViewSuggestionHistoryActivity.this);
                                    TextView textViewrelationship = new TextView(ViewSuggestionHistoryActivity.this);
                                    TextView textViewStatuswitness = new TextView(ViewSuggestionHistoryActivity.this);
                                    TextView textViewAddresswitness = new TextView(ViewSuggestionHistoryActivity.this);
                                    TextView textViewLine1 = new TextView(ViewSuggestionHistoryActivity.this);

                                    textViewWitnessName.setText("ชื่อพยานรู้เห็นการเกิดคนที่" + (i + 1) + " : " + witnesses.get(i).getNamewitness());
                                    textViewrelationship.setText("เกี่ยวข้องเป็น : " + witnesses.get(i).getRelationship());
                                    if (witnesses.get(i).getStatusbealive() == 1) {
                                        textViewStatuswitness.setText("สถานะ : มีชีวิต");
                                    }
                                    if (witnesses.get(i).getStatusbealive() == 2) {
                                        textViewStatuswitness.setText("สถานะ : เสียชีวิต");
                                    }
                                    textViewAddresswitness.setText("ที่อยู่ : " + witnesses.get(i).getAddresswitness());
                                    textViewLine1.setText("--------------------------------");

                                    Listdetailrequest.addView(textViewWitnessName);
                                    Listdetailrequest.addView(textViewrelationship);
                                    Listdetailrequest.addView(textViewStatuswitness);
                                    Listdetailrequest.addView(textViewAddresswitness);
                                    Listdetailrequest.addView(textViewLine1);
                                }
                            }

                            @Override
                            public void onError(String err) {
                                progress.dismiss();
                                Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else if(requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth()==2){
                    TextView textViewCountryBirth = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewCountryBirth.setText("ประเทศที่เกิด : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getCountryofbirth().toString());

                    TextView textViewDistrictComeThai = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewDistrictComeThai.setText("เข้ามาไทยทางด่านอำเภอ : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getDistrictcomethai().toString());

                    TextView textViewDateComeThai = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewDateComeThai.setText("วันที่เดินทางเข้ามาไทย : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getDatecomethai().toString());

                    TextView textViewModeComethai = new TextView(ViewSuggestionHistoryActivity.this);
                    textViewModeComethai.setText("เดินทางเข้ามาไทยโดย : "+requestForHelpModel.getRequestForHelp().getStatelessperon().getModecomethai().toString());

                    final WSManager manager = new WSManager(ViewSuggestionHistoryActivity.this);
                    final ProgressDialog progress = ProgressDialog.show(ViewSuggestionHistoryActivity.this, getString(R.string.please_wait),
                            getString(R.string.please_wait), true);
                    StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
                    StatelessPerson statelessPerson = new StatelessPerson();
                    statelessPerson.setUsername(username);
                    statelessPersonModel.setStatelessPerson(statelessPerson);
                    manager.getListWitnessByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                        @Override
                        public void onComplete(Object response) {
                            progress.dismiss();
                            WitnessModel witnessModel = (WitnessModel) response;
                            List<WitnessModel.Witness> witnesses = witnessModel.getWitnessList();
                            TextView textViewDetailWitness = new TextView(ViewSuggestionHistoryActivity.this);
                            textViewDetailWitness.setText("=ข้อมูลพยานรู้เห็นการเข้ามาไทย=");
                            textViewDetailWitness.setTypeface(null, Typeface.BOLD);
                            textViewDetailWitness.setTextColor(Color.parseColor("#00189D"));
                            Listdetailrequest.addView(textViewDetailWitness);

                            for(int i = 0 ; i<witnesses.size();i++){
                                TextView textViewWitnessName = new TextView(ViewSuggestionHistoryActivity.this);
                                TextView textViewrelationship = new TextView(ViewSuggestionHistoryActivity.this);
                                TextView textViewStatuswitness = new TextView(ViewSuggestionHistoryActivity.this);
                                TextView textViewAddresswitness = new TextView(ViewSuggestionHistoryActivity.this);

                                textViewWitnessName.setText("ชื่อพยานรู้เห็นการเข้ามาไทย : \n"+witnesses.get(i).getNamewitness());
                                textViewrelationship.setText("ความเกี่ยวข้อง : "+witnesses.get(i).getRelationship());
                                if(witnesses.get(i).getStatusbealive()==1){
                                    textViewStatuswitness.setText("สถานะ : มีชีวิต");
                                }
                                if (witnesses.get(i).getStatusbealive()==2){
                                    textViewStatuswitness.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddresswitness.setText("ที่อยู่ : "+witnesses.get(i).getAddresswitness());

                                Listdetailrequest.addView(textViewWitnessName);
                                Listdetailrequest.addView(textViewrelationship);
                                Listdetailrequest.addView(textViewStatuswitness);
                                Listdetailrequest.addView(textViewAddresswitness);
                            }
                        }

                        @Override
                        public void onError(String err) {
                            progress.dismiss();
                            Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Listdetailrequest.addView(textViewCountryBirth);
                    Listdetailrequest.addView(textViewDistrictComeThai);
                    Listdetailrequest.addView(textViewDateComeThai);
                    Listdetailrequest.addView(textViewModeComethai);
                }

                final WSManager manager = new WSManager(ViewSuggestionHistoryActivity.this);
                final ProgressDialog progress = ProgressDialog.show(ViewSuggestionHistoryActivity.this, getString(R.string.please_wait),
                        getString(R.string.please_wait), true);
                StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
                StatelessPerson statelessPerson = new StatelessPerson();
                statelessPerson.setUsername(username);
                statelessPersonModel.setStatelessPerson(statelessPerson);

                manager.getListAddressByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        AddressModel addressModel = (AddressModel) response;
                        List<AddressModel.Address> addresses = addressModel.getAddressList();
                        TextView textViewDetailAddress = new TextView(ViewSuggestionHistoryActivity.this);
                        textViewDetailAddress.setText("=ข้อมูลที่อยู่=");
                        textViewDetailAddress.setTypeface(null, Typeface.BOLD);
                        textViewDetailAddress.setTextColor(Color.parseColor("#00189D"));
                        Listdetailrequest.addView(textViewDetailAddress);
                        for(int i = 0 ; i<addresses.size();i++){
                            TextView textViewdetailaddress = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewfromyears = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewtoyears = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewhomdstatus = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewstatusaddress = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewLine2 = new TextView(ViewSuggestionHistoryActivity.this);
                            if(addresses.get(i).getStatusaddress()==1){
                                textViewdetailaddress.setText("ที่อยู่ตามทะเบียนบ้าน \n"+addresses.get(i).getDetailaddress());
                                if(addresses.get(i).getHomestatus()==1){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านตัวเอง");
                                }else if(addresses.get(i).getHomestatus()==2){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านเช่า");
                                }else if(addresses.get(i).getHomestatus()==3){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านอาศัย");
                                }
                            }else if(addresses.get(i).getStatusaddress()==2){
                                textViewdetailaddress.setText("ที่อยู่ปัจจุบัน\n"+addresses.get(i).getDetailaddress());
                                textViewfromyears.setText("อาศัยอยู่ตั้งแต่ปี : "+addresses.get(i).getFromyears());
                                textViewtoyears.setText("ถึงปี : "+addresses.get(i).getToyears());
                                if(addresses.get(i).getHomestatus()==1){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านตัวเอง");
                                }else if(addresses.get(i).getHomestatus()==2){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านเช่า");
                                }else if(addresses.get(i).getHomestatus()==3){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านอาศัย");
                                }
                            }else if(addresses.get(i).getStatusaddress()==3){
                                textViewdetailaddress.setText("ที่อยู่ก่อนหน้า \n"+addresses.get(i).getDetailaddress());
                                textViewfromyears.setText("อาศัยอยู่ตั้งแต่ปี : "+addresses.get(i).getFromyears());
                                textViewtoyears.setText("ถึงปี : "+addresses.get(i).getToyears());
                                if(addresses.get(i).getHomestatus()==1){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านตัวเอง");
                                }else if(addresses.get(i).getHomestatus()==2){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านเช่า");
                                }else if(addresses.get(i).getHomestatus()==3){
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านอาศัย");
                                }
                            }
                            textViewLine2.setText("--------------------------------");

                            Listdetailrequest.addView(textViewdetailaddress);
                            Listdetailrequest.addView(textViewfromyears);
                            Listdetailrequest.addView(textViewtoyears);
                            Listdetailrequest.addView(textViewhomdstatus);
                            Listdetailrequest.addView(textViewstatusaddress);
                            Listdetailrequest.addView(textViewLine2);
                        }
                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });

                manager.getListEducationByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        EducationModel educationModel = (EducationModel) response;
                        List<EducationModel.Education> educations = educationModel.getEducationList();
                        TextView textViewDetailEducation = new TextView(ViewSuggestionHistoryActivity.this);
                        textViewDetailEducation.setText("=ข้อมูลประวัติการศึกษา=");
                        textViewDetailEducation.setTypeface(null, Typeface.BOLD);
                        textViewDetailEducation.setTextColor(Color.parseColor("#00189D"));
                        Listdetailrequest.addView(textViewDetailEducation);

                        for(int i = 0 ; i<educations.size();i++){
                            TextView textViewEducation = new TextView(ViewSuggestionHistoryActivity.this);
                            textViewEducation.setText("ระดับการศึกษา : "+educations.get(i).getEducationdetails());
                            Listdetailrequest.addView(textViewEducation);
                        }
                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });
                manager.getListParentByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        ParentModel parentModel = (ParentModel) response;
                        List<ParentModel.Parent> parents = parentModel.getParentList();
                        TextView textViewDetailParent = new TextView(ViewSuggestionHistoryActivity.this);
                        textViewDetailParent.setText("=ข้อมูลบิดา/มารดา=");
                        textViewDetailParent.setTypeface(null, Typeface.BOLD);
                        textViewDetailParent.setTextColor(Color.parseColor("#00189D"));
                        Listdetailrequest.addView(textViewDetailParent);
                        for(int i = 0 ; i<parents.size();i++){
                            TextView textViewName = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewIdcard = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewbirthday = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewEthnic = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewNationality = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewDatecomethai = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewStatusAlive = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewStatusParent = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewAddress = new TextView(ViewSuggestionHistoryActivity.this);
                            TextView textViewLine3 = new TextView(ViewSuggestionHistoryActivity.this);
                            if(parents.get(i).getStatusparent()==1){
                                textViewName.setText("ชื่อบิดา : "+parents.get(i).getName());
                                textViewIdcard.setText("เลขประจำตัวประชาชน : "+parents.get(i).getIdcard());
                                textViewbirthday.setText("วันเกิด : "+parents.get(i).getBirthday());
                                textViewEthnic.setText("ชาติพันธุ์ : "+parents.get(i).getEthnic());
                                textViewNationality.setText("สัญชาติ : "+parents.get(i).getNationality());
                                textViewDatecomethai.setText("วันที่เดินทางมาไทย : "+parents.get(i).getDatecomethai());
                                if(parents.get(i).getStatusbealive()==1){
                                    textViewStatusAlive.setText("สถานะ : มีชีวิต");
                                }else if(parents.get(i).getStatusbealive()==2){
                                    textViewStatusAlive.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddress.setText("ที่อยู่ \n"+parents.get(i).getAddress());

                            }else if(parents.get(i).getStatusparent()==2){

                                textViewName.setText("ชื่อมารดา : "+parents.get(i).getName());
                                textViewIdcard.setText("เลขประจำตัวประชาชน : "+parents.get(i).getIdcard());
                                textViewbirthday.setText("วันเกิด : "+parents.get(i).getBirthday());
                                textViewEthnic.setText("ชาติพันธุ์ : "+parents.get(i).getEthnic());
                                textViewNationality.setText("สัญชาติ : "+parents.get(i).getNationality());
                                textViewDatecomethai.setText("วันที่เดินทางมาไทย : "+parents.get(i).getDatecomethai());
                                if(parents.get(i).getStatusbealive()==1){
                                    textViewStatusAlive.setText("สถานะ : มีชีวิต");
                                }else if(parents.get(i).getStatusbealive()==2){
                                    textViewStatusAlive.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddress.setText("ที่อยู่ \n"+parents.get(i).getAddress());
                            }
                            textViewLine3.setText("--------------------------------");

                            Listdetailrequest.addView(textViewName);
                            Listdetailrequest.addView(textViewIdcard);
                            Listdetailrequest.addView(textViewbirthday);
                            Listdetailrequest.addView(textViewEthnic);
                            Listdetailrequest.addView(textViewNationality);
                            Listdetailrequest.addView(textViewDatecomethai);
                            Listdetailrequest.addView(textViewStatusAlive);
                            Listdetailrequest.addView(textViewStatusParent);
                            Listdetailrequest.addView(textViewAddress);
                            Listdetailrequest.addView(textViewLine3);

                        }

                        TextView textViewHeadSuggestion = new TextView(ViewSuggestionHistoryActivity.this);
                        textViewHeadSuggestion.setText("\nการพิจารณาให้คำแนะนำ");
                        textViewHeadSuggestion.setTextSize(20);
                        textViewHeadSuggestion.setTypeface(null, Typeface.BOLD);
                        textViewHeadSuggestion.setTextColor(Color.parseColor("#BF9100"));
                        Listdetailrequest.addView(textViewHeadSuggestion);


                        ViewSuggestionHistoryController manager = ViewSuggestionHistoryController.getWsManager(ViewSuggestionHistoryActivity.this);
                        final ProgressDialog progress = ProgressDialog.show(ViewSuggestionHistoryActivity.this, getString(R.string.please_wait),
                                getString(R.string.please_wait), true);

                        final AssignModel assignModel = new AssignModel();
                        assignModel.getAssign().setAssignid(assignid);

                        manager.detailSuggestionByIdAssign(assignModel, new ViewSuggestionHistoryController.ViewSuggestionHistoryControllerListener() {
                            @Override
                            public void onComplete(Object response) {
                                if (((response instanceof AssignModel))) {
                                    AssignModel assignModel1 = (AssignModel) response;
                                    AssignModel.Assign assign = assignModel1.getAssign();
                                    assignModel.setAssign(assign);
                                }
                                progress.dismiss();

                                TextView textViewfactperson = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewfactperson.setText("=ข้อเท็จจริงของ=");
                                textViewfactperson.setTypeface(null, Typeface.BOLD);
                                Listdetailrequest.addView(textViewfactperson);

                                TextView textViewfactpersonshow = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewfactpersonshow.setText(assignModel.getAssign().getFactperson());
                                Listdetailrequest.addView(textViewfactpersonshow);


                                TextView textViewfactfathermother = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewfactfathermother.setText("\n\n=ข้อเท็จจริงของบิดา/มารดา ของ=");
                                textViewfactfathermother.setTypeface(null, Typeface.BOLD);
                                Listdetailrequest.addView(textViewfactfathermother);

                                TextView textViewfactfathermothershow = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewfactfathermothershow.setText(assignModel.getAssign().getFactfathermother());
                                Listdetailrequest.addView(textViewfactfathermothershow);


                                TextView textViewforlegalopinion = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewforlegalopinion.setText("\n\n=ความคิดเห็นทางกฏหมาย(การทะเบียนราษฎร และสัญชาติ)=");
                                textViewforlegalopinion.setTypeface(null, Typeface.BOLD);
                                Listdetailrequest.addView(textViewforlegalopinion);

                                TextView textViewforlegalopinionshow = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewforlegalopinionshow.setText(assignModel.getAssign().getForlegalopinion());
                                Listdetailrequest.addView(textViewforlegalopinionshow);

                                TextView textViewperonstatus = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewperonstatus.setText("\n\n=คำแนะนำ ขั้นตอนการพัฒนาสถานะ=");
                                textViewperonstatus.setTypeface(null, Typeface.BOLD);
                                Listdetailrequest.addView(textViewperonstatus);

                                TextView textViewperonstatusshow = new TextView(ViewSuggestionHistoryActivity.this);
                                textViewperonstatusshow.setText(assignModel.getAssign().getPersonstatus());
                                Listdetailrequest.addView(textViewperonstatusshow);

                            }

                            @Override
                            public void onError(String err) {
                                progress.dismiss();
                                Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });

                TextView textViewDocument = new TextView(ViewSuggestionHistoryActivity.this);
                textViewDocument.setText("=ข้อมูลการเอกสารที่มี=");
                textViewDocument.setTypeface(null, Typeface.BOLD);
                Listdetailrequest.addView(textViewDocument);
                textViewDocument.setTextColor(Color.parseColor("#00189D"));
                TextView textViewCerBirth = new TextView(ViewSuggestionHistoryActivity.this);
                if(requestForHelpModel.getRequestForHelp().getCerbirth()==1){
                    textViewCerBirth.setText("มีเอกสารรับรองการเกิด");
                    textViewCerBirth.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerBirth);

                    TextView textViewTr1 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr1()==0){
                        textViewTr1.setText("-ไม่มีสูติบัตร (ท.ร.๑)");
                        textViewTr1.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr1()==1){
                        textViewTr1.setText("-มีสูติบัตร (ท.ร.๑)");
                        textViewTr1.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr2 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr2()==0){
                        textViewTr2.setText("-ไม่มีสูติบัตร (ท.ร.๒)");
                        textViewTr2.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr2()==1){
                        textViewTr2.setText("-มีสูติบัตร (ท.ร.๒)");
                        textViewTr2.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr3 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr3()==0){
                        textViewTr3.setText("-ไม่มีสูติบัตร (ท.ร.๓)");
                        textViewTr3.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr3()==1){
                        textViewTr3.setText("-มีสูติบัตร (ท.ร.๓)");
                        textViewTr3.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr031 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr0310()==0){
                        textViewTr031.setText("-ไม่มีเอกสารบุตรบุคคลไม่มีสถานะทางทะเบียน (ท.ร.๐๓๑)");
                        textViewTr031.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr0310()==1){
                        textViewTr031.setText("-มีเอกสารบุตรบุคคลไม่มีสถานะทางทะเบียน (ท.ร.๐๓๑)");
                        textViewTr031.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr1front = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr1front()==0){
                        textViewTr1front.setText("-ไม่มีใบรับรองการแจ้งเกิด (ทร.๑ ตอนหน้า)");
                        textViewTr1front.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr1front()==1){
                        textViewTr1front.setText("-มีใบรับรองการแจ้งเกิด (ทร.๑ ตอนหน้า)");
                        textViewTr1front.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr11part1 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr11part1()==0){
                        textViewTr11part1.setText("-ไม่มีหนังสือรับรองการเกิด (ทร.๑/๑ ตอนที่ ๑)");
                        textViewTr11part1.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr11part1()==1){
                        textViewTr11part1.setText("-มีหนังสือรับรองการเกิด (ทร.๑/๑ ตอนที่ ๑)");
                        textViewTr11part1.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewBcerbirth = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getBcerbirth()==0){
                        textViewBcerbirth.setText("-ไม่มีหนังสือรับรองการเกิด");
                        textViewBcerbirth.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getBcerbirth()==1){
                        textViewBcerbirth.setText("-มีหนังสือรับรองการเกิด");
                        textViewBcerbirth.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewBcerplacebirth = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getBcerplacebirth()==0){
                        textViewBcerplacebirth.setText("-ไม่มีหนังสือรับรองสถานที่เกิด");
                        textViewBcerplacebirth.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getBcerplacebirth()==1){
                        textViewBcerplacebirth.setText("-มีหนังสือรับรองสถานที่เกิด");
                        textViewBcerplacebirth.setTextColor(Color.parseColor("#009D1D"));
                    }

                    Listdetailrequest.addView(textViewTr1);
                    Listdetailrequest.addView(textViewTr2);
                    Listdetailrequest.addView(textViewTr3);
                    Listdetailrequest.addView(textViewTr031);
                    Listdetailrequest.addView(textViewTr1front);
                    Listdetailrequest.addView(textViewTr11part1);
                    Listdetailrequest.addView(textViewBcerbirth);
                    Listdetailrequest.addView(textViewBcerplacebirth);

                }
                if (requestForHelpModel.getRequestForHelp().getCerbirth()==2){
                    textViewCerBirth.setText("ไม่มีเอกสารรับรองการเกิด");
                    textViewCerBirth.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerBirth);
                }
                TextView textViewCerRegister = new TextView(ViewSuggestionHistoryActivity.this);
                if(requestForHelpModel.getRequestForHelp().getCerregister()==1){
                    textViewCerRegister.setText("มีเอกสารหลักฐานประเภททะเบียน");
                    textViewCerRegister.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerRegister);

                    TextView textViewTr14 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr14()==0){
                        textViewTr14.setText("-ไม่มีทะเบียนบ้าน (ท.ร.๑๔)");
                        textViewTr14.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr14()==1){
                        textViewTr14.setText("-มีทะเบียนบ้าน (ท.ร.๑๔)");
                        textViewTr14.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr133 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr13()==0){
                        textViewTr133.setText("-ไม่มีทะเบียนบ้านในลักษณะชั่วคราว (ท.ร.๑๓)");
                        textViewTr133.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr13()==1){
                        textViewTr133.setText("-มีทะเบียนบ้านในลักษณะชั่วคราว (ท.ร.๑๓)");
                        textViewTr133.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewFmperson = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getFmperson()==0){
                        textViewFmperson.setText("-ไม่มีแบบฟอร์มประวัติบุคคลบนพื้นที่สูง");
                        textViewFmperson.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getFmperson()==1){
                        textViewFmperson.setText("-มีแบบฟอร์มประวัติบุคคลบนพื้นที่สูง");
                        textViewFmperson.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewHfmpersonno2 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getHfmpersonno2()==0){
                        textViewHfmpersonno2.setText("-ไม่มีทะเบียนประวัติชุมชนบนพื้นที่สูง ตามแผนแม่บทฯ ฉบับที่2");
                        textViewHfmpersonno2.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getHfmpersonno2()==1){
                        textViewHfmpersonno2.setText("-มีทะเบียนประวัติชุมชนบนพื้นที่สูง ตามแผนแม่บทฯ ฉบับที่2");
                        textViewHfmpersonno2.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTrchk = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTrchk()==0){
                        textViewTrchk.setText("-ไม่มีทะเบียนสำรวจบัญชีบุคคลในบ้าน (ทร.ชข.)");
                        textViewTrchk.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTrchk()==1){
                        textViewTrchk.setText("-มีทะเบียนสำรวจบัญชีบุคคลในบ้าน (ทร.ชข.)");
                        textViewTrchk.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr38g = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr38g()==0){
                        textViewTr38g.setText("-ไม่มีทะเบียนบุคคลผู้ไม่มีสถานะทางทะเบียน (ท.ร.๓๘ ก)");
                        textViewTr38g.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr38g()==1){
                        textViewTr38g.setText("-มีทะเบียนบุคคลผู้ไม่มีสถานะทางทะเบียน (ท.ร.๓๘ ก)");
                        textViewTr38g.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr381 = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getTr381()==0){
                        textViewTr381.setText("-ไม่มีทะเบียนแรงงานต่างด้าว");
                        textViewTr381.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getTr381()==1){
                        textViewTr381.setText("-มีทะเบียนแรงงานต่างด้าว");
                        textViewTr381.setTextColor(Color.parseColor("#009D1D"));
                    }

                    Listdetailrequest.addView(textViewTr14);
                    Listdetailrequest.addView(textViewTr133);
                    Listdetailrequest.addView(textViewFmperson);
                    Listdetailrequest.addView(textViewHfmpersonno2);
                    Listdetailrequest.addView(textViewTrchk);
                    Listdetailrequest.addView(textViewTr38g);
                    Listdetailrequest.addView(textViewTr381);
                }
                if(requestForHelpModel.getRequestForHelp().getCerregister()==2){
                    textViewCerRegister.setText("ไม่มีเอกสารหลักฐานประเภททะเบียน");
                    textViewCerRegister.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerRegister);
                }
                TextView textViewCerIdcard = new TextView(ViewSuggestionHistoryActivity.this);
                if(requestForHelpModel.getRequestForHelp().getCeridcard()==1){
                    textViewCerIdcard.setText("มีเอกสารหลักฐานประเภทบัตรประจำตัว");
                    textViewCerIdcard.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerIdcard);

                    TextView textViewThaiidcard = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getThaiidcard()==0){
                        textViewThaiidcard.setText("-ไม่มีบัตรประจำตัวประชาชน");
                        textViewThaiidcard.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getThaiidcard()==1){
                        textViewThaiidcard.setText("-มีบัตรประจำตัวประชาชน");
                        textViewThaiidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewNotthaiidcard = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getNotthaiidcard()==0){
                        textViewNotthaiidcard.setText("-ไม่มีบัตรประจำตัวคนซึ่งไม่มีสัญชาติไทย");
                        textViewNotthaiidcard.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getNotthaiidcard()==1){
                        textViewNotthaiidcard.setText("-มีบัตรประจำตัวคนซึ่งไม่มีสัญชาติไทย");
                        textViewNotthaiidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewStatelessidcard = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getStatelessidcard()==0){
                        textViewStatelessidcard.setText("-ไม่มีบัตรประจำตัวบุคคลที่ไม่มีสถานะทางทะเบียน");
                        textViewStatelessidcard.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getStatelessidcard()==1){
                        textViewStatelessidcard.setText("-มีบัตรประจำตัวบุคคลที่ไม่มีสถานะทางทะเบียน");
                        textViewStatelessidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewRecydencer = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getResidencycer()==0){
                        textViewRecydencer.setText("-ไม่มีใบสำคัญประจำตัวคนต่างด้าว(ใบสำคัญถิ่นที่อยู่)");
                        textViewRecydencer.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getResidencycer()==1){
                        textViewRecydencer.setText("-มีใบสำคัญประจำตัวคนต่างด้าว(ใบสำคัญถิ่นที่อยู่)");
                        textViewRecydencer.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewRefugeidcardwar = new TextView(ViewSuggestionHistoryActivity.this);
                    if(requestForHelpModel.getRequestForHelp().getRefugeeidcardfromwar()==0){
                        textViewRefugeidcardwar.setText("-ไม่มีบัตรประจำตัวผู้ลี้ภัยจากการสู้รบ อื่นๆ");
                        textViewRefugeidcardwar.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestForHelpModel.getRequestForHelp().getRefugeeidcardfromwar()==1){
                        textViewRefugeidcardwar.setText("-มีบัตรประจำตัวผู้ลี้ภัยจากการสู้รบ อื่นๆ");
                        textViewRefugeidcardwar.setTextColor(Color.parseColor("#009D1D"));
                    }

                    Listdetailrequest.addView(textViewThaiidcard);
                    Listdetailrequest.addView(textViewNotthaiidcard);
                    Listdetailrequest.addView(textViewStatelessidcard);
                    Listdetailrequest.addView(textViewRecydencer);
                    Listdetailrequest.addView(textViewRefugeidcardwar);
                }
                if(requestForHelpModel.getRequestForHelp().getCeridcard()==2){
                    textViewCerIdcard.setText("ไม่มีเอกสารหลักฐานประเภทบัตรประจำตัว");
                    textViewCerIdcard.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerIdcard);
                }

            }


            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(ViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();

            }
        });
    }
}
