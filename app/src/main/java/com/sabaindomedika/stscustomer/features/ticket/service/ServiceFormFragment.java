package com.sabaindomedika.stscustomer.features.ticket.service;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseFragment;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class ServiceFormFragment extends BaseFragment {

  public static ServiceFormFragment newInstance(){
    return new ServiceFormFragment();
  }
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_service_form, container, false);
    bind(this, view);
    return view;
  }
}
