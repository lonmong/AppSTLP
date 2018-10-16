package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.IDCardTypeModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class SearchIDCardTypeController {
    private static SearchIDCardTypeController wsManager;
    private Context context;

    public interface SearchIDCardTypeControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public SearchIDCardTypeController(Context context) {
        this.context = context;
    }

    public static SearchIDCardTypeController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new SearchIDCardTypeController(context);
        return wsManager;
    }

    public void searchIDCardTypeByPattern(String idcardno, final SearchIDCardTypeControllerListener listener) {
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                IDCardTypeModel idCardTypeModel1 = new IDCardTypeModel(response);
                listener.onComplete(idCardTypeModel1);
            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        Gson gson = new GsonBuilder().create();
        String idcardfroquery = gson.toJson(idcardno);
        task.execute(context.getString(R.string.searchidcardtype_url), idcardfroquery);

    }

}
