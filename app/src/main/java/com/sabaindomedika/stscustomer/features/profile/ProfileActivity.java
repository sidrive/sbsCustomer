package com.sabaindomedika.stscustomer.features.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseActivity;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.profile.adapter.EmployeeAdapter;
import com.sabaindomedika.stscustomer.features.profile.adapter.InstrumentAdapter;
import com.sabaindomedika.stscustomer.features.profile.adapter.InterfaceAdapter;
import com.sabaindomedika.stscustomer.model.profile.Datum;
import com.sabaindomedika.stscustomer.model.profile.Datum_;
import com.sabaindomedika.stscustomer.model.profile.Datum__;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ProfileActivity extends BaseActivity {

  @Bind(R.id.toolbar)
  Toolbar toolbar;
  @Inject
  ApiService apiService;
  InterfaceAdapter adapterinterface;
  InstrumentAdapter adapterinstrument;
  EmployeeAdapter adapteremployee;
  @Bind(R.id.txtId)
  TextView txtId;
  @Bind(R.id.txtName)
  TextView txtName;
  @Bind(R.id.txtLocation)
  TextView txtLocation;
  @Bind(R.id.lvinstrument)
  RecyclerView lvInstrument;
  @Bind(R.id.lvinterface)
  RecyclerView lvInterface;
  @Bind(R.id.lvEmployee)
  RecyclerView lvEmployee;

  public static void start(Context context) {
    Intent intent = new Intent(context, ProfileActivity.class);
    context.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_profile);
    ButterKnife.bind(this);
    DaggerInit.networkComponent(this).inject(this);
    setupToolbar();
    init();
    loadData();
  }

  private void init() {
    adapterinstrument = new InstrumentAdapter(new ArrayList<Datum_>(0), getApplicationContext());
    adapterinterface = new InterfaceAdapter(new ArrayList<Datum__>(0), getApplicationContext());
    adapteremployee = new EmployeeAdapter(new ArrayList<Datum>(0), getApplicationContext());
    lvEmployee.setHasFixedSize(true);
    lvEmployee.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    lvEmployee.setAdapter(adapteremployee);
    lvInstrument.setHasFixedSize(true);
    lvInstrument.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    lvInstrument.setAdapter(adapterinstrument);
    lvInterface.setHasFixedSize(true);
    lvInterface.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    lvInterface.setAdapter(adapterinterface);
  }

  private void setupToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Profile");
  }

  private void loadData() {
    apiService.getUserProfile()
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          txtId.setText(object.getData().getCustomer().getData().getName());
          txtName.setText(object.getData().getName());
          txtLocation.setText(object.getData().getCustomer().getData().getAddress());
          adapteremployee.UpdateData(object.getData().getSupports().getData());
          adapterinterface.UpdateData(object.getData().getInterfaces().getData());
          adapterinstrument.UpdateData(object.getData().getInstruments().getData());
        }, error -> {
          if (!isFinishing()) {
            ErrorHelper.thrown(error);
          }
        });
  }

  /* Menu */
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        finish();
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
