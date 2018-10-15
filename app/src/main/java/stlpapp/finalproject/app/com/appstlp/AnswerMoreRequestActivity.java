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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import stlpapp.finalproject.app.com.appstlp.controller.MoreRequestController;
import stlpapp.finalproject.app.com.appstlp.model.MoreRequestModel;

public class AnswerMoreRequestActivity extends AppCompatActivity {
    private int morerequestid;
    private String whatstory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_more_request);

        Intent intent = getIntent();
        morerequestid = intent.getIntExtra("morerequestid",0);
        whatstory = intent.getStringExtra("whatstory");
        setDetailMoreRequest();
    }

    public void setDetailMoreRequest(){
        final LinearLayout Listdetailmorerequest = (LinearLayout)findViewById(R.id.detailmorerequest);
        TextView textViewWhatstory = new TextView(AnswerMoreRequestActivity.this);
        textViewWhatstory.setText(whatstory.replaceAll("\\+"," ").replaceAll("%2F","/").replaceAll("%2C",",").replaceAll("%0A","\n"));
        textViewWhatstory.setTypeface(null, Typeface.BOLD);
        textViewWhatstory.setTextColor(Color.parseColor("#D81101"));
        Listdetailmorerequest.addView(textViewWhatstory);

        final EditText answertext = new EditText(AnswerMoreRequestActivity.this);
        answertext.setHint("คำตอบ");
        Listdetailmorerequest.addView(answertext);

        Button btnanswer = new Button(AnswerMoreRequestActivity.this);
        btnanswer.setText("ตอบคำร้อง");
        btnanswer.setBackgroundResource(R.drawable.rounded_button_approve_or_not);
        Listdetailmorerequest.addView(btnanswer);
        btnanswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answertext.getText().toString().isEmpty()){
                    answertext.setError("กรุณากรอกข้อมูล");
                    answertext.requestFocus();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(AnswerMoreRequestActivity.this);
                    builder.setTitle("คำเตือน");
                    builder.setMessage("ยืนยันคำตอบ?");
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final ProgressDialog progress = ProgressDialog.show(AnswerMoreRequestActivity.this, getString(R.string.please_wait),
                                    getString(R.string.please_wait), true);

                            MoreRequestController manager = MoreRequestController.getWsManager(AnswerMoreRequestActivity.this);
                            MoreRequestModel moreRequestModel = new MoreRequestModel();
                            moreRequestModel.getMoreRequest().setMorerequestid(morerequestid);
                            moreRequestModel.getMoreRequest().setAnswer(answertext.getText().toString().replaceAll(" ","+").replaceAll("/","%2F").replaceAll(",","%2C").replaceAll("\n","%0A"));

                            manager.setAnswerMoreRequest(moreRequestModel, new MoreRequestController.MoreRequestControllerListener() {
                                @Override
                                public void onComplete(Object response) {
                                    progress.dismiss();
                                    Toast.makeText(AnswerMoreRequestActivity.this, "เสร็จสิ้นการเลือกคำแนะนำ", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AnswerMoreRequestActivity.this, MainUserActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }

                                @Override
                                public void onError(String err) {
                                    Toast.makeText(AnswerMoreRequestActivity.this, err, Toast.LENGTH_SHORT).show();
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
            }
        });
    }
}
