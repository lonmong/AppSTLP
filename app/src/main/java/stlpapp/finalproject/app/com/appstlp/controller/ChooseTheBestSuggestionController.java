package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class ChooseTheBestSuggestionController {
    private static ChooseTheBestSuggestionController wsManager;
    private Context context;

    public interface ChooseTheBestSuggestionControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public ChooseTheBestSuggestionController(Context context) {
        this.context = context;
    }

    public static ChooseTheBestSuggestionController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new ChooseTheBestSuggestionController(context);
        return wsManager;
    }

    public void detailRequestByUsername(String username, final ChooseTheBestSuggestionControllerListener listener) {

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
        String usernamejson = gson.toJson(username);
        task.execute(context.getString(R.string.getdetailrequestbyusername_url_choosebest), usernamejson);

    }
    public void listSuggestionByIdRequest(int idrequest,final ChooseTheBestSuggestionControllerListener listener) {

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
        task.execute(context.getString(R.string.getAssignByIDrequest_url),idrequestjson);
    }

    public void detailSuggestionByIdassign(int idassign, final ChooseTheBestSuggestionControllerListener listener) {

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
        String idassignjson = gson.toJson(idassign);
        task.execute(context.getString(R.string.detailsuggestion_url), idassignjson);

    }

    public void setBestSuggestionForRequest(Object object, final ChooseTheBestSuggestionControllerListener listener) {
        if (!(object instanceof AssignModel)) {
            return;
        }
        AssignModel assignModel = (AssignModel) object;
        assignModel.toJSONString();
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
        task.execute(context.getString(R.string.choosethebestsuggestion_url), assignModel.toJSONString());
    }
}
