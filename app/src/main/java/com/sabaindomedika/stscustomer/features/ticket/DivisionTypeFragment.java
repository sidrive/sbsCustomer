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
public class DivisionTypeFragment extends BaseFragment {

  public static DivisionTypeFragment newInstance() {
    return new DivisionTypeFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_division_type, container, false);
    bind(this, view);
    return view;
  }

  @OnClick({ R.id.txtEngineer, R.id.txtAplikasi }) public void OnEngineer() {
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToForm();
  }

  @OnClick(R.id.txtIT) public void OnIT() {
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToForm();
  }

  @OnClick(R.id.txtHanter) public void OnHanter() {
    OpenTicketActivity activity = (OpenTicketActivity) getActivity();
    activity.navigateToForm();
  }
}
