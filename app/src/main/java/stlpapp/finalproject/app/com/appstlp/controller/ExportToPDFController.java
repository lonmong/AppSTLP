package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    public void detailRequestByidRequest(int idrequest, final ExportToPDFControllerListener listener) {

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
        Gson gson = new GsonBuilder().create();
        String idrequestjson = gson.toJson(idrequest);
        task.execute(context.getString(R.string.exportpdf_getdetailrequestbyidrequest_url), idrequestjson);

    }

    public void detailBestSuggestionByIdrequestAndStatusAssign(int idrequest,final ExportToPDFControllerListener listener) {


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
        Gson gson = new GsonBuilder().create();
        String idrequestjson = gson.toJson(idrequest);
        task.execute(context.getString(R.string.detailBestsuggestion_url),idrequestjson);
    }
}
