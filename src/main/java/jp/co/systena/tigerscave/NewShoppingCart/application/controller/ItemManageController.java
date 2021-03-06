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
import org.springframework.web.bind.annotation.RequestParam;
import jp.co.systena.tigerscave.NewShoppingCart.application.model.ItemForm;
import jp.co.systena.tigerscave.NewShoppingCart.application.model.ItemListForm;


@Controller
public class ItemManageController {

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
  @RequestMapping(value = "/itemmanage", method = RequestMethod.GET) // URLとのマッピング
  public String index(Model model) {

    model.addAttribute("items", getItemList());

    return "itemmanage";
  }

  /**
   * 「更新」ボタン押下時の処理
   *
   * 入力された名前と価格をアイテムIDをキーとして更新する
   *
   * @param listForm
   * @param result
   * @param model
   * @return
   */
  @RequestMapping(value = "/itemmanage", method = RequestMethod.POST) // URLとのマッピング
  public String update(@Valid ItemListForm listForm,
                        BindingResult result,
                        Model model) {

    // listFormに画面で入力したデータが入っているので取得する
    List<ItemForm> itemList = listForm.getItemList();
    // ビューに受け渡し用にmodelにセット
    model.addAttribute("items", itemList);
    model.addAttribute("listForm", listForm);


    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {
      if (itemList != null) {
        //画面入力値1行ずつ処理をする
        for (ItemForm item : itemList) {

          //1行分の値でデータベースをUPDATEする
          //item_idをキーに名称と価格を更新する
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int updateCount = jdbcTemplate.update(
              "UPDATE items SET item_name = ?, price = ? WHERE item_id = ?",
              item.getItemName(),
              Integer.parseInt(item.getPrice()),
              Integer.parseInt(item.getItemId()));

        }
      }
    }

    return "itemmanage";

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
  @RequestMapping(value = "/deleteitem", method = RequestMethod.GET) // URLとのマッピング
  public String update(@RequestParam(name = "item_id", required = true) String itemId,
      Model model) {


    // 本来はここで入力チェックなど


      // パラメータで受けとったアイテムIDのデータを削除する
    // SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
    int deleteCount = jdbcTemplate.update("DELETE FROM items WHERE item_id = ?", Integer.parseInt(itemId));


    return "redirect:/itemmanage";

  }

  /**
   * 「登録」ボタン押下時の処理
   *
   * 入力されたアイテムID、名前、価格をデータベースに登録する
   *
   * @param form
   * @param result
   * @param model
   * @return
   */

  @RequestMapping(value = "/additem", method = RequestMethod.POST) // URLとのマッピング
  public String insert(@Valid ItemForm form,
                        BindingResult result,
                        Model model) {

    //画面入力値にエラーがない場合
    if (!result.hasErrors()) {

          //1行分の値をデータベースにINSERTする
          //SQL文字列中の「?」の部分に、後ろで指定した変数が埋め込まれる
          int insertCount = jdbcTemplate.update(
                "INSERT INTO items VALUES( ?, ?, ? )",
                Integer.parseInt(form.getItemId()),
                form.getItemName(),
                Integer.parseInt(form.getPrice())
              );

    }

    return "redirect:/itemmanage";

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

    /*
    //結果はMapのリストとして取得することもできる
    List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT * FROM items ORDER BY item_id");

    */


  }
}
