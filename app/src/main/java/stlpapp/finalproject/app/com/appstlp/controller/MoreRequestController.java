package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.MoreRequestModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class MoreRequestController {
    private static MoreRequestController wsManager;
    private Context context;

    public interface MoreRequestControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public MoreRequestController(Context context) {
        this.context = context;
    }

    public static MoreRequestController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new MoreRequestController(context);
        return wsManager;
    }


    public void isAddMoreRequest(Object object, final MoreRequestControllerListener listener) {
        if (!(object instanceof MoreRequestModel)) {
            return;
        }
        MoreRequestModel moreRequestModel = (MoreRequestModel) object;
        moreRequestModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                MoreRequestModel moreRequestModel1 = new MoreRequestModel(response);
                listener.onComplete(moreRequestModel1);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.more_request_for_help_url), moreRequestModel.toJSONString());
    }

    public void moreRequestlistByidRequest(Object object,final MoreRequestControllerListener listener) {
        if (!(object instanceof MoreRequestModel)) {
            return;
        }
        final MoreRequestModel moreRequestModel = (MoreRequestModel) object;
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                MoreRequestModel moreRequestModel = new MoreRequestModel(response);
                listener.onComplete(moreRequestModel);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.list_morerequest_url),moreRequestModel.toJSONString());
    }

    public void removeMoreRequest(Object object, final MoreRequestControllerListener listener) {
        if (!(object instanceof MoreRequestModel)) {
            return;
        }
        MoreRequestModel moreRequestModel = (MoreRequestModel) object;
        moreRequestModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                MoreRequestModel moreRequestModel1 = new MoreRequestModel(response);
                listener.onComplete(moreRequestModel1);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.removeMoreRequest_url), moreRequestModel.toJSONString());
    }

    public void detailRequestByIdRequest(Object object, final MoreRequestControllerListener listener) {
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

    public void viewSuggestionHistory(Object object, final MoreRequestControllerListener listener) {
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

    public void setAnswerMoreRequest(Object object, final MoreRequestControllerListener listener) {
        if (!(object instanceof MoreRequestModel)) {
            return;
        }
        MoreRequestModel moreRequestModel = (MoreRequestModel) object;
        moreRequestModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                MoreRequestModel moreRequestModel1 = new MoreRequestModel(response);
                listener.onComplete(moreRequestModel1);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.setAnswerMoreRequest_url), moreRequestModel.toJSONString());
    }

}
