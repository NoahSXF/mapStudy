package com.example.mapStudy.controller;

import com.example.mapStudy.bean.Password;
import com.example.mapStudy.mapper.PasswordMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy.controller
 * @Author: Noah
 * @CreateTime: 2022-11-22  18:32
 * @Description: TODO 密码表的相关操作
 * @Version: 1.0
 */
@RestController
@CrossOrigin
@Slf4j
public class PasswordController {
    //  2时刷新
    static int flag = 1;

    @Resource
    PasswordMapper mapper;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @RequestMapping(value = {"/getPassword/{key}", "/getPassword/"})
    public Map<String, Object> getPassWord(@PathVariable(value = "key", required = false) String key) {
        Map<String, Object> map = new HashMap<>(16);
        Password password = new Password();
        password.setKey(key);
        List<Password> list = mapper.selectByPassword(password);
        flag = 1;
        list.forEach(x -> x.setPassword(decode(x.getPassword())));
        map.put("result", list);
        if (!StringUtils.isEmpty(key) && !CollectionUtils.isEmpty(list)) {
            threadPoolTaskExecutor.execute(() -> {
                list.forEach(x -> {
                    Password entity = new Password();
                    entity.setId(x.getId());
                    entity.setIndex(x.getIndex() + 1);
                    mapper.update(entity);
                });
                log.info("线程执行完毕");
            });
        }
        return map;
    }

    @RequestMapping("/insertPassword")
    public Map<String, Object> insertPassword(@RequestBody Password password) {
        Map<String, Object> map = new HashMap<>(16);
        password.setCreateTime(new Date());
        password.setUpdateTime(new Date());
        password.setPassword(encode(password.getPassword()));
        password.setId(UUID.randomUUID().toString().replace("-", ""));
        int insert = mapper.insert(password);
        if (insert == 1) {
            map.put("result", true);
            flag = 2;
        } else {
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/updatePassword")
    public Map<String, Object> updatePassword(@RequestBody Password password) {
        Map<String, Object> map = new HashMap<>(16);
        password.setUpdateTime(new Date());
        password.setPassword(encode(password.getPassword()));
        int update = mapper.update(password);
        if (update == 1) {
            map.put("result", true);
            flag = 2;
        } else {
            map.put("result", false);
        }
        return map;
    }

    @RequestMapping("/timerSelect")
    public Map<String, Object> timerSelect() {
        Map<String, Object> map = new HashMap<>(16);
        map.put("result", flag == 2);
        return map;
    }

    private String encode(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        byte[] sBytes = s.getBytes();
        for (int i = 0; i < sBytes.length; i++) {
            if (i / 2 == 0) {
                sBytes[i] += 1;
            } else {
                sBytes[i] += 2;
            }
        }
        return Base64.getEncoder().encodeToString(sBytes);
    }

    private String decode(String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        byte[] sBytes = Base64.getDecoder().decode(s);
        for (int i = 0; i < sBytes.length; i++) {
            if (i / 2 == 0) {
                sBytes[i] -= 1;
            } else {
                sBytes[i] -= 2;
            }
        }
        return new String(sBytes);
    }
}
