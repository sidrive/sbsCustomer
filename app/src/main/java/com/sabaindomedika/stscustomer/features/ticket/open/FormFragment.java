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
import android.widget.Spinner;
import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpFragment;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Toasts;

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

  String priority;
  String ticketType;
  String divisionType;

  public static FormFragment newInstance(String ticketType, String divisioType) {
    Bundle bundle = new Bundle();
    bundle.putString(Ticket.class.getSimpleName(), ticketType);
    bundle.putString(Division.class.getSimpleName(), divisioType);
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
  }

  private void setupToolbar() {
    getBaseActivity().setSupportActionBar(toolbar);
    getBaseActivity().getSupportActionBar().setDisplayShowTitleEnabled(false);
    toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
    toolbar.setNavigationOnClickListener(v -> {
      getBaseActivity().onBackPressed();
    });
  }

  @Override public void statusOpenTicket(boolean success) {
    if (success) {
      Toasts.show("Open Tiket Sukses");
      getBaseActivity().finish();
    }
  }

  @OnClick(R.id.btnSubmit) public void onOpenTicket() {
    if (!isValid()) {
      return;
    }

    Ticket ticket = new Ticket();
    ticket.setTicketTypeId(ticketType);
    ticket.setDivisionId(divisionType);
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
