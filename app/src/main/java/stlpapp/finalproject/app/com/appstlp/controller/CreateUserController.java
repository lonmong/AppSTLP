package stlpapp.finalproject.app.com.appstlp.controller;

import android.content.Context;

import stlpapp.finalproject.app.com.appstlp.R;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.task.WSTask;


public class CreateUserController {
    private static CreateUserController wsManager;
    private Context context;

    public interface CreateUserControllerListener {
        void onComplete(Object response);

        void onError(String err);
    }

    public CreateUserController(Context context) {
        this.context = context;
    }

    public static CreateUserController getWsManager(Context context) {
        if (wsManager == null)
            wsManager = new CreateUserController(context);
        return wsManager;
    }

    public void isAddUser(Object object, final CreateUserControllerListener listener) {
        if (!(object instanceof StatelessPersonModel)) {
            return;
        }
        StatelessPersonModel statelessPersonModel = (StatelessPersonModel) object;
        statelessPersonModel.toJSONString();
        WSTask task = new WSTask(this.context,new WSTask.WSTaskListener() {
            @Override
            public void onComplete(String response) {
                StatelessPersonModel statelessPersonModel1 = new StatelessPersonModel(response);
                listener.onComplete(statelessPersonModel1);

            }

            @Override
            public void onError(String err) {
                listener.onError(err);
            }
        });
        task.execute(context.getString(R.string.createuser_url), statelessPersonModel.toJSONString());
    }


}
