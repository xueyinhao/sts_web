package com.test2;

import java.util.ArrayList;

public class TestFor {
	public static void main(String[] args)
    {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        for (Integer temp : list)
        {
//            if (temp == 1)
//            {
//                temp = temp * 2;
//            }
        }
        for (Integer a : list)
        {
            System.out.println(a);
        }
        
        ArrayList<User> list2 = new ArrayList<User>();
        list2.add(new User("a"));
        list2.add(new User("b"));
        list2.add(new User("c"));
        for (User u : list2)
        {
        	u.setName("aaaa");
        }
        for (User a : list2)
        {
            System.out.println(a.getName());
        }
    }
}

class User
{
    private String name;
    
    public User(String name){
    	this.name=name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
}
