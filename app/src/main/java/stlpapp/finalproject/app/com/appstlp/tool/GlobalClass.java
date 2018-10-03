package stlpapp.finalproject.app.com.appstlp.tool;

import android.app.Application;

import stlpapp.finalproject.app.com.appstlp.model.Login;


/**
 * Created by NickyIT14 on 12 ก.ค. 2561.
 */

public class GlobalClass extends Application {
    private Login login;

    public GlobalClass(){

    }

    public Login getLogin() {
        return login;
    }

    public void setLogin(Login login) {
        this.login = login;
    }
}
