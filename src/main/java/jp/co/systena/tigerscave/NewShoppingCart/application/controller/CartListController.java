package jp.co.systena.tigerscave.NewShoppingCart.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import jp.co.systena.tigerscave.NewShoppingCart.application.model.CartForm;

@Controller
public class CartListController {
  @Autowired
  JdbcTemplate jdbcTemplate;


  /**
   * 初期表示用
   *
   * アイテムデータを取得して一覧表示する
   *
   * @param model
   * @return
   */
  @RequestMapping(value = "/cartlist", method = RequestMethod.GET) // URLとのマッピング
  public String index(Model model) {

    model.addAttribute("items", getCartList());

    return "cartlist";
  }

  /**
   * データベースからアイテムデータ一覧を取得する
   *
   * @return
   */
  private List<CartForm> getCartList() {

    //SELECTを使用してテーブルの情報をすべて取得する
    List<CartForm> list = jdbcTemplate.query("SELECT A.item_name,A.price,B.item_count,B.cart_id FROM items as A inner join cart as B on A.item_id = B.item_id;", new BeanPropertyRowMapper<CartForm>(CartForm.class));

    return list;
    }

  /**
   * 「削除」リンク押下時の処理
   *
   * パラメータで受け取ったアイテムIDのデータを削除する
   *
   * @param itemId
   * @param model
   * @return
   */
  @RequestMapping(value = "/deletecart", method = RequestMethod.GET) // URLとのマッピング
  public String update(@RequestParam(name = "cart_id", required = true) String itemId,
      Model model) {


    // 本来はここで入力チェックなど


      // パラメータで受けとったアイテムIDのデータを削除する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    int deleteCount = jdbcTemplate.update("DELETE FROM cart WHERE cart_id = ?", Integer.parseInt(itemId));


    return "redirect:/cartlist";

  }

}
