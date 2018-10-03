package stlpapp.finalproject.app.com.appstlp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import stlpapp.finalproject.app.com.appstlp.model.StatelessPerson;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class MainUserActivity extends AppCompatActivity {
    private GlobalClass globalClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        globalClass = (GlobalClass) getApplicationContext();
        LinearLayout linear_main = (LinearLayout)findViewById(R.id.linear_main);
        LinearLayout linear_idcardtype = (LinearLayout)findViewById(R.id.linear_idcardtype);

        if (globalClass.getLogin().getType() == 1) {
            linear_main.removeView(findViewById(R.id.linear_staff));
            linear_main.removeView(findViewById(R.id.linear_president));
            StatelessPerson statelessPerson = (StatelessPerson) globalClass.getLogin();

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
            textViewBenefit.setText("สวัสดิการจากภาครัฐ : " + statelessPerson.getIdcardtype().getBenefitsfromgovern());

            linear_idcardtype.addView(textViewIdPerson);
            linear_idcardtype.addView(textViewIdcardNo);
            linear_idcardtype.addView(textViewIdcardCall);
            linear_idcardtype.addView(textViewIdcardMean);
            linear_idcardtype.addView(textViewIdcardjob);
            linear_idcardtype.addView(textViewBenefit);

        }else if(globalClass.getLogin().getType() == 2){
            linear_main.removeView(findViewById(R.id.linear_stateless));
            linear_main.removeView(findViewById(R.id.linear_president));
            linear_main.removeView(findViewById(R.id.cardviewidcardtype));
            linear_main.removeView(findViewById(R.id.linear_requestforhelp));
        }else if(globalClass.getLogin().getType() == 3){
            linear_main.removeView(findViewById(R.id.linear_staff));
            linear_main.removeView(findViewById(R.id.linear_stateless));
            linear_main.removeView(findViewById(R.id.cardviewidcardtype));
            linear_main.removeView(findViewById(R.id.linear_requestforhelp));
        }else if(globalClass.getLogin().getType() == 0){
            linear_main.removeView(findViewById(R.id.linear_staff));
            linear_main.removeView(findViewById(R.id.linear_stateless));
            linear_main.removeView(findViewById(R.id.linear_president));
            linear_main.removeView(findViewById(R.id.cardviewidcardtype));
            linear_main.removeView(findViewById(R.id.linear_requestforhelp));
            Toast.makeText(MainUserActivity.this,"สวัสดีผู้ดูแลระบบ!!",Toast.LENGTH_LONG).show();
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

}
