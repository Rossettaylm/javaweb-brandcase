package com.rossetta;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

/**
 * Created by Rossetta on 2022/3/20
 */
public class TestClass {
    @Test
    public void testReverseInt() {
        System.out.println(Integer.reverse(1));
        System.out.println(Integer.reverse(0));
    }

    @Test
    public void testJson() {
        // 1.声明json字符串
        String jsonStr = "{'code':'200','desc':'OK','comments':[{'name':'张三','age':18}," +
                "{'name':'赵四','age':20}]}";
        System.out.println("读取json字符串中的信息:" + jsonStr);
        // 2.使用alibaba的JSON工具类 处理字符串
        JSONObject jsonObject = (JSONObject) JSON.parse(jsonStr);
        String code = (String) jsonObject.get("code");
        System.out.println("code=" + code);
        String desc = (String) jsonObject.get("desc");
        System.out.println("desc=" + desc);

        // 2.使用JSONObject对象中的信息
        JSONArray jsonArray = jsonObject.getJSONArray("comments");
        System.out.println("声明的json数组信息:" + jsonArray);

        // 3.使用增强型循环遍历输出数组中的信息
        System.out.println("循环输出数组中的对象信息");
        for (Object object : jsonArray) {
            JSONObject obj = (JSONObject) object;
            System.out.println("声明的json对象信息:" + obj);
            System.out.println("name=" + obj.get("name") + ",age=" + obj.get("age"));
        }
    }

}
