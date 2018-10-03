package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
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


    public void listSuggestionByIdRequest(Object object, final ViewSuggesstionControllerListener listener) {
        if (!(object instanceof AssignModel)) {
            return;
        }
        AssignModel assignModel = (AssignModel) object;
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
        task.execute(context.getString(R.string.viewsuggestion_url), assignModel.toJSONString());
    }
}
