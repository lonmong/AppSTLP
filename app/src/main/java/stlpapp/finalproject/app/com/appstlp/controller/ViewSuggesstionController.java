package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class ViewSuggesstionController {
    private static ViewSuggesstionController wsManager;
    private Context context;

    public interface ViewSuggesstionControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public ViewSuggesstionController(Context context) {
        this.context = context;
    }

    public static ViewSuggesstionController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new ViewSuggesstionController(context);
        return wsManager;
    }

    public void detailRequestByUsername(String username, final ViewSuggesstionControllerListener listener) {

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
        String usernamejson= gson.toJson(username);
        task.execute(context.getString(R.string.detailrequestbyusername_url), usernamejson);

    }


    public void listSuggestionByIdRequest(int idrequest, final ViewSuggesstionControllerListener listener) {

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
        String idrequestjson= gson.toJson(idrequest);
        task.execute(context.getString(R.string.viewsuggestion_url), idrequestjson);
    }
}
