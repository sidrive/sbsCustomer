package com.sabaindomedika.stscustomer.dagger.component;

import com.sabaindomedika.stscustomer.LoginActivity;
import com.sabaindomedika.stscustomer.dagger.PerActivity;
import com.sabaindomedika.stscustomer.dagger.module.NetworkModule;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.DivisionTypeFragment;
import com.sabaindomedika.stscustomer.features.ticket.TicketTypeFragment;
import com.sabaindomedika.stscustomer.features.ticket.open.FormPresenter;
import com.sabaindomedika.stscustomer.features.ticket.status.HistoryTicketStatusFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.OpenTicketStatusFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusPresenter;
import dagger.Component;

/**
 * Created by Fajar Rianda on 26/01/2017.
 */
@PerActivity @Component(modules = { NetworkModule.class }, dependencies = { AppComponent.class })
public interface NetworkComponent {

  void inject(TicketTypeFragment ticketTypeFragment);

  void inject(LoginActivity loginActivity);

  void inject(FormPresenter formPresenter);

  void inject(TicketStatusPresenter ticketStatusPresenter);

  void inject(ProfileActivity profileActivity);

  void inject(DivisionTypeFragment divisionTypeFragment);

  void inject(OpenTicketStatusFragment openTicketStatusFragment);

  void inject(HistoryTicketStatusFragment historyTicketStatusFragment);
}
