package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import stlpapp.finalproject.app.com.appstlp.controller.ChooseTheBestSuggestionController;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;

public class DetailSuggestionActivity extends AppCompatActivity {
    private String nameperson = "";
    private int assignid;
    private TextView textViewNameperson,textViewNameStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_suggestion);

        Intent intent = getIntent();
        nameperson = intent.getStringExtra("nameperson");
        assignid = intent.getIntExtra("assignid",0);

        detailSuggestionByIdassign();
    }

    private void detailSuggestionByIdassign(){
        textViewNameperson = (TextView)findViewById(R.id.textNameperson);
        textViewNameperson.setText("คำร้องของ : "+nameperson);
        textViewNameStaff = (TextView)findViewById(R.id.textDetilSuggesstion);
        final LinearLayout detailsuggestion = (LinearLayout)findViewById(R.id.suggesstiondetail);

        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        ChooseTheBestSuggestionController manager = ChooseTheBestSuggestionController.getWsManager(DetailSuggestionActivity.this);
        final AssignModel assignModel = new AssignModel();
        assignModel.getAssign().setAssignid(assignid);

        manager.detailSuggestionByIdassign(assignModel, new ChooseTheBestSuggestionController.ChooseTheBestSuggestionControllerListener() {
            @Override
            public void onComplete(Object response) {
                if(((response instanceof AssignModel))){
                    AssignModel assignModel1 = (AssignModel) response;
                    AssignModel.Assign assign = assignModel1.getAssign();
                    assignModel.setAssign(assign);
                }
                progress.dismiss();
                textViewNameStaff.setText("สรุป/วิเคราะห์("+assignModel.getAssign().getStaff().getNameperson()+")");
                TextView textViewfactperson = new TextView(DetailSuggestionActivity.this);
                textViewfactperson.setText("=ข้อเท็จจริงของ=");
                textViewfactperson.setTypeface(null, Typeface.BOLD);
                detailsuggestion.addView(textViewfactperson);

                TextView textViewfactpersonshow = new TextView(DetailSuggestionActivity.this);
                textViewfactpersonshow.setText(assignModel.getAssign().getFactperson());
                detailsuggestion.addView(textViewfactpersonshow);

                TextView textViewfactfathermother = new TextView(DetailSuggestionActivity.this);
                textViewfactfathermother.setText("=ข้อเท็จจริงของบิดา/มารดา ของ=");
                textViewfactfathermother.setTypeface(null, Typeface.BOLD);
                detailsuggestion.addView(textViewfactfathermother);

                TextView textViewfactfathermothershow = new TextView(DetailSuggestionActivity.this);
                textViewfactfathermothershow.setText(assignModel.getAssign().getFactfathermother());
                detailsuggestion.addView(textViewfactfathermothershow);


                TextView textViewforlegalopinion = new TextView(DetailSuggestionActivity.this);
                textViewforlegalopinion.setText("=ความคิดเห็นทางกฏหมาย(การทะเบียนราษฎร และสัญชาติ)=");
                textViewforlegalopinion.setTypeface(null, Typeface.BOLD);
                detailsuggestion.addView(textViewforlegalopinion);

                TextView textViewforlegalopinionshow = new TextView(DetailSuggestionActivity.this);
                textViewforlegalopinionshow.setText(assignModel.getAssign().getForlegalopinion());
                detailsuggestion.addView(textViewforlegalopinionshow);

                TextView textViewperonstatus = new TextView(DetailSuggestionActivity.this);
                textViewperonstatus.setText("=คำแนะนำ ขั้นตอนการพัฒนาสถานะ=");
                textViewperonstatus.setTypeface(null, Typeface.BOLD);
                detailsuggestion.addView(textViewperonstatus);

                TextView textViewperonstatusshow = new TextView(DetailSuggestionActivity.this);
                textViewperonstatusshow.setText(assignModel.getAssign().getPersonstatus());
                detailsuggestion.addView(textViewperonstatusshow);

                Button btnselestthissuggestion = new Button(DetailSuggestionActivity.this);
                btnselestthissuggestion.setText("เลือกคำแนะนำนี้");
                btnselestthissuggestion.setTextColor(Color.parseColor("#0FA700"));
                detailsuggestion.addView(btnselestthissuggestion);
                btnselestthissuggestion.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(DetailSuggestionActivity.this);
                        builder.setTitle("คำเตือน");
                        builder.setMessage("เลือกคำแนะนำนี้หรือไม่?");
                        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final ProgressDialog progress = ProgressDialog.show(DetailSuggestionActivity.this, getString(R.string.please_wait),
                                        getString(R.string.please_wait), true);

                                ChooseTheBestSuggestionController manager = ChooseTheBestSuggestionController.getWsManager(DetailSuggestionActivity.this);
                                AssignModel assignModel1 = new AssignModel();
                                assignModel1.getAssign().setAssignid(assignid);

                                manager.setBestSuggestionForRequest(assignModel1, new ChooseTheBestSuggestionController.ChooseTheBestSuggestionControllerListener() {
                                    @Override
                                    public void onComplete(Object response) {
                                        progress.dismiss();
                                        Toast.makeText(DetailSuggestionActivity.this, "เสร็จสิ้นการเลือกคำแนะนำ", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(DetailSuggestionActivity.this, MainUserActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onError(String err) {
                                        Toast.makeText(DetailSuggestionActivity.this, err, Toast.LENGTH_SHORT).show();
                                        progress.dismiss();
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

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(DetailSuggestionActivity.this, err, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
