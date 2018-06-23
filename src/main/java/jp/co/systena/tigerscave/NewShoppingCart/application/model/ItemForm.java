package jp.co.systena.tigerscave.NewShoppingCart.application.model;

import javax.validation.constraints.NotBlank;

public class ItemForm {

  @NotBlank
  private String itemId;
  @NotBlank
  private String itemName;
  @NotBlank
  private String price;

  public String getItemId() {
    return this.itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getItemName() {
    return this.itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getPrice() {
    return this.price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

}
