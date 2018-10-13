package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class ExportToPDFController {
    private static ExportToPDFController wsManager;
    private Context context;

    public interface ExportToPDFControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public ExportToPDFController(Context context) {
        this.context = context;
    }

    public static ExportToPDFController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new ExportToPDFController(context);
        return wsManager;
    }

    public void detailRequestByidRequest(Object object, final ExportToPDFControllerListener listener) {
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
        task.execute(context.getString(R.string.exportpdf_getdetailrequestbyidrequest_url), requestForHelpModel.toJSONString());

    }

    public void detailBestSuggestionByIdrequestAndStatusAssign(Object object,final ExportToPDFControllerListener listener) {
        if (!(object instanceof AssignModel)) {
            return;
        }
        final AssignModel assignModel = (AssignModel) object;

        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                AssignModel assignModel = new AssignModel(response);
                listener.onComplete(assignModel);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });

        task.execute(context.getString(R.string.detailBestsuggestion_url),assignModel.toJSONString());
    }
}
