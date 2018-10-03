package stlpapp.finalproject.app.com.appstlp.model;

/**
 * Created by NickyIT14 on 19 มิ.ย. 2561.
 */

public class ResponseModel {
    int code;
    Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Boolean isSuccess() {
        return code == 200;
    }
}
