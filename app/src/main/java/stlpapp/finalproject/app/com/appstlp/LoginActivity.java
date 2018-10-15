package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import stlpapp.finalproject.app.com.appstlp.controller.LoginMobileController;
import stlpapp.finalproject.app.com.appstlp.model.LoginModel;
import stlpapp.finalproject.app.com.appstlp.model.Person;
import stlpapp.finalproject.app.com.appstlp.model.PersonModel;
import stlpapp.finalproject.app.com.appstlp.model.Staff;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPerson;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.tool.GlobalClass;


public class LoginActivity extends AppCompatActivity {

    private EditText password,username;
    private GlobalClass globalClass;
    private String usernames="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        globalClass = (GlobalClass)getApplicationContext();
        username = (EditText)findViewById(R.id.username);

        Intent intent = getIntent();
        usernames = intent.getStringExtra("username");
        username.setText(usernames);

    }

    public void verifyLogin(View view){
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        if(username.getText().toString().isEmpty()){
            username.setError("กรุณากรอกชื่อผู้ใช้งาน");
            username.requestFocus();
            return;
        }else if(password.getText().toString().isEmpty()){
            password.setError("กรุณากรอกรหัสผ่าน");
            password.requestFocus();
            return;
        }else {
            final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                    getString(R.string.please_wait), true);

            LoginMobileController manager = LoginMobileController.getWsManager(LoginActivity.this);

            LoginModel loginModel = new LoginModel();
            loginModel.getLogin().setUsername(username.getText().toString());
            loginModel.getLogin().setPassword(password.getText().toString());

            manager.verifyLogin(loginModel, new LoginMobileController.LoginMobileControllerListener() {
                @Override
                public void onComplete(Object response) {
                    if ((response instanceof StatelessPersonModel)) {
                        StatelessPersonModel statelessPersonModel = (StatelessPersonModel) response;
                        StatelessPerson statelessPerson = statelessPersonModel.getStatelessPerson();
                        globalClass.setLogin(statelessPerson);
                    } else if (((response instanceof StaffModel))) {
                        StaffModel staffModel = (StaffModel) response;
                        Staff staff = staffModel.getStaff();
                        globalClass.setLogin(staff);
                    }
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, "เข้าสู่ระบบสำเร็จ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(getApplicationContext(), MainUserActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onError(String err) {
                    progress.dismiss();
                    Toast.makeText(LoginActivity.this, err, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}

