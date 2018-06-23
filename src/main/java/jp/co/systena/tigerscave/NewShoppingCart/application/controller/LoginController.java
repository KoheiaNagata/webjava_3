package jp.co.systena.tigerscave.NewShoppingCart.application.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jp.co.systena.tigerscave.NewShoppingCart.application.model.UserMasterForm;



@Controller
public class LoginController {

  @Autowired
  JdbcTemplate jdbcTemplate;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  private String getUserList() {

    UserMasterForm userMasterForm = new UserMasterForm();

    //SELECTを使用してテーブルの情報をすべて取得する
    List<UserMasterForm> list = jdbcTemplate.query("SELECT * FROM usermaster WHERE user_id = ?)", new BeanPropertyRowMapper<UserMasterForm>(UserMasterForm.class),userMasterForm.getUserId());

    if(list.isEmpty()) {
      return
    }else {
      return ;
    }

  }


}
