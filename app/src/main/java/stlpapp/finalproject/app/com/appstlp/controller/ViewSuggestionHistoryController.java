package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class ViewSuggestionHistoryController {
    private static ViewSuggestionHistoryController wsManager;
    private Context context;

    public interface ViewSuggestionHistoryControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public ViewSuggestionHistoryController(Context context) {
        this.context = context;
    }

    public static ViewSuggestionHistoryController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new ViewSuggestionHistoryController(context);
        return wsManager;
    }

    public void listSuggestionHistoryByUsernameAndStatusAssign(String username,final ViewSuggestionHistoryControllerListener listener) {

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
        String usernamejson = gson.toJson(username);
        task.execute(context.getString(R.string.listsuggestionhistory_url),usernamejson);
    }

    public void detailRequestByidRequest(int requestid, final ViewSuggestionHistoryControllerListener listener) {

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
        String requestidjson = gson.toJson(requestid);
        task.execute(context.getString(R.string.getdetailrequestbyidrequest_url_viewhistory), requestidjson);

    }

    public void detailSuggestionByIdAssign(int assignid, final ViewSuggestionHistoryControllerListener listener) {
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                AssignModel assignModel1 = new AssignModel(response);
                listener.onComplete(assignModel1);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String assignidjson = gson.toJson(assignid);
        task.execute(context.getString(R.string.viewhistorysuggestion_url), assignidjson);

    }

}
