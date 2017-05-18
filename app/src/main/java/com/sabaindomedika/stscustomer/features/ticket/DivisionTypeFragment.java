package com.sabaindomedika.stscustomer.features.ticket;

import android.content.Context;
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
public class DivisionTypeFragment extends BaseFragment {

  @Bind(R.id.toolbar) Toolbar toolbar;

  public static DivisionTypeFragment newInstance(String ticketType) {
    Bundle bundle = new Bundle();
    bundle.putString(String.class.getSimpleName(), ticketType);
    DivisionTypeFragment fragment = new DivisionTypeFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_division_type, container, false);
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

  @OnClick(R.id.txtEngineer) public void onEngineer() {
    navigatetoForm("1");
  }

  @OnClick(R.id.txtApplication) public void OnApplication() {
    navigatetoForm("2");
  }

  @OnClick(R.id.txtIT) public void OnIT() {
    navigatetoForm("3");
  }

  @OnClick(R.id.txtHanter) public void OnHanter() {
    navigatetoForm("4");
  }

  private void navigatetoForm(String divisioType) {
    String ticketType = getArguments().getString(String.class.getSimpleName());
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToForm(ticketType,divisioType);
  }
}
