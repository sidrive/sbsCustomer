package com.sabaindomedika.stscustomer.features.ticket.open;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.sabaindomedika.stscustomer.model.Category;
import com.sabaindomedika.stscustomer.model.Department;
import com.sabaindomedika.stscustomer.model.Instrument;
import com.sabaindomedika.stscustomer.model.Interface;
import com.sabaindomedika.stscustomer.model.RequestDivision;
import java.util.List;

/**
 * Created by Fajar Rianda on 17/05/2017.
 */
public interface FormView extends MvpView {
  void statusOpenTicket(boolean success);
  void showRequestDivision(List<RequestDivision> requestDivisions);
  void showDepartment(List<Department> departments);
  void showInstrument(List<Instrument> instruments);
  void showDeviceName(List<Category> interfaces);
  void showLoading(boolean firstLoad);
  void showError(Throwable throwable);
}
