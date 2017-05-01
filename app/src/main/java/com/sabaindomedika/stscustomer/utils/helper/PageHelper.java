package com.sabaindomedika.stscustomer.utils.helper;

/**
 * Created by Fajar Rianda on 01/03/2017.
 */
public class PageHelper {

  private int page;
  private boolean isLastPage;

  /*For Handle multi scroll*/
  private boolean onHold;

  public static PageHelper getDefault() {
    return with(0);
  }

  public static PageHelper with(int firstPage) {
    PageHelper pageHelper = new PageHelper();
    pageHelper.setPage(firstPage);
    return pageHelper;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getPage(){
    return page;
  }

  public synchronized void nextPage() {
    page++;
    onHold = false;
  }

  public synchronized void hold() {
    onHold = true;
  }
  public boolean isOnHold() {
    return onHold;
  }
  public synchronized void reset(){
    page = 0;
    isLastPage = false;
    onHold = false;
  }


  /*For Checking Scroll / Load More*/
  public boolean isLastPage() {
    return isLastPage;
  }

  public void setIsLastPage(boolean isLastPage) {
    this.isLastPage = isLastPage;
  }

}
