package jp.co.systena.tigerscave.NewShoppingCart.application.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jp.co.systena.tigerscave.NewShoppingCart.application.model.CartForm;
import jp.co.systena.tigerscave.NewShoppingCart.application.model.ItemForm;

@Controller
public class ItemListController {

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
  @RequestMapping(value = "/itemlist", method = RequestMethod.GET) // URLとのマッピング
  public String index(Model model) {

    model.addAttribute("items", getItemList());

    return "itemlist";
  }

  /**
   * データベースからアイテムデータ一覧を取得する
   *
   * @return
   */
  private List<ItemForm> getItemList() {

    //SELECTを使用してテーブルの情報をすべて取得する
    List<ItemForm> list = jdbcTemplate.query("SELECT * FROM items ORDER BY item_id", new BeanPropertyRowMapper<ItemForm>(ItemForm.class));

    return list;
    }

  /**
   * 「カートに追加」ボタン押下時の処理
   *
   * 入力されたアイテムID、名前、価格をデータベースに登録する
   *
   * @param form
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/addCart", method = RequestMethod.POST) // URLとのマッピング
  public String insert(@Valid CartForm form,
                        BindingResult result,
                        Model model) {

    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {

          //1行分の値をデータベースにINSERTする
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int insertCount = jdbcTemplate.update(
                "INSERT INTO cart VALUES( ?, ? )",
                Integer.parseInt(form.getItemId()),
                Integer.parseInt(form.getItemCount())
              );
    }
    return "redirect:/itemlist";

  }


}
