package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.AddressModel;
import stlpapp.finalproject.app.com.appstlp.model.AssignModel;
import stlpapp.finalproject.app.com.appstlp.model.EducationModel;
import stlpapp.finalproject.app.com.appstlp.model.ParentModel;
import stlpapp.finalproject.app.com.appstlp.model.RequestForHelpModel;
import stlpapp.finalproject.app.com.appstlp.model.MoreRequestModel;
import stlpapp.finalproject.app.com.appstlp.model.StaffModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.model.WitnessModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class WSManager {
    private static WSManager wsManager;
    private Context context;

    public interface WSManagerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public WSManager(Context context) {
        this.context = context;
    }

    public static WSManager getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new WSManager(context);
        return wsManager;
    }

    public void getListEducationByUsername(String username,final WSManagerListener listener) {

        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                EducationModel Education = new EducationModel(response);
                listener.onComplete(Education);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String usernamejson = gson.toJson(username);
        task.execute(context.getString(R.string.list_education_url),usernamejson);
    }
    public void getListWitnessByUsername(String username,final WSManagerListener listener) {
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                WitnessModel Witness = new WitnessModel(response);
                listener.onComplete(Witness);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String usernamejson = gson.toJson(username);
        task.execute(context.getString(R.string.list_witness_url),usernamejson);
    }
    public void getListAddressByUsername(String username,final WSManagerListener listener) {

        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                AddressModel Adddress = new AddressModel(response);
                listener.onComplete(Adddress);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String usernamejson = gson.toJson(username);
        task.execute(context.getString(R.string.list_address_url),usernamejson);
    }

    public void getListParentByUsername(String username,final WSManagerListener listener) {

        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                ParentModel Parent = new ParentModel(response);
                listener.onComplete(Parent);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String usernamejson = gson.toJson(username);
        task.execute(context.getString(R.string.list_parent_url),usernamejson);
    }

    public void detailRequestByUsername(String username, final WSManagerListener listener) {

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

    public void listAssignbyUsername(Object object,final WSManagerListener listener) {
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

        task.execute(context.getString(R.string.getAssignByUsername_url),staffModel.toJSONString());
    }

    public void listMoreRequestForStatus(Object object, final WSManagerListener listener) {
        if (!(object instanceof RequestForHelpModel)) {
            return;
        }
        final RequestForHelpModel requestForHelpModel = (RequestForHelpModel) object;
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
        task.execute(context.getString(R.string.listmorerequest_url), requestForHelpModel.toJSONString());

    }

}
