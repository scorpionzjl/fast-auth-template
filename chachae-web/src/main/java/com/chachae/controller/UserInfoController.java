package com.chachae.controller;

import com.chachae.core.bean.Result;
import com.chachae.core.entity.bo.UserInfo;
import com.chachae.core.entity.vo.UserInfoVO;
import com.chachae.service.UserInfoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author chachae
 * @date 2019/11/13 15:51
 */
@RestController
@RequestMapping("/user/info")
public class UserInfoController {

  @Resource private UserInfoService userInfoService;

  @GetMapping("/list")
  public Result list() {
    List<UserInfoVO> list = this.userInfoService.queryAll();
    return Result.ok(list);
  }

  @GetMapping("/query/{uuid}")
  public Result list(@PathVariable String uuid) {
    UserInfoVO vo = this.userInfoService.queryByUuid(uuid);
    return Result.ok(vo);
  }

  @PutMapping("/update")
  public Result update(@Valid UserInfo userInfo) {
    this.userInfoService.update(userInfo);
    return Result.ok();
  }

  @GetMapping("/query")
  public Result fuzzyQuery(String name, Integer deptId) {
    List<UserInfoVO> voList = this.userInfoService.fuzzyQuery(name, deptId);
    return Result.ok(voList);
  }
}
