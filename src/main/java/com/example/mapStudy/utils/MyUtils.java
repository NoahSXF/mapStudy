package com.example.mapStudy.utils;

import com.alibaba.fastjson.JSON;
import com.example.mapStudy.bean.Dog;
import com.example.mapStudy.bean.Person;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: mapStudy
 * @BelongsPackage: com.example.mapStudy
 * @Author: Noah
 * @CreateTime: 2022-09-18  11:15
 * @Description: 练习java常用工具类
 * @Version: 1.0
 */
@Slf4j
public class MyUtils {
    /**
     * 集合操作类
     *
     * @date 2022/9/18 11:31
     * @return: void
     */
    public void myCollections() {
        //import java.util.Collections;
        ArrayList<String> list = new ArrayList<>();
        Collections.sort(list);
        Collections.max(list);
        Collections.emptyList();
        Collections.binarySearch(list, "二分查找");
    }

    /**
     * 字符操作类
     *
     * @date 2022/9/18 11:31
     * @return: null
     */
    void myStringUtils() {
        String s = "";
        StringUtils.isNoneEmpty(s);
        //s为空也不会报异常，而是会返回空
        StringUtils.split(s, ",");
        //拼接字符串
        ArrayList<String> list = new ArrayList<>();
        StringUtils.join(list, ",");
    }

    /**
     * 断言Assert
     *
     * @date 2022/9/18 11:37
     * @return: void
     */
    void myAssert() {
        String str = "";
        Assert.isNull(str, "str必须为空");
        Assert.isNull(str, () -> "str必须为空");
        Assert.notNull(str, "str不能为空");
    }

    /**
     * BeanUtils
     *
     * @date 2022/9/18 11:39
     * @return: void
     */
    @Test
    public void myBeanUtils() {
        //复制属性
        Person person1 = new Person();
        person1.setName("人鱼1");
        person1.setAge(15);
        Person person2 = new Person();
        BeanUtils.copyProperties(person1, person2);
        String jsonString = JSON.toJSONString(person2);
        log.info("copy对象，{}", jsonString);
        //生成对象
        Person person = BeanUtils.instantiateClass(Person.class);
        String s2 = "调用默认的构造方法";
        log.info("{}，{}", s2, person);
    }

    /**
     * 加密
     *
     * @date 2022/9/18 12:09
     * @return: void
     */
    @Test
    public void myDigestUtils() {
        String md5Hex = DigestUtils.md5Hex("994518231");
        System.out.println(md5Hex);
    }

    /**
     * 文件工作类
     *
     * @date 2022/9/18 12:21
     * @return: void
     */
    public void myFileUtils() {
//        deleteDirectory：删除文件夹
//        readFileToString：以字符形式读取文件内容
//        deleteQueitly：删除文件或文件夹且不会抛出异常
//        copyFile：复制文件
//        writeStringToFile：把字符写到目标文件，如果文件不存在，则创建
//        forceMkdir：强制创建文件夹，如果该文件夹父级目录不存在，则创建父级
//        write：把字符写到指定文件中
//        listFiles：列举某个目录下的文件(根据过滤器)
//        copyDirectory：复制文件夹
//        forceDelete：强制删除文件

    }

    public <T> T mapToBean(Map<String, Object> map, Class<T> clazz) {
        HashMap<String, Object> hashMap = new HashMap<>(16);
        map.forEach((x, y) -> {
            String[] s = x.toLowerCase().split("_");
            StringBuffer newX = new StringBuffer();
            for (int i = 1; i < s.length; i++) {
                String firstLetter = s[i].substring(0, 1).toUpperCase();
                String nextLetter = s[i].substring(1);
                newX = newX.append(firstLetter.concat(nextLetter));
            }
            hashMap.put(s[0] + newX, y);
        });
        T t = JSON.parseObject(JSON.toJSONString(hashMap), clazz);
        return t;
    }

    @Test
    public void test() throws InstantiationException, IllegalAccessException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("USER_NAME_WE", "张三");
        map.put("USER_AGE", 19);
        map.put("NAME", "李四");
        Dog o = mapToBean(map, Dog.class);
        System.out.println(o);
    }
}
