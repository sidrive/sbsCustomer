package com.sabaindomedika.stscustomer.features.ticket.open;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.sabaindomedika.stscustomer.R;
import com.sabaindomedika.stscustomer.basecommon.BaseMvpFragment;
import com.sabaindomedika.stscustomer.model.Department;
import com.sabaindomedika.stscustomer.model.Division;
import com.sabaindomedika.stscustomer.model.Instrument;
import com.sabaindomedika.stscustomer.model.RequestDivision;
import com.sabaindomedika.stscustomer.model.Ticket;
import com.sabaindomedika.stscustomer.utils.Toasts;
import com.sabaindomedika.stscustomer.utils.helper.ErrorHelper;
import java.util.ArrayList;
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
  @Bind(R.id.radioContainer) RadioGroup lyRadioContainer;
  @Bind(R.id.spnInstrument) Spinner spnInstrument;
  @Bind(R.id.spnInstrumentContainer) RelativeLayout spnInstrumentContainer;
  @Bind(R.id.inpDeviceName) EditText inpDeviceName;

  @Bind(R.id.progressBar) ProgressBar progressBar;
  @Bind(R.id.txtContentAvailable) TextView txtContentAvailable;
  String priority;
  String ticketTypeId;
  String divisionId;
  String divisionName;
  String instrumentId;
  String requestDivisionId;
  String departmentId;
  String deviceName;

  public static FormFragment newInstance(String ticketTypeId, String divisioType,
      String divisionName) {
    Bundle bundle = new Bundle();
    bundle.putString(Ticket.class.getSimpleName(), ticketTypeId);
    bundle.putString(Division.class.getSimpleName(), divisioType);
    bundle.putString(String.class.getSimpleName(), divisionName);
    FormFragment fragment = new FormFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  public static FormFragment newInstanceFormComplaint(String ticketTypeId) {
    Bundle bundle = new Bundle();
    bundle.putString(Ticket.class.getSimpleName(), ticketTypeId);
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
    ticketTypeId = bundle.getString(Ticket.class.getSimpleName());
    divisionId = bundle.getString(Division.class.getSimpleName());
    divisionName = bundle.getString(String.class.getSimpleName());

    presenter.loadData(ticketTypeId, divisionId);
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

  private String getTitleToolbar() {
    if (ticketTypeId.equals("3")) {
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

  @Override public void showRequestDivision(List<RequestDivision> requestDivisions) {
    lyRadioContainer.setVisibility(View.VISIBLE);

    for (int i = 0; i < requestDivisions.size(); i++) {
      RequestDivision requestDivision = requestDivisions.get(i);

      RadioButton radio = new RadioButton(context);
      radio.setText(requestDivision.getName());
      radio.setHighlightColor(ContextCompat.getColor(context, R.color.colorPrimary));
      radio.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if (isChecked) {
          requestDivisionId = requestDivision.getId();
        }
      });
      lyRadioContainer.addView(radio);
    }
  }

  @Override public void showDepartment(List<Department> departments) {
    lyRadioContainer.setVisibility(View.VISIBLE);

    for (int i = 0; i < departments.size(); i++) {
      Department department = departments.get(i);

      RadioButton radio = new RadioButton(context);
      radio.setText(department.getName());
      radio.setHighlightColor(ContextCompat.getColor(context, R.color.colorPrimary));
      radio.setOnCheckedChangeListener((buttonView, isChecked) -> {
        if (isChecked) {
          departmentId = department.getId();
        }
      });
      lyRadioContainer.addView(radio);
    }
  }

  @Override public void showInstrument(List<Instrument> instruments) {
    spnInstrumentContainer.setVisibility(View.VISIBLE);
    List<String> list = new ArrayList<>();

    for (Instrument instrument : instruments) {
      list.add(instrument.getInstrumentCategory()
          .getData()
          .getName()
          .concat("-")
          .concat(instrument.getSerialNumber()));
    }
    ArrayAdapter<String> adapter =
        new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, list);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spnInstrument.setAdapter(adapter);

    spnInstrument.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        instrumentId = instruments.get(position).getId();
      }

      @Override public void onNothingSelected(AdapterView<?> parent) {

      }
    });
  }

  @Override public void showDeviceName() {
    inpDeviceName.setVisibility(View.VISIBLE);
  }

  @Override public void showLoading(boolean firstLoad) {
    progressBar.setVisibility(!firstLoad
        ? View.GONE
        : View.VISIBLE);
  }

  @Override public void showError(Throwable throwable) {

    if (isVisible()) ErrorHelper.thrown(throwable);

    txtContentAvailable.setVisibility(View.VISIBLE);
    SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
    stringBuilder.append(getString(R.string.refresh));
    stringBuilder.setSpan(new ClickableSpan() {
      @Override public void onClick(View widget) {
        presenter.loadData(ticketTypeId, divisionId);
        txtContentAvailable.setVisibility(View.GONE);
      }
    }, 0, getString(R.string.refresh).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    stringBuilder.setSpan(
        new ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)), 0,
        getString(R.string.refresh).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    txtContentAvailable.setMovementMethod(LinkMovementMethod.getInstance());
    txtContentAvailable.setText(stringBuilder);
  }

  @OnClick(R.id.btnSubmit) public void onOpenTicket() {
    if (!isValid()) {
      return;
    }

    Ticket ticket = new Ticket();
    ticket.setTicketTypeId(ticketTypeId);

    if (!TextUtils.isEmpty(inpDeviceName.getText().toString().trim())) {
      deviceName = inpDeviceName.getText().toString().trim();
      ticket.setDeviceName(deviceName);
    }

    if (instrumentId != null) {
      ticket.setInstrumentId(instrumentId);
    }

    if (departmentId != null) {
      ticket.setDepartmentId(departmentId);
    } else {
      ticket.setDivisionId(divisionId);
      if (requestDivisionId != null) {
        ticket.setRequestId(requestDivisionId);
      }
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
