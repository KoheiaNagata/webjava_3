package jp.co.systena.tigerscave.NewShoppingCart.application.model;

import java.util.List;
import javax.validation.Valid;

public class ItemListForm {

  @Valid
  private List<ItemForm> itemList;

  public List<ItemForm> getItemList() {
    return itemList;
  }

  public void setItemList(List<ItemForm> itemList) {
    this.itemList = itemList;
  }

}
