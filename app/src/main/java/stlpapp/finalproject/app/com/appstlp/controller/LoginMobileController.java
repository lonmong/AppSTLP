package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.Login;
import stlpapp.finalproject.app.com.appstlp.model.LoginModel;
import stlpapp.finalproject.app.com.appstlp.model.PersonModel;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class LoginMobileController {
    private static LoginMobileController wsManager;
    private Context context;

    public interface LoginMobileControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public LoginMobileController(Context context) {
        this.context = context;
    }

    public static LoginMobileController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new LoginMobileController(context);
        return wsManager;
    }


    public void verifyLogin(List<String> objectLogin, final LoginMobileControllerListener listener) {

        List<String> listLogin = objectLogin;

        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                LoginModel loginModel1 = new LoginModel(response);
                Login login = loginModel1.getLogin();
                if(login.getType()==1){
                    StatelessPersonModel statelessPersonModel = new StatelessPersonModel(response);
                    listener.onComplete(statelessPersonModel);

                }else if(login.getType()==2||login.getType()==3){
                    StaffModel staffModel = new StaffModel(response);
                    listener.onComplete(staffModel);

                }
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String jsonLogin = gson.toJson(listLogin);

        task.execute(context.getString(R.string.login_url), jsonLogin);

    }

}
