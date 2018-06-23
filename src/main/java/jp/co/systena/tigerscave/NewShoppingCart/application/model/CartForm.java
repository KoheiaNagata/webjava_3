package jp.co.systena.tigerscave.NewShoppingCart.application.model;

public class CartForm {

  private String itemId;
  private String cartId;
  private String itemName;
  private String price;
  private String itemCount;


  public String getItemId() {
    return this.itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public String getCartId() {
    return this.cartId;
  }

  public void setCartId(String cartId) {
    this.cartId = cartId;
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

  public String getItemCount() {
    return this.itemCount;
  }

  public void setItemCount(String itemCount) {
    this.itemCount = itemCount;
  }

}
