package com.sabaindomedika.stscustomer.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Fajar Rianda on 07/06/2017.
 */
public class Strings {

  public static String getDate(String date) {
    try {
      SimpleDateFormat formatIn = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSSSS");
      SimpleDateFormat formatOut = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
      Date day = formatIn.parse(date);
      return formatOut.format(day);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return "";
  }
}
