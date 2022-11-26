package com.example.mapStudy.controller;

import com.example.mapStudy.bean.Ip;
import com.example.mapStudy.bean.Password;
import com.example.mapStudy.mapper.IpMapper;
import com.example.mapStudy.service.PasswordServer;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

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
    PasswordServer service;

    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Resource
    private IpMapper mapper;


    @RequestMapping(value = {"/getPassword/{page}/{size}/{key}", "/getPassword/{page}/{size}/"})
    public Map<String, Object> getPassWord(@PathVariable(value = "key", required = false) String key, @PathVariable(value = "page", required = true) String page, @PathVariable(value = "size", required = true) String size) throws Exception {
        String ip = getRemortIP();
        List<Ip> ips = mapper.selectIp();
        Map<String, Object> map = new HashMap<>(16);
        PageInfo<Password> passwordPageInfo = new PageInfo<>();
        List<Password> list = new ArrayList<>();
        passwordPageInfo.setList(list);
        map.put("pageInfo", passwordPageInfo);
        List<String> ipList = ips.stream().map(Ip::getIp).collect(Collectors.toList());
        if (!ipList.contains(ip)) {
            return map;
        }
        Password password = new Password();
        password.setKey(key);
        passwordPageInfo = service.selectByPassword(password, Integer.parseInt(page), Integer.parseInt(size));
        list = passwordPageInfo.getList();
        flag = 1;
        list.forEach(x -> x.setPassword(decode(x.getPassword())));
        map.put("pageInfo", passwordPageInfo);
        if (!StringUtils.isEmpty(key) && !CollectionUtils.isEmpty(list)) {
            List<Password> finalList = list;
            threadPoolTaskExecutor.execute(() -> {
                finalList.forEach(x -> {
                    Password entity = new Password();
                    entity.setId(x.getId());
                    entity.setIndex(x.getIndex() + 1);
                    service.update(entity);
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
        int insert = service.insert(password);
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
        int update = service.update(password);
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
            return "";
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

    /**
     * 获取请求的ip
     *
     * @date 2022/11/26 20:07
     * @return: java.lang.String
     */
    private String getRemortIP() throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = "";
        if (request.getHeader("x-forwarded-for") == null) {
            ip = request.getRemoteAddr();
        } else {
            ip = request.getHeader("x-forwarded-for");
        }
        return ip;
    }
}
