package com.sabaindomedika.stscustomer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.constant.ErrorCons;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.Auth;
import com.sabaindomedika.stscustomer.utils.Preferences;
import com.sabaindomedika.stscustomer.utils.Toasts;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 15/05/2017.
 */
public class LoginActivity extends BaseActivity {

  @Bind(R.id.inpUsername) EditText inpUsername;
  @Bind(R.id.inpPassword) EditText inpPassword;
  @Inject ApiService apiService;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
    ButterKnife.bind(this);
    DaggerInit.networkComponent(this).inject(this);
  }

  private boolean isValid() {
    if (TextUtils.isEmpty(inpUsername.getText().toString().trim())) {
      inpUsername.setError("Email harus diisi");
    }
    if (TextUtils.isEmpty(inpPassword.getText().toString().trim())) {
      inpPassword.setError("Password harus diisi");
    }
    return true;
  }

  @OnClick(R.id.btnSubmit) public void onLoginClick() {
    login();
  }

  private void login() {
    if (!isValid()) {
      return;
    }

    ProgressDialog progressDialog = new ProgressDialog(this);
    progressDialog.setMessage("Login...");
    progressDialog.show();

    Auth auth = new Auth();
    auth.setUsername(inpUsername.getText().toString().trim());
    auth.setPassword(inpPassword.getText().toString().trim());
    auth.setGrantType("password");
    auth.setClientId(getString(R.string.client_id));
    auth.setClientSecret(getString(R.string.client_secret));
    auth.setScope("*");

    apiService.login(auth)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(token -> {
          Preferences.setToken(token);
          progressDialog.dismiss();
          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
          intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
          startActivity(intent);
          finish();
        }, throwable -> {
          progressDialog.dismiss();
          if (ErrorHelper.getError(throwable).equals(ErrorCons.INVALID_CREDENTIAL)) {
            Toasts.show("Email dan Password anda salah");
          } else {
            ErrorHelper.thrown(throwable);
          }
        });
  }
}