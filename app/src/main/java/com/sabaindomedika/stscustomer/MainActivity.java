package com.sabaindomedika.stscustomer;

import android.Manifest.permission;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.firebase.iid.FirebaseInstanceId;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusActivity;
import com.sabaindomedika.stscustomer.model.FcmToken;
import com.sabaindomedika.stscustomer.utils.Preferences;
import com.sabaindomedika.stscustomer.utils.Toasts;
import java.util.List;
import javax.inject.Inject;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.AppSettingsDialog.Builder;
import pub.devrel.easypermissions.EasyPermissions;
import pub.devrel.easypermissions.EasyPermissions.PermissionCallbacks;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity implements PermissionCallbacks{

  @Inject
  ApiService apiService;
  @Bind(R.id.imghead)
  AppCompatImageView imghead;
  private static final String[] LOCATION_AND_CONTACTS =
      {permission.CAMERA,permission.BLUETOOTH, permission.BLUETOOTH_ADMIN};
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
    requestPermissionCamera();
    DaggerInit.networkComponent(this).inject(this);
    String fcm_token = FirebaseInstanceId.getInstance().getToken();
    updateFCMToken(fcm_token);
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

  private void requestPermissionCamera() {
    EasyPermissions.requestPermissions(
        this,
        getString(R.string.rationale_camera),
        RC_CAMERA_PERM,
        permission.CAMERA);
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

  @Override
  public void onPermissionsGranted(int requestCode, List<String> perms) {

  }

  @Override
  public void onPermissionsDenied(int requestCode, List<String> perms) {
    if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
      new Builder(this).build().show();
    }
  }
  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
  }

  @SuppressLint("StringFormatMatches")
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
      String yes = getString(R.string.yes);
      String no = getString(R.string.no);
      String returned_from_app_settings_to_activity = "Returned from app settings to MainActivity with the following permissions:\n"
          + "        \\n\\nCamera: %s\n"
          + "        \\nLocation &amp; Contacts: %s\n"
          + "        \\nSMS: %s";
      // Do something after user returned from app settings screen, like showing a Toast.
      Toasts.showToast(this, returned_from_app_settings_to_activity);
      hasCameraPermission();
    }
  }

  private boolean hasCameraPermission() {
    return EasyPermissions.hasPermissions(this, permission.CAMERA);
  }
}
