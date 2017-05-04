package com.sabaindomedika.stscustomer.features.notification;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.sabaindomedika.stscustomer.model.Notification;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fajar Rianda on 04/05/2017.
 */
public class NotificationPresenter extends MvpNullObjectBasePresenter<NotificationView> {
  public void loadData(){
    List<Notification> dummyData = new ArrayList<>();

    for (int i = 0;i < 5 ;i++){
      Notification notification = new Notification();
      notification.setContent("Lorem Ipsum");
      dummyData.add(notification);
    }
    getView().showContent(dummyData);
  }
}
