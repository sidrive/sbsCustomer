package com.sabaindomedika.stscustomer.features.ticket.open.service;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpFragment;
import com.sabaindomedika.stscustomer.features.ticket.open.FormPresenter;
import com.sabaindomedika.stscustomer.features.ticket.open.FormView;
import com.sabaindomedika.stscustomer.model.Department;
import java.util.List;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class ServiceFormFragment extends BaseMvpFragment<FormView,FormPresenter> implements FormView {

  @Bind(R.id.toolbar) Toolbar toolbar;

  public static ServiceFormFragment newInstance() {
    return new ServiceFormFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_service_form, container, false);
    bind(this, view);
    return view;
  }

  @NonNull @Override public FormPresenter createPresenter() {
    return new FormPresenter(context);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    setupToolbar();
  }

  private void setupToolbar() {
    getBaseActivity().setSupportActionBar(toolbar);
    getBaseActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(v -> {
      getBaseActivity().onBackPressed();
    });
  }

  @Override public void statusOpenTicket(boolean status) {

  }

  @Override public void showRequestDivision() {

  }

  @Override public void showDepartment(List<Department> departments) {

  }
}
