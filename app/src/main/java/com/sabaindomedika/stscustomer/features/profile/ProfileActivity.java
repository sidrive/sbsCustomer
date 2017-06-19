package com.sabaindomedika.stscustomer.features.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.model.User;
import com.sabaindomedika.stscustomer.utils.Preferences;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class ProfileActivity extends BaseActivity {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Inject ApiService apiService;
  @Bind(R.id.txtId) TextView txtId;
  @Bind(R.id.txtName) TextView txtName;

  public static void start(Context context) {
    Intent intent = new Intent(context, ProfileActivity.class);
    context.startActivity(intent);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);
    DaggerInit.networkComponent(this).inject(this);
    setupToolbar();
    loadData();
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Profile");
  }

  private void loadData() {
    if (Preferences.getUserProfile() == null) {
      apiService.getUserProfile()
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(object -> {
            if (object == null) {
              return;
            }
            Preferences.setUserProfile(object.getData());
            showContent(object.getData());
          }, error -> {
            if (!isFinishing()) ErrorHelper.thrown(error);
          });
    } else {
      showContent(Preferences.getUserProfile());
    }
  }

  private void showContent(User user) {
    txtId.setText(user.getId());
    txtName.setText(user.getName());
  }

  /* Menu */
  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
