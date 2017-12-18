package com.sabaindomedika.stscustomer.features.ticket.status.detail;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.apiservice.ApiService;
import com.sabaindomedika.stscustomer.basecommon.BaseDialogFragment;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.PartAdapter;
import com.sabaindomedika.stscustomer.model.part.Datum;
import java.util.ArrayList;
import javax.inject.Inject;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by raka on 14/12/17.
 */

@SuppressLint("ValidFragment")
public class ServiceReportFragment extends BaseDialogFragment {

  public static final int DIALOG_REQUEST_CODE = 0x511;
  @Inject
  ApiService apiService;
  int id_report;
  String id_ticket;
  @Bind(R.id.tvEmpty)
  TextView tvEmpty;
  @Bind(R.id.rcvsparepart)
  RecyclerView rcvsparepart;
  PartAdapter adapter;

  @SuppressLint("ValidFragment")
  public ServiceReportFragment(Integer id_report, String id_ticket) {
    this.id_report = id_report;
    this.id_ticket = id_ticket;
  }

  @Override
  public void onStart() {
    super.onStart();
    DaggerInit.networkComponent(context).inject(this);
    initView();
    initData();
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    Dialog dialogfrag = new Dialog(context);
    dialogfrag.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialogfrag.setContentView(getContentView());
    return dialogfrag;
  }

  public View getContentView() {
    View view = inflater.inflate(R.layout.fragment_listpart, null);
    ButterKnife.bind(this, view);
    return view;
  }

  private void initView() {
    adapter = new PartAdapter(new ArrayList<Datum>(0),context);
    rcvsparepart.setHasFixedSize(true);
    rcvsparepart.setLayoutManager(new LinearLayoutManager(context));
    rcvsparepart.setAdapter(adapter);
  }

  private void initData() {
    apiService.servicePart(id_ticket, id_report)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(object -> {
          adapter.UpdateData(object.getData());
          tvEmpty.setVisibility(View.GONE);
          rcvsparepart.setVisibility(View.VISIBLE);
        }, error -> {
          rcvsparepart.setVisibility(View.GONE);
          tvEmpty.setVisibility(View.VISIBLE);
        });
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}
