package com.test2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class TestEnumDemo {

	public static final SerializerFeature[] DEFAULT_FORMAT = { SerializerFeature.WriteDateUseDateFormat,
			SerializerFeature.WriteEnumUsingToString, SerializerFeature.WriteNonStringKeyAsString,
			SerializerFeature.QuoteFieldNames, SerializerFeature.SkipTransientField, SerializerFeature.SortField,
			SerializerFeature.PrettyFormat };
	

	public static void main(String[] args) {

		// for (Color color : Color.values()) {
		//
		// System.out.println(color);
		//
		// }

		// System.out.println(Color.RED);

		TestA a = new TestA();
		a.setC(Color.RED);

		System.out.println(a.getC());

		String s = JSON.toJSONString(a,DEFAULT_FORMAT);

		System.out.println(s);
		
		String s2 = JSON.toJSONString(a,SerializerFeature.WriteEnumUsingToString);

		System.out.println(s2);
		
		String s3 = JSON.toJSONString(a);

		System.out.println(s3);
	}

}

enum Color {

	RED("red color", 0), GREEN("green color", 1), BLUE("blue color", 2), YELLOW(

			"yellow color", 3);

	Color(String name, int id) {

		_name = name;

		_id = id;

	}

	private String _name;

	private int _id;

	public String getName() {

		return _name;

	}

	public int getId() {

		return _id;

	}

	@Override

	public String toString() {

		return _name;

	}

}

class TestA {
	private Color c;

	public Color getC() {
		return c;
	}

	public void setC(Color c) {
		this.c = c;
	}
}
