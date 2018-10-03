package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import stlpapp.finalproject.app.com.appstlp.controller.ExportToPDFController;
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



public class ExportToPDFActivity extends AppCompatActivity {
    private String username = "";
    private int requestid;
    private String namepresident;
    private String namepersonToFile;

    private String nameperson;
    private String telperson;
    private String birthdayperson;
    private String religion;
    private String ethnic;
    private String nationality;
    private String homeid;
    private String idcardperson;
    private String factperson;
    private String factpersonshow;
    private String factfm;
    private String factfmshow;
    private String forlegal;
    private String forlegalshow;
    private String personstatus;
    private String personstatusshow;
    private String lineshow;
    private String suggesfrom;
    private String namecenter;
    private String namepresidentshow;
    private String headsugges;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_to_pdf);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        requestid = intent.getIntExtra("requestid", 0);
        namepresident = intent.getStringExtra("presidentname");
        detailRequest();

    }

    public void detailRequest() {
        final LinearLayout Listdetailrequest = (LinearLayout) findViewById(R.id.detailrequest);
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);
        final DateFormat dfm = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("th", "TH"));
        ExportToPDFController manager = ExportToPDFController.getWsManager(ExportToPDFActivity.this);
        final RequestForHelpModel requestForHelpModel = new RequestForHelpModel();
        final RequestForHelp requestForHelp = new RequestForHelp();
        requestForHelp.setRequestid(requestid);
        requestForHelpModel.setRequestForHelp(requestForHelp);

        manager.detailRequestByidRequest(requestForHelpModel, new ExportToPDFController.ExportToPDFControllerListener() {
            @Override
            public void onComplete(Object response) {
                if (((response instanceof RequestForHelpModel))) {
                    RequestForHelpModel requestForHelpModel1 = (RequestForHelpModel) response;
                    RequestForHelp requestForHelp1 = requestForHelpModel1.getRequestForHelp();
                    requestForHelpModel.setRequestForHelp(requestForHelp1);
                }
                progress.dismiss();

                final TextView textViewNameperson = new TextView(ExportToPDFActivity.this);
                textViewNameperson.setText("ชื่อ : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getNameperson().toString());
                nameperson = textViewNameperson.getText().toString();
                namepersonToFile = requestForHelpModel.getRequestForHelp().getStatelessperon().getNameperson().toString();
                Listdetailrequest.addView(textViewNameperson);

                final TextView textViewEmailperson = new TextView(ExportToPDFActivity.this);
                textViewEmailperson.setText("อีเมลล์ : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getEmailperson().toString());
                Listdetailrequest.addView(textViewEmailperson);

                final TextView textViewTelperson = new TextView(ExportToPDFActivity.this);
                textViewTelperson.setText("เบอร์โทร : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getTelperson().toString());
                telperson = textViewTelperson.getText().toString();
                Listdetailrequest.addView(textViewTelperson);

                final TextView textViewBirthdayperson = new TextView(ExportToPDFActivity.this);
                textViewBirthdayperson.setText("วันเกิด : " + dfm.format(requestForHelpModel.getRequestForHelp().getStatelessperon().getBirthday()));
                birthdayperson = textViewBirthdayperson.getText().toString();
                Listdetailrequest.addView(textViewBirthdayperson);

                final TextView textViewReligionperson = new TextView(ExportToPDFActivity.this);
                textViewReligionperson.setText("ศาสนา : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getReligion().toString());
                religion = textViewReligionperson.getText().toString();
                Listdetailrequest.addView(textViewReligionperson);

                final TextView textViewEthnicperson = new TextView(ExportToPDFActivity.this);
                textViewEthnicperson.setText("ชาติพันธุ์ : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getEthnic().toString());
                ethnic = textViewEthnicperson.getText().toString();
                Listdetailrequest.addView(textViewEthnicperson);

                final TextView textViewNationalityperson = new TextView(ExportToPDFActivity.this);
                textViewNationalityperson.setText("สัญชาติ : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getNationality().toString());
                nationality = textViewNationalityperson.getText().toString();
                Listdetailrequest.addView(textViewNationalityperson);

                final TextView textViewHomeid = new TextView(ExportToPDFActivity.this);
                textViewHomeid.setText("รหัสประจำบ้าน : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getHomeid().toString());
                homeid = textViewHomeid.getText().toString();
                Listdetailrequest.addView(textViewHomeid);

                final TextView textViewIdcard = new TextView(ExportToPDFActivity.this);
                textViewIdcard.setText("เลขประจำตัวประชาชน : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getIdcard().toString());
                idcardperson = textViewIdcard.getText().toString();
                Listdetailrequest.addView(textViewIdcard);

                final TextView textViewStatusMarryperson = new TextView(ExportToPDFActivity.this);
                if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 1) {
                    textViewStatusMarryperson.setText("สถานะ : โสด");
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 2) {
                    textViewStatusMarryperson.setText("สถานะ : หย่า");
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 3) {
                    textViewStatusMarryperson.setText("สถานะ : หม้าย");
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 4) {
                    textViewStatusMarryperson.setText("สถานะ : ไม่จดทะเบียนสมรส");
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 5) {
                    textViewStatusMarryperson.setText("สถานะ : จดทะเบียนสมรส");
                }
                Listdetailrequest.addView(textViewStatusMarryperson);

                if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 2 || requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 3 || requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 4 || requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusmarry() == 5) {
                    final TextView textViewDetailSpouse = new TextView(ExportToPDFActivity.this);
                    textViewDetailSpouse.setText("=ข้อมูลคู่สมรส=");
                    textViewDetailSpouse.setTypeface(null, Typeface.BOLD);
                    textViewDetailSpouse.setTextColor(Color.parseColor("#00189D"));

                    TextView textViewNamespouse = new TextView(ExportToPDFActivity.this);
                    textViewNamespouse.setText("ชื่อคู่สมรส : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getNameofspouse().toString());

                    TextView textViewIdcardSpouse = new TextView(ExportToPDFActivity.this);
                    textViewIdcardSpouse.setText("รหัสประจำตัวคู่สมรส : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getIdcardofspouse().toString());

                    TextView textViewNationalityspouse = new TextView(ExportToPDFActivity.this);
                    textViewNationalityspouse.setText("สัญชาติคู่สมรส : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getNationalityofspouse().toString());

                    TextView textViewDateMarry = new TextView(ExportToPDFActivity.this);
                    textViewDateMarry.setText("วันที่สมรส : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getDateofmarry().toString());


                    TextView textViewAddressspouse = new TextView(ExportToPDFActivity.this);
                    textViewAddressspouse.setText("ที่อยู่คู่สมรส : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getAddressofspouse().toString());

                    Listdetailrequest.addView(textViewDetailSpouse);
                    Listdetailrequest.addView(textViewNamespouse);
                    Listdetailrequest.addView(textViewIdcardSpouse);
                    Listdetailrequest.addView(textViewNationalityspouse);
                    Listdetailrequest.addView(textViewDateMarry);
                    Listdetailrequest.addView(textViewAddressspouse);
                }
                TextView textViewStatusPlaceBirth = new TextView(ExportToPDFActivity.this);
                if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusplaceofbirth() == 1) {
                    TextView textViewBirth = new TextView(ExportToPDFActivity.this);
                    textViewBirth.setText("=ข้อมูลการเกิด=");
                    textViewBirth.setTypeface(null, Typeface.BOLD);
                    textViewBirth.setTextColor(Color.parseColor("#00189D"));


                    textViewStatusPlaceBirth.setText("สถานะการทราบสถานที่เกิด : ทราบสถานที่เกิด");

                    Listdetailrequest.addView(textViewBirth);
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusplaceofbirth() == 2) {
                    TextView textViewBirth = new TextView(ExportToPDFActivity.this);
                    textViewBirth.setText("=ข้อมูลการเกิด=");
                    textViewBirth.setTypeface(null, Typeface.BOLD);
                    textViewBirth.setTextColor(Color.parseColor("#00189D"));

                    textViewStatusPlaceBirth.setText("สถานะการทราบสถานที่เกิด : ไม่ทราบสถานที่เกิด");
                    Listdetailrequest.addView(textViewBirth);
                }
                Listdetailrequest.addView(textViewStatusPlaceBirth);

                TextView textViewStatusThaiOrAbroadBirth = new TextView(ExportToPDFActivity.this);
                if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth() == 1) {
                    textViewStatusThaiOrAbroadBirth.setText("-เกิดที่ไทย");
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth() == 2) {
                    textViewStatusThaiOrAbroadBirth.setText("-เกิดนอกไทย");
                }

                Listdetailrequest.addView(textViewStatusThaiOrAbroadBirth);

                if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth() == 1) {
                    TextView textViewHospitalbirth = new TextView(ExportToPDFActivity.this);
                    textViewHospitalbirth.setText("ชื่อสถานพยาบาลที่เกิด : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getHospitalofbirth().toString());

                    TextView textViewVillagebirth = new TextView(ExportToPDFActivity.this);
                    textViewVillagebirth.setText("ที่อยู่หมู่บ้านที่เกิด : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getAddressofvillagebirth().toString());

                    Listdetailrequest.addView(textViewHospitalbirth);
                    Listdetailrequest.addView(textViewVillagebirth);

                    TextView textViewStatuswitness = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatuswitness() == 1) {
                        textViewStatuswitness.setText("มีพยานรู้เห็นการเกิด");
                        textViewStatuswitness.setTextColor(Color.parseColor("#009D1D"));
                    } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatuswitness() == 2) {
                        textViewStatuswitness.setText("ไม่มีพยานรู้เห็นการเกิด");
                        textViewStatuswitness.setTextColor(Color.parseColor("#D81101"));
                    }
                    Listdetailrequest.addView(textViewStatuswitness);

                    if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatuswitness() == 1) {
                        final WSManager manager = new WSManager(ExportToPDFActivity.this);
                        final ProgressDialog progress = ProgressDialog.show(ExportToPDFActivity.this, getString(R.string.please_wait),
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
                                TextView textViewDetailWitness = new TextView(ExportToPDFActivity.this);
                                textViewDetailWitness.setText("=ข้อมูลพยานรู้เห็นการเกิด=");
                                textViewDetailWitness.setTypeface(null, Typeface.BOLD);
                                textViewDetailWitness.setTextColor(Color.parseColor("#00189D"));

                                Listdetailrequest.addView(textViewDetailWitness);

                                for (int i = 0; i < witnesses.size(); i++) {
                                    TextView textViewWitnessName = new TextView(ExportToPDFActivity.this);
                                    TextView textViewrelationship = new TextView(ExportToPDFActivity.this);
                                    TextView textViewStatuswitness = new TextView(ExportToPDFActivity.this);
                                    TextView textViewAddresswitness = new TextView(ExportToPDFActivity.this);
                                    TextView textViewLine1 = new TextView(ExportToPDFActivity.this);

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
                                Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                } else if (requestForHelpModel.getRequestForHelp().getStatelessperon().getStatusthaiorabroadbirth() == 2) {
                    TextView textViewCountryBirth = new TextView(ExportToPDFActivity.this);
                    textViewCountryBirth.setText("ประเทศที่เกิด : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getCountryofbirth().toString());


                    TextView textViewDistrictComeThai = new TextView(ExportToPDFActivity.this);
                    textViewDistrictComeThai.setText("เข้ามาไทยทางด่านอำเภอ : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getDistrictcomethai().toString());

                    TextView textViewDateComeThai = new TextView(ExportToPDFActivity.this);
                    textViewDateComeThai.setText("วันที่เดินทางเข้ามาไทย : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getDatecomethai().toString());

                    TextView textViewModeComethai = new TextView(ExportToPDFActivity.this);
                    textViewModeComethai.setText("เดินทางเข้ามาไทยโดย : " + requestForHelpModel.getRequestForHelp().getStatelessperon().getModecomethai().toString());

                    final WSManager manager = new WSManager(ExportToPDFActivity.this);
                    final ProgressDialog progress = ProgressDialog.show(ExportToPDFActivity.this, getString(R.string.please_wait),
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
                            TextView textViewDetailWitness = new TextView(ExportToPDFActivity.this);
                            textViewDetailWitness.setText("=ข้อมูลพยานรู้เห็นการเข้ามาไทย=");
                            textViewDetailWitness.setTypeface(null, Typeface.BOLD);
                            textViewDetailWitness.setTextColor(Color.parseColor("#00189D"));
                            Listdetailrequest.addView(textViewDetailWitness);

                            for (int i = 0; i < witnesses.size(); i++) {
                                TextView textViewWitnessName = new TextView(ExportToPDFActivity.this);
                                TextView textViewrelationship = new TextView(ExportToPDFActivity.this);
                                TextView textViewStatuswitness = new TextView(ExportToPDFActivity.this);
                                TextView textViewAddresswitness = new TextView(ExportToPDFActivity.this);

                                textViewWitnessName.setText("ชื่อพยานรู้เห็นการเข้ามาไทย : \n" + witnesses.get(i).getNamewitness());

                                textViewrelationship.setText("ความเกี่ยวข้อง : " + witnesses.get(i).getRelationship());
                                if (witnesses.get(i).getStatusbealive() == 1) {
                                    textViewStatuswitness.setText("สถานะ : มีชีวิต");
                                }
                                if (witnesses.get(i).getStatusbealive() == 2) {
                                    textViewStatuswitness.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddresswitness.setText("ที่อยู่ : " + witnesses.get(i).getAddresswitness());

                                Listdetailrequest.addView(textViewWitnessName);
                                Listdetailrequest.addView(textViewrelationship);
                                Listdetailrequest.addView(textViewStatuswitness);
                                Listdetailrequest.addView(textViewAddresswitness);
                            }
                        }

                        @Override
                        public void onError(String err) {
                            progress.dismiss();
                            Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();
                        }
                    });

                    Listdetailrequest.addView(textViewCountryBirth);
                    Listdetailrequest.addView(textViewDistrictComeThai);
                    Listdetailrequest.addView(textViewDateComeThai);
                    Listdetailrequest.addView(textViewModeComethai);
                }

                final WSManager manager = new WSManager(ExportToPDFActivity.this);
                final ProgressDialog progress = ProgressDialog.show(ExportToPDFActivity.this, getString(R.string.please_wait),
                        getString(R.string.please_wait), true);
                StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
                StatelessPerson statelessPerson = new StatelessPerson();
                statelessPerson.setUsername(username);
                statelessPersonModel.setStatelessPerson(statelessPerson);

                manager.getListAddressByUsername(statelessPersonModel, new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        AddressModel addressModel = (AddressModel) response;
                        List<AddressModel.Address> addresses = addressModel.getAddressList();
                        TextView textViewDetailAddress = new TextView(ExportToPDFActivity.this);
                        textViewDetailAddress.setText("=ข้อมูลที่อยู่=");
                        textViewDetailAddress.setTypeface(null, Typeface.BOLD);
                        textViewDetailAddress.setTextColor(Color.parseColor("#00189D"));

                        Listdetailrequest.addView(textViewDetailAddress);
                        for (int i = 0; i < addresses.size(); i++) {
                            TextView textViewdetailaddress = new TextView(ExportToPDFActivity.this);
                            TextView textViewfromyears = new TextView(ExportToPDFActivity.this);
                            TextView textViewtoyears = new TextView(ExportToPDFActivity.this);
                            TextView textViewhomdstatus = new TextView(ExportToPDFActivity.this);
                            TextView textViewstatusaddress = new TextView(ExportToPDFActivity.this);
                            TextView textViewLine2 = new TextView(ExportToPDFActivity.this);
                            if (addresses.get(i).getStatusaddress() == 1) {
                                textViewdetailaddress.setText("ที่อยู่ตามทะเบียนบ้าน \n" + addresses.get(i).getDetailaddress());

                                if (addresses.get(i).getHomestatus() == 1) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านตัวเอง");
                                } else if (addresses.get(i).getHomestatus() == 2) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านเช่า");
                                } else if (addresses.get(i).getHomestatus() == 3) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านอาศัย");
                                }
                            } else if (addresses.get(i).getStatusaddress() == 2) {
                                textViewdetailaddress.setText("ที่อยู่ปัจจุบัน\n" + addresses.get(i).getDetailaddress());

                                textViewfromyears.setText("อาศัยอยู่ตั้งแต่ปี : " + addresses.get(i).getFromyears());
                                textViewtoyears.setText("ถึงปี : " + addresses.get(i).getToyears());
                                if (addresses.get(i).getHomestatus() == 1) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านตัวเอง");
                                } else if (addresses.get(i).getHomestatus() == 2) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านเช่า");
                                } else if (addresses.get(i).getHomestatus() == 3) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านอาศัย");
                                }
                            } else if (addresses.get(i).getStatusaddress() == 3) {
                                textViewdetailaddress.setText("ที่อยู่ก่อนหน้า \n" + addresses.get(i).getDetailaddress());

                                textViewfromyears.setText("อาศัยอยู่ตั้งแต่ปี : " + addresses.get(i).getFromyears());
                                textViewtoyears.setText("ถึงปี : " + addresses.get(i).getToyears());
                                if (addresses.get(i).getHomestatus() == 1) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านตัวเอง");
                                } else if (addresses.get(i).getHomestatus() == 2) {
                                    textViewhomdstatus.setText("สถานะบ้าน : บ้านเช่า");
                                } else if (addresses.get(i).getHomestatus() == 3) {
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
                        Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });

                manager.getListEducationByUsername(statelessPersonModel, new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        EducationModel educationModel = (EducationModel) response;
                        List<EducationModel.Education> educations = educationModel.getEducationList();
                        TextView textViewDetailEducation = new TextView(ExportToPDFActivity.this);
                        textViewDetailEducation.setText("=ข้อมูลประวัติการศึกษา=");
                        textViewDetailEducation.setTypeface(null, Typeface.BOLD);
                        textViewDetailEducation.setTextColor(Color.parseColor("#00189D"));
                        Listdetailrequest.addView(textViewDetailEducation);

                        for (int i = 0; i < educations.size(); i++) {
                            TextView textViewEducation = new TextView(ExportToPDFActivity.this);
                            textViewEducation.setText("ระดับการศึกษา : " + educations.get(i).getEducationdetails());
                            Listdetailrequest.addView(textViewEducation);
                        }
                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });
                manager.getListParentByUsername(statelessPersonModel, new WSManager.WSManagerListener() {
                    @Override
                    public void onComplete(Object response) {
                        progress.dismiss();
                        ParentModel parentModel = (ParentModel) response;
                        List<ParentModel.Parent> parents = parentModel.getParentList();
                        TextView textViewDetailParent = new TextView(ExportToPDFActivity.this);
                        textViewDetailParent.setText("=ข้อมูลบิดา/มารดา=");
                        textViewDetailParent.setTypeface(null, Typeface.BOLD);
                        textViewDetailParent.setTextColor(Color.parseColor("#00189D"));
                        Listdetailrequest.addView(textViewDetailParent);
                        for (int i = 0; i < parents.size(); i++) {
                            TextView textViewName = new TextView(ExportToPDFActivity.this);
                            TextView textViewIdcard = new TextView(ExportToPDFActivity.this);
                            TextView textViewbirthday = new TextView(ExportToPDFActivity.this);
                            TextView textViewEthnic = new TextView(ExportToPDFActivity.this);
                            TextView textViewNationality = new TextView(ExportToPDFActivity.this);
                            TextView textViewDatecomethai = new TextView(ExportToPDFActivity.this);
                            TextView textViewStatusAlive = new TextView(ExportToPDFActivity.this);
                            TextView textViewStatusParent = new TextView(ExportToPDFActivity.this);
                            TextView textViewAddress = new TextView(ExportToPDFActivity.this);
                            TextView textViewLine3 = new TextView(ExportToPDFActivity.this);
                            if (parents.get(i).getStatusparent() == 1) {
                                textViewName.setText("ชื่อบิดา : " + parents.get(i).getName());
                                textViewIdcard.setText("เลขประจำตัวประชาชน : " + parents.get(i).getIdcard());
                                textViewbirthday.setText("วันเกิด : " + parents.get(i).getBirthday());
                                textViewEthnic.setText("ชาติพันธุ์ : " + parents.get(i).getEthnic());
                                textViewNationality.setText("สัญชาติ : " + parents.get(i).getNationality());
                                textViewDatecomethai.setText("วันที่เดินทางมาไทย : " + parents.get(i).getDatecomethai());
                                if (parents.get(i).getStatusbealive() == 1) {
                                    textViewStatusAlive.setText("สถานะ : มีชีวิต");
                                } else if (parents.get(i).getStatusbealive() == 2) {
                                    textViewStatusAlive.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddress.setText("ที่อยู่ \n" + parents.get(i).getAddress());

                            } else if (parents.get(i).getStatusparent() == 2) {

                                textViewName.setText("ชื่อมารดา : " + parents.get(i).getName());
                                textViewIdcard.setText("เลขประจำตัวประชาชน : " + parents.get(i).getIdcard());
                                textViewbirthday.setText("วันเกิด : " + parents.get(i).getBirthday());
                                textViewEthnic.setText("ชาติพันธุ์ : " + parents.get(i).getEthnic());
                                textViewNationality.setText("สัญชาติ : " + parents.get(i).getNationality());
                                textViewDatecomethai.setText("วันที่เดินทางมาไทย : " + parents.get(i).getDatecomethai());
                                if (parents.get(i).getStatusbealive() == 1) {
                                    textViewStatusAlive.setText("สถานะ : มีชีวิต");
                                } else if (parents.get(i).getStatusbealive() == 2) {
                                    textViewStatusAlive.setText("สถานะ : เสียชีวิต");
                                }
                                textViewAddress.setText("ที่อยู่ \n" + parents.get(i).getAddress());
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

                        TextView textViewHeadSuggestion = new TextView(ExportToPDFActivity.this);
                        textViewHeadSuggestion.setText("\nการพิจารณาให้คำแนะนำ");
                        textViewHeadSuggestion.setTextSize(20);
                        textViewHeadSuggestion.setTypeface(null, Typeface.BOLD);
                        textViewHeadSuggestion.setTextColor(Color.parseColor("#BF9100"));
                        headsugges = textViewHeadSuggestion.getText().toString();
                        Listdetailrequest.addView(textViewHeadSuggestion);


                        final ProgressDialog progress = ProgressDialog.show(ExportToPDFActivity.this, getString(R.string.please_wait),
                                getString(R.string.please_wait), true);

                        ExportToPDFController manager = ExportToPDFController.getWsManager(ExportToPDFActivity.this);
                        final RequestForHelpModel requestForHelpModel1 = new RequestForHelpModel();
                        RequestForHelp requestForHelp1 = new RequestForHelp();
                        requestForHelp1.setRequestid(requestid);
                        requestForHelpModel1.setRequestForHelp(requestForHelp1);

                        manager.detailBestSuggestion(requestForHelpModel1, new ExportToPDFController.ExportToPDFControllerListener() {
                            @Override
                            public void onComplete(Object response) {
                                AssignModel assignModel = (AssignModel) response;
                                final List<AssignModel.Assign> assigns = assignModel.getAssignList();
                                for (int i = 0; i < assigns.size(); i++) {

                                    TextView textViewfactperson = new TextView(ExportToPDFActivity.this);
                                    textViewfactperson.setText("=ข้อเท็จจริงของ=");
                                    textViewfactperson.setTypeface(null, Typeface.BOLD);
                                    factperson = textViewfactperson.getText().toString();
                                    Listdetailrequest.addView(textViewfactperson);

                                    TextView textViewfactpersonshow = new TextView(ExportToPDFActivity.this);
                                    textViewfactpersonshow.setText(assigns.get(i).getFactperson());
                                    factpersonshow = textViewfactpersonshow.getText().toString();
                                    Listdetailrequest.addView(textViewfactpersonshow);

                                    TextView textViewfactfathermother = new TextView(ExportToPDFActivity.this);
                                    textViewfactfathermother.setText("=ข้อเท็จจริงของบิดา/มารดา ของ=");
                                    textViewfactfathermother.setTypeface(null, Typeface.BOLD);
                                    factfm = textViewfactfathermother.getText().toString();
                                    Listdetailrequest.addView(textViewfactfathermother);

                                    TextView textViewfactfathermothershow = new TextView(ExportToPDFActivity.this);
                                    textViewfactfathermothershow.setText(assigns.get(i).getFactfathermother());
                                    factfmshow = textViewfactfathermothershow.getText().toString();
                                    Listdetailrequest.addView(textViewfactfathermothershow);


                                    TextView textViewforlegalopinion = new TextView(ExportToPDFActivity.this);
                                    textViewforlegalopinion.setText("=ความคิดเห็นทางกฏหมาย=");
                                    textViewforlegalopinion.setTypeface(null, Typeface.BOLD);
                                    forlegal = textViewforlegalopinion.getText().toString();
                                    Listdetailrequest.addView(textViewforlegalopinion);

                                    TextView textViewforlegalopinionshow = new TextView(ExportToPDFActivity.this);
                                    textViewforlegalopinionshow.setText(assigns.get(i).getForlegalopinion());
                                    forlegalshow = textViewforlegalopinionshow.getText().toString();
                                    Listdetailrequest.addView(textViewforlegalopinionshow);

                                    TextView textViewperonstatus = new TextView(ExportToPDFActivity.this);
                                    textViewperonstatus.setText("=คำแนะนำ ขั้นตอนการพัฒนาสถานะ=");
                                    textViewperonstatus.setTypeface(null, Typeface.BOLD);
                                    personstatus = textViewperonstatus.getText().toString();
                                    Listdetailrequest.addView(textViewperonstatus);

                                    TextView textViewperonstatusshow = new TextView(ExportToPDFActivity.this);
                                    textViewperonstatusshow.setText(assigns.get(i).getPersonstatus());
                                    personstatusshow = textViewperonstatusshow.getText().toString();
                                    Listdetailrequest.addView(textViewperonstatusshow);

                                    final TextView line = new TextView(ExportToPDFActivity.this);
                                    line.setText("**********************************");
                                    lineshow = line.getText().toString();
                                    Listdetailrequest.addView(line);

                                    final TextView textViewSuggesFrom = new TextView(ExportToPDFActivity.this);
                                    textViewSuggesFrom.setText("คำแนะนำจาก : " + assigns.get(i).getStaff().getNameperson());
                                    textViewSuggesFrom.setTextColor(Color.parseColor("#BF9100"));
                                    suggesfrom = textViewSuggesFrom.getText().toString();
                                    Listdetailrequest.addView(textViewSuggesFrom);

                                    final TextView textViewNamecenter = new TextView(ExportToPDFActivity.this);
                                    textViewNamecenter.setText("สังกัด : " + assigns.get(i).getStaff().getCenter().getNamecenter());
                                    textViewNamecenter.setTextColor(Color.parseColor("#BF9100"));
                                    namecenter = textViewNamecenter.getText().toString();
                                    Listdetailrequest.addView(textViewNamecenter);

                                    final TextView textViewNamepresident = new TextView(ExportToPDFActivity.this);
                                    textViewNamepresident.setText("ชื่อประธานศูนย์ฯ : " + namepresident);
                                    textViewNamepresident.setTextColor(Color.parseColor("#BF9100"));
                                    namepresidentshow = textViewNamepresident.getText().toString();
                                    Listdetailrequest.addView(textViewNamepresident);

                                    Button btnmanageassign = new Button(ExportToPDFActivity.this);
                                    btnmanageassign.setText("ส่งออกเป็นไฟล์ PDF");
                                    btnmanageassign.setTextColor(Color.parseColor("#AA1500"));

                                    Listdetailrequest.addView(btnmanageassign);


                                    btnmanageassign.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            genPDFFile();

                                        }
                                    });
                                }
                                progress.dismiss();
                            }

                            @Override
                            public void onError(String err) {
                                progress.dismiss();
                                Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();
                            }
                        });


                    }

                    @Override
                    public void onError(String err) {
                        progress.dismiss();
                        Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();
                    }
                });

                TextView textViewDocument = new TextView(ExportToPDFActivity.this);
                textViewDocument.setText("=ข้อมูลการเอกสารที่มี=");
                textViewDocument.setTypeface(null, Typeface.BOLD);
                Listdetailrequest.addView(textViewDocument);
                textViewDocument.setTextColor(Color.parseColor("#00189D"));
                TextView textViewCerBirth = new TextView(ExportToPDFActivity.this);
                if (requestForHelpModel.getRequestForHelp().getCerbirth() == 1) {
                    textViewCerBirth.setText("มีเอกสารรับรองการเกิด");
                    textViewCerBirth.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerBirth);

                    TextView textViewTr1 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr1() == 0) {
                        textViewTr1.setText("-ไม่มีสูติบัตร (ท.ร.๑)");
                        textViewTr1.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr1() == 1) {
                        textViewTr1.setText("-มีสูติบัตร (ท.ร.๑)");
                        textViewTr1.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr2 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr2() == 0) {
                        textViewTr2.setText("-ไม่มีสูติบัตร (ท.ร.๒)");
                        textViewTr2.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr2() == 1) {
                        textViewTr2.setText("-มีสูติบัตร (ท.ร.๒)");
                        textViewTr2.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr3 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr3() == 0) {
                        textViewTr3.setText("-ไม่มีสูติบัตร (ท.ร.๓)");
                        textViewTr3.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr3() == 1) {
                        textViewTr3.setText("-มีสูติบัตร (ท.ร.๓)");
                        textViewTr3.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTr031 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr0310() == 0) {
                        textViewTr031.setText("-ไม่มีเอกสารบุตรบุคคลไม่มีสถานะทางทะเบียน (ท.ร.๐๓๑)");
                        textViewTr031.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr0310() == 1) {
                        textViewTr031.setText("-มีเอกสารบุตรบุคคลไม่มีสถานะทางทะเบียน (ท.ร.๐๓๑)");
                        textViewTr031.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr1front = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr1front() == 0) {
                        textViewTr1front.setText("-ไม่มีใบรับรองการแจ้งเกิด (ทร.๑ ตอนหน้า)");
                        textViewTr1front.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr1front() == 1) {
                        textViewTr1front.setText("-มีใบรับรองการแจ้งเกิด (ทร.๑ ตอนหน้า)");
                        textViewTr1front.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr11part1 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr11part1() == 0) {
                        textViewTr11part1.setText("-ไม่มีหนังสือรับรองการเกิด (ทร.๑/๑ ตอนที่ ๑)");
                        textViewTr11part1.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr11part1() == 1) {
                        textViewTr11part1.setText("-มีหนังสือรับรองการเกิด (ทร.๑/๑ ตอนที่ ๑)");
                        textViewTr11part1.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewBcerbirth = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getBcerbirth() == 0) {
                        textViewBcerbirth.setText("-ไม่มีหนังสือรับรองการเกิด");
                        textViewBcerbirth.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getBcerbirth() == 1) {
                        textViewBcerbirth.setText("-มีหนังสือรับรองการเกิด");
                        textViewBcerbirth.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewBcerplacebirth = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getBcerplacebirth() == 0) {
                        textViewBcerplacebirth.setText("-ไม่มีหนังสือรับรองสถานที่เกิด");
                        textViewBcerplacebirth.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getBcerplacebirth() == 1) {
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
                if (requestForHelpModel.getRequestForHelp().getCerbirth() == 2) {
                    textViewCerBirth.setText("ไม่มีเอกสารรับรองการเกิด");
                    textViewCerBirth.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerBirth);
                }
                TextView textViewCerRegister = new TextView(ExportToPDFActivity.this);
                if (requestForHelpModel.getRequestForHelp().getCerregister() == 1) {
                    textViewCerRegister.setText("มีเอกสารหลักฐานประเภททะเบียน");
                    textViewCerRegister.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerRegister);

                    TextView textViewTr14 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr14() == 0) {
                        textViewTr14.setText("-ไม่มีทะเบียนบ้าน (ท.ร.๑๔)");
                        textViewTr14.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr14() == 1) {
                        textViewTr14.setText("-มีทะเบียนบ้าน (ท.ร.๑๔)");
                        textViewTr14.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr133 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr13() == 0) {
                        textViewTr133.setText("-ไม่มีทะเบียนบ้านในลักษณะชั่วคราว (ท.ร.๑๓)");
                        textViewTr133.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr13() == 1) {
                        textViewTr133.setText("-มีทะเบียนบ้านในลักษณะชั่วคราว (ท.ร.๑๓)");
                        textViewTr133.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewFmperson = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getFmperson() == 0) {
                        textViewFmperson.setText("-ไม่มีแบบฟอร์มประวัติบุคคลบนพื้นที่สูง");
                        textViewFmperson.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getFmperson() == 1) {
                        textViewFmperson.setText("-มีแบบฟอร์มประวัติบุคคลบนพื้นที่สูง");
                        textViewFmperson.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewHfmpersonno2 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getHfmpersonno2() == 0) {
                        textViewHfmpersonno2.setText("-ไม่มีทะเบียนประวัติชุมชนบนพื้นที่สูง ตามแผนแม่บทฯ ฉบับที่2");
                        textViewHfmpersonno2.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getHfmpersonno2() == 1) {
                        textViewHfmpersonno2.setText("-มีทะเบียนประวัติชุมชนบนพื้นที่สูง ตามแผนแม่บทฯ ฉบับที่2");
                        textViewHfmpersonno2.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewTrchk = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTrchk() == 0) {
                        textViewTrchk.setText("-ไม่มีทะเบียนสำรวจบัญชีบุคคลในบ้าน (ทร.ชข.)");
                        textViewTrchk.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTrchk() == 1) {
                        textViewTrchk.setText("-มีทะเบียนสำรวจบัญชีบุคคลในบ้าน (ทร.ชข.)");
                        textViewTrchk.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr38g = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr38g() == 0) {
                        textViewTr38g.setText("-ไม่มีทะเบียนบุคคลผู้ไม่มีสถานะทางทะเบียน (ท.ร.๓๘ ก)");
                        textViewTr38g.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr38g() == 1) {
                        textViewTr38g.setText("-มีทะเบียนบุคคลผู้ไม่มีสถานะทางทะเบียน (ท.ร.๓๘ ก)");
                        textViewTr38g.setTextColor(Color.parseColor("#009D1D"));
                    }
                    TextView textViewTr381 = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getTr381() == 0) {
                        textViewTr381.setText("-ไม่มีทะเบียนแรงงานต่างด้าว");
                        textViewTr381.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getTr381() == 1) {
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
                if (requestForHelpModel.getRequestForHelp().getCerregister() == 2) {
                    textViewCerRegister.setText("ไม่มีเอกสารหลักฐานประเภททะเบียน");
                    textViewCerRegister.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerRegister);
                }
                TextView textViewCerIdcard = new TextView(ExportToPDFActivity.this);
                if (requestForHelpModel.getRequestForHelp().getCeridcard() == 1) {
                    textViewCerIdcard.setText("มีเอกสารหลักฐานประเภทบัตรประจำตัว");
                    textViewCerIdcard.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerIdcard);

                    TextView textViewThaiidcard = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getThaiidcard() == 0) {
                        textViewThaiidcard.setText("-ไม่มีบัตรประจำตัวประชาชน");
                        textViewThaiidcard.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getThaiidcard() == 1) {
                        textViewThaiidcard.setText("-มีบัตรประจำตัวประชาชน");
                        textViewThaiidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewNotthaiidcard = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getNotthaiidcard() == 0) {
                        textViewNotthaiidcard.setText("-ไม่มีบัตรประจำตัวคนซึ่งไม่มีสัญชาติไทย");
                        textViewNotthaiidcard.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getNotthaiidcard() == 1) {
                        textViewNotthaiidcard.setText("-มีบัตรประจำตัวคนซึ่งไม่มีสัญชาติไทย");
                        textViewNotthaiidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewStatelessidcard = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getStatelessidcard() == 0) {
                        textViewStatelessidcard.setText("-ไม่มีบัตรประจำตัวบุคคลที่ไม่มีสถานะทางทะเบียน");
                        textViewStatelessidcard.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getStatelessidcard() == 1) {
                        textViewStatelessidcard.setText("-มีบัตรประจำตัวบุคคลที่ไม่มีสถานะทางทะเบียน");
                        textViewStatelessidcard.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewRecydencer = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getResidencycer() == 0) {
                        textViewRecydencer.setText("-ไม่มีใบสำคัญประจำตัวคนต่างด้าว(ใบสำคัญถิ่นที่อยู่)");
                        textViewRecydencer.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getResidencycer() == 1) {
                        textViewRecydencer.setText("-มีใบสำคัญประจำตัวคนต่างด้าว(ใบสำคัญถิ่นที่อยู่)");
                        textViewRecydencer.setTextColor(Color.parseColor("#009D1D"));
                    }

                    TextView textViewRefugeidcardwar = new TextView(ExportToPDFActivity.this);
                    if (requestForHelpModel.getRequestForHelp().getRefugeeidcardfromwar() == 0) {
                        textViewRefugeidcardwar.setText("-ไม่มีบัตรประจำตัวผู้ลี้ภัยจากการสู้รบ อื่นๆ");
                        textViewRefugeidcardwar.setTextColor(Color.parseColor("#D81101"));
                    } else if (requestForHelpModel.getRequestForHelp().getRefugeeidcardfromwar() == 1) {
                        textViewRefugeidcardwar.setText("-มีบัตรประจำตัวผู้ลี้ภัยจากการสู้รบ อื่นๆ");
                        textViewRefugeidcardwar.setTextColor(Color.parseColor("#009D1D"));
                    }

                    Listdetailrequest.addView(textViewThaiidcard);
                    Listdetailrequest.addView(textViewNotthaiidcard);
                    Listdetailrequest.addView(textViewStatelessidcard);
                    Listdetailrequest.addView(textViewRecydencer);
                    Listdetailrequest.addView(textViewRefugeidcardwar);
                }
                if (requestForHelpModel.getRequestForHelp().getCeridcard() == 2) {
                    textViewCerIdcard.setText("ไม่มีเอกสารหลักฐานประเภทบัตรประจำตัว");
                    textViewCerIdcard.setTypeface(null, Typeface.BOLD);
                    Listdetailrequest.addView(textViewCerIdcard);
                }

            }


            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(ExportToPDFActivity.this, err, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void genPDFFile() {
        PdfDocument document = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
        PdfDocument.Page page = document.startPage(pageInfo);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        paint.setColor(Color.BLACK);
        canvas.drawText(nameperson, 15, 20, paint);
        canvas.drawText(telperson, 15, 35, paint);
        canvas.drawText(birthdayperson, 15, 50, paint);
        canvas.drawText(religion, 15, 65, paint);
        canvas.drawText(ethnic, 15, 80, paint);
        canvas.drawText(nationality, 15, 95, paint);
        canvas.drawText(homeid, 15, 115, paint);
        canvas.drawText(idcardperson, 15, 130, paint);

        canvas.drawText(headsugges, 15, 200, paint);
        canvas.drawText(factperson, 15, 215, paint);
        canvas.drawText(factpersonshow, 15, 230, paint);
        canvas.drawText(factfm, 15, 245, paint);
        canvas.drawText(factfmshow, 15, 260, paint);
        canvas.drawText(forlegal, 15, 275, paint);
        canvas.drawText(forlegalshow, 15, 290, paint);
        canvas.drawText(personstatus, 15, 305, paint);
        canvas.drawText(personstatusshow, 15, 320, paint);
        canvas.drawText(lineshow, 15, 335, paint);
        canvas.drawText(suggesfrom, 15, 350, paint);
        canvas.drawText(namecenter, 15, 365, paint);
        canvas.drawText(namepresidentshow, 15, 380, paint);
        canvas.drawText("ลงวันที่ : "+sdf.format(Calendar.getInstance().getTime()), 15, 395, paint);

        document.finishPage(page);

        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/STLPAPP/ไฟล์การพิจารณา/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String targetPdf = directory_path + namepersonToFile + sdf.format(Calendar.getInstance().getTime()) + ".pdf";
        File filePath = new File(targetPdf);
        try {
            document.writeTo(new FileOutputStream(filePath));
            Toast.makeText(ExportToPDFActivity.this, "ส่งไฟล์ไปที่" + directory_path, Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Log.e("main", "error " + e.toString());
            Toast.makeText(ExportToPDFActivity.this, "บางอย่างผิดพลาดกรูณาลองใหม่ภายหลัง..!! " + e.toString(), Toast.LENGTH_LONG).show();
        }
        document.close();
    }
}

