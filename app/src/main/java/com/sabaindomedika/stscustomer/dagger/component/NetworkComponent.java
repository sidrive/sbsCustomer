package com.sabaindomedika.stscustomer.dagger.component;

import com.sabaindomedika.stscustomer.LoginActivity;
import com.sabaindomedika.stscustomer.MainActivity;
import com.sabaindomedika.stscustomer.dagger.PerActivity;
import com.sabaindomedika.stscustomer.dagger.module.NetworkModule;
import com.sabaindomedika.stscustomer.features.notification.NotificationActivity;
import com.sabaindomedika.stscustomer.features.notification.NotificationDetailActivity;
import com.sabaindomedika.stscustomer.features.notification.NotificationPresenter;
import com.sabaindomedika.stscustomer.features.profile.ProfileActivity;
import com.sabaindomedika.stscustomer.features.ticket.CloseTicketFragment;
import com.sabaindomedika.stscustomer.features.ticket.DivisionTypeFragment;
import com.sabaindomedika.stscustomer.features.ticket.TicketTypeFragment;
import com.sabaindomedika.stscustomer.features.ticket.open.FormPresenter;
import com.sabaindomedika.stscustomer.features.ticket.status.detail.TicketStatusDetailActivity;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusHistoryFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusOpenFragment;
import com.sabaindomedika.stscustomer.features.ticket.status.TicketStatusPresenter;
import com.sabaindomedika.stscustomer.features.ticket.status.adapter.TicketStatusAdapter;
import com.sabaindomedika.stscustomer.features.ticket.status.detail.TicketStatusDetailPresenter;
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

  void inject(TicketStatusOpenFragment ticketStatusOpenFragment);

  void inject(TicketStatusHistoryFragment ticketStatusHistoryFragment);

  void inject(CloseTicketFragment closeTicketFragment);

  void inject(TicketStatusAdapter ticketStatusAdapter);

  void inject(MainActivity mainActivity);

  void inject(TicketStatusDetailActivity ticketStatusDetailActivity);

  void inject(NotificationActivity notificationActivity);

  void inject(NotificationPresenter notificationPresenter);

  void inject(NotificationDetailActivity notificationDetailActivity);

  void inject(TicketStatusDetailPresenter ticketStatusDetailPresenter);
}
