package com.sabaindomedika.stscustomer.features.ticket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;
import com.sabaindomedika.stscustomer.constant.URLCons;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;
import com.sabaindomedika.stscustomer.utils.Logger;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class TicketTypeFragment extends BaseFragment {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Inject ApiService apiService;

  public static TicketTypeFragment newInstance() {
    return new TicketTypeFragment();
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    DaggerInit.networkComponent(context).inject(this);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ticket_type, container, false);
    bind(this, view);
    return view;
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

  @OnClick(R.id.txtService) public void onService() {
   navigateToDivisionType("1");
  }

  @OnClick(R.id.txtOther) public void onOther() {
    navigateToDivisionType("2");
  }

  @OnClick(R.id.txtComplaint) public void onComplaint() {
    navigateToDivisionType("3");
  }

  private void navigateToDivisionType(String ticketType){
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToDivisionType(ticketType);
  }

}
