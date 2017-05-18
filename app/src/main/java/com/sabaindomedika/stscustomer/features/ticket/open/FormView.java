package com.sabaindomedika.stscustomer.features.ticket.open;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public interface FormView extends MvpView {
  void statusOpenTicket(boolean success);
}
