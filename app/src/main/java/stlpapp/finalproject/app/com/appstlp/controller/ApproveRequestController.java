package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.CenterModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;

import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class ApproveRequestController {
    private static ApproveRequestController wsManager;
    private Context context;

    public interface ApproveRequestControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public ApproveRequestController(Context context) {
        this.context = context;
    }

    public static ApproveRequestController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new ApproveRequestController(context);
        return wsManager;
    }

    public void listRequestByTelCenter(Object object,final ApproveRequestControllerListener listener) {
        if (!(object instanceof CenterModel)) {
            return;
        }
        final CenterModel centerModel = (CenterModel) object;

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

        task.execute(context.getString(R.string.getRequestByTelCenter_url),centerModel.toJSONString());
    }

    public void detailRequestByUsername(Object object, final ApproveRequestControllerListener listener) {
        if (!(object instanceof RequestForHelpModel)) {
            return;
        }
        final RequestForHelpModel requestForHelpModel = (RequestForHelpModel) object;
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
        task.execute(context.getString(R.string.getdetailrequestbyusername_url), requestForHelpModel.toJSONString());

    }

    public void setApproveRequestStatus(Object object,final ApproveRequestControllerListener listener) {
        if (!(object instanceof RequestForHelpModel)) {
            return;
        }
        final RequestForHelpModel requestForHelpModel = (RequestForHelpModel) object;
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                RequestForHelpModel requestForHelpModel1 = new RequestForHelpModel(response);
                listener.onComplete(requestForHelpModel1);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.approve_request_url),requestForHelpModel.toJSONString());
    }

}
