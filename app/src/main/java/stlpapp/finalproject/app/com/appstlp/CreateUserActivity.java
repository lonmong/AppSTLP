package stlpapp.finalproject.app.com.appstlp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import stlpapp.finalproject.app.com.appstlp.controller.CreateUserController;
import stlpapp.finalproject.app.com.appstlp.databinding.ActivityCreateUserBinding;
import stlpapp.finalproject.app.com.appstlp.model.AddressModel;
import stlpapp.finalproject.app.com.appstlp.model.EducationModel;
import stlpapp.finalproject.app.com.appstlp.model.ParentModel;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPerson;
import stlpapp.finalproject.app.com.appstlp.model.StatelessPersonModel;
import stlpapp.finalproject.app.com.appstlp.model.WitnessModel;

public class CreateUserActivity extends AppCompatActivity {
    private ActivityCreateUserBinding mBinding;
    private String datePattern = "\\d{1,2}/\\d{1,2}/\\d{4}";
    private String namePattern = "[a-zA-Zก-์  ]{3,35}";
    private String homeidPattern = "[0-9]{11,11}";
    private String idcardPattern = "[0-9]{13,13}";
    private String idcardno1_1 = "[1-5]";
    private String idcardno1_2 = "[8-9]";
    private List<AutoCompleteTextView> listEditTextEducation = new ArrayList<>();
    private String string ;
    private String substringidcard ;
    private String sub1 ;
    private String sub2 ;
    private String sub3 ;
    private String sub4 ;
    private String sub5 ;
    private String sub6 ;
    private String sub7 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this ,R.layout.activity_create_user);

        mBinding.txtNamespouse.setVisibility(View.GONE);
        mBinding.txtIdcardspouse.setVisibility(View.GONE);
        mBinding.txtNationaltyspouse.setVisibility(View.GONE);
        mBinding.txtDatemarry.setVisibility(View.GONE);
        mBinding.txtAddressspouse.setVisibility(View.GONE);
        mBinding.RadioGroupThaiBirthOrAbroadBirth.setVisibility(View.GONE);

        mBinding.txtHospitalbirth.setVisibility(View.GONE);
        mBinding.txtVillagebirth.setVisibility(View.GONE);
        mBinding.RadioGroupWitness.setVisibility(View.GONE);
        mBinding.txtNamewitness1.setVisibility(View.GONE);
        mBinding.txtWitnessrelationship1.setVisibility(View.GONE);
        mBinding.RadioGroupWitnessAlive1.setVisibility(View.GONE);
        mBinding.txtAddresswitness1.setVisibility(View.GONE);
        mBinding.txtNamewitness2.setVisibility(View.GONE);
        mBinding.txtWitnessrelationship2.setVisibility(View.GONE);
        mBinding.RadioGroupWitnessAlive2.setVisibility(View.GONE);
        mBinding.txtAddresswitness2.setVisibility(View.GONE);

        mBinding.txtCountrybirth.setVisibility(View.GONE);
        mBinding.txtDistrictcomethai.setVisibility(View.GONE);
        mBinding.txtDatecomethai.setVisibility(View.GONE);
        mBinding.txtModecomethai.setVisibility(View.GONE);
        mBinding.txtNamewitness3.setVisibility(View.GONE);
        mBinding.txtWitnessrelationship3.setVisibility(View.GONE);
        mBinding.RadioGroupWitnessAlive3.setVisibility(View.GONE);
        mBinding.txtAddresswitness3.setVisibility(View.GONE);
    }

    public void birthDay(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtBirthday.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
        dpd.getDatePicker().setMaxDate(System.currentTimeMillis());
    }
    public void dateMarry(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtDatemarry.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    public void fromYears(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtFromyear.setText(year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void toYears(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtToyear.setText(year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    public void dateComethai(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtDatecomethai.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    public void fatherBirth(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtFatherbirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    public void dateComethaifather(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtDatecomethaifather.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    public void motherBirth(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtMotherbirth.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }
    public void dateComethaimother(View view){
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dpd = new DatePickerDialog(CreateUserActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        mBinding.txtDatecomethaimother.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    public void onMarryClick(View view){
        int checkedMaryRadioButtonId = mBinding.RadioGroupMarry.getCheckedRadioButtonId();
        switch (checkedMaryRadioButtonId){
            case R.id.single:
                mBinding.txtNamespouse.setVisibility(View.GONE);
                mBinding.txtIdcardspouse.setVisibility(View.GONE);
                mBinding.txtNationaltyspouse.setVisibility(View.GONE);
                mBinding.txtDatemarry.setVisibility(View.GONE);
                mBinding.txtAddressspouse.setVisibility(View.GONE);

                break;
            case R.id.divorce:
                mBinding.txtNamespouse.setVisibility(View.VISIBLE);
                mBinding.txtIdcardspouse.setVisibility(View.VISIBLE);
                mBinding.txtNationaltyspouse.setVisibility(View.VISIBLE);
                mBinding.txtDatemarry.setVisibility(View.VISIBLE);
                mBinding.txtAddressspouse.setVisibility(View.VISIBLE);
                break;
            case R.id.widow:
                mBinding.txtNamespouse.setVisibility(View.VISIBLE);
                mBinding.txtIdcardspouse.setVisibility(View.VISIBLE);
                mBinding.txtNationaltyspouse.setVisibility(View.VISIBLE);
                mBinding.txtDatemarry.setVisibility(View.VISIBLE);
                mBinding.txtAddressspouse.setVisibility(View.VISIBLE);
                break;
            case R.id.notmarry:
                mBinding.txtNamespouse.setVisibility(View.VISIBLE);
                mBinding.txtIdcardspouse.setVisibility(View.VISIBLE);
                mBinding.txtNationaltyspouse.setVisibility(View.VISIBLE);
                mBinding.txtDatemarry.setVisibility(View.VISIBLE);
                mBinding.txtAddressspouse.setVisibility(View.VISIBLE);
                break;
            case R.id.marry:
                mBinding.txtNamespouse.setVisibility(View.VISIBLE);
                mBinding.txtIdcardspouse.setVisibility(View.VISIBLE);
                mBinding.txtNationaltyspouse.setVisibility(View.VISIBLE);
                mBinding.txtDatemarry.setVisibility(View.VISIBLE);
                mBinding.txtAddressspouse.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }

    public void onPlacebirthClick(View view){
        int checkedPlacebirthRadioButtonId = mBinding.RadioGroupPlaceBirth.getCheckedRadioButtonId();
        switch (checkedPlacebirthRadioButtonId){
            case R.id.knowplacebirth:
                mBinding.RadioGroupThaiBirthOrAbroadBirth.setVisibility(View.VISIBLE);
                break;
            case R.id.dontknowplacebirth:
                mBinding.RadioGroupThaiBirthOrAbroadBirth.setVisibility(View.GONE);
                mBinding.RadioGroupThaiBirthOrAbroadBirth.check(0);
                mBinding.txtHospitalbirth.setVisibility(View.GONE);
                mBinding.txtHospitalbirth.setText("-");
                mBinding.txtVillagebirth.setVisibility(View.GONE);
                mBinding.txtVillagebirth.setText("-");
                mBinding.RadioGroupWitness.setVisibility(View.GONE);
                mBinding.RadioGroupWitness.check(0);
                mBinding.txtNamewitness1.setVisibility(View.GONE);
                mBinding.txtNamewitness1.setText("-");
                mBinding.txtWitnessrelationship1.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship1.setText("-");
                mBinding.RadioGroupWitnessAlive1.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive1.check(0);
                mBinding.txtAddresswitness1.setVisibility(View.GONE);
                mBinding.txtAddresswitness1.setText("-");
                mBinding.txtNamewitness2.setVisibility(View.GONE);
                mBinding.txtNamewitness2.setText("-");
                mBinding.txtWitnessrelationship2.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship2.setText("-");
                mBinding.RadioGroupWitnessAlive2.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive2.check(0);
                mBinding.txtAddresswitness2.setVisibility(View.GONE);
                mBinding.txtAddresswitness2.setText("-");
                mBinding.txtCountrybirth.setVisibility(View.GONE);
                mBinding.txtCountrybirth.setText("-");
                mBinding.txtDistrictcomethai.setVisibility(View.GONE);
                mBinding.txtDistrictcomethai.setText("-");
                mBinding.txtDatecomethai.setVisibility(View.GONE);
                mBinding.txtDatecomethai.setText("-");
                mBinding.txtModecomethai.setVisibility(View.GONE);
                mBinding.txtModecomethai.setText("-");
                mBinding.txtNamewitness3.setVisibility(View.GONE);
                mBinding.txtNamewitness3.setText("-");
                mBinding.txtWitnessrelationship3.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship3.setText("-");
                mBinding.RadioGroupWitnessAlive3.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive3.check(0);
                mBinding.txtAddresswitness3.setVisibility(View.GONE);
                mBinding.txtAddresswitness3.setText("-");
                break;
            default:
                break;
        }
    }
    public void onThaiOrAbroadbirthClick(View view){
        int checkedThaiOrAbroadbirthRadioButtonId = mBinding.RadioGroupThaiBirthOrAbroadBirth.getCheckedRadioButtonId();
        switch (checkedThaiOrAbroadbirthRadioButtonId){
            case R.id.thaibirth:
                mBinding.txtHospitalbirth.setVisibility(View.VISIBLE);
                mBinding.txtVillagebirth.setVisibility(View.VISIBLE);
                mBinding.RadioGroupWitness.setVisibility(View.VISIBLE);
                mBinding.thaibirth.setHint("1");
                mBinding.txtNamewitness1.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship1.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive1.setVisibility(View.GONE);
                mBinding.txtAddresswitness1.setVisibility(View.GONE);
                mBinding.txtNamewitness2.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship2.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive2.setVisibility(View.GONE);
                mBinding.txtAddresswitness2.setVisibility(View.GONE);

                mBinding.txtCountrybirth.setVisibility(View.GONE);
                mBinding.txtDistrictcomethai.setVisibility(View.GONE);
                mBinding.txtDatecomethai.setVisibility(View.GONE);
                mBinding.txtModecomethai.setVisibility(View.GONE);
                mBinding.txtNamewitness3.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship3.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive3.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive3.check(0);
                mBinding.txtAddresswitness3.setVisibility(View.GONE);
                break;
            case R.id.notthaibirth:
                mBinding.notthaibirth.setHint("2");
                mBinding.txtHospitalbirth.setVisibility(View.GONE);
                mBinding.txtVillagebirth.setVisibility(View.GONE);
                mBinding.RadioGroupWitness.setVisibility(View.GONE);
                mBinding.RadioGroupWitness.check(0);
                mBinding.txtNamewitness1.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship1.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive1.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive1.check(0);
                mBinding.txtAddresswitness1.setVisibility(View.GONE);
                mBinding.txtNamewitness2.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship2.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive2.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive2.check(0);
                mBinding.txtAddresswitness2.setVisibility(View.GONE);

                mBinding.txtCountrybirth.setVisibility(View.VISIBLE);
                mBinding.txtDistrictcomethai.setVisibility(View.VISIBLE);
                mBinding.txtDatecomethai.setVisibility(View.VISIBLE);
                mBinding.txtModecomethai.setVisibility(View.VISIBLE);
                mBinding.txtNamewitness3.setVisibility(View.VISIBLE);
                mBinding.txtWitnessrelationship3.setVisibility(View.VISIBLE);
                mBinding.RadioGroupWitnessAlive3.setVisibility(View.VISIBLE);
                mBinding.txtAddresswitness3.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
    }
    public void onWitnessClick(View view){
        int checkedWitnessRadioButtonId = mBinding.RadioGroupWitness.getCheckedRadioButtonId();
        switch (checkedWitnessRadioButtonId){
            case R.id.yes_witness:
                mBinding.txtNamewitness1.setVisibility(View.VISIBLE);
                mBinding.txtWitnessrelationship1.setVisibility(View.VISIBLE);
                mBinding.RadioGroupWitnessAlive1.setVisibility(View.VISIBLE);
                mBinding.txtAddresswitness1.setVisibility(View.VISIBLE);
                mBinding.txtNamewitness2.setVisibility(View.VISIBLE);
                mBinding.txtWitnessrelationship2.setVisibility(View.VISIBLE);
                mBinding.RadioGroupWitnessAlive2.setVisibility(View.VISIBLE);
                mBinding.txtAddresswitness2.setVisibility(View.VISIBLE);
                break;
            case R.id.no_witness:
                mBinding.txtNamewitness1.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship1.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive1.setVisibility(View.GONE);
                mBinding.txtAddresswitness1.setVisibility(View.GONE);
                mBinding.txtNamewitness2.setVisibility(View.GONE);
                mBinding.txtWitnessrelationship2.setVisibility(View.GONE);
                mBinding.RadioGroupWitnessAlive2.setVisibility(View.GONE);
                mBinding.txtAddresswitness2.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }
    public void doAddEducation(View view){
        LinearLayout linear_add_education = (LinearLayout) findViewById(R.id.linear_add_education);
        AutoCompleteTextView auto_text_educationlevel = new AutoCompleteTextView(this);
        auto_text_educationlevel.setHint("ระดับการศึกษา/ชื่อสถานศึกษา/อำเภอ/จังหวัด");

        listEditTextEducation.add(auto_text_educationlevel);
        linear_add_education.addView(auto_text_educationlevel);
    }

    public void doRemoveEducation(View view){
        LinearLayout linear_add_education = (LinearLayout) findViewById(R.id.linear_add_education);
        if(!listEditTextEducation.isEmpty()){
            linear_add_education.removeView(listEditTextEducation.get(listEditTextEducation.size()-1));
            listEditTextEducation.remove(listEditTextEducation.size()-1);
        }

    }
    public void isAddUser(View view) {
        if (mBinding.txtPersonname.getText() == null || mBinding.txtPersonname.getText().toString().isEmpty()) {
            mBinding.txtPersonname.requestFocus();
            mBinding.txtPersonname.setError(getString(R.string.please_input_person_name));
            return;
        }else if (!mBinding.txtPersonname.getText().toString().matches(namePattern)) {
            mBinding.txtPersonname.requestFocus();
            mBinding.txtPersonname.setError(getString(R.string.name_not_match));
            return;
        }else if (mBinding.txtEmail.getText() == null || mBinding.txtEmail.getText().toString().isEmpty()) {
            mBinding.txtEmail.requestFocus();
            mBinding.txtEmail.setError(getString(R.string.please_input_email));
            return;
        }else if (mBinding.txtTel.getText() == null || mBinding.txtTel.getText().toString().isEmpty()) {
            mBinding.txtTel.requestFocus();
            mBinding.txtTel.setError(getString(R.string.please_input_tel));
            return;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(mBinding.txtEmail.getText().toString()).matches()) {
            mBinding.txtEmail.requestFocus();
            mBinding.txtEmail.setError(getString(R.string.error_invalid_email));
            return;
        } else
        if (mBinding.RadioGroupGender.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupGender.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_gender,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.txtBirthday.getText() == null || mBinding.txtBirthday.getText().toString().isEmpty()) {
            mBinding.txtBirthday.requestFocus();
            mBinding.txtBirthday.setError(getString(R.string.please_input_birthday));
            return;
        }else if (!mBinding.txtBirthday.getText().toString().matches(datePattern)) {
            mBinding.txtBirthday.requestFocus();
            mBinding.txtBirthday.setError(getString(R.string.birthday_not_match));
            return;
        }else if (mBinding.txtReligion.getText() == null || mBinding.txtReligion.getText().toString().isEmpty()) {
            mBinding.txtReligion.requestFocus();
            mBinding.txtReligion.setError(getString(R.string.please_input_religion));
            return;
        }else if (mBinding.txtEthnic.getText() == null || mBinding.txtEthnic.getText().toString().isEmpty()) {
            mBinding.txtEthnic.requestFocus();
            mBinding.txtEthnic.setError(getString(R.string.please_input_ethnic));
            return;
        }else if (mBinding.txtNationality.getText() == null || mBinding.txtNationality.getText().toString().isEmpty()) {
            mBinding.txtNationality.requestFocus();
            mBinding.txtNationality.setError(getString(R.string.have_nationality));
            return;
        }else if (mBinding.txtHomeid.getText() == null || mBinding.txtHomeid.getText().toString().isEmpty()) {
            mBinding.txtHomeid.requestFocus();
            mBinding.txtHomeid.setError(getString(R.string.please_input_home_id));
            return;
        }else if (!mBinding.txtHomeid.getText().toString().matches(homeidPattern)) {
            mBinding.txtHomeid.requestFocus();
            mBinding.txtHomeid.setError(getString(R.string.home_id_not_match));
            return;
        }else if (mBinding.txtIdcard.getText() == null || mBinding.txtIdcard.getText().toString().isEmpty()) {
            mBinding.txtIdcard.requestFocus();
            mBinding.txtIdcard.setError(getString(R.string.please_input_id_card));
            return;
        }else if (!mBinding.txtIdcard.getText().toString().matches(idcardPattern)) {
            mBinding.txtIdcard.requestFocus();
            mBinding.txtIdcard.setError(getString(R.string.id_card_not_match));
            return;
        }else if (mBinding.RadioGroupMarry.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupMarry.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_status_marry,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.RadioGroupPlaceBirth.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupPlaceBirth.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_place_birth,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.txtAddressregister.getText().toString()==null||mBinding.txtAddressregister.getText().toString().isEmpty()) {
            mBinding.txtAddressregister.requestFocus();
            mBinding.txtAddressregister.setError(getString(R.string.please_input_address));
            return;
        }else if (mBinding.RadioGroupHomeStatusRegister.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupHomeStatusRegister.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_home_status_register,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.txtAddressnow.getText().toString()==null||mBinding.txtAddressnow.getText().toString().isEmpty()) {
            mBinding.txtAddressnow.requestFocus();
            mBinding.txtAddressnow.setError(getString(R.string.please_input_address));
            return;
        }else if (mBinding.RadioGroupHomeStatus.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupHomeStatus.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_home_status_now,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.educationlevel.getText().toString()==null||mBinding.educationlevel.getText().toString().isEmpty()) {
            mBinding.educationlevel.requestFocus();
            mBinding.educationlevel.setError(getString(R.string.please_input_education));
            return;
        }else if (mBinding.txtNamefather.getText().toString()==null||mBinding.txtNamefather.getText().toString().isEmpty()) {
            mBinding.txtNamefather.requestFocus();
            mBinding.txtNamefather.setError(getString(R.string.please_input_father_name));
            return;
        }else if (mBinding.RadioGroupFather.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupFather.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_status_be_alive_f,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.txtFatherbirth.getText().toString()==null||mBinding.txtFatherbirth.getText().toString().isEmpty()) {
            mBinding.txtFatherbirth.requestFocus();
            mBinding.txtFatherbirth.setError(getString(R.string.please_input_birthday));
            return;
        }else if (mBinding.txtIdcardfather.getText().toString()==null||mBinding.txtIdcardfather.getText().toString().isEmpty()) {
            mBinding.txtIdcardfather.requestFocus();
            mBinding.txtIdcardfather.setError(getString(R.string.please_input_id_card));
            return;
        }else if (!mBinding.txtIdcardfather.getText().toString().matches(idcardPattern)) {
            mBinding.txtIdcardfather.requestFocus();
            mBinding.txtIdcardfather.setError(getString(R.string.id_card_not_match));
            return;
        }else if (mBinding.txtEthnicfather.getText().toString()==null||mBinding.txtEthnicfather.getText().toString().isEmpty()) {
            mBinding.txtEthnicfather.requestFocus();
            mBinding.txtEthnicfather.setError(getString(R.string.please_input_ethnic));
            return;
        }else if (mBinding.txtNationaltyfather.getText().toString()==null||mBinding.txtNationaltyfather.getText().toString().isEmpty()) {
            mBinding.txtNationaltyfather.requestFocus();
            mBinding.txtNationaltyfather.setError(getString(R.string.have_nationality));
            return;
        }else if (mBinding.txtAddressfather.getText().toString()==null||mBinding.txtAddressfather.getText().toString().isEmpty()) {
            mBinding.txtAddressfather.requestFocus();
            mBinding.txtAddressfather.setError(getString(R.string.please_input_address));
            return;
        }else if (mBinding.txtNamemother.getText().toString()==null||mBinding.txtNamemother.getText().toString().isEmpty()) {
            mBinding.txtNamemother.requestFocus();
            mBinding.txtNamemother.setError(getString(R.string.please_input_mother_name));
            return;
        }else if (mBinding.RadioGroupMother.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupMother.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_status_be_alive_m,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.txtMotherbirth.getText().toString()==null||mBinding.txtMotherbirth.getText().toString().isEmpty()) {
            mBinding.txtMotherbirth.requestFocus();
            mBinding.txtMotherbirth.setError(getString(R.string.please_input_birthday));
            return;
        }else if (mBinding.txtIdcardmother.getText().toString()==null||mBinding.txtIdcardmother.getText().toString().isEmpty()) {
            mBinding.txtIdcardmother.requestFocus();
            mBinding.txtIdcardmother.setError(getString(R.string.please_input_id_card));
            return;
        }else if (!mBinding.txtIdcardmother.getText().toString().matches(idcardPattern)) {
            mBinding.txtIdcardmother.requestFocus();
            mBinding.txtIdcardmother.setError(getString(R.string.id_card_not_match));
            return;
        }else if (mBinding.txtEthnicmother.getText().toString()==null||mBinding.txtEthnicmother.getText().toString().isEmpty()) {
            mBinding.txtEthnicmother.requestFocus();
            mBinding.txtEthnicmother.setError(getString(R.string.please_input_ethnic));
            return;
        }else if (mBinding.txtNationaltymother.getText().toString()==null||mBinding.txtNationaltymother.getText().toString().isEmpty()) {
            mBinding.txtNationaltymother.requestFocus();
            mBinding.txtNationaltymother.setError(getString(R.string.have_nationality));
            return;
        }else if (mBinding.txtAddressmother.getText().toString()==null||mBinding.txtAddressmother.getText().toString().isEmpty()) {
            mBinding.txtAddressmother.requestFocus();
            mBinding.txtAddressmother.setError(getString(R.string.please_input_address));
            return;
        }else if (!mBinding.txtNamefather.getText().toString().matches(namePattern)) {
            mBinding.txtNamefather.requestFocus();
            mBinding.txtNamefather.setError(getString(R.string.name_not_match));
            return;
        }else if (!mBinding.txtNamemother.getText().toString().matches(namePattern)) {
            mBinding.txtNamemother.requestFocus();
            mBinding.txtNamemother.setError(getString(R.string.name_not_match));
            return;
        }else if (!mBinding.txtBirthday.getText().toString().matches(datePattern)) {
            mBinding.txtBirthday.requestFocus();
            mBinding.txtBirthday.setError(getString(R.string.birthday_not_match));
            return;
        }else if (mBinding.RadioGroupHomeStatus.getCheckedRadioButtonId()==-1) {
            mBinding.RadioGroupHomeStatus.requestFocus();
            Toast.makeText(CreateUserActivity.this,R.string.please_select_home_status_now,Toast.LENGTH_LONG).show();
            return;
        }else if (mBinding.txtUsernameregister.getText() == null || mBinding.txtUsernameregister.getText().toString().isEmpty()) {
            mBinding.txtUsernameregister.requestFocus();
            mBinding.txtUsernameregister.setError(getString(R.string.please_input_username));
            return;
        } else if (mBinding.txtPasswordregister.getText() == null || mBinding.txtPasswordregister.getText().toString().isEmpty()) {
            mBinding.txtPasswordregister.requestFocus();
            mBinding.txtPasswordregister.setError(getString(R.string.please_input_password));
            return;
        }else if(mBinding.txtConfirmpassword.getText().toString() == null || mBinding.txtConfirmpassword.getText().toString().isEmpty()){
            mBinding.txtConfirmpassword.requestFocus();
            mBinding.txtConfirmpassword.setError(getString(R.string.please_input_confirm_password));
            return;
        }else if(!mBinding.txtConfirmpassword.getText().toString().matches(mBinding.txtPasswordregister.getText().toString())){
            mBinding.txtConfirmpassword.requestFocus();
            mBinding.txtConfirmpassword.setError(getString(R.string.password_not_match));
            return;
        }else{
            string = mBinding.txtIdcard.getText().toString().replaceAll(" ","");
            substringidcard = string.substring(0,7);
            sub1 = substringidcard.substring(0,1);
            sub2 = substringidcard.substring(1,2);
            sub3 = substringidcard.substring(2,3);
            sub4 = substringidcard.substring(3,4);
            sub5 = substringidcard.substring(4,5);
            sub6 = substringidcard.substring(5,6);
            sub7 = substringidcard.substring(6,7);
            if(sub1.toString().matches(idcardno1_1)||sub1.toString().matches(idcardno1_2)){
                mBinding.txtIdcard.requestFocus();
                mBinding.txtIdcard.setError(getString(R.string.id_card_thai));
                return;
            }
        }

        RadioButton radiobuttongender = (RadioButton) findViewById(mBinding.RadioGroupGender.getCheckedRadioButtonId());
        int idgender = mBinding.RadioGroupGender.indexOfChild(radiobuttongender);

        RadioButton radiobuttonstatusmarry = (RadioButton) findViewById(mBinding.RadioGroupMarry.getCheckedRadioButtonId());
        int idstatusmarry = mBinding.RadioGroupMarry.indexOfChild(radiobuttonstatusmarry);

        RadioButton radiobuttonstatusplacebirth = (RadioButton) findViewById(mBinding.RadioGroupPlaceBirth.getCheckedRadioButtonId());
        int idstatusplacebirth = mBinding.RadioGroupPlaceBirth.indexOfChild(radiobuttonstatusplacebirth);

        RadioButton radiobuttonstatusthaibirth = (RadioButton) findViewById(mBinding.RadioGroupThaiBirthOrAbroadBirth.getCheckedRadioButtonId());
        int idstatusthaiorabroadbirth = mBinding.RadioGroupThaiBirthOrAbroadBirth.indexOfChild(radiobuttonstatusthaibirth);

        RadioButton radiobuttonstatuswitness = (RadioButton) findViewById(mBinding.RadioGroupWitness.getCheckedRadioButtonId());
        int idstatuswitness = mBinding.RadioGroupWitness.indexOfChild(radiobuttonstatuswitness);


        RadioButton radiobuttonhomestatusregister = (RadioButton) findViewById(mBinding.RadioGroupHomeStatusRegister.getCheckedRadioButtonId());
        int idhomestatusregister = mBinding.RadioGroupHomeStatusRegister.indexOfChild(radiobuttonhomestatusregister);

        RadioButton radiobuttonhomestatus = (RadioButton) findViewById(mBinding.RadioGroupHomeStatus.getCheckedRadioButtonId());
        int idhomestatus = mBinding.RadioGroupHomeStatus.indexOfChild(radiobuttonhomestatus);

        RadioButton radiobuttonhomestatusbefor = (RadioButton) findViewById(mBinding.RadioGroupHomeStatusBefor.getCheckedRadioButtonId());
        int idhomestatusbefor = mBinding.RadioGroupHomeStatusBefor.indexOfChild(radiobuttonhomestatusbefor);

        RadioButton radiobuttonstatusbealivef = (RadioButton) findViewById(mBinding.RadioGroupFather.getCheckedRadioButtonId());
        int idstatusbealivef = mBinding.RadioGroupFather.indexOfChild(radiobuttonstatusbealivef);

        RadioButton radiobuttonstatusbealivem = (RadioButton) findViewById(mBinding.RadioGroupMother.getCheckedRadioButtonId());
        int idstatusbealivem = mBinding.RadioGroupMother.indexOfChild(radiobuttonstatusbealivem);

        RadioButton radiobuttonstatusbealivew1 = (RadioButton) findViewById(mBinding.RadioGroupWitnessAlive1.getCheckedRadioButtonId());
        int idstatusbealivew1 = mBinding.RadioGroupWitnessAlive1.indexOfChild(radiobuttonstatusbealivew1);

        RadioButton radiobuttonstatusbealivew2 = (RadioButton) findViewById(mBinding.RadioGroupWitnessAlive2.getCheckedRadioButtonId());
        int idstatusbealivew2 = mBinding.RadioGroupWitnessAlive2.indexOfChild(radiobuttonstatusbealivew2);

        RadioButton radiobuttonstatusbealivew3 = (RadioButton) findViewById(mBinding.RadioGroupWitnessAlive3.getCheckedRadioButtonId());
        int idstatusbealivew3 = mBinding.RadioGroupWitnessAlive3.indexOfChild(radiobuttonstatusbealivew3);

        CreateUserController manager = CreateUserController.getWsManager(CreateUserActivity.this);
        StatelessPersonModel statelessPersonModel = new StatelessPersonModel();
        StatelessPerson statelessPerson = new StatelessPerson();

        statelessPerson.setGender(idgender+1);
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("dd/MM/yyyy").parse(mBinding.txtBirthday.getText().toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        statelessPerson.setBirthDay(birthday);
        statelessPerson.setReligion(mBinding.txtReligion.getText().toString().trim().replaceAll(" ",""));
        statelessPerson.setEthnic(mBinding.txtEthnic.getText().toString().trim().replaceAll(" ",""));
        statelessPerson.setNationality(mBinding.txtNationality.getText().toString().trim().replaceAll(" ",""));
        statelessPerson.setHomeid(mBinding.txtHomeid.getText().toString().trim().replaceAll(" ",""));
        statelessPerson.setIdcard(mBinding.txtIdcard.getText().toString().trim().replaceAll(" ",""));
        statelessPerson.setStatusmarry(idstatusmarry+1);
        statelessPerson.setNameofspouse(mBinding.txtNamespouse.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
        if(mBinding.txtNamespouse.getText().toString()==null||mBinding.txtNamespouse.getText().toString().isEmpty()){
            statelessPerson.setNameofspouse("-");
        }
        statelessPerson.setIdcardofspouse(mBinding.txtIdcardspouse.getText().toString().trim().replaceAll(" ",""));
        if(mBinding.txtIdcardspouse.getText().toString()==null||mBinding.txtIdcardspouse.getText().toString().isEmpty()){
            statelessPerson.setIdcardofspouse("-");
        }
        statelessPerson.setNationalityofspouse(mBinding.txtNationaltyspouse.getText().toString().trim().replaceAll(" ",""));
        if(mBinding.txtNationaltyspouse.getText().toString()==null||mBinding.txtNationaltyspouse.getText().toString().isEmpty()){
            statelessPerson.setNationalityofspouse("-");
        }
        statelessPerson.setDateofmarry(mBinding.txtDatemarry.getText().toString().trim().replaceAll("/","%2F"));
        if(mBinding.txtDatemarry.getText().toString()==null||mBinding.txtDatemarry.getText().toString().isEmpty()){
            statelessPerson.setDateofmarry("-");
        }
        statelessPerson.setAddressofspouse(mBinding.txtAddressspouse.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
        if(mBinding.txtAddressspouse.getText().toString()==null||mBinding.txtAddressspouse.getText().toString().isEmpty()){
            statelessPerson.setAddressofspouse("-");
        }
        statelessPerson.setStatusplaceofbirth(idstatusplacebirth+1);
        statelessPerson.setStatusthaiorabroadbirth(idstatusthaiorabroadbirth+1);
        statelessPerson.setHospitalofbirth(mBinding.txtHospitalbirth.getText().toString().trim().replaceAll(" ",""));
        if(mBinding.txtHospitalbirth.getText().toString()==null||mBinding.txtHospitalbirth.getText().toString().isEmpty()){
            statelessPerson.setHospitalofbirth("-");
        }
        statelessPerson.setAddressofvillagebirth(mBinding.txtVillagebirth.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
        if(mBinding.txtVillagebirth.getText().toString()==null||mBinding.txtVillagebirth.getText().toString().isEmpty()){
            statelessPerson.setAddressofvillagebirth("-");
        }
        statelessPerson.setStatuswitness(idstatuswitness+1);
        statelessPerson.setCountryofbirth(mBinding.txtCountrybirth.getText().toString().replaceAll(" ",""));
        if(mBinding.txtCountrybirth.getText().toString()==null||mBinding.txtCountrybirth.getText().toString().isEmpty()){
            statelessPerson.setCountryofbirth("-");
        }
        statelessPerson.setDistrictcomethai(mBinding.txtDistrictcomethai.getText().toString().trim().replaceAll(" ",""));
        if(mBinding.txtDistrictcomethai.getText().toString()==null||mBinding.txtDistrictcomethai.getText().toString().isEmpty()){
            statelessPerson.setDistrictcomethai("-");
        }
        statelessPerson.setDatecomethai(mBinding.txtDatecomethai.getText().toString().replaceAll("/","%2F"));
        if(mBinding.txtDatecomethai.getText().toString()==null||mBinding.txtDatecomethai.getText().toString().isEmpty()){
            statelessPerson.setDatecomethai("-");
        }
        statelessPerson.setModecomethai(mBinding.txtModecomethai.getText().toString().trim().replaceAll(" ",""));
        if(mBinding.txtModecomethai.getText().toString()==null||mBinding.txtModecomethai.getText().toString().isEmpty()){
            statelessPerson.setModecomethai("-");
        }
        statelessPerson.setNameperson(mBinding.txtPersonname.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
        statelessPerson.setEmailperson(mBinding.txtEmail.getText().toString());
        statelessPerson.setTelperson(mBinding.txtTel.getText().toString());
        statelessPerson.setUsername(mBinding.txtUsernameregister.getText().toString().replaceAll(" ",""));
        statelessPerson.setPassword(mBinding.txtPasswordregister.getText().toString().replaceAll(" ",""));
        statelessPerson.setType(1);
        statelessPersonModel.setStatelessPerson(statelessPerson);

        if(sub1.equalsIgnoreCase("6")||sub1.equalsIgnoreCase("7")){
            statelessPerson.getIdcardtype().setIdcardno(sub1);
        }else if(sub1.equalsIgnoreCase("0")) {
            statelessPerson.getIdcardtype().setIdcardno(sub1 + sub6 + sub7);
        }


        AddressModel.Address addressregister = new AddressModel.Address();
        addressregister.setDetailaddress(mBinding.txtAddressregister.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
        if(mBinding.txtAddressregister.getText().toString().isEmpty()||mBinding.txtAddressregister.getText().toString()==null){
            addressregister.setDetailaddress("-");
        }
        addressregister.setFromyears("-");
        addressregister.setToyears("-");
        addressregister.setHomestatus(idhomestatusregister+1);
        addressregister.setStatusaddress(1);

        AddressModel.Address address = new AddressModel.Address();
        address.setDetailaddress(mBinding.txtAddressnow.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
        if(mBinding.txtAddressnow.getText().toString().isEmpty()||mBinding.txtAddressnow.getText().toString()==null){
            address.setDetailaddress("-");
        }
        if(mBinding.txtToyear.getText().toString().isEmpty()){
            address.setFromyears("-");
        }else if(mBinding.txtToyear.getText().toString().isEmpty()){
            address.setToyears("-");
        }else{
            address.setFromyears(mBinding.txtFromyear.getText().toString());
            address.setToyears(mBinding.txtToyear.getText().toString());
        }
        address.setHomestatus(idhomestatus+1);
        address.setStatusaddress(2);

        AddressModel.Address addressbefor = new AddressModel.Address();
        addressbefor.setDetailaddress(mBinding.txtAddressbefor.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
        if(mBinding.txtAddressbefor.getText().toString().isEmpty()||mBinding.txtAddressbefor.getText().toString()==null){
            addressbefor.setDetailaddress("-");
        }
        if(mBinding.txtFromyearbefor.getText().toString().isEmpty()||mBinding.txtFromyearbefor.getText().toString()==null){
            addressbefor.setFromyears("-");
        }else if(mBinding.txtToyearbefor.getText().toString().isEmpty()||mBinding.txtToyearbefor.getText().toString()==null){
            addressbefor.setToyears("-");
        }else{
            addressbefor.setFromyears(mBinding.txtFromyearbefor.getText().toString());
            addressbefor.setToyears(mBinding.txtToyearbefor.getText().toString());
        }
        addressbefor.setHomestatus(idhomestatusbefor+1);
        addressbefor.setStatusaddress(3);

        List<AddressModel.Address> listAddress = new ArrayList<>();
        listAddress.add(addressregister);
        listAddress.add(address);
        listAddress.add(addressbefor);

        statelessPerson.setAddressList(listAddress);

        EducationModel.Education education = new EducationModel.Education();
        education.setEducationdetails(mBinding.educationlevel.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
        List<EducationModel.Education> listEducations = new ArrayList<>();
        listEducations.add(education);
        for(EditText e : listEditTextEducation){
            EducationModel.Education education2 = new EducationModel.Education();
            education2.setEducationdetails(e.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
            listEducations.add(education2);
        }
        statelessPerson.setEducationList(listEducations);

        List<ParentModel.Parent> listParent = new ArrayList<>();

        ParentModel.Parent father = new ParentModel.Parent();
        father.setName(mBinding.txtNamefather.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
        father.setStatusbealive(idstatusbealivef+1);
        father.setBirthday(mBinding.txtFatherbirth.getText().toString().replaceAll("/","%2F"));
        if(mBinding.txtFatherbirth.getText().toString()==null||mBinding.txtFatherbirth.getText().toString().isEmpty()){
            father.setBirthday("-");
        }
        father.setIdcard(mBinding.txtIdcardfather.getText().toString().replaceAll(" ",""));
        father.setEthnic(mBinding.txtEthnicfather.getText().toString().replaceAll(" ",""));
        father.setNationality(mBinding.txtNationaltyfather.getText().toString().replaceAll(" ",""));
        father.setDatecomethai(mBinding.txtDatecomethaifather.getText().toString().replaceAll("/","%2F"));
        if(mBinding.txtDatecomethaifather.getText().toString().isEmpty()){
            father.setDatecomethai("-");
        }
        father.setAddress(mBinding.txtAddressfather.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
        father.setStatusparent(1);

        ParentModel.Parent mother = new ParentModel.Parent();
        mother.setName(mBinding.txtNamemother.getText().toString().replaceAll(" ","+"));
        mother.setStatusbealive(idstatusbealivem+1);
        mother.setBirthday(mBinding.txtMotherbirth.getText().toString().replaceAll("/","%2F"));
        if(mBinding.txtMotherbirth.getText().toString()==null||mBinding.txtMotherbirth.getText().toString().isEmpty()){
            father.setBirthday("-");
        }
        mother.setIdcard(mBinding.txtIdcardmother.getText().toString().replaceAll(" ",""));
        mother.setEthnic(mBinding.txtEthnicmother.getText().toString().replaceAll(" ",""));
        mother.setNationality(mBinding.txtNationaltymother.getText().toString().replaceAll(" ",""));
        mother.setDatecomethai(mBinding.txtDatecomethaimother.getText().toString().replaceAll("/","%2F"));
        if(mBinding.txtDatecomethaimother.getText().toString().isEmpty()){
            mother.setDatecomethai("-");
        }
        mother.setAddress(mBinding.txtAddressmother.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
        mother.setStatusparent(2);

        listParent.add(father);
        listParent.add(mother);

        statelessPerson.setParentList(listParent);

        if(mBinding.thaibirth.isChecked()||mBinding.yesWitness.isChecked()){
            if (mBinding.txtNamewitness1.getText().toString()==null||mBinding.txtNamewitness1.getText().toString().isEmpty()){
                mBinding.txtNamewitness1.requestFocus();
                mBinding.txtNamewitness1.setError(getString(R.string.please_input_data));
                return;
            }else if(mBinding.txtWitnessrelationship1.getText().toString()==null||mBinding.txtWitnessrelationship1.getText().toString().isEmpty()){
                mBinding.txtWitnessrelationship1.requestFocus();
                mBinding.txtWitnessrelationship1.setError(getString(R.string.please_input_data));
                return;
            }else if(mBinding.txtWitnessrelationship1.getText().toString()==null||mBinding.txtWitnessrelationship1.getText().toString().isEmpty()){
                mBinding.txtWitnessrelationship1.requestFocus();
                mBinding.txtWitnessrelationship1.setError(getString(R.string.please_input_data));
                return;
            }else if (mBinding.RadioGroupWitnessAlive1.getCheckedRadioButtonId()==-1) {
                mBinding.RadioGroupWitnessAlive1.requestFocus();
                Toast.makeText(CreateUserActivity.this,R.string.please_select_status_witness_be_alive,Toast.LENGTH_LONG).show();
                return;
            }else if(mBinding.txtAddresswitness1.getText().toString()==null||mBinding.txtAddresswitness1.getText().toString().isEmpty()){
                mBinding.txtAddresswitness1.requestFocus();
                mBinding.txtAddresswitness1.setError(getString(R.string.please_input_data));
                return;
            }
            List<WitnessModel.Witness> listWitnessThai = new ArrayList<>();
            WitnessModel.Witness witness1 = new WitnessModel.Witness();
            witness1.setNamewitness(mBinding.txtNamewitness1.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
            witness1.setRelationship(mBinding.txtWitnessrelationship1.getText().toString().replaceAll(" ",""));
            witness1.setStatusbealive(idstatusbealivew1+1);
            witness1.setAddresswitness(mBinding.txtAddresswitness1.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));

            WitnessModel.Witness witness2 = new WitnessModel.Witness();
            witness2.setNamewitness(mBinding.txtNamewitness2.getText().toString().replaceAll(" ","+").replaceAll("/","%2F"));
            if(mBinding.txtNamewitness2.getText().toString().isEmpty()){
                witness2.setNamewitness("-");
            }
            witness2.setRelationship(mBinding.txtWitnessrelationship2.getText().toString().replaceAll(" ",""));
            if(mBinding.txtWitnessrelationship2.getText().toString().isEmpty()){
                witness2.setRelationship("-");
            }
            witness2.setStatusbealive(idstatusbealivew2+1);
            witness2.setAddresswitness(mBinding.txtAddresswitness2.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));
            if(mBinding.txtAddresswitness2.getText().toString().isEmpty()){
                witness2.setAddresswitness("-");
            }
            listWitnessThai.add(witness1);
            listWitnessThai.add(witness2);
            statelessPerson.setWitnessList(listWitnessThai);
        }
        if(mBinding.notthaibirth.isChecked()){
            if(mBinding.txtNamewitness3.getText().toString().isEmpty()){
                mBinding.txtNamewitness3.requestFocus();
                mBinding.txtNamewitness3.setError(getString(R.string.please_input_data));
                return;
            }else if(mBinding.txtWitnessrelationship3.getText().toString().isEmpty()){
                mBinding.txtWitnessrelationship3.requestFocus();
                mBinding.txtWitnessrelationship3.setError(getString(R.string.please_input_data));
                return;
            }
            else if(mBinding.txtAddresswitness3.getText().toString().isEmpty()){
                mBinding.txtAddresswitness3.requestFocus();
                mBinding.txtAddresswitness3.setError(getString(R.string.please_input_data));
                return;
            }
            List<WitnessModel.Witness> listWitnessNotThai = new ArrayList<>();
            WitnessModel.Witness witness3 = new WitnessModel.Witness();
            witness3.setNamewitness(mBinding.txtNamewitness3.getText().toString().replaceAll(" ","+"));
            witness3.setRelationship(mBinding.txtWitnessrelationship3.getText().toString().replaceAll(" ",""));
            witness3.setStatusbealive(idstatusbealivew3+1);
            witness3.setAddresswitness(mBinding.txtAddresswitness3.getText().toString().replaceAll(" ", "+").replaceAll("/", "%2F").replaceAll(",", "%2C").replaceAll("\n","%0A"));

            listWitnessNotThai.add(witness3);
            statelessPerson.setWitnessList(listWitnessNotThai);
        }


        final ProgressDialog progress = ProgressDialog.show(this, getString(R.string.please_wait),
                getString(R.string.please_wait), true);

        manager.isAddUser(statelessPersonModel, new CreateUserController.CreateUserControllerListener() {
            @Override
            public void onComplete(Object response) {
                progress.dismiss();
                Intent intent = new Intent(CreateUserActivity.this, LoginActivity.class);
                intent.putExtra("username",mBinding.txtUsernameregister.getText().toString());
                startActivity(intent);
            }

            @Override
            public void onError(String err) {
                progress.dismiss();
                Toast.makeText(CreateUserActivity.this, err, Toast.LENGTH_LONG).show();
            }
        });

    }
}
