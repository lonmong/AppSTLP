package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import stlpapp.finalproject.app.com.appstlp.controller.ApproveRequestController;
import stlpapp.finalproject.app.com.appstlp.databinding.ActivityProfilePresidentBinding;
import stlpapp.finalproject.app.com.appstlp.model.CenterModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelp;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.model.Staff;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class ProfilePresidentActivity extends AppCompatActivity {
    private ActivityProfilePresidentBinding profilePresidentBinding;
    private GlobalClass globalClass;
    private String telcenter;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    profilePresidentBinding.textView1.setVisibility(View.VISIBLE);
                    profilePresidentBinding.cardviewlistrequest.setVisibility(View.VISIBLE);

                    profilePresidentBinding.textView2.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequestapprove.setVisibility(View.GONE);

                    profilePresidentBinding.textView3.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewprofile.setVisibility(View.GONE);

                    profilePresidentBinding.textView4.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequestfinish.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_requestapprove:

                    profilePresidentBinding.textView1.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequest.setVisibility(View.GONE);

                    profilePresidentBinding.textView2.setVisibility(View.VISIBLE);
                    profilePresidentBinding.cardviewlistrequestapprove.setVisibility(View.VISIBLE);

                    profilePresidentBinding.textView3.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewprofile.setVisibility(View.GONE);

                    profilePresidentBinding.textView4.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequestfinish.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_requestfinish:

                    profilePresidentBinding.textView4.setVisibility(View.VISIBLE);
                    profilePresidentBinding.cardviewlistrequestfinish.setVisibility(View.VISIBLE);

                    profilePresidentBinding.textView1.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequest.setVisibility(View.GONE);

                    profilePresidentBinding.textView2.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequestapprove.setVisibility(View.GONE);

                    profilePresidentBinding.textView3.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewprofile.setVisibility(View.GONE);

                    return true;
                case R.id.navigation_info:
                    profilePresidentBinding.textView1.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequest.setVisibility(View.GONE);

                    profilePresidentBinding.textView2.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequestapprove.setVisibility(View.GONE);

                    profilePresidentBinding.textView3.setVisibility(View.VISIBLE);
                    profilePresidentBinding.cardviewprofile.setVisibility(View.VISIBLE);

                    profilePresidentBinding.textView4.setVisibility(View.GONE);
                    profilePresidentBinding.cardviewlistrequestfinish.setVisibility(View.GONE);

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        profilePresidentBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile_president);
        globalClass = (GlobalClass) getApplicationContext();

        profilePresidentBinding.textView1.setVisibility(View.VISIBLE);
        profilePresidentBinding.cardviewlistrequest.setVisibility(View.VISIBLE);

        profilePresidentBinding.textView2.setVisibility(View.GONE);
        profilePresidentBinding.cardviewlistrequestapprove.setVisibility(View.GONE);

        profilePresidentBinding.textView3.setVisibility(View.GONE);
        profilePresidentBinding.cardviewprofile.setVisibility(View.GONE);

        profilePresidentBinding.textView4.setVisibility(View.GONE);
        profilePresidentBinding.cardviewlistrequestfinish.setVisibility(View.GONE);

        Staff staff = (Staff) globalClass.getLogin();
        telcenter = staff.getCenter().getTelcenter();

        listRequestByTelCenter();
        setProfilePresident();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_profilepresident);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void listRequestByTelCenter(){
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        ApproveRequestController manager = ApproveRequestController.getWsManager(ProfilePresidentActivity.this);

        manager.listRequestByTelCenter(telcenter, new ApproveRequestController.ApproveRequestControllerListener() {
            @Override
            public void onComplete(Object response) {
                RequestForHelpModel requestForHelpModel = (RequestForHelpModel) response;
                final List<RequestForHelp> requestForHelps = requestForHelpModel.getRequestForHelpList();

                LinearLayout linearLayoutRequest = (LinearLayout) findViewById(R.id.ListRequestCenter);
                LinearLayout linearLayoutRequestapprove = (LinearLayout) findViewById(R.id.ListRequestCenterApprove);
                LinearLayout linearLayoutRequestfinish = (LinearLayout) findViewById(R.id.ListRequestCenterfinish);
                for(int i=0;i<requestForHelps.size();i++) {
                    if (requestForHelps.get(i).getStatusrequest() == 1) {
                        TextView textViewPersonName = new TextView(ProfilePresidentActivity.this);
                        textViewPersonName.setText("\nคำร้องของ : " + requestForHelps.get(i).getStatelessperon().getNameperson());

                        TextView textViewreIdcardPerson = new TextView(ProfilePresidentActivity.this);
                        textViewreIdcardPerson.setText("เลขประจำตัว 13 หลัก : " + requestForHelps.get(i).getStatelessperon().getIdcard());

                        TextView textViewGender = new TextView(ProfilePresidentActivity.this);
                        if (requestForHelps.get(i).getStatelessperon().getGender() == 1) {
                            textViewGender.setText("เพศ : ชาย");
                        } else {
                            textViewGender.setText("เพศ : หญิง");
                        }

                        TextView textViewStatusRequest = new TextView(ProfilePresidentActivity.this);
                        if (requestForHelps.get(i).getStatusrequest() == 1) {
                            textViewStatusRequest.setText("สถานะคำร้อง : รอการอนุมัติจากประธานศูนย์\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#F70202"));
                        } else if (requestForHelps.get(i).getStatusrequest() == 2) {
                            textViewStatusRequest.setText("สถานะคำร้อง : เจ้าหน้าที่กำลังพิจารณา\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#B30AF7"));
                        }

                        Button btnmanagerequest = new Button(ProfilePresidentActivity.this);
                        btnmanagerequest.setText("กดเพื่อดูรายละเอียด");
                        btnmanagerequest.setBackgroundResource(R.drawable.rounded_button_approve);

                        linearLayoutRequest.addView(textViewPersonName);
                        linearLayoutRequest.addView(textViewreIdcardPerson);
                        linearLayoutRequest.addView(textViewGender);
                        linearLayoutRequest.addView(textViewStatusRequest);
                        linearLayoutRequest.addView(btnmanagerequest);

                        final int finalI = i;
                        btnmanagerequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProfilePresidentActivity.this, DetailRequestActivity.class);
                                intent.putExtra("username", requestForHelps.get(finalI).getStatelessperon().getUsername());
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }else if (requestForHelps.get(i).getStatusrequest() == 2) {
                        TextView textViewPersonName = new TextView(ProfilePresidentActivity.this);
                        textViewPersonName.setText("\nคำร้องของ : " + requestForHelps.get(i).getStatelessperon().getNameperson());

                        TextView textViewreIdcardPerson = new TextView(ProfilePresidentActivity.this);
                        textViewreIdcardPerson.setText("เลขประจำตัว 13 หลัก : " + requestForHelps.get(i).getStatelessperon().getIdcard());

                        TextView textViewGender = new TextView(ProfilePresidentActivity.this);
                        if (requestForHelps.get(i).getStatelessperon().getGender() == 1) {
                            textViewGender.setText("เพศ : ชาย");
                        } else {
                            textViewGender.setText("เพศ : หญิง");
                        }

                        TextView textViewStatusRequest = new TextView(ProfilePresidentActivity.this);
                        if (requestForHelps.get(i).getStatusrequest() == 1) {
                            textViewStatusRequest.setText("สถานะคำร้อง : รอการอนุมัติจากประธานศูนย์\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#F70202"));
                        } else if (requestForHelps.get(i).getStatusrequest() == 2) {
                            textViewStatusRequest.setText("สถานะคำร้อง : เจ้าหน้าที่กำลังพิจารณา\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#B30AF7"));
                        }

                        Button btnmanagerequest = new Button(ProfilePresidentActivity.this);
                        btnmanagerequest.setText("กดเพื่อดูรายละเอียด");
                        btnmanagerequest.setBackgroundResource(R.drawable.rounded_button_approve_or_not);

                        linearLayoutRequestapprove.addView(textViewPersonName);
                        linearLayoutRequestapprove.addView(textViewreIdcardPerson);
                        linearLayoutRequestapprove.addView(textViewGender);
                        linearLayoutRequestapprove.addView(textViewStatusRequest);
                        linearLayoutRequestapprove.addView(btnmanagerequest);

                        final int finalI = i;
                        btnmanagerequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProfilePresidentActivity.this, ChooseTheBestSuggestionActivity.class);
                                intent.putExtra("username", requestForHelps.get(finalI).getStatelessperon().getUsername());
                                intent.putExtra("idrequest", requestForHelps.get(finalI).getRequestid());
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        });
                    }else if (requestForHelps.get(i).getStatusrequest() == 3) {
                        TextView textViewPersonName = new TextView(ProfilePresidentActivity.this);
                        textViewPersonName.setText("\nคำร้องของ : " + requestForHelps.get(i).getStatelessperon().getNameperson());

                        TextView textViewreIdcardPerson = new TextView(ProfilePresidentActivity.this);
                        textViewreIdcardPerson.setText("เลขประจำตัว 13 หลัก : " + requestForHelps.get(i).getStatelessperon().getIdcard());

                        TextView textViewGender = new TextView(ProfilePresidentActivity.this);
                        if (requestForHelps.get(i).getStatelessperon().getGender() == 1) {
                            textViewGender.setText("เพศ : ชาย");
                        } else {
                            textViewGender.setText("เพศ : หญิง");
                        }

                        TextView textViewStatusRequest = new TextView(ProfilePresidentActivity.this);
                        if (requestForHelps.get(i).getStatusrequest() == 1) {
                            textViewStatusRequest.setText("สถานะคำร้อง : รอการอนุมัติจากประธานศูนย์\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#F70202"));
                        } else if (requestForHelps.get(i).getStatusrequest() == 2) {
                            textViewStatusRequest.setText("สถานะคำร้อง : เจ้าหน้าที่กำลังพิจารณา\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#B30AF7"));
                        }else if (requestForHelps.get(i).getStatusrequest() == 3) {
                            textViewStatusRequest.setText("สถานะคำร้อง : การพิจารณาเสร็จสิ้น\n");
                            textViewStatusRequest.setTextColor(Color.parseColor("#0FA700"));
                        }

                        Button btnmanagerequest = new Button(ProfilePresidentActivity.this);
                        btnmanagerequest.setText("กดเพื่อดูรายละเอียด");
                        btnmanagerequest.setBackgroundResource(R.drawable.rounded_button_approve);

                        linearLayoutRequestfinish.addView(textViewPersonName);
                        linearLayoutRequestfinish.addView(textViewreIdcardPerson);
                        linearLayoutRequestfinish.addView(textViewGender);
                        linearLayoutRequestfinish.addView(textViewStatusRequest);
                        linearLayoutRequestfinish.addView(btnmanagerequest);
                        final Staff staff = (Staff)globalClass.getLogin();

                        final int finalI = i;
                        btnmanagerequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProfilePresidentActivity.this, ExportToPDFActivity.class);
                                intent.putExtra("username", requestForHelps.get(finalI).getStatelessperon().getUsername());
                                intent.putExtra("requestid", requestForHelps.get(finalI).getRequestid());
                                intent.putExtra("presidentname",staff.getNameperson());
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                }
                progress.dismiss();
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(ProfilePresidentActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setProfilePresident(){
        Staff staff = (Staff)globalClass.getLogin();
        LinearLayout linearLayoutProfile = (LinearLayout) findViewById(R.id.LinearListProfile);

        TextView textViewNameStaff = new TextView(ProfilePresidentActivity.this);
        textViewNameStaff.setText("ชื่อ : "+staff.getNameperson());
        TextView textViewPosition = new TextView(ProfilePresidentActivity.this);
        textViewPosition.setText("ตำแหน่ง : "+staff.getPosition().replaceAll("\\+", " ").replaceAll("%2F", "/").replaceAll("%2C", ",").replaceAll("%0A","\n"));
        TextView textViewCenter = new TextView(ProfilePresidentActivity.this);
        textViewCenter.setText("สังกัด : "+staff.getCenter().getNamecenter());
        TextView textViewEmail = new TextView(ProfilePresidentActivity.this);
        textViewEmail.setText("อีเมลล์ : "+staff.getEmailperson());
        TextView textViewTel = new TextView(ProfilePresidentActivity.this);
        textViewTel.setText("เบอร์โทร : "+staff.getTelperson());
        TextView textViewAddress = new TextView(ProfilePresidentActivity.this);
        textViewAddress.setText("ที่อยู่ : \n"+staff.getAddress());


        linearLayoutProfile.addView(textViewNameStaff);
        linearLayoutProfile.addView(textViewPosition);
        linearLayoutProfile.addView(textViewCenter);
        linearLayoutProfile.addView(textViewEmail);
        linearLayoutProfile.addView(textViewTel);
        linearLayoutProfile.addView(textViewAddress);
    }
}
