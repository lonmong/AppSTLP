package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

import stlpapp.finalproject.app.com.appstlp.controller.MoreRequestController;
import stlpapp.finalproject.app.com.appstlp.controller.ViewSuggesstionController;
import stlpapp.finalproject.app.com.appstlp.databinding.ActivityViewSuggestionAndAddMoreRequestBinding;
import stlpapp.finalproject.app.com.appstlp.controller.WSManager;
import stlpapp.finalproject.app.com.appstlp.model.AddressModel;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.EducationModel;
import stlpapp.finalproject.app.com.appstlp.model.MoreRequestModel;
import stlpapp.finalproject.app.com.appstlp.model.ParentModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelp;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPerson;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.model.WitnessModel;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class ViewSuggestionAndAddMoreRequestActivity extends AppCompatActivity {
    private ActivityViewSuggestionAndAddMoreRequestBinding welcomeBinding;
    private GlobalClass globalClass;
    private StatelessPerson statelessPerson;
    private String username="";
    private int statusrequest;
    private int idrequest;
    private RequestForHelpModel requestModel = new RequestForHelpModel();


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    welcomeBinding.textView1.setVisibility(View.VISIBLE);
                    welcomeBinding.cardviewsuggestion.setVisibility(View.VISIBLE);

                    welcomeBinding.textView2.setVisibility(View.GONE);
                    welcomeBinding.txtWhatStory.setVisibility(View.GONE);
                    welcomeBinding.cardviewdetailmorerequest.setVisibility(View.GONE);
                    welcomeBinding.btnRequestmore.setVisibility(View.GONE);
                    welcomeBinding.textView3.setVisibility(View.GONE);
                    welcomeBinding.cardviewprofile.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_morerequest_for_help:
                    if(statusrequest!=3){
                        welcomeBinding.textView2.setVisibility(View.GONE);
                        welcomeBinding.txtWhatStory.setVisibility(View.GONE);
                        welcomeBinding.cardviewdetailmorerequest.setVisibility(View.GONE);
                        welcomeBinding.btnRequestmore.setVisibility(View.GONE);
                    }else{
                        welcomeBinding.textView2.setVisibility(View.VISIBLE);
                        welcomeBinding.txtWhatStory.setVisibility(View.VISIBLE);
                        welcomeBinding.cardviewdetailmorerequest.setVisibility(View.VISIBLE);
                        welcomeBinding.btnRequestmore.setVisibility(View.VISIBLE);
                    }


                    welcomeBinding.textView1.setVisibility(View.GONE);
                    welcomeBinding.cardviewsuggestion.setVisibility(View.GONE);
                    welcomeBinding.textView3.setVisibility(View.GONE);
                    welcomeBinding.cardviewprofile.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_info:
                    welcomeBinding.textView1.setVisibility(View.GONE);
                    welcomeBinding.textView2.setVisibility(View.GONE);
                    welcomeBinding.txtWhatStory.setVisibility(View.GONE);
                    welcomeBinding.cardviewdetailmorerequest.setVisibility(View.GONE);
                    welcomeBinding.btnRequestmore.setVisibility(View.GONE);
                    welcomeBinding.textView3.setVisibility(View.VISIBLE);
                    welcomeBinding.cardviewprofile.setVisibility(View.VISIBLE);
                    welcomeBinding.cardviewsuggestion.setVisibility(View.GONE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        welcomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_view_suggestion_and_add_more_request);
        globalClass = (GlobalClass) getApplicationContext();

        welcomeBinding.textView1.setVisibility(View.VISIBLE);
        welcomeBinding.cardviewsuggestion.setVisibility(View.VISIBLE);

        welcomeBinding.textView2.setVisibility(View.GONE);
        welcomeBinding.txtWhatStory.setVisibility(View.GONE);
        welcomeBinding.btnRequestmore.setVisibility(View.GONE);
        welcomeBinding.textView3.setVisibility(View.GONE);
        welcomeBinding.cardviewprofile.setVisibility(View.GONE);
        welcomeBinding.cardviewdetailmorerequest.setVisibility(View.GONE);
        setProfile();
        listSuggestion();



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_welcome);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    public void setProfile(){
        final LinearLayout linearLayoutProfile = (LinearLayout)findViewById(R.id.LinearListProfile);
        Intent intent = getIntent();
        statelessPerson = intent.getParcelableExtra("StatelessPerson");
        DateFormat dfm = DateFormat.getDateInstance(DateFormat.MEDIUM,new Locale("th", "TH"));
        if(statelessPerson!=null||globalClass.getLogin().getType()==1){
            if(statelessPerson==null){
                statelessPerson = (StatelessPerson) globalClass.getLogin();
            }
            username = statelessPerson.getUsername().toString();
            TextView textViewNameperson = new TextView(this);
            textViewNameperson.setText("ชื่อ : "+ statelessPerson.getNameperson().toString());
            linearLayoutProfile.addView(textViewNameperson);

            TextView textViewEmailperson = new TextView(this);
            textViewEmailperson.setText("อีเมลล์ : "+statelessPerson.getEmailperson().toString());
            linearLayoutProfile.addView(textViewEmailperson);

            TextView textViewTelperson = new TextView(this);
            textViewTelperson.setText("เบอร์โทร : "+statelessPerson.getTelperson().toString());
            linearLayoutProfile.addView(textViewTelperson);

            TextView textViewBirthdayperson = new TextView(this);
            textViewBirthdayperson.setText("วันเกิด : "+dfm.format(statelessPerson.getBirthday()));
            linearLayoutProfile.addView(textViewBirthdayperson);

            TextView textViewReligionperson = new TextView(this);
            textViewReligionperson.setText("ศาสนา : "+statelessPerson.getReligion().toString());
            linearLayoutProfile.addView(textViewReligionperson);

            TextView textViewEthnicperson = new TextView(this);
            textViewEthnicperson.setText("ชาติพันธุ์ : "+statelessPerson.getEthnic().toString());
            linearLayoutProfile.addView(textViewEthnicperson);

            TextView textViewNationalityperson = new TextView(this);
            textViewNationalityperson.setText("สัญชาติ : "+statelessPerson.getNationality().toString());
            linearLayoutProfile.addView(textViewNationalityperson);

            TextView textViewHomeid = new TextView(this);
            textViewHomeid.setText("รหัสประจำบ้าน : "+statelessPerson.getHomeid().toString());
            linearLayoutProfile.addView(textViewHomeid);

            TextView textViewIdcard = new TextView(this);
            textViewIdcard.setText("เลขประจำตัวประชาชน : "+statelessPerson.getIdcard().toString());
            linearLayoutProfile.addView(textViewIdcard);

            TextView textViewStatusMarryperson = new TextView(this);
            if(statelessPerson.getStatusmarry()==1){
                textViewStatusMarryperson.setText("สถานะ : โสด");
            }else if(statelessPerson.getStatusmarry()==2){
                textViewStatusMarryperson.setText("สถานะ : หย่า");
            }else if(statelessPerson.getStatusmarry()==3){
                textViewStatusMarryperson.setText("สถานะ : หม้าย");
            }else if(statelessPerson.getStatusmarry()==4){
                textViewStatusMarryperson.setText("สถานะ : ไม่จดทะเบียนสมรส");
            }else if(statelessPerson.getStatusmarry()==5){
                textViewStatusMarryperson.setText("สถานะ : จดทะเบียนสมรส");
            }
            linearLayoutProfile.addView(textViewStatusMarryperson);

            if(statelessPerson.getStatusmarry()==2||statelessPerson.getStatusmarry()==3||statelessPerson.getStatusmarry()==4||statelessPerson.getStatusmarry()==5){
                TextView textViewDetailSpouse = new TextView(this);
                textViewDetailSpouse.setText("=ข้อมูลคู่สมรส=");
                textViewDetailSpouse.setTypeface(null, Typeface.BOLD);
                textViewDetailSpouse.setTextColor(Color.parseColor("#00189D"));
                TextView textViewNamespouse = new TextView(this);
                textViewNamespouse.setText("ชื่อคู่สมรส : "+statelessPerson.getNameofspouse().toString());

                TextView textViewIdcardSpouse = new TextView(this);
                textViewIdcardSpouse.setText("รหัสประจำตัวคู่สมรส : "+statelessPerson.getIdcardofspouse().toString());

                TextView textViewNationalityspouse = new TextView(this);
                textViewNationalityspouse.setText("สัญชาติคู่สมรส : "+statelessPerson.getNationalityofspouse().toString());

                TextView textViewDateMarry = new TextView(this);
                textViewDateMarry.setText("วันที่สมรส : "+statelessPerson.getDateofmarry().toString());

                TextView textViewAddressspouse = new TextView(this);
                textViewAddressspouse.setText("ที่อยู่คู่สมรส : "+statelessPerson.getAddressofspouse().toString());

                linearLayoutProfile.addView(textViewDetailSpouse);
                linearLayoutProfile.addView(textViewNamespouse);
                linearLayoutProfile.addView(textViewIdcardSpouse);
                linearLayoutProfile.addView(textViewNationalityspouse);
                linearLayoutProfile.addView(textViewDateMarry);
                linearLayoutProfile.addView(textViewAddressspouse);
            }
            TextView textViewStatusPlaceBirth = new TextView(this);
            if(statelessPerson.getStatusplaceofbirth()==1){
                TextView textViewBirth = new TextView(this);
                textViewBirth.setText("=ข้อมูลการเกิด=");
                textViewBirth.setTypeface(null, Typeface.BOLD);
                textViewBirth.setTextColor(Color.parseColor("#00189D"));
                textViewStatusPlaceBirth.setText("สถานะการทราบสถานที่เกิด : ทราบสถานที่เกิด");
                linearLayoutProfile.addView(textViewBirth);
            }else if(statelessPerson.getStatusplaceofbirth()==2){
                TextView textViewBirth = new TextView(this);
                textViewBirth.setText("=ข้อมูลการเกิด=");
                textViewBirth.setTypeface(null, Typeface.BOLD);
                textViewBirth.setTextColor(Color.parseColor("#00189D"));
                textViewStatusPlaceBirth.setText("สถานะการทราบสถานที่เกิด : ไม่ทราบสถานที่เกิด");
                linearLayoutProfile.addView(textViewBirth);
            }
            linearLayoutProfile.addView(textViewStatusPlaceBirth);

            TextView textViewStatusThaiOrAbroadBirth = new TextView(this);
            if(statelessPerson.getStatusthaiorabroadbirth()==1){
                textViewStatusThaiOrAbroadBirth.setText("-เกิดที่ไทย");
            }else if(statelessPerson.getStatusthaiorabroadbirth()==2){
                textViewStatusThaiOrAbroadBirth.setText("-เกิดนอกไทย");
            }
            linearLayoutProfile.addView(textViewStatusThaiOrAbroadBirth);

            if(statelessPerson.getStatusthaiorabroadbirth()==1){
                TextView textViewHospitalbirth = new TextView(this);
                textViewHospitalbirth.setText("ชื่อสถานพยาบาลที่เกิด : "+statelessPerson.getHospitalofbirth().toString());

                TextView textViewVillagebirth = new TextView(this);
                textViewVillagebirth.setText("ที่อยู่หมู่บ้านที่เกิด : "+statelessPerson.getAddressofvillagebirth().toString());

                linearLayoutProfile.addView(textViewHospitalbirth);
                linearLayoutProfile.addView(textViewVillagebirth);

                TextView textViewStatuswitness = new TextView(this);
                if(statelessPerson.getStatuswitness()==1){
                    textViewStatuswitness.setText("มีพยานรู้เห็นการเกิด");
                    textViewStatuswitness.setTextColor(Color.parseColor("#009D1D"));
                }else if(statelessPerson.getStatuswitness()==2){
                    textViewStatuswitness.setText("ไม่มีพยานรู้เห็นการเกิด");
                    textViewStatuswitness.setTextColor(Color.parseColor("#D81101"));
                }
                linearLayoutProfile.addView(textViewStatuswitness);

                if(statelessPerson.getStatuswitness()==1){
                    final WSManager manager = new WSManager(this);
                    final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                            getString(R.string.please_wait), true);
                    StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
                    StatelessPerson statelessPerson = new StatelessPerson();
                    statelessPerson.setUsername(globalClass.getLogin().getUsername());
                    statelessPersonModel.setStatelessPerson(statelessPerson);

                    manager.getListWitnessByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                        @Override
                        public void onComplete(Object response) {
                            progress.dismiss();
                            WitnessModel witnessModel = (WitnessModel) response;
                            List<WitnessModel.Witness> witnesses = witnessModel.getWitnessList();
                            TextView textViewDetailWitness = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewDetailWitness.setText("=ข้อมูลพยานรู้เห็นการเกิด=");
                            textViewDetailWitness.setTypeface(null, Typeface.BOLD);
                            textViewDetailWitness.setTextColor(Color.parseColor("#00189D"));
                            linearLayoutProfile.addView(textViewDetailWitness);

                            for(int i = 0 ; i<witnesses.size();i++){
                                TextView textViewWitnessName = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                                TextView textViewrelationship = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                                TextView textViewStatuswitness = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                                TextView textViewAddresswitness = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                                TextView textViewLine1 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);

                                textViewWitnessName.setText("ชื่อพยานรู้เห็นการเกิดคนที่"+(i+1)+" : "+witnesses.get(i).getNamewitness());
                                textViewrelationship.setText("เกี่ยวข้องเป็น : "+witnesses.get(i).getRelationship());
                                if(witnesses.get(i).getStatusbealive()==1){
                                    textViewStatuswitness.setText("สถานะ : มีชีวิต");
                                }
                                if (witnesses.get(i).getStatusbealive()==2){
                                    textViewStatuswitness.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddresswitness.setText("ที่อยู่ : "+witnesses.get(i).getAddresswitness());
                                textViewLine1.setText("--------------------------------");

                                linearLayoutProfile.addView(textViewWitnessName);
                                linearLayoutProfile.addView(textViewrelationship);
                                linearLayoutProfile.addView(textViewStatuswitness);
                                linearLayoutProfile.addView(textViewAddresswitness);
                                linearLayoutProfile.addView(textViewLine1);
                            }
                        }

                        @Override
                        public void onError(String err) {
                            progress.dismiss();
                            Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }else if(statelessPerson.getStatusthaiorabroadbirth()==2){
                TextView textViewCountryBirth = new TextView(this);
                textViewCountryBirth.setText("ประเทศที่เกิด : "+statelessPerson.getCountryofbirth().toString());

                TextView textViewDistrictComeThai = new TextView(this);
                textViewDistrictComeThai.setText("เข้ามาไทยทางด่านอำเภอ : "+statelessPerson.getDistrictcomethai().toString());

                TextView textViewDateComeThai = new TextView(this);
                textViewDateComeThai.setText("วันที่เดินทางเข้ามาไทย : "+statelessPerson.getDatecomethai().toString());

                TextView textViewModeComethai = new TextView(this);
                textViewModeComethai.setText("เดินทางเข้ามาไทยโดย : "+statelessPerson.getModecomethai().toString());

                final WSManager manager = new WSManager(this);
                final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                        getString(R.string.please_wait), true);
                StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
                StatelessPerson statelessPerson = new StatelessPerson();
                statelessPerson.setUsername(globalClass.getLogin().getUsername());
                statelessPersonModel.setStatelessPerson(statelessPerson);
                manager.getListWitnessByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        WitnessModel witnessModel = (WitnessModel) response;
                        List<WitnessModel.Witness> witnesses = witnessModel.getWitnessList();
                        TextView textViewDetailWitness = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        textViewDetailWitness.setText("=ข้อมูลพยานรู้เห็นการเข้ามาไทย=");
                        textViewDetailWitness.setTypeface(null, Typeface.BOLD);
                        textViewDetailWitness.setTextColor(Color.parseColor("#00189D"));
                        linearLayoutProfile.addView(textViewDetailWitness);

                        for(int i = 0 ; i<witnesses.size();i++){
                            TextView textViewWitnessName = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView textViewrelationship = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView textViewStatuswitness = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView textViewAddresswitness = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);

                            textViewWitnessName.setText("ชื่อพยานรู้เห็นการเข้ามาไทย : \n"+witnesses.get(i).getNamewitness());
                            textViewrelationship.setText("ความเกี่ยวข้อง : "+witnesses.get(i).getRelationship());
                            if(witnesses.get(i).getStatusbealive()==1){
                                textViewStatuswitness.setText("สถานะ : มีชีวิต");
                            }
                            if (witnesses.get(i).getStatusbealive()==2){
                                textViewStatuswitness.setText("สถานะ : เสียชีวิต");
                            }
                            textViewAddresswitness.setText("ที่อยู่ : "+witnesses.get(i).getAddresswitness());

                            linearLayoutProfile.addView(textViewWitnessName);
                            linearLayoutProfile.addView(textViewrelationship);
                            linearLayoutProfile.addView(textViewStatuswitness);
                            linearLayoutProfile.addView(textViewAddresswitness);
                        }
                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });

                linearLayoutProfile.addView(textViewCountryBirth);
                linearLayoutProfile.addView(textViewDistrictComeThai);
                linearLayoutProfile.addView(textViewDateComeThai);
                linearLayoutProfile.addView(textViewModeComethai);
            }

            final WSManager manager = new WSManager(this);
            final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                    getString(R.string.please_wait), true);
            StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
            StatelessPerson statelessPerson = new StatelessPerson();
            statelessPerson.setUsername(globalClass.getLogin().getUsername());
            statelessPersonModel.setStatelessPerson(statelessPerson);

            manager.getListAddressByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                @Override
                public void onComplete(Object response) {
                    progress.dismiss();
                    AddressModel addressModel = (AddressModel) response;
                    List<AddressModel.Address> addresses = addressModel.getAddressList();
                    TextView textViewDetailAddress = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    textViewDetailAddress.setText("=ข้อมูลที่อยู่=");
                    textViewDetailAddress.setTypeface(null, Typeface.BOLD);
                    textViewDetailAddress.setTextColor(Color.parseColor("#00189D"));
                    linearLayoutProfile.addView(textViewDetailAddress);
                    for(int i = 0 ; i<addresses.size();i++){
                        TextView textViewdetailaddress = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewfromyears = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewtoyears = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewhomdstatus = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewstatusaddress = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewLine2 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
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

                        linearLayoutProfile.addView(textViewdetailaddress);
                        linearLayoutProfile.addView(textViewfromyears);
                        linearLayoutProfile.addView(textViewtoyears);
                        linearLayoutProfile.addView(textViewhomdstatus);
                        linearLayoutProfile.addView(textViewstatusaddress);
                        linearLayoutProfile.addView(textViewLine2);
                    }
                }

                @Override
                public void onError(String err) {
                    progress.dismiss();
                    Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });

            manager.getListEducationByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                @Override
                public void onComplete(Object response) {
                    progress.dismiss();
                    EducationModel educationModel = (EducationModel) response;
                    List<EducationModel.Education> educations = educationModel.getEducationList();
                    TextView textViewDetailEducation = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    textViewDetailEducation.setText("=ข้อมูลประวัติการศึกษา=");
                    textViewDetailEducation.setTypeface(null, Typeface.BOLD);
                    textViewDetailEducation.setTextColor(Color.parseColor("#00189D"));
                    linearLayoutProfile.addView(textViewDetailEducation);

                    for(int i = 0 ; i<educations.size();i++){
                        TextView textViewEducation = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        textViewEducation.setText("ระดับการศึกษา : "+educations.get(i).getEducationdetails());
                        linearLayoutProfile.addView(textViewEducation);
                    }
                }

                @Override
                public void onError(String err) {
                    progress.dismiss();
                    Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });
            manager.getListParentByUsername(statelessPersonModel,new WSManager.WSManagerListener() {
                @Override
                public void onComplete(Object response) {
                    progress.dismiss();
                    ParentModel parentModel = (ParentModel) response;
                    List<ParentModel.Parent> parents = parentModel.getParentList();
                    TextView textViewDetailParent = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    textViewDetailParent.setText("=ข้อมูลบิดา/มารดา=");
                    textViewDetailParent.setTypeface(null, Typeface.BOLD);
                    textViewDetailParent.setTextColor(Color.parseColor("#00189D"));
                    linearLayoutProfile.addView(textViewDetailParent);
                    for(int i = 0 ; i<parents.size();i++){
                        TextView textViewName = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewIdcard = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewbirthday = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewEthnic = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewNationality = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewDatecomethai = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewStatusAlive = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewStatusParent = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewAddress = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                        TextView textViewLine3 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
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

                        linearLayoutProfile.addView(textViewName);
                        linearLayoutProfile.addView(textViewIdcard);
                        linearLayoutProfile.addView(textViewbirthday);
                        linearLayoutProfile.addView(textViewEthnic);
                        linearLayoutProfile.addView(textViewNationality);
                        linearLayoutProfile.addView(textViewDatecomethai);
                        linearLayoutProfile.addView(textViewStatusAlive);
                        linearLayoutProfile.addView(textViewStatusParent);
                        linearLayoutProfile.addView(textViewAddress);
                        linearLayoutProfile.addView(textViewLine3);
                    }
                }

                @Override
                public void onError(String err) {
                    progress.dismiss();
                    Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
    public void listSuggestion(){
        StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
        StatelessPerson statelessPerson = new StatelessPerson();
        statelessPerson.setUsername(username);
        statelessPersonModel.setStatelessPerson(statelessPerson);
        WSManager manager = WSManager.getWsManager(ViewSuggestionAndAddMoreRequestActivity.this);
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        final LinearLayout linearLayoutProfile = (LinearLayout)findViewById(R.id.LinearListProfile);

        manager.detailRequestByUsername(statelessPersonModel, new WSManager.WSManagerListener() {
            @Override
            public void onComplete(Object response) {
                if(((response instanceof RequestForHelpModel))){
                    RequestForHelpModel requestForHelpModel = (RequestForHelpModel)response;
                    RequestForHelp requestForHelp = requestForHelpModel.getRequestForHelp();
                    requestModel.setRequestForHelp(requestForHelp);
                }

                TextView textViewDocument = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                textViewDocument.setText("=ข้อมูลการเอกสารที่มี=");
                textViewDocument.setTypeface(null, Typeface.BOLD);
                linearLayoutProfile.addView(textViewDocument);
                textViewDocument.setTextColor(Color.parseColor("#00189D"));
                TextView textViewCerBirth = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                if(requestModel.getRequestForHelp().getCerbirth()==1){
                    textViewCerBirth.setText("มีเอกสารรับรองการเกิด");
                    textViewCerBirth.setTypeface(null, Typeface.BOLD);
                    linearLayoutProfile.addView(textViewCerBirth);

                    TextView textViewTr1 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr1()==0){
                        textViewTr1.setText("-ไม่มีสูติบัตร (ท.ร.๑)");
                        textViewTr1.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr1()==1){
                        textViewTr1.setText("-มีสูติบัตร (ท.ร.๑)");
                        textViewTr1.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr2 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr2()==0){
                        textViewTr2.setText("-ไม่มีสูติบัตร (ท.ร.๒)");
                        textViewTr2.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr2()==1){
                        textViewTr2.setText("-มีสูติบัตร (ท.ร.๒)");
                        textViewTr2.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr3 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr3()==0){
                        textViewTr3.setText("-ไม่มีสูติบัตร (ท.ร.๓)");
                        textViewTr3.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr3()==1){
                        textViewTr3.setText("-มีสูติบัตร (ท.ร.๓)");
                        textViewTr3.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr031 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr0310()==0){
                        textViewTr031.setText("-ไม่มีเอกสารบุตรบุคคลไม่มีสถานะทางทะเบียน (ท.ร.๐๓๑)");
                        textViewTr031.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr0310()==1){
                        textViewTr031.setText("-มีเอกสารบุตรบุคคลไม่มีสถานะทางทะเบียน (ท.ร.๐๓๑)");
                        textViewTr031.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr1front = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr1front()==0){
                        textViewTr1front.setText("-ไม่มีใบรับรองการแจ้งเกิด (ทร.๑ ตอนหน้า)");
                        textViewTr1front.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr1front()==1){
                        textViewTr1front.setText("-มีใบรับรองการแจ้งเกิด (ทร.๑ ตอนหน้า)");
                        textViewTr1front.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr11part1 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr11part1()==0){
                        textViewTr11part1.setText("-ไม่มีหนังสือรับรองการเกิด (ทร.๑/๑ ตอนที่ ๑)");
                        textViewTr11part1.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr11part1()==1){
                        textViewTr11part1.setText("-มีหนังสือรับรองการเกิด (ทร.๑/๑ ตอนที่ ๑)");
                        textViewTr11part1.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewBcerbirth = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getBcerbirth()==0){
                        textViewBcerbirth.setText("-ไม่มีหนังสือรับรองการเกิด");
                        textViewBcerbirth.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getBcerbirth()==1){
                        textViewBcerbirth.setText("-มีหนังสือรับรองการเกิด");
                        textViewBcerbirth.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewBcerplacebirth = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getBcerplacebirth()==0){
                        textViewBcerplacebirth.setText("-ไม่มีหนังสือรับรองสถานที่เกิด");
                        textViewBcerplacebirth.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getBcerplacebirth()==1){
                        textViewBcerplacebirth.setText("-มีหนังสือรับรองสถานที่เกิด");
                        textViewBcerplacebirth.setTextColor(Color.parseColor("#009D1D"));
                    }

                    linearLayoutProfile.addView(textViewTr1);
                    linearLayoutProfile.addView(textViewTr2);
                    linearLayoutProfile.addView(textViewTr3);
                    linearLayoutProfile.addView(textViewTr031);
                    linearLayoutProfile.addView(textViewTr1front);
                    linearLayoutProfile.addView(textViewTr11part1);
                    linearLayoutProfile.addView(textViewBcerbirth);
                    linearLayoutProfile.addView(textViewBcerplacebirth);

                }
                if (requestModel.getRequestForHelp().getCerbirth()==2){
                    textViewCerBirth.setText("ไม่มีเอกสารรับรองการเกิด");
                    textViewCerBirth.setTypeface(null, Typeface.BOLD);
                    linearLayoutProfile.addView(textViewCerBirth);
                }
                TextView textViewCerRegister = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                if(requestModel.getRequestForHelp().getCerregister()==1){
                    textViewCerRegister.setText("มีเอกสารหลักฐานประเภททะเบียน");
                    textViewCerRegister.setTypeface(null, Typeface.BOLD);
                    linearLayoutProfile.addView(textViewCerRegister);

                    TextView textViewTr14 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr14()==0){
                        textViewTr14.setText("-ไม่มีทะเบียนบ้าน (ท.ร.๑๔)");
                        textViewTr14.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr14()==1){
                        textViewTr14.setText("-มีทะเบียนบ้าน (ท.ร.๑๔)");
                        textViewTr14.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr133 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr13()==0){
                        textViewTr133.setText("-ไม่มีทะเบียนบ้านในลักษณะชั่วคราว (ท.ร.๑๓)");
                        textViewTr133.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr13()==1){
                        textViewTr133.setText("-มีทะเบียนบ้านในลักษณะชั่วคราว (ท.ร.๑๓)");
                        textViewTr133.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewFmperson = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getFmperson()==0){
                        textViewFmperson.setText("-ไม่มีแบบฟอร์มประวัติบุคคลบนพื้นที่สูง");
                        textViewFmperson.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getFmperson()==1){
                        textViewFmperson.setText("-มีแบบฟอร์มประวัติบุคคลบนพื้นที่สูง");
                        textViewFmperson.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewHfmpersonno2 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getHfmpersonno2()==0){
                        textViewHfmpersonno2.setText("-ไม่มีทะเบียนประวัติชุมชนบนพื้นที่สูง ตามแผนแม่บทฯ ฉบับที่2");
                        textViewHfmpersonno2.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getHfmpersonno2()==1){
                        textViewHfmpersonno2.setText("-มีทะเบียนประวัติชุมชนบนพื้นที่สูง ตามแผนแม่บทฯ ฉบับที่2");
                        textViewHfmpersonno2.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTrchk = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTrchk()==0){
                        textViewTrchk.setText("-ไม่มีทะเบียนสำรวจบัญชีบุคคลในบ้าน (ทร.ชข.)");
                        textViewTrchk.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTrchk()==1){
                        textViewTrchk.setText("-มีทะเบียนสำรวจบัญชีบุคคลในบ้าน (ทร.ชข.)");
                        textViewTrchk.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr38g = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr38g()==0){
                        textViewTr38g.setText("-ไม่มีทะเบียนบุคคลผู้ไม่มีสถานะทางทะเบียน (ท.ร.๓๘ ก)");
                        textViewTr38g.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr38g()==1){
                        textViewTr38g.setText("-มีทะเบียนบุคคลผู้ไม่มีสถานะทางทะเบียน (ท.ร.๓๘ ก)");
                        textViewTr38g.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr381 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getTr381()==0){
                        textViewTr381.setText("-ไม่มีทะเบียนแรงงานต่างด้าว");
                        textViewTr381.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getTr381()==1){
                        textViewTr381.setText("-มีทะเบียนแรงงานต่างด้าว");
                        textViewTr381.setTextColor(Color.parseColor("#009D1D"));
                    }

                    linearLayoutProfile.addView(textViewTr14);
                    linearLayoutProfile.addView(textViewTr133);
                    linearLayoutProfile.addView(textViewFmperson);
                    linearLayoutProfile.addView(textViewHfmpersonno2);
                    linearLayoutProfile.addView(textViewTrchk);
                    linearLayoutProfile.addView(textViewTr38g);
                    linearLayoutProfile.addView(textViewTr381);
                }
                if(requestModel.getRequestForHelp().getCerregister()==2){
                    textViewCerRegister.setText("ไม่มีเอกสารหลักฐานประเภททะเบียน");
                    textViewCerRegister.setTypeface(null, Typeface.BOLD);
                    linearLayoutProfile.addView(textViewCerRegister);
                }
                TextView textViewCerIdcard = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                if(requestModel.getRequestForHelp().getCeridcard()==1){
                    textViewCerIdcard.setText("มีเอกสารหลักฐานประเภทบัตรประจำตัว");
                    textViewCerIdcard.setTypeface(null, Typeface.BOLD);
                    linearLayoutProfile.addView(textViewCerIdcard);

                    TextView textViewThaiidcard = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getThaiidcard()==0){
                        textViewThaiidcard.setText("-ไม่มีบัตรประจำตัวประชาชน");
                        textViewThaiidcard.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getThaiidcard()==1){
                        textViewThaiidcard.setText("-มีบัตรประจำตัวประชาชน");
                        textViewThaiidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewNotthaiidcard = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getNotthaiidcard()==0){
                        textViewNotthaiidcard.setText("-ไม่มีบัตรประจำตัวคนซึ่งไม่มีสัญชาติไทย");
                        textViewNotthaiidcard.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getNotthaiidcard()==1){
                        textViewNotthaiidcard.setText("-มีบัตรประจำตัวคนซึ่งไม่มีสัญชาติไทย");
                        textViewNotthaiidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewStatelessidcard = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getStatelessidcard()==0){
                        textViewStatelessidcard.setText("-ไม่มีบัตรประจำตัวบุคคลที่ไม่มีสถานะทางทะเบียน");
                        textViewStatelessidcard.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getStatelessidcard()==1){
                        textViewStatelessidcard.setText("-มีบัตรประจำตัวบุคคลที่ไม่มีสถานะทางทะเบียน");
                        textViewStatelessidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewRecydencer = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getResidencycer()==0){
                        textViewRecydencer.setText("-ไม่มีใบสำคัญประจำตัวคนต่างด้าว(ใบสำคัญถิ่นที่อยู่)");
                        textViewRecydencer.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getResidencycer()==1){
                        textViewRecydencer.setText("-มีใบสำคัญประจำตัวคนต่างด้าว(ใบสำคัญถิ่นที่อยู่)");
                        textViewRecydencer.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewRefugeidcardwar = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                    if(requestModel.getRequestForHelp().getRefugeeidcardfromwar()==0){
                        textViewRefugeidcardwar.setText("-ไม่มีบัตรประจำตัวผู้ลี้ภัยจากการสู้รบ อื่นๆ");
                        textViewRefugeidcardwar.setTextColor(Color.parseColor("#D81101"));
                    }else if(requestModel.getRequestForHelp().getRefugeeidcardfromwar()==1){
                        textViewRefugeidcardwar.setText("-มีบัตรประจำตัวผู้ลี้ภัยจากการสู้รบ อื่นๆ");
                        textViewRefugeidcardwar.setTextColor(Color.parseColor("#009D1D"));
                    }

                    linearLayoutProfile.addView(textViewThaiidcard);
                    linearLayoutProfile.addView(textViewNotthaiidcard);
                    linearLayoutProfile.addView(textViewStatelessidcard);
                    linearLayoutProfile.addView(textViewRecydencer);
                    linearLayoutProfile.addView(textViewRefugeidcardwar);
                }
                if(requestModel.getRequestForHelp().getCeridcard()==2){
                    textViewCerIdcard.setText("ไม่มีเอกสารหลักฐานประเภทบัตรประจำตัว");
                    textViewCerIdcard.setTypeface(null, Typeface.BOLD);
                    linearLayoutProfile.addView(textViewCerIdcard);
                }
                statusrequest = requestModel.getRequestForHelp().getStatusrequest();
                idrequest = requestModel.getRequestForHelp().getRequestid();

                ViewSuggesstionController manager = ViewSuggesstionController.getWsManager(ViewSuggestionAndAddMoreRequestActivity.this);
                final AssignModel assignModel = new AssignModel();
                assignModel.getAssign().getRequestforhelp().setRequestid(idrequest);
                manager.listSuggestionByIdRequest(assignModel, new ViewSuggesstionController.ViewSuggesstionControllerListener() {
                    @Override
                    public void onComplete(Object response) {
                        AssignModel assignModel1 = (AssignModel) response;
                        List<AssignModel.Assign> assigns = assignModel1.getAssignList();
                        final LinearLayout linearLayoutSuggestion = (LinearLayout)findViewById(R.id.ListSuggestion);
                        for(int i=0;i<assigns.size();i++){
                            TextView textViewcount = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewcount.setText("คำร้องครั้งที่ : "+(i+1));
                            textViewcount.setTypeface(null, Typeface.BOLD);
                            textViewcount.setTextColor(Color.parseColor("#00D3C6"));
                            linearLayoutSuggestion.addView(textViewcount);

                            TextView textViewfactperson = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewfactperson.setText("=ข้อเท็จจริงของ=");
                            textViewfactperson.setTypeface(null, Typeface.BOLD);
                            linearLayoutSuggestion.addView(textViewfactperson);

                            TextView textViewfactpersonshow = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewfactpersonshow.setText(assigns.get(i).getFactperson());
                            textViewfactpersonshow.setTextColor(Color.parseColor("#11BA06"));
                            linearLayoutSuggestion.addView(textViewfactpersonshow);

                            TextView textViewfactfathermother = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewfactfathermother.setText("=ข้อเท็จจริงของบิดา/มารดา ของ=");
                            textViewfactfathermother.setTypeface(null, Typeface.BOLD);
                            linearLayoutSuggestion.addView(textViewfactfathermother);

                            TextView textViewfactfathermothershow = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewfactfathermothershow.setText(assigns.get(i).getFactfathermother());
                            textViewfactfathermothershow.setTextColor(Color.parseColor("#11BA06"));
                            linearLayoutSuggestion.addView(textViewfactfathermothershow);


                            TextView textViewforlegalopinion = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewforlegalopinion.setText("=ความคิดเห็นทางกฏหมาย(การทะเบียนราษฎร และสัญชาติ)=");
                            textViewforlegalopinion.setTypeface(null, Typeface.BOLD);
                            linearLayoutSuggestion.addView(textViewforlegalopinion);

                            TextView textViewforlegalopinionshow = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewforlegalopinionshow.setText(assigns.get(i).getForlegalopinion());
                            textViewforlegalopinionshow.setTextColor(Color.parseColor("#11BA06"));
                            linearLayoutSuggestion.addView(textViewforlegalopinionshow);

                            TextView textViewperonstatus = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewperonstatus.setText("=คำแนะนำ ขั้นตอนการพัฒนาสถานะ=");
                            textViewperonstatus.setTypeface(null, Typeface.BOLD);
                            linearLayoutSuggestion.addView(textViewperonstatus);

                            TextView textViewperonstatusshow = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            textViewperonstatusshow.setText(assigns.get(i).getPersonstatus());
                            textViewperonstatusshow.setTextColor(Color.parseColor("#11BA06"));
                            linearLayoutSuggestion.addView(textViewperonstatusshow);

                            TextView line = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            line.setText("******************************");
                            linearLayoutSuggestion.addView(line);
                        }

                    }

                    @Override
                    public void onError(String err) {
                        Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_LONG).show();
                        progress.dismiss();
                    }
                });
                final LinearLayout Listdetailrequest = (LinearLayout)findViewById(R.id.detailmorerequest);
                TextView textViewHeadMoreRequest = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                textViewHeadMoreRequest.setText("\nรายการคำร้องเพิ่มเติม");
                textViewHeadMoreRequest.setTextSize(20);
                textViewHeadMoreRequest.setTypeface(null, Typeface.BOLD);
                textViewHeadMoreRequest.setTextColor(Color.parseColor("#5EE500"));
                Listdetailrequest.addView(textViewHeadMoreRequest);

                MoreRequestController managermore = MoreRequestController.getWsManager(ViewSuggestionAndAddMoreRequestActivity.this);

                MoreRequestModel moreRequestModel = new MoreRequestModel();
                moreRequestModel.getMoreRequest().getRequestforhelp().setRequestid(idrequest);

                managermore.moreRequestlistByidRequest(moreRequestModel, new MoreRequestController.MoreRequestControllerListener() {
                    @Override
                    public void onComplete(Object response) {
                        MoreRequestModel moreRequestModel = (MoreRequestModel) response;
                        final List<MoreRequestModel.MoreRequest> moreRequests = moreRequestModel.getMoreRequestList();

                        for(int i = 0 ; i<moreRequests.size();i++){
                            TextView whatestory = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView answer = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView line1 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView line2 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);
                            TextView line3 = new TextView(ViewSuggestionAndAddMoreRequestActivity.this);


                            line1.setText("*คำร้อง*");
                            line1.setTypeface(null, Typeface.BOLD);
                            Listdetailrequest.addView(line1);
                            whatestory.setText(moreRequests.get(i).getWhatstoryforrequest().replaceAll("\\+", " ").replaceAll("%2F", "/").replaceAll("%2C", ",").replaceAll("%0A","\n"));
                            whatestory.setTextColor(Color.parseColor("#D81101"));
                            Listdetailrequest.addView(whatestory);

                            line2.setText("=คำตอบ=");
                            line2.setTypeface(null, Typeface.BOLD);
                            Listdetailrequest.addView(line2);
                            answer.setText(moreRequests.get(i).getAnswer().replaceAll("\\+", " ").replaceAll("%2F", "/").replaceAll("%2C", ",").replaceAll("%0A","\n"));
                            answer.setTextColor(Color.parseColor("#009D1D"));
                            Listdetailrequest.addView(answer);
                            line3.setText("----------------------------------");
                            Listdetailrequest.addView(line3);
                            if(moreRequests.get(i).getStatusmrequest() == 1){
                                Button btndelete = new Button(ViewSuggestionAndAddMoreRequestActivity.this);
                                btndelete.setText("ลบ");
                                btndelete.setTypeface(null, Typeface.BOLD);
                                btndelete.setTextColor(Color.parseColor("#D81101"));
                                Listdetailrequest.addView(btndelete);

                                final int finalI = i;
                                btndelete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(ViewSuggestionAndAddMoreRequestActivity.this);
                                        builder.setTitle("คำเตือน");
                                        builder.setMessage("ต้องการลบคำร้องนี้หรือไม่?");
                                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                final ProgressDialog progress = ProgressDialog.show(ViewSuggestionAndAddMoreRequestActivity.this, getString(R.string.please_wait),
                                                        getString(R.string.please_wait), true);

                                                MoreRequestController manager = MoreRequestController.getWsManager(ViewSuggestionAndAddMoreRequestActivity.this);
                                                MoreRequestModel moreRequestModel = new MoreRequestModel();
                                                moreRequestModel.getMoreRequest().setMorerequestid(moreRequests.get(finalI).getMorerequestid());

                                                manager.removeMoreRequest(moreRequestModel, new MoreRequestController.MoreRequestControllerListener() {
                                                    @Override
                                                    public void onComplete(Object response) {
                                                        progress.dismiss();
                                                        Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, "ลบข้อมูลสำเร็จ", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(ViewSuggestionAndAddMoreRequestActivity.this, ViewSuggestionAndAddMoreRequestActivity.class);
                                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        startActivity(intent);
                                                        finish();
                                                    }

                                                    @Override
                                                    public void onError(String err) {
                                                        progress.dismiss();
                                                        Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                                                    }
                                                });

                                            }
                                        });
                                        builder.setNeutralButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {

                                            }
                                        });
                                        AlertDialog alert = builder.create();
                                        alert.show();

                                    }
                                });
                            }

                        }
                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });
                progress.dismiss();
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_LONG).show();
            }
        });

    }

    public void isAddMoreRequest(View view) {
        if (welcomeBinding.txtWhatStory.getText() == null || welcomeBinding.txtWhatStory.getText().toString().isEmpty()) {
            welcomeBinding.txtWhatStory.setError(getString(R.string.please_input_data));
            return;
        }

        MoreRequestController manager = MoreRequestController.getWsManager(ViewSuggestionAndAddMoreRequestActivity.this);
        MoreRequestModel moreRequestModel = new MoreRequestModel();
        moreRequestModel.getMoreRequest().setWhatstoryforrequest(welcomeBinding.txtWhatStory.getText().toString().replaceAll(" ","+").replaceAll("/","%2F").replaceAll(",","%2C"));
        moreRequestModel.getMoreRequest().setAnswer("-");
        moreRequestModel.getMoreRequest().setStatusmrequest(1);
        moreRequestModel.getMoreRequest().getRequestforhelp().setRequestid(idrequest);

        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        manager.isAddMoreRequest(moreRequestModel, new MoreRequestController.MoreRequestControllerListener() {
            @Override
            public void onComplete(Object response) {
                progress.dismiss();
                Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, "เขียนคำร้องเพื่มเติมสำเร็จ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ViewSuggestionAndAddMoreRequestActivity.this, ViewSuggestionAndAddMoreRequestActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(ViewSuggestionAndAddMoreRequestActivity.this, err, Toast.LENGTH_LONG).show();
            }
        });
    }
}
