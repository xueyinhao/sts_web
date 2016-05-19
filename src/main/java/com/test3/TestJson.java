package com.test3;

import java.util.Iterator;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class TestJson {
	public static void main(String[] args) {
		String rulCheck = "{'checker':[]}";
		//rulCheck = null;

		JSONObject jsonObject = JSON.parseObject(rulCheck);

		System.out.println(jsonObject);
		
		JSONArray array = jsonObject.getJSONArray("checker");
		System.out.println(array);
		System.out.println(array == null);
		Iterator it = array.iterator();
		System.out.println(it.hasNext());

	}
}
