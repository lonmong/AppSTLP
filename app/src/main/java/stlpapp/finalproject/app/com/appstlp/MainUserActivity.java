package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

import stlpapp.finalproject.app.com.appstlp.controller.WSManager;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPerson;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class MainUserActivity extends AppCompatActivity {
    private GlobalClass globalClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        globalClass = (GlobalClass) getApplicationContext();
        LinearLayout linear_main = (LinearLayout)findViewById(R.id.linear_main);
        final LinearLayout linear_requestforhelp = (LinearLayout)findViewById(R.id.linearrequest);

        if (globalClass.getLogin().getType() == 1) {
            linear_main.removeView(findViewById(R.id.cardviewstaff));
            linear_main.removeView(findViewById(R.id.cardviewpresident));

            StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
            StatelessPerson statelessPerson = new StatelessPerson();
            statelessPerson.setUsername(globalClass.getLogin().getUsername());
            statelessPersonModel.setStatelessPerson(statelessPerson);
            WSManager manager = WSManager.getWsManager(MainUserActivity.this);
            final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                    getString(R.string.please_wait), true);
            manager.detailRequestByUsername(statelessPersonModel, new WSManager.WSManagerListener() {
                @Override
                public void onComplete(Object response) {
                    RequestForHelpModel requestForHelpModel = (RequestForHelpModel)response;
                    final TextView statusRequest = (TextView)findViewById(R.id.textStatusRequest);
                    if (requestForHelpModel.getRequestForHelp().getStatusrequest() == 0){
                        statusRequest.setText("สถานะคำร้อง : ไม่รับเรื่องคำร้อง");
                        statusRequest.setTextColor(Color.parseColor("#F70202"));
                    }else if(requestForHelpModel.getRequestForHelp().getStatusrequest() == 1){
                        statusRequest.setText("สถานะคำร้อง : รอการอนุมัติคำร้อง");
                        statusRequest.setTextColor(Color.parseColor("#B30AF7"));
                    }else if(requestForHelpModel.getRequestForHelp().getStatusrequest() == 2){
                        statusRequest.setText("สถานะคำร้อง : เจ้าหน้าที่กำลังพิจารณา");
                        statusRequest.setTextColor(Color.parseColor("#B30AF7"));
                    }else if(requestForHelpModel.getRequestForHelp().getStatusrequest() == 3){
                        statusRequest.setText("สถานะคำร้อง : การพิจารณาเสร็จสิ้น");
                        statusRequest.setTextColor(Color.parseColor("#0FA700"));
                    }
                    progress.dismiss();
                }

                @Override
                public void onError(String err) {
                    final TextView statusRequest = (TextView)findViewById(R.id.textStatusRequest);
                    statusRequest.setText("บัญชีนี้ยังไม่ได้เขียนคำร้อง");
                    statusRequest.setTextColor(Color.parseColor("#F70202"));
                    progress.dismiss();
                }
            });



            /*StatelessPerson statelessPerson = (StatelessPerson) globalClass.getLogin();

            TextView textViewIdPerson = new TextView(this);
            textViewIdPerson.setText("หมายเลขบัตรประจำตัวคือ : " + statelessPerson.getIdcard());
            TextView textViewIdcardNo = new TextView(this);
            textViewIdcardNo.setText("หมายเลขประเภทบัตรที่ถือ : " + statelessPerson.getIdcardtype().getIdcardno());
            TextView textViewIdcardCall = new TextView(this);
            textViewIdcardCall.setText("คำเรียกทั่วไปของบัตรคือ : " + statelessPerson.getIdcardtype().getIdcardcall());
            TextView textViewIdcardMean = new TextView(this);
            textViewIdcardMean.setText("ความหมายประเภทบัตรคือ : " + statelessPerson.getIdcardtype().getIdcardmean());
            TextView textViewIdcardjob = new TextView(this);
            textViewIdcardjob.setText("งานที่สามารถทำได้คือ : " + statelessPerson.getIdcardtype().getIdcardjob());
            TextView textViewBenefit = new TextView(this);
            textViewBenefit.setText("สวัสดิการจากภาครัฐ : " + statelessPerson.getIdcardtype().getBenefitsfromgovern());*/

            /*linear_idcardtype.addView(textViewIdPerson);
            linear_idcardtype.addView(textViewIdcardNo);
            linear_idcardtype.addView(textViewIdcardCall);
            linear_idcardtype.addView(textViewIdcardMean);
            linear_idcardtype.addView(textViewIdcardjob);
            linear_idcardtype.addView(textViewBenefit);*/

        }else if(globalClass.getLogin().getType() == 2){
            linear_main.removeView(findViewById(R.id.statelesspersonmain));
            linear_main.removeView(findViewById(R.id.cardviewpresident));
            linear_main.removeView(findViewById(R.id.cardviewrequestforhelp));
        }else if(globalClass.getLogin().getType() == 3){
            linear_main.removeView(findViewById(R.id.cardviewstaff));
            linear_main.removeView(findViewById(R.id.statelesspersonmain));
            linear_main.removeView(findViewById(R.id.cardviewrequestforhelp));
            //linear_main.removeView(findViewById(R.id.linear_requestforhelp));
        }else if(globalClass.getLogin().getType() == 0){
            linear_main.removeView(findViewById(R.id.cardviewstaff));
            linear_main.removeView(findViewById(R.id.statelesspersonmain));
            linear_main.removeView(findViewById(R.id.cardviewpresident));
            linear_main.removeView(findViewById(R.id.cardviewrequestforhelp));
            Toast.makeText(MainUserActivity.this,"สวัสดีผู้ดูแลระบบกลาง!!",Toast.LENGTH_LONG).show();
        }

    }
    public void intenttoProfileStatelessPerson(View view){
        startActivity(new Intent(this, ViewSuggestionAndAddMoreRequestActivity.class));
    }
    public void intenttostaffprofile(View view){
        startActivity(new Intent(this, ProfileStaffAndViewSuggestionHistoryActivity.class));
    }
    public void intenttopresidentprofile(View view){
        startActivity(new Intent(this, ProfilePresidentActivity.class));
    }
    public void intentRequestForHelp(View view){
        startActivity(new Intent(this, RequestForHelpActivity.class));
    }
    public void logOut(View view){
        Intent intent = new Intent(MainUserActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
