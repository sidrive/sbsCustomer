package com.sabaindomedika.stscustomer.features.ticket.status;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.sabaindomedika.stscustomer.model.Ticket;
import java.util.List;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public interface TicketStatusView extends MvpView {
  void showContent(List<Ticket> ticket);
}
