package com.sabaindomedika.stscustomer.features.ticket.open;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpFragment;
import com.sabaindomedika.stscustomer.model.Department;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Toasts;
import java.util.List;

import static butterknife.ButterKnife.bind;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class FormFragment extends BaseMvpFragment<FormView, FormPresenter> implements FormView {

  @Bind(R.id.toolbar) Toolbar toolbar;
  @Bind(R.id.inpDescription) EditText inpDescription;
  @Bind(R.id.inpName) EditText inpName;
  @Bind(R.id.inpPhone) EditText inpPhone;
  @Bind(R.id.spnPriority) Spinner spnPriority;
  @Bind(R.id.lyRadioContainer) LinearLayout lyRadioContainer;
  String priority;
  String ticketType;
  String divisionType;
  String divisionName;
  String departmentType;

  public static FormFragment newInstance(String ticketType, String divisioType, String divisionName) {
    Bundle bundle = new Bundle();
    bundle.putString(Ticket.class.getSimpleName(), ticketType);
    bundle.putString(Division.class.getSimpleName(), divisioType);
    bundle.putString(String.class.getSimpleName(), divisionName);
    FormFragment fragment = new FormFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  public static FormFragment newInstanceFormComplaint(String ticketType) {
    Bundle bundle = new Bundle();
    bundle.putString(Ticket.class.getSimpleName(), ticketType);
    FormFragment fragment = new FormFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  @Override public void onAttach(Context context) {
    super.onAttach(context);
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_form, container, false);
    bind(this, view);
    return view;
  }

  @NonNull @Override public FormPresenter createPresenter() {
    return new FormPresenter(context);
  }

  @Override public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    init();
    setupToolbar();
  }

  private void init() {
    Bundle bundle = getArguments();
    priority = spnPriority.getSelectedItem().toString();
    ticketType = bundle.getString(Ticket.class.getSimpleName());
    divisionType = bundle.getString(Division.class.getSimpleName());
    divisionName = bundle.getString(String.class.getSimpleName());

    /* Check for Complaint Ticket*/
    if (divisionType == null){
      presenter.loadDepartment();
    }
  }

  private void setupToolbar() {
    getBaseActivity().setSupportActionBar(toolbar);
    getBaseActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setTitle("Request to ".concat(getTitleToolbar()));
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(v -> {
      getBaseActivity().onBackPressed();
    });
  }

  private String getTitleToolbar(){
    if (ticketType.equals("3")){
      return "Complaint";
    } else {
      return divisionName;
    }
  }

  @Override public void statusOpenTicket(boolean success) {
    if (success) {
      Toasts.show("Open Tiket Sukses");
      getBaseActivity().finish();
    }
  }

  @Override public void showRequestDivision() {

  }

  @Override public void showDepartment(List<Department> departments) {

    for (int i = 0; i < departments.size(); i++) {
      Department department = departments.get(i);

      View view = LayoutInflater.from(context)
          .inflate(R.layout.list_item_radio_button, lyRadioContainer, false);
      RadioButton radio = ButterKnife.findById(view, R.id.radio);
      TextView txtRadio = ButterKnife.findById(view, R.id.txtRadio);

      txtRadio.setText(department.getName());
      lyRadioContainer.addView(view, i);
    }
  }

  @OnClick(R.id.btnSubmit) public void onOpenTicket() {
    if (!isValid()) {
      return;
    }

    Ticket ticket = new Ticket();
    ticket.setTicketTypeId(ticketType);
    if (departmentType != null) {
      ticket.setDepartmentId(departmentType);
    } else {
      ticket.setDivisionId(divisionType);
    }
    ticket.setDescription(inpDescription.getText().toString().trim());
    ticket.setPriority(priority);
    ticket.setStaffPhoneNumber(inpPhone.getText().toString().trim());
    ticket.setStaffName(inpName.getText().toString().trim());

    presenter.postOpenTicket(ticket);
  }

  private boolean isValid() {
    if (TextUtils.isEmpty(inpDescription.getText().toString().trim())) {
      inpDescription.setError("Silahkan isi deskripsi");
      return false;
    }

    if (TextUtils.isEmpty(inpName.getText().toString().trim())) {
      inpName.setError("Silahkan isi nama ahli");
      return false;
    }

    if (TextUtils.isEmpty(inpPhone.getText().toString().trim())) {
      inpPhone.setError("Silahkan isi nomor hp");
      return false;
    }
    return true;
  }

  @OnItemSelected(R.id.spnPriority) public void onSpinnerPriority(Spinner spinner, int position) {
    priority = spinner.getSelectedItem().toString();
  }
}
