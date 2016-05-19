package com.test;

import java.util.HashMap;
import java.util.UUID;

public class TestHash {
	public static void main(String[] args) {
//		String a = "aaa";
//		String b = "aaa";
//		String c = new String("aaa");
//		System.out.println(a.hashCode());
//		System.out.println(b.hashCode());
//		System.out.println(c.hashCode());
//		Integer i1 = new Integer(123);
//		Integer i2 = new Integer(123);
//		Integer i3 = 123;
//		Integer i4 = 123;
//		System.out.println(i1.hashCode());
//		System.out.println(i2.hashCode());
//		System.out.println(i3.hashCode());
//		System.out.println(i4.hashCode());
//		
//		
//        
//        People p1 = new People("Jack", 12);
//        System.out.println(p1.hashCode());
//             
//        HashMap<People, Integer> hashMap = new HashMap<People, Integer>();
//        hashMap.put(p1, 1);
//         
//        System.out.println(hashMap.get(new People("Jack", 12)));
		
		UUID u = UUID.randomUUID();
		
		System.out.println(u.toString());
		System.out.println(u.hashCode());
    }
}


class People{
    private String name;
    private int age;
     
    public People(String name,int age) {
        this.name = name;
        this.age = age;
    }  
     
    public void setAge(int age){
        this.age = age;
    }
     
    @Override
    public int hashCode() {
        return name.hashCode()*37+age;
    }
     
    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        return this.name.equals(((People)obj).name) && this.age== ((People)obj).age;
    }
}