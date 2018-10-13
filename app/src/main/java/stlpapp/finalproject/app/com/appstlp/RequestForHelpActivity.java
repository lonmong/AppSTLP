package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import java.util.List;

import stlpapp.finalproject.app.com.appstlp.controller.ChooseTheBestSuggestionController;
import stlpapp.finalproject.app.com.appstlp.controller.RequestForHelpController;
import stlpapp.finalproject.app.com.appstlp.databinding.ActivityRequestForHelpBinding;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.CenterModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelp;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class RequestForHelpActivity extends AppCompatActivity {
    private ActivityRequestForHelpBinding mBinding;
    private String centertel;
    private GlobalClass globalClass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        globalClass = (GlobalClass) getApplicationContext();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_request_for_help);
        showListCenter();

        mBinding.tr1.setVisibility(View.GONE);
        mBinding.tr2.setVisibility(View.GONE);
        mBinding.tr3.setVisibility(View.GONE);
        mBinding.tr031.setVisibility(View.GONE);
        mBinding.tr1front.setVisibility(View.GONE);
        mBinding.tr1part1.setVisibility(View.GONE);
        mBinding.bcerbirth.setVisibility(View.GONE);
        mBinding.bcerplacebirth.setVisibility(View.GONE);

        mBinding.tr14.setVisibility(View.GONE);
        mBinding.tr13.setVisibility(View.GONE);
        mBinding.fmperson.setVisibility(View.GONE);
        mBinding.hfmperson.setVisibility(View.GONE);
        mBinding.trChk.setVisibility(View.GONE);
        mBinding.tr381.setVisibility(View.GONE);
        mBinding.tr38g.setVisibility(View.GONE);

        mBinding.thaiidcard.setVisibility(View.GONE);
        mBinding.notthaiidcard.setVisibility(View.GONE);
        mBinding.statelessidcard.setVisibility(View.GONE);
        mBinding.residencycer.setVisibility(View.GONE);
        mBinding.refudgeeWar.setVisibility(View.GONE);

    }

    public void onCerBirthClick(View view){
        int checkedCerBirthRadioButtonId = mBinding.RadioGroupCerBirth.getCheckedRadioButtonId();
        switch (checkedCerBirthRadioButtonId){
            case R.id.yes_cerbirth:
                mBinding.tr1.setVisibility(View.VISIBLE);
                mBinding.tr2.setVisibility(View.VISIBLE);
                mBinding.tr3.setVisibility(View.VISIBLE);
                mBinding.tr031.setVisibility(View.VISIBLE);
                mBinding.tr1front.setVisibility(View.VISIBLE);
                mBinding.tr1part1.setVisibility(View.VISIBLE);
                mBinding.bcerbirth.setVisibility(View.VISIBLE);
                mBinding.bcerplacebirth.setVisibility(View.VISIBLE);

                break;
            case R.id.no_cerbirth:
                mBinding.tr1.setVisibility(View.GONE);
                mBinding.tr1.setChecked(false);
                mBinding.tr2.setVisibility(View.GONE);
                mBinding.tr2.setChecked(false);
                mBinding.tr3.setVisibility(View.GONE);
                mBinding.tr3.setChecked(false);
                mBinding.tr031.setVisibility(View.GONE);
                mBinding.tr031.setChecked(false);
                mBinding.tr1front.setVisibility(View.GONE);
                mBinding.tr1front.setChecked(false);
                mBinding.tr1part1.setVisibility(View.GONE);
                mBinding.tr1part1.setChecked(false);
                mBinding.bcerbirth.setVisibility(View.GONE);
                mBinding.bcerbirth.setChecked(false);
                mBinding.bcerplacebirth.setVisibility(View.GONE);
                mBinding.bcerplacebirth.setChecked(false);

                break;
            default:
                break;
        }
    }
    public void onCerRegisterClick(View view){
        int checkedCerRegisterRadioButtonId = mBinding.RadioGroupCerRegister.getCheckedRadioButtonId();
        switch (checkedCerRegisterRadioButtonId){
            case R.id.yes_cerregister:
                mBinding.tr14.setVisibility(View.VISIBLE);
                mBinding.tr13.setVisibility(View.VISIBLE);
                mBinding.fmperson.setVisibility(View.VISIBLE);
                mBinding.hfmperson.setVisibility(View.VISIBLE);
                mBinding.trChk.setVisibility(View.VISIBLE);
                mBinding.tr38g.setVisibility(View.VISIBLE);
                mBinding.tr381.setVisibility(View.VISIBLE);

                break;
            case R.id.no_cerregister:
                mBinding.tr14.setVisibility(View.GONE);
                mBinding.tr14.setChecked(false);
                mBinding.tr13.setVisibility(View.GONE);
                mBinding.tr13.setChecked(false);
                mBinding.fmperson.setVisibility(View.GONE);
                mBinding.fmperson.setChecked(false);
                mBinding.hfmperson.setVisibility(View.GONE);
                mBinding.hfmperson.setChecked(false);
                mBinding.trChk.setVisibility(View.GONE);
                mBinding.trChk.setChecked(false);
                mBinding.tr38g.setVisibility(View.GONE);
                mBinding.tr38g.setChecked(false);
                mBinding.tr381.setVisibility(View.GONE);
                mBinding.tr381.setChecked(false);

                break;
            default:
                break;
        }
    }
    public void onCerIDCardClick(View view){
        int checkedCerIDCardRadioButtonId = mBinding.RadioGroupCerIDCard.getCheckedRadioButtonId();
        switch (checkedCerIDCardRadioButtonId){
            case R.id.yes_ceridcard:
                mBinding.thaiidcard.setVisibility(View.VISIBLE);
                mBinding.notthaiidcard.setVisibility(View.VISIBLE);
                mBinding.statelessidcard.setVisibility(View.VISIBLE);
                mBinding.residencycer.setVisibility(View.VISIBLE);
                mBinding.refudgeeWar.setVisibility(View.VISIBLE);
                break;
            case R.id.no_cerridcard:
                mBinding.thaiidcard.setVisibility(View.GONE);
                mBinding.thaiidcard.setChecked(false);
                mBinding.notthaiidcard.setVisibility(View.GONE);
                mBinding.notthaiidcard.setChecked(false);
                mBinding.statelessidcard.setVisibility(View.GONE);
                mBinding.statelessidcard.setChecked(false);
                mBinding.residencycer.setVisibility(View.GONE);
                mBinding.residencycer.setChecked(false);
                mBinding.refudgeeWar.setVisibility(View.GONE);
                mBinding.refudgeeWar.setChecked(false);
                break;
            default:
                break;
        }
    }
    public void isAddRequest(View view) {
        if (mBinding.RadioGroupCerBirth.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupCerBirth.requestFocus();
            AlertDialog.Builder builder = new AlertDialog.Builder(RequestForHelpActivity.this);
            builder.setTitle("คำเตือน");
            builder.setMessage(R.string.please_select_status_c_birth);
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }else if (mBinding.RadioGroupCerRegister.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupCerRegister.requestFocus();
            AlertDialog.Builder builder = new AlertDialog.Builder(RequestForHelpActivity.this);
            builder.setTitle("คำเตือน");
            builder.setMessage(R.string.please_select_status_cer_register);
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }else if (mBinding.RadioGroupCerIDCard.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupCerIDCard.requestFocus();
            AlertDialog.Builder builder = new AlertDialog.Builder(RequestForHelpActivity.this);
            builder.setTitle("คำเตือน");
            builder.setMessage(R.string.please_select_status_cer_id_card);
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }else if (mBinding.RadioGroupCenter.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupCenter.requestFocus();
            AlertDialog.Builder builder = new AlertDialog.Builder(RequestForHelpActivity.this);
            builder.setTitle("คำเตือน");
            builder.setMessage(R.string.please_select_center);
            builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }


        RadioButton radiobuttoncerbirth = (RadioButton) findViewById(mBinding.RadioGroupCerBirth.getCheckedRadioButtonId());
        int idcerbirth = mBinding.RadioGroupCerBirth.indexOfChild(radiobuttoncerbirth);

        RadioButton radiobuttoncerregister = (RadioButton) findViewById(mBinding.RadioGroupCerRegister.getCheckedRadioButtonId());
        int idcerregister = mBinding.RadioGroupCerRegister.indexOfChild(radiobuttoncerregister);

        RadioButton radiobuttonceridcard = (RadioButton) findViewById(mBinding.RadioGroupCerIDCard.getCheckedRadioButtonId());
        int idceridcard = mBinding.RadioGroupCerIDCard.indexOfChild(radiobuttonceridcard);


        RequestForHelpController manager = RequestForHelpController.getWsManager(RequestForHelpActivity.this);
        RequestForHelpModel requestForHelpModel = new RequestForHelpModel();
        RequestForHelp requestForHelp = new RequestForHelp();
        requestForHelp.setCerbirth(idcerbirth+1);
        requestForHelp.setCerregister(idcerregister+1);
        requestForHelp.setCeridcard(idceridcard+1);
        int convertcheckbox = 0;

        requestForHelp.setTr1(convertcheckbox);
        if(mBinding.tr1.isChecked()){
            requestForHelp.setTr1(convertcheckbox+1);
        }
        requestForHelp.setTr2(convertcheckbox);
        if(mBinding.tr2.isChecked()){
            requestForHelp.setTr2(convertcheckbox+1);
        }
        requestForHelp.setTr3(convertcheckbox);
        if(mBinding.tr3.isChecked()){
            requestForHelp.setTr3(convertcheckbox+1);
        }
        requestForHelp.setTr0310(convertcheckbox);
        if(mBinding.tr031.isChecked()){
            requestForHelp.setTr0310(convertcheckbox+1);
        }
        requestForHelp.setTr1front(convertcheckbox);
        if(mBinding.tr1front.isChecked()){
            requestForHelp.setTr1front(convertcheckbox+1);
        }
        requestForHelp.setTr11part1(convertcheckbox);
        if(mBinding.tr1part1.isChecked()){
            requestForHelp.setTr11part1(convertcheckbox+1);
        }
        requestForHelp.setBcerbirth(convertcheckbox);
        if(mBinding.bcerbirth.isChecked()){
            requestForHelp.setBcerbirth(convertcheckbox+1);
        }
        requestForHelp.setBcerplacebirth(convertcheckbox);
        if(mBinding.bcerplacebirth.isChecked()){
            requestForHelp.setBcerplacebirth(convertcheckbox+1);
        }
        requestForHelp.setTr14(convertcheckbox);
        if(mBinding.tr14.isChecked()){
            requestForHelp.setTr14(convertcheckbox+1);
        }
        requestForHelp.setTr13(convertcheckbox);
        if(mBinding.tr13.isChecked()){
            requestForHelp.setTr13(convertcheckbox+1);
        }
        requestForHelp.setFmperson(convertcheckbox);
        if(mBinding.fmperson.isChecked()){
            requestForHelp.setFmperson(convertcheckbox+1);
        }
        requestForHelp.setHfmpersonno2(convertcheckbox);
        if(mBinding.hfmperson.isChecked()){
            requestForHelp.setHfmpersonno2(convertcheckbox+1);
        }
        requestForHelp.setTrchk(convertcheckbox);
        if(mBinding.trChk.isChecked()){
            requestForHelp.setTrchk(convertcheckbox+1);
        }
        requestForHelp.setTr38g(convertcheckbox);
        if(mBinding.tr38g.isChecked()){
            requestForHelp.setTr38g(convertcheckbox+1);
        }
        requestForHelp.setTr381(convertcheckbox);
        if(mBinding.tr381.isChecked()){
            requestForHelp.setTr381(convertcheckbox+1);
        }
        requestForHelp.setThaiidcard(convertcheckbox);
        if(mBinding.thaiidcard.isChecked()){
            requestForHelp.setThaiidcard(convertcheckbox+1);
        }
        requestForHelp.setNotthaiidcard(convertcheckbox);
        if(mBinding.notthaiidcard.isChecked()){
            requestForHelp.setNotthaiidcard(convertcheckbox+1);
        }
        requestForHelp.setStatelessidcard(convertcheckbox);
        if(mBinding.statelessidcard.isChecked()){
            requestForHelp.setStatelessidcard(convertcheckbox+1);
        }
        requestForHelp.setResidencycer(convertcheckbox);
        if(mBinding.residencycer.isChecked()){
            requestForHelp.setResidencycer(convertcheckbox+1);
        }
        requestForHelp.setRefugeeidcardfromwar(convertcheckbox);
        if(mBinding.refudgeeWar.isChecked()){
            requestForHelp.setRefugeeidcardfromwar(convertcheckbox+1);
        }
        requestForHelp.setStatusrequest(1);
        requestForHelp.getStatelessperon().setUsername(globalClass.getLogin().getUsername().toString());
        requestForHelp.getCenter().setTelcenter(centertel);
        requestForHelpModel.setRequestForHelp(requestForHelp);

        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        manager.isAddRequest(requestForHelpModel, new RequestForHelpController.RequestForHelpControllerListener() {
            @Override
            public void onComplete(Object response) {
                progress.dismiss();
                Toast.makeText(RequestForHelpActivity.this, "เขียนคำร้องสำเร็จ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RequestForHelpActivity.this, MainUserActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(RequestForHelpActivity.this, err, Toast.LENGTH_LONG).show();
            }
        });

    }

    private void showListCenter() {
        final RequestForHelpController manager = new RequestForHelpController(this);
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);
        manager.getListCenter(new RequestForHelpController.RequestForHelpControllerListener() {
            @Override
            public void onComplete(Object response) {
                progress.dismiss();
                CenterModel centerModel = (CenterModel) response;
                List<CenterModel.Center> centers = centerModel.getCenterList();

                RadioGroup radioGroupcenter = (RadioGroup)findViewById(R.id.RadioGroupCenter);
                for(int i = 0 ; i<centers.size();i++){
                    final RadioButton radioButtonCenter = new RadioButton(RequestForHelpActivity.this);
                    radioButtonCenter.setHint(centers.get(i).getTelcenter());
                    radioButtonCenter.setText(centers.get(i).getNamecenter().replaceAll("\\+", " ").replaceAll("%2F", "/").replaceAll("%2C", ","));
                    radioGroupcenter.addView(radioButtonCenter);
                    radioButtonCenter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            centertel = radioButtonCenter.getHint().toString();
                        }
                    });
                }
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(RequestForHelpActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
