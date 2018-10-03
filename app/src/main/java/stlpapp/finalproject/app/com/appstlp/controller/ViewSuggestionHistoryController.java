package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

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

    public void listSuggestionHistory(Object object,final ViewSuggestionHistoryControllerListener listener) {
        if (!(object instanceof StaffModel)) {
            return;
        }
        final StaffModel staffModel = (StaffModel) object;

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

        task.execute(context.getString(R.string.listsuggestionhistory_url),staffModel.toJSONString());
    }

    public void detailRequestByidRequest(Object object, final ViewSuggestionHistoryControllerListener listener) {
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
        task.execute(context.getString(R.string.getdetailrequestbyidrequest_url), requestForHelpModel.toJSONString());

    }

    public void detailSuggestionByIdAssign(Object object, final ViewSuggestionHistoryControllerListener listener) {
        if (!(object instanceof AssignModel)) {
            return;
        }
        final AssignModel assignModel = (AssignModel) object;
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
        task.execute(context.getString(R.string.viewhistorysuggestion_url), assignModel.toJSONString());

    }

}
