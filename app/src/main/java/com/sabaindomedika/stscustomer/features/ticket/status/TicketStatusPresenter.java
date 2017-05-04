package com.sabaindomedika.stscustomer.features.ticket.status;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.model.Ticket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class TicketStatusPresenter extends MvpNullObjectBasePresenter<TicketStatusView> {

  public void loadData(){
    List<Ticket> dummyData = new ArrayList<>();

    for (int i = 0;i < 5 ;i++){
      Ticket ticket = new Ticket();
      ticket.setTicketNumber("#123456");
      ticket.setContent("Lorem Ipsum");

      dummyData.add(ticket);
    }
    getView().showContent(dummyData);
  }
}
