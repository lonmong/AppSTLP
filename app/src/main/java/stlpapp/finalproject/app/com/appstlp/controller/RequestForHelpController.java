package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class RequestForHelpController {
    private static RequestForHelpController wsManager;
    private Context context;

    public interface RequestForHelpControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public RequestForHelpController(Context context) {
        this.context = context;
    }

    public static RequestForHelpController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new RequestForHelpController(context);
        return wsManager;
    }

    public void isAddRequest(Object object, final RequestForHelpControllerListener listener) {
        if (!(object instanceof RequestForHelpModel)) {
            return;
        }
        RequestForHelpModel requestForHelpModel = (RequestForHelpModel) object;
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                RequestForHelpModel requestForHelpModel = new RequestForHelpModel(response);
                listener.onComplete(requestForHelpModel);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.request_for_help_url), requestForHelpModel.toJSONString());
    }


}
