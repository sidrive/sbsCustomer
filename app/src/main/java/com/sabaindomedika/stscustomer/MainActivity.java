package com.sabaindomedika.stscustomer;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.broadcast.BroadcastActivity;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusPresenter;
import com.sabaindomedika.stscustomer.model.FcmToken;
import com.sabaindomedika.stscustomer.utils.Preferences;
import com.sabaindomedika.stscustomer.utils.Toasts;
import com.sabaindomedika.stscustomer.utils.helper.DialogListener;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseMvpActivity<DialogListener,MainPresenter> implements DialogListener{

  @Inject
  ApiService apiService;
  @Bind(R.id.imghead) ImageView imghead;
  private String[] permission = new String[]{
      android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
  private static final int PERMISSION_CALLBACK_CONSTANT = 100;

  public static void start(Context context) {
    Intent intent = new Intent(context, MainActivity.class);
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    DaggerInit.networkComponent(this).inject(this);
    String fcm_token = FirebaseInstanceId.getInstance().getToken();
    updateFCMToken(fcm_token);
    getPermission();
  }

  @NonNull
  @Override
  public MainPresenter createPresenter() {
    return new MainPresenter(this);
  }

  private void getPermission() {
    if (ActivityCompat.checkSelfPermission(this, permission[0]) != PackageManager.PERMISSION_GRANTED
        || ActivityCompat.checkSelfPermission(this, permission[1])
        != PackageManager.PERMISSION_GRANTED) {
      if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission[0])
          || ActivityCompat.shouldShowRequestPermissionRationale(this, permission[1])){
      } else {
        presenter.showDialog(this, this, permission);
      }
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    if (requestCode == PERMISSION_CALLBACK_CONSTANT) {
      boolean allGrant = false;
      for (int i = 0; i < grantResults.length; i++) {
        if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
          allGrant = true;
        } else {
          allGrant = false;
        }
      }
      if (allGrant) {
        Toasts.showToast(this, "ALL GRANTED");
      } else {
        getPermission();
      }
    }
  }

  private void updateFCMToken(String fcm_token) {
    Log.e("updateFCMToken", "MainActivity" + fcm_token);
    FcmToken fcm = new FcmToken();
    fcm.setFcmToken(fcm_token);
    apiService.updateFcm(fcm)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(userResponses -> {
        }, throwable -> {
        });
  }

  @OnClick(R.id.txtOpenTicket)
  public void onOpenTicket() {
    OpenTicketActivity.start(this);
  }

  @OnClick(R.id.txtNotification)
  public void onNotification() {
    NotificationActivity.start(this);
  }

  @OnClick(R.id.txtStatusTicket)
  public void OnStatusTicket() {
    TicketStatusActivity.start(this);
  }

  @OnClick(R.id.txtProfile)
  public void onProfile() {
    ProfileActivity.start(this);
  }

  @OnClick(R.id.btnLogout)
  public void onLogout() {
    Preferences.clear();
    LoginActivity.start(this);
  }

  @OnClick(R.id.btnbroadcast)
  public void onBroadcast() {
    BroadcastActivity.start(this);
  }

  @Override
  public void dialogPositive(DialogInterface dialogInterface, String[] permission) {
    dialogInterface.dismiss();
    ActivityCompat.requestPermissions(this, permission, PERMISSION_CALLBACK_CONSTANT);
  }

  @Override
  public void dialogNegative(DialogInterface dialogInterface) {

  }

  @Override
  public void dialogSetting(DialogInterface dialogInterface) {

  }
}
