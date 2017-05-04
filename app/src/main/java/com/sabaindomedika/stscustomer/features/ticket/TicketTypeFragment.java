package com.sabaindomedika.stscustomer.features.ticket;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;
import com.sabaindomedika.stscustomer.features.ticket.open.OpenTicketActivity;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class TicketTypeFragment extends BaseFragment {

  @Bind(R.id.toolbar) Toolbar toolbar;
  public static TicketTypeFragment newInstance() {
    return new TicketTypeFragment();
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
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToDivisionType();
  }

  @OnClick(R.id.txtOther) public void onOther() {
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToDivisionType();
  }

  @OnClick(R.id.txtComplaint) public void onComplaint() {
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToDivisionType();
  }
}
