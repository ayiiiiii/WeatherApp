package com.mobven.weatherapp.ui.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.mobven.weatherapp.R;
import com.mobven.weatherapp.ui.ViewNavigator;


public class BaseActivity extends AppCompatActivity implements IView {
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewNavigator.addActivityToStack(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ViewNavigator.removeActivity(this);
    }

    @Override
    public void showMessageDialog(String text) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void showLoading() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getText(R.string.app_name));
            progressDialog.setMessage(getText(R.string.please_wait));
        }

        progressDialog.show();
    }

    @Override
    public void dissmisLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showMessageToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void killActivity() {
        this.finish();
    }


}
