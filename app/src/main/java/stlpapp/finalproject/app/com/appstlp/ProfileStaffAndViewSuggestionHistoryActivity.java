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

import stlpapp.finalproject.app.com.appstlp.controller.ViewSuggestionHistoryController;
import stlpapp.finalproject.app.com.appstlp.controller.WSManager;
import stlpapp.finalproject.app.com.appstlp.databinding.ActivityProfileStaffAndViewSuggestionHistoryBinding;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.Staff;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class ProfileStaffAndViewSuggestionHistoryActivity extends AppCompatActivity {
    private ActivityProfileStaffAndViewSuggestionHistoryBinding profileStaffBinding;
    private GlobalClass globalClass;
    private String username;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    profileStaffBinding.textView1.setVisibility(View.VISIBLE);
                    profileStaffBinding.textView2.setVisibility(View.GONE);
                    profileStaffBinding.textView3.setVisibility(View.GONE);
                    profileStaffBinding.cardviewprofile.setVisibility(View.GONE);
                    profileStaffBinding.cardviewlistrequest.setVisibility(View.VISIBLE);
                    profileStaffBinding.cardviewlistrequestfinish.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_finishgivesuggestion:
                    profileStaffBinding.textView1.setVisibility(View.GONE);
                    profileStaffBinding.cardviewlistrequest.setVisibility(View.GONE);
                    profileStaffBinding.textView3.setVisibility(View.GONE);
                    profileStaffBinding.cardviewprofile.setVisibility(View.GONE);

                    profileStaffBinding.textView2.setVisibility(View.VISIBLE);
                    profileStaffBinding.cardviewlistrequestfinish.setVisibility(View.VISIBLE);
                    return true;

                case R.id.navigation_info:
                    profileStaffBinding.textView2.setVisibility(View.GONE);
                    profileStaffBinding.cardviewlistrequestfinish.setVisibility(View.GONE);
                    profileStaffBinding.textView1.setVisibility(View.GONE);
                    profileStaffBinding.cardviewlistrequest.setVisibility(View.GONE);
                    profileStaffBinding.textView3.setVisibility(View.VISIBLE);
                    profileStaffBinding.cardviewprofile.setVisibility(View.VISIBLE);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalClass = (GlobalClass) getApplicationContext();
        profileStaffBinding = DataBindingUtil.setContentView(this,R.layout.activity_profile_staff_and_view_suggestion_history);

        profileStaffBinding.textView3.setVisibility(View.GONE);
        profileStaffBinding.cardviewprofile.setVisibility(View.GONE);
        profileStaffBinding.cardviewlistrequest.setVisibility(View.VISIBLE);
        profileStaffBinding.textView2.setVisibility(View.GONE);
        profileStaffBinding.cardviewlistrequestfinish.setVisibility(View.GONE);

        Staff staff = (Staff) globalClass.getLogin();
        username = staff.getUsername();
        setProfileStaff();
        listAssignbyUsername();
        listSuggestionHistory();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation_profilestaff);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private void listAssignbyUsername(){
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        WSManager manager = WSManager.getWsManager(ProfileStaffAndViewSuggestionHistoryActivity.this);
        final StaffModel staffModel = new StaffModel();
        staffModel.getStaff().setUsername(username);

        manager.listAssignbyUsername(staffModel, new WSManager.WSManagerListener() {
            @Override
            public void onComplete(Object response) {
                AssignModel assignModel = (AssignModel) response;
                final List<AssignModel.Assign> assigns = assignModel.getAssignList();
                LinearLayout linearLayoutAssign = (LinearLayout) findViewById(R.id.ListAssign);
                for(int i=0;i<assigns.size();i++) {
                    if(assigns.get(i).getStatusassign()==1&&assigns.get(i).getRequestforhelp().getStatusrequest()!=3){
                        final TextView textViewIdrequest = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewIdrequest.setText("รหัสคำแนะนำ : "+assigns.get(i).getAssignid());

                        TextView textViewPersonName = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewPersonName.setText("คำร้องของ : " + assigns.get(i).getRequestforhelp().getStatelessperon().getNameperson());

                        TextView textViewreIdcardPerson = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewreIdcardPerson.setText("เลขประจำตัว 13 หลัก : " + assigns.get(i).getRequestforhelp().getStatelessperon().getIdcard());

                        TextView textViewGender = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        if (assigns.get(i).getRequestforhelp().getStatelessperon().getGender() == 1) {
                            textViewGender.setText("เพศ : ชาย");
                        } else {
                            textViewGender.setText("เพศ : หญิง");
                        }

                        TextView textViewStatusRequest = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        if (assigns.get(i).getRequestforhelp().getStatusrequest() == 1) {
                            textViewStatusRequest.setText("สถานะคำร้อง : รอการอนุมัติจากประธานศูนย์");
                            textViewStatusRequest.setTextColor(Color.parseColor("#F70202"));
                        } else if (assigns.get(i).getRequestforhelp().getStatusrequest() == 2) {
                            textViewStatusRequest.setText("สถานะคำร้อง : รอการพิจารณาให้คำแนะนำ");
                            textViewStatusRequest.setTextColor(Color.parseColor("#B30AF7"));
                        }

                        Button btnmanagerequest = new Button(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        btnmanagerequest.setText("กดเพื่อดูรายละเอียด");

                        linearLayoutAssign.addView(textViewIdrequest);
                        linearLayoutAssign.addView(textViewPersonName);
                        linearLayoutAssign.addView(textViewreIdcardPerson);
                        linearLayoutAssign.addView(textViewGender);
                        linearLayoutAssign.addView(textViewStatusRequest);
                        linearLayoutAssign.addView(btnmanagerequest);
                        final int finalI = i;

                        btnmanagerequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProfileStaffAndViewSuggestionHistoryActivity.this, GiveSuggenstionActivity.class);
                                intent.putExtra("idrequest", assigns.get(finalI).getRequestforhelp().getRequestid());
                                intent.putExtra("assignid", assigns.get(finalI).getAssignid());
                                intent.putExtra("username", assigns.get(finalI).getRequestforhelp().getStatelessperon().getUsername());
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }else if(assigns.get(i).getStatusassign()==3){
                        final TextView textViewIdrequest = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewIdrequest.setText("รหัสคำแนะนำ : "+assigns.get(i).getAssignid());

                        TextView textViewPersonName = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewPersonName.setText("คำร้องของ : " + assigns.get(i).getRequestforhelp().getStatelessperon().getNameperson());

                        TextView textViewreIdcardPerson = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewreIdcardPerson.setText("เลขประจำตัว 13 หลัก : " + assigns.get(i).getRequestforhelp().getStatelessperon().getIdcard());

                        TextView textViewGender = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        if (assigns.get(i).getRequestforhelp().getStatelessperon().getGender() == 1) {
                            textViewGender.setText("เพศ : ชาย");
                        } else {
                            textViewGender.setText("เพศ : หญิง");
                        }

                        TextView textViewStatusRequest = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewStatusRequest.setText("สถานะคำร้อง : การพิจารณาถูกเลือก");
                        textViewStatusRequest.setTextColor(Color.parseColor("#0FA700"));

                        Button btnmanagerequest = new Button(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        btnmanagerequest.setText("กดเพื่อดูรายละเอียดคำร้องเพิ่มเติม");

                        linearLayoutAssign.addView(textViewIdrequest);
                        linearLayoutAssign.addView(textViewPersonName);
                        linearLayoutAssign.addView(textViewreIdcardPerson);
                        linearLayoutAssign.addView(textViewGender);
                        linearLayoutAssign.addView(textViewStatusRequest);
                        linearLayoutAssign.addView(btnmanagerequest);
                        final int finalI = i;

                        btnmanagerequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProfileStaffAndViewSuggestionHistoryActivity.this, MorerequestActivity.class);
                                intent.putExtra("idrequest", assigns.get(finalI).getRequestforhelp().getRequestid());
                                intent.putExtra("assignid", assigns.get(finalI).getAssignid());
                                intent.putExtra("username", assigns.get(finalI).getRequestforhelp().getStatelessperon().getUsername());
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
                Toast.makeText(ProfileStaffAndViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void listSuggestionHistory(){
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        ViewSuggestionHistoryController manager = ViewSuggestionHistoryController.getWsManager(ProfileStaffAndViewSuggestionHistoryActivity.this);
        final StaffModel staffModel = new StaffModel();
        staffModel.getStaff().setUsername(username);

        manager.listSuggestionHistory(staffModel, new ViewSuggestionHistoryController.ViewSuggestionHistoryControllerListener() {
            @Override
            public void onComplete(Object response) {
                AssignModel assignModel = (AssignModel) response;
                final List<AssignModel.Assign> assigns = assignModel.getAssignList();
                LinearLayout linearLayoutAssignFinish = (LinearLayout) findViewById(R.id.ListAssignFinish);
                for(int i=0;i<assigns.size();i++) {
                        final TextView textViewIdrequest = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewIdrequest.setText("รหัสคำแนะนำ : "+assigns.get(i).getAssignid());

                        TextView textViewPersonName = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewPersonName.setText("คำร้องของ : " + assigns.get(i).getRequestforhelp().getStatelessperon().getNameperson());

                        TextView textViewreIdcardPerson = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        textViewreIdcardPerson.setText("เลขประจำตัว 13 หลัก : " + assigns.get(i).getRequestforhelp().getStatelessperon().getIdcard());

                        TextView textViewGender = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        if (assigns.get(i).getRequestforhelp().getStatelessperon().getGender() == 1) {
                            textViewGender.setText("เพศ : ชาย");
                        } else {
                            textViewGender.setText("เพศ : หญิง");
                        }

                        TextView textViewStatusRequest = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        if (assigns.get(i).getRequestforhelp().getStatusrequest() == 1) {
                            textViewStatusRequest.setText("สถานะคำร้อง : มีการแก้ไขคำร้อง");
                            textViewStatusRequest.setTextColor(Color.parseColor("#F70202"));
                        } else if (assigns.get(i).getRequestforhelp().getStatusrequest() == 2) {
                            textViewStatusRequest.setText("สถานะคำร้อง : ให้คำแนะนำคำร้องนี้แล้ว");
                            textViewStatusRequest.setTextColor(Color.parseColor("#B30AF7"));
                        }else if (assigns.get(i).getRequestforhelp().getStatusrequest() == 3) {
                            textViewStatusRequest.setText("สถานะคำร้อง : การพิจารณาเสร็จสิ้น");
                            textViewStatusRequest.setTextColor(Color.parseColor("#0FA700"));
                        }

                        Button btnmanagerequest = new Button(ProfileStaffAndViewSuggestionHistoryActivity.this);
                        btnmanagerequest.setText("กดเพื่อดูรายละเอียด");

                        linearLayoutAssignFinish.addView(textViewIdrequest);
                        linearLayoutAssignFinish.addView(textViewPersonName);
                        linearLayoutAssignFinish.addView(textViewreIdcardPerson);
                        linearLayoutAssignFinish.addView(textViewGender);
                        linearLayoutAssignFinish.addView(textViewStatusRequest);
                        linearLayoutAssignFinish.addView(btnmanagerequest);
                        final int finalI = i;

                        btnmanagerequest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProfileStaffAndViewSuggestionHistoryActivity.this, ViewSuggestionHistoryActivity.class);
                                intent.putExtra("requestid", assigns.get(finalI).getRequestforhelp().getRequestid());
                                intent.putExtra("assignid", assigns.get(finalI).getAssignid());
                                intent.putExtra("username", assigns.get(finalI).getRequestforhelp().getStatelessperon().getUsername());
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        });
                }
                progress.dismiss();
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(ProfileStaffAndViewSuggestionHistoryActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setProfileStaff(){
        Staff staff = (Staff)globalClass.getLogin();
        LinearLayout linearLayoutProfile = (LinearLayout) findViewById(R.id.LinearListProfile);

        TextView textViewNameStaff = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
        textViewNameStaff.setText("ชื่อ : "+staff.getNameperson());
        TextView textViewPosition = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
        textViewPosition.setText("ตำแหน่ง : "+staff.getPosition().replaceAll("\\+", " ").replaceAll("%2F", "/").replaceAll("%2C", ",").replaceAll("%0A","\n"));
        TextView textViewCenter = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
        textViewCenter.setText("สังกัด : "+staff.getCenter().getNamecenter());
        TextView textViewEmail = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
        textViewEmail.setText("อีเมลล์ : "+staff.getEmailperson());
        TextView textViewTel = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
        textViewTel.setText("เบอร์โทร : "+staff.getTelperson());
        TextView textViewAddress = new TextView(ProfileStaffAndViewSuggestionHistoryActivity.this);
        textViewAddress.setText("ที่อยู่ : \n"+staff.getAddress());


        linearLayoutProfile.addView(textViewNameStaff);
        linearLayoutProfile.addView(textViewPosition);
        linearLayoutProfile.addView(textViewCenter);
        linearLayoutProfile.addView(textViewEmail);
        linearLayoutProfile.addView(textViewTel);
        linearLayoutProfile.addView(textViewAddress);
    }
}
