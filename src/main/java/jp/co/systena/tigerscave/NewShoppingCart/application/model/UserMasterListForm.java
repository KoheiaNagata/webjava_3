package jp.co.systena.tigerscave.NewShoppingCart.application.model;

import java.util.List;
import javax.validation.Valid;

public class UserMasterListForm {
  @Valid
  private List<UserMasterForm> userList;

  public List<UserMasterForm> getUserList() {
    return userList;
  }

  public void setUserList(List<UserMasterForm> userList) {
    this.userList = userList;
  }


}
