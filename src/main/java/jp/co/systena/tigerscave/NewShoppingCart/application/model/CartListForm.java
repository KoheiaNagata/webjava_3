package jp.co.systena.tigerscave.NewShoppingCart.application.model;

import java.util.List;
import javax.validation.Valid;

public class CartListForm {

  @Valid
  private List<CartForm> cartList;

  public List<CartForm> getCartList() {
    return cartList;
  }

  public void setCartList(List<CartForm> cartList) {
    this.cartList = cartList;
  }
}
