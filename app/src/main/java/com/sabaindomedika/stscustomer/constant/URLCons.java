package com.sabaindomedika.stscustomer.constant;

/**
 * Created by Fajar Rianda on 01/05/2017.
 */
public class URLCons {

  public static final String URL_BASE = "http://saba.dev.komuri.co.id/";
  public static final String URL_TICKET_TYPES = URL_BASE + "api/ticket_types/";
  public static final String URL_DEPARTMENTS = URL_BASE + "api/departments";
  public static final String URL_DIVISIONS = URL_BASE + "api/divisions";
  public static final String URL_REQUEST_DIVISIONS = URL_BASE + "api/requests/{division_id}";
  public static final String URL_INSTRUMENT_CATEGORY = URL_BASE + "api/customer/instruments";

  public static final String URL_LOGIN = URL_BASE + "oauth/token";
  public static final String URL_PROFILE = URL_BASE + "api/me";

  public static final String URL_OPEN_TICKET = URL_BASE + "api/customer/ticket";
  public static final String URL_CLOSE_TICKET = URL_BASE + "api/customer/ticket/{ticket}/close";
  public static final String URL_STATUS_TICKET = URL_BASE + "api/customer/tickets";
}
