package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.CenterModel;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class SelectStaffsForRequestController {
    private static SelectStaffsForRequestController wsManager;
    private Context context;

    public interface SelectStaffsForRequestControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public SelectStaffsForRequestController(Context context) {
        this.context = context;
    }

    public static SelectStaffsForRequestController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new SelectStaffsForRequestController(context);
        return wsManager;
    }

    public void listStaffsByTelCenter(String telcenter,final SelectStaffsForRequestControllerListener listener) {


        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                StaffModel staffModel = new StaffModel(response);
                listener.onComplete(staffModel);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String telcenterjson = gson.toJson(telcenter);
        task.execute(context.getString(R.string.getListStaffByTelCenter_url),telcenterjson);
    }

    public void setStaffForRequest(Object object, final SelectStaffsForRequestControllerListener listener) {
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
        task.execute(context.getString(R.string.selectstaffforrequest_url),assignModel.toJSONString());
    }



}
