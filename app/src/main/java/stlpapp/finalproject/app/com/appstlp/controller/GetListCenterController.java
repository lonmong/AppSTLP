package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;


import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.CenterModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class GetListCenterController {
    private static GetListCenterController wsManager;
    private Context context;

    public interface GetListCenterControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public GetListCenterController(Context context) {
        this.context = context;
    }

    public static GetListCenterController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new GetListCenterController(context);
        return wsManager;
    }

    public void getListCenter(final GetListCenterControllerListener listener) {
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                CenterModel Center= new CenterModel(response);
                listener.onComplete(Center);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.list_center_url));
    }
}
