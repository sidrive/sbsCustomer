package com.sabaindomedika.stscustomer.features.ticket.status;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnItemClick;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpFragment;
import com.sabaindomedika.stscustomer.dagger.DaggerInit;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.TicketStatusAdapter;
import com.sabaindomedika.stscustomer.features.ticket.status.detail.TicketStatusDetailActivity;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.util.List;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 06/06/2017.
 */
public class TicketStatusHistoryFragment
    extends BaseMvpFragment<TicketStatusView, TicketStatusPresenter> implements TicketStatusView {

  @Bind(R.id.lvContent) ListView lvContent;
  @Bind(R.id.progressBar) ProgressBar progressBar;
  @Bind(R.id.txtContentAvailable) TextView txtContentAvailable;
  @Bind(R.id.swipeRefresh) SwipeRefreshLayout swipeRefresh;

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
    adapter = new TicketStatusAdapter(context, this, true);
    lvContent.setAdapter(adapter);
    lvContent.setDivider(null);
    lvContent.setDividerHeight(0);

    presenter.loadDataHistory();
    swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        presenter.loadDataHistory();
      }
    });
  }

  @NonNull @Override public TicketStatusPresenter createPresenter() {
    return new TicketStatusPresenter(context);
  }

  @Override public void showContent(List<Ticket> tickets) {
    txtContentAvailable.setVisibility(tickets.isEmpty()
        ? View.VISIBLE
        : View.GONE);
    adapter.pushData(tickets);
  }

  @Override public void showLoading(boolean firstLoad, boolean isRefresh) {
https://repo.komuri.co.id/saba-indomedika/sts-android-customer.git
    if (!firstLoad && isRefresh) {
      swipeRefresh.setRefreshing(false);
    }

    if (!firstLoad) progressBar.setVisibility(View.GONE);
  }

  @Override public void showError(Throwable throwable) {
    if (isVisible()) {
      ErrorHelper.thrown(throwable);
    }
  }

  @OnItemClick(R.id.lvContent)
  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    TicketStatusDetailActivity.start(context, adapter.getItem(i));
  }
}
