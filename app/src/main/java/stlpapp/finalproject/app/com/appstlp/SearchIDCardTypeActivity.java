package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import stlpapp.finalproject.app.com.appstlp.databinding.ActivitySearchIdcardTypeBinding;
import stlpapp.finalproject.app.com.appstlp.controller.SearchIDCardTypeController;
import stlpapp.finalproject.app.com.appstlp.model.IDCardTypeModel;

public class SearchIDCardTypeActivity extends AppCompatActivity {
    private ActivitySearchIdcardTypeBinding searchIdcardTypeBinding;
    private String idcardPattern = "[0-9]{13,13}";
    private String idcardforquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchIdcardTypeBinding = DataBindingUtil.setContentView(this,R.layout.activity_search_idcard_type);

        searchIdcardTypeBinding.cardviewidcardno.setVisibility(View.GONE);
        searchIdcardTypeBinding.cardviewidcardcall.setVisibility(View.GONE);
        searchIdcardTypeBinding.cardviewidcardmean.setVisibility(View.GONE);
        searchIdcardTypeBinding.cardviewidcardjob.setVisibility(View.GONE);
        searchIdcardTypeBinding.cardviewbenefits.setVisibility(View.GONE);
    }

    public void searchIDCardTypeByPattern(View view){
        if(searchIdcardTypeBinding.textSearchIdcardtype.getText()==null||searchIdcardTypeBinding.textSearchIdcardtype.getText().toString().isEmpty()){
            searchIdcardTypeBinding.textSearchIdcardtype.setError(getString(R.string.please_input_data));
            return;
        }else if(!searchIdcardTypeBinding.textSearchIdcardtype.getText().toString().matches(idcardPattern)){
            searchIdcardTypeBinding.textSearchIdcardtype.setError(getString(R.string.id_card_not_match));
        }else {
            String string = searchIdcardTypeBinding.textSearchIdcardtype.getText().toString().replaceAll(" ","");
            String substringidcard = string.substring(0,7);
            String sub1 = substringidcard.substring(0,1);
            String sub2 = substringidcard.substring(1,2);
            String sub3 = substringidcard.substring(2,3);
            String sub4 = substringidcard.substring(3,4);
            String sub5 = substringidcard.substring(4,5);
            String sub6 = substringidcard.substring(5,6);
            String sub7 = substringidcard.substring(6,7);
            if(sub1.equalsIgnoreCase("6")||sub1.equalsIgnoreCase("7")){
                idcardforquery = sub1;
            }else if((sub1.equalsIgnoreCase("0")&&sub6.equalsIgnoreCase("8")&&sub7.equalsIgnoreCase("9")) || (sub1.equalsIgnoreCase("0")&&sub6.equalsIgnoreCase("0")&&sub7.equalsIgnoreCase("0"))){
                if(sub1.equalsIgnoreCase("0") && sub2.equalsIgnoreCase("0") && sub3.equalsIgnoreCase("0")) {
                    idcardforquery = "0000000000000";
                }
                else{
                    idcardforquery = sub1+sub6+sub7;
                }
            }else if (sub1.equalsIgnoreCase("0") && sub2.equalsIgnoreCase("0")) {
                idcardforquery = "00";
            }else{
                idcardforquery = " ";
            }
            final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                    getString(R.string.please_wait), true);

            SearchIDCardTypeController manager = SearchIDCardTypeController.getWsManager(SearchIDCardTypeActivity.this);

            manager.searchIDCardTypeByPattern(idcardforquery, new SearchIDCardTypeController.SearchIDCardTypeControllerListener() {
                @Override
                public void onComplete(Object response) {
                    IDCardTypeModel idCardTypeModel1 = (IDCardTypeModel)response;
                    progress.dismiss();
                    searchIdcardTypeBinding.cardviewidcardno.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.showidcardno.setText(idCardTypeModel1.getIdCardType().getIdcardno());

                    searchIdcardTypeBinding.cardviewidcardcall.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.showidcardcall.setText(idCardTypeModel1.getIdCardType().getIdcardcall());

                    searchIdcardTypeBinding.cardviewidcardmean.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.showidcardmean.setText(idCardTypeModel1.getIdCardType().getIdcardmean());

                    searchIdcardTypeBinding.cardviewidcardjob.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.showidcardjob.setText(idCardTypeModel1.getIdCardType().getIdcardjob());

                    searchIdcardTypeBinding.cardviewbenefits.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.showbenefits.setText(idCardTypeModel1.getIdCardType().getBenefitsfromgovern());

                }

                @Override
                public void onError(String err) {
                    progress.dismiss();
                    AlertDialog.Builder builder = new AlertDialog.Builder(SearchIDCardTypeActivity.this);
                    builder.setTitle("คำเตือน");
                    builder.setMessage(err);
                    builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    searchIdcardTypeBinding.cardviewidcardno.setVisibility(View.GONE);
                    searchIdcardTypeBinding.cardviewidcardcall.setVisibility(View.GONE);
                    searchIdcardTypeBinding.cardviewidcardmean.setVisibility(View.GONE);
                    searchIdcardTypeBinding.cardviewidcardjob.setVisibility(View.GONE);
                    searchIdcardTypeBinding.cardviewbenefits.setVisibility(View.GONE);
                }
            });
        }
    }
}
