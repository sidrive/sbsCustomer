package com.sabaindomedika.stscustomer.features.ticket;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class TicketTypeFragment extends BaseFragment {
  public static TicketTypeFragment newInstance() {
    return new TicketTypeFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ticket_type, container, false);
    bind(this, view);
    return view;
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
