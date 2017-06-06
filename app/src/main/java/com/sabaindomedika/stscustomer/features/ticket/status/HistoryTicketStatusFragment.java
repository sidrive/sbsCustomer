package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.Bind;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpFragment;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.TicketStatusAdapter;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.util.List;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 06/06/2017.
 */
public class HistoryTicketStatusFragment
    extends BaseMvpFragment<TicketStatusView, TicketStatusPresenter> implements TicketStatusView {

  @Bind(R.id.lvContent) ListView lvContent;
  TicketStatusAdapter adapter;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_ticket_status, container, false);
    bind(this, view);
    return view;
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    DaggerInit.networkComponent(context).inject(this);
    init();
  }

  private void init() {
    adapter = new TicketStatusAdapter(context,getBaseFragmentManager());
    lvContent.setAdapter(adapter);
    lvContent.setDivider(null);
    lvContent.setDividerHeight(0);

    presenter.loadData();
  }

  @NonNull @Override public TicketStatusPresenter createPresenter() {
    return new TicketStatusPresenter(context);
  }

  @Override public void showContent(List<Ticket> tickets) {
    adapter.pushData(tickets);
  }

  @Override public void showLoading(boolean firstLoad, boolean isRefresh) {

  }

  @Override public void showError(Throwable throwable) {
    if (isVisible()){
      ErrorHelper.thrown(throwable);
    }

  }
}
