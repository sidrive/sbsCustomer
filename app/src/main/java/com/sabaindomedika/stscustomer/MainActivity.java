package com.sabaindomedika.stscustomer;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest.permission;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.iid.FirebaseInstanceId;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;
import com.sabaindomedika.stscustomer.model.FcmToken;
import com.sabaindomedika.stscustomer.utils.Preferences;
import java.util.List;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

  @Inject
  ApiService apiService;
  @Bind(R.id.imghead) ImageView imghead;
  private static final String[] LOCATION_AND_CONTACTS =
      {CAMERA,permission.BLUETOOTH, permission.BLUETOOTH_ADMIN, permission.WRITE_EXTERNAL_STORAGE, permission.READ_EXTERNAL_STORAGE};
  private static final int RC_CAMERA_PERM = 123;

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

  private void getPermission() {
    Dexter.withActivity(this)
        .withPermissions(
            permission.CAMERA,
            permission.WRITE_EXTERNAL_STORAGE,
            permission.READ_EXTERNAL_STORAGE
        ).withListener(new MultiplePermissionsListener() {
      @Override public void onPermissionsChecked(MultiplePermissionsReport report) {/* ... */}
      @Override public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
    }).check();
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
}
