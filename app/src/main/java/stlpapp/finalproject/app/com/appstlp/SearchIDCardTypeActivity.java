package stlpapp.finalproject.app.com.appstlp;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
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

        searchIdcardTypeBinding.textView1.setVisibility(View.GONE);
        searchIdcardTypeBinding.txtShowidcard.setVisibility(View.GONE);

        searchIdcardTypeBinding.line1.setVisibility(View.GONE);
        searchIdcardTypeBinding.textView2.setVisibility(View.GONE);
        searchIdcardTypeBinding.txtShowidcardcall.setVisibility(View.GONE);

        searchIdcardTypeBinding.line2.setVisibility(View.GONE);
        searchIdcardTypeBinding.textView3.setVisibility(View.GONE);
        searchIdcardTypeBinding.txtShowidcardmean.setVisibility(View.GONE);

        searchIdcardTypeBinding.line3.setVisibility(View.GONE);
        searchIdcardTypeBinding.textView4.setVisibility(View.GONE);
        searchIdcardTypeBinding.txtShowjob.setVisibility(View.GONE);

        searchIdcardTypeBinding.line4.setVisibility(View.GONE);
        searchIdcardTypeBinding.textView5.setVisibility(View.GONE);
        searchIdcardTypeBinding.txtShowbenefit.setVisibility(View.GONE);

        searchIdcardTypeBinding.line5.setVisibility(View.GONE);
        searchIdcardTypeBinding.textView6.setVisibility(View.GONE);
        searchIdcardTypeBinding.imageViewIdcard.setVisibility(View.GONE);
    }

    public void searchIDCardTypeByPattern(View view){
        if(searchIdcardTypeBinding.txtSearch.getText()==null||searchIdcardTypeBinding.txtSearch.getText().toString().isEmpty()){
            searchIdcardTypeBinding.txtSearch.setError(getString(R.string.please_input_data));
            return;
        }else if(!searchIdcardTypeBinding.txtSearch.getText().toString().matches(idcardPattern)){
            searchIdcardTypeBinding.txtSearch.setError(getString(R.string.id_card_not_match));
        }else {
            String string = searchIdcardTypeBinding.txtSearch.getText().toString().replaceAll(" ","");
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
            }else if(sub1.equalsIgnoreCase("0")){
                idcardforquery = sub1+sub6+sub7;
            }else {
                idcardforquery = " ";
            }
            final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                    getString(R.string.please_wait), true);

            SearchIDCardTypeController manager = SearchIDCardTypeController.getWsManager(SearchIDCardTypeActivity.this);
            final IDCardTypeModel idCardTypeModel = new IDCardTypeModel();
            idCardTypeModel.getIdCardType().setIdcardno(idcardforquery);

            manager.searchIDCardTypeByPattern(idCardTypeModel, new SearchIDCardTypeController.SearchIDCardTypeControllerListener() {
                @Override
                public void onComplete(Object response) {
                    if(((response instanceof IDCardTypeModel))){
                        IDCardTypeModel idCardTypeModel1 = (IDCardTypeModel)response;
                        IDCardTypeModel.IDCardType idCardType = idCardTypeModel1.getIdCardType();
                        idCardTypeModel.setIdCardType(idCardType);
                    }
                    progress.dismiss();

                    searchIdcardTypeBinding.textView1.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcard.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcard.setText(idCardTypeModel.getIdCardType().getIdcardno());

                    searchIdcardTypeBinding.line1.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView2.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardcall.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardcall.setText(idCardTypeModel.getIdCardType().getIdcardcall());

                    searchIdcardTypeBinding.line2.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView3.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardmean.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardmean.setText(idCardTypeModel.getIdCardType().getIdcardmean());

                    searchIdcardTypeBinding.line3.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView4.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowjob.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowjob.setText(idCardTypeModel.getIdCardType().getIdcardjob());

                    searchIdcardTypeBinding.line4.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView5.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowbenefit.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowbenefit.setText(idCardTypeModel.getIdCardType().getBenefitsfromgovern());

                    searchIdcardTypeBinding.line5.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView6.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.imageViewIdcard.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError(String err) {
                    progress.dismiss();
                    Toast.makeText(SearchIDCardTypeActivity.this, err, Toast.LENGTH_SHORT).show();
                    searchIdcardTypeBinding.textView1.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcard.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcard.setText("ไม่พบข้อมูล?");

                    searchIdcardTypeBinding.line1.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView2.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardcall.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardcall.setText("ไม่พบข้อมูล?");

                    searchIdcardTypeBinding.line2.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView3.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardmean.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowidcardmean.setText("ไม่พบข้อมูล?");

                    searchIdcardTypeBinding.line3.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView4.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowjob.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowjob.setText("ไม่พบข้อมูล?");

                    searchIdcardTypeBinding.line4.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView5.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowbenefit.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.txtShowbenefit.setText("ไม่พบข้อมูล?");

                    searchIdcardTypeBinding.line5.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.textView6.setVisibility(View.VISIBLE);
                    searchIdcardTypeBinding.imageViewIdcard.setVisibility(View.VISIBLE);
                }
            });
        }
    }
}
