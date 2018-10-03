package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import stlpapp.finalproject.app.com.appstlp.controller.SelectStaffsForRequestController;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.CenterModel;
import stlpapp.finalproject.app.com.appstlp.model.Staff;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;

public class SelectStaffForRequestActivity extends AppCompatActivity {
    private GlobalClass globalClass;
    private String nameperson="";
    private int requestid;
    private TextView textViewNameperson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_staff_for_request);
        globalClass = (GlobalClass) getApplicationContext();

        Intent intent = getIntent();
        nameperson = intent.getStringExtra("nampeerson");
        requestid = intent.getIntExtra("requestid",0);

        textViewNameperson = (TextView)findViewById(R.id.textNameperson);
        textViewNameperson.setText("คำร้องของ : "+nameperson);

        listStaffsByTelCenter();

    }

    private void listStaffsByTelCenter(){

        final Staff staff = (Staff)globalClass.getLogin();
        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        SelectStaffsForRequestController manager = SelectStaffsForRequestController.getWsManager(SelectStaffForRequestActivity.this);
        final CenterModel centerModel = new CenterModel();
        centerModel.getCenter().setTelcenter(staff.getCenter().getTelcenter());

        manager.listStaffsByTelCenter(centerModel, new SelectStaffsForRequestController.SelectStaffsForRequestControllerListener() {
            @Override
            public void onComplete(Object response) {
                progress.dismiss();
                StaffModel staffModel = (StaffModel) response;
                final List<Staff> staffs = staffModel.getStaffList();
                LinearLayout linearLayoutListStaff = (LinearLayout) findViewById(R.id.stafflist);
                for (int i = 0; i < staffs.size(); i++) {
                    final CheckBox checkBoxSelect = new CheckBox(SelectStaffForRequestActivity.this);
                    checkBoxSelect.setText(staffs.get(i).getNameperson() + " ตำแหน่ง : " + staffs.get(i).getPosition().replaceAll("\\+"," "));
                    checkBoxSelect.setHint(staffs.get(i).getUsername());
                    final int finalI = i;
                    checkBoxSelect.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(checkBoxSelect.isChecked()){
                                AlertDialog.Builder builder = new AlertDialog.Builder(SelectStaffForRequestActivity.this);
                                builder.setTitle("คำเตือน");
                                builder.setMessage("ส่งคำร้องให้กับ "+staffs.get(finalI).getNameperson() + " หรือไม่?");
                                builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final ProgressDialog progress = ProgressDialog.show(SelectStaffForRequestActivity.this, getString(R.string.please_wait),
                                                getString(R.string.please_wait), true);

                                        SelectStaffsForRequestController manager = SelectStaffsForRequestController.getWsManager(SelectStaffForRequestActivity.this);

                                        AssignModel assignModel = new AssignModel();
                                        assignModel.getAssign().setFactperson("-");
                                        assignModel.getAssign().setFactfathermother("-");
                                        assignModel.getAssign().setForlegalopinion("-");
                                        assignModel.getAssign().setPersonstatus("-");
                                        assignModel.getAssign().setStatusassign(1);
                                        assignModel.getAssign().getStaff().setUsername(checkBoxSelect.getHint().toString());
                                        assignModel.getAssign().getRequestforhelp().setRequestid(requestid);

                                        manager.setStaffForRequest(assignModel, new SelectStaffsForRequestController.SelectStaffsForRequestControllerListener() {
                                            @Override
                                            public void onComplete(Object response) {
                                                progress.dismiss();
                                                Toast.makeText(SelectStaffForRequestActivity.this, "เพิ่มเรียบร้อย", Toast.LENGTH_SHORT).show();
                                                checkBoxSelect.setVisibility(View.GONE);
                                            }

                                            @Override
                                            public void onError(String err) {
                                                Toast.makeText(SelectStaffForRequestActivity.this, err, Toast.LENGTH_SHORT).show();
                                                progress.dismiss();
                                            }
                                        });

                                    }
                                });
                                builder.setNeutralButton("ยกเลิก", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        checkBoxSelect.setChecked(false);
                                    }
                                });
                                AlertDialog alert = builder.create();
                                alert.show();
                            }

                        }
                    });

                    linearLayoutListStaff.addView(checkBoxSelect);
                }
                Button btngotolistrequest = new Button(SelectStaffForRequestActivity.this);
                btngotolistrequest.setText("กระบวนการรับเรื่องเสร็จสิ้น");
                btngotolistrequest.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SelectStaffForRequestActivity.this);
                        builder.setTitle("คำเตือน");
                        builder.setMessage("กระบวนการรับเรื่องเสร็จสิ้น?");
                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SelectStaffForRequestActivity.this, ProfilePresidentActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();
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
                btngotolistrequest.setTextColor(Color.parseColor("#BF9100"));
                linearLayoutListStaff.addView(btngotolistrequest);
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(SelectStaffForRequestActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
