package com.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


class NotSerializ implements Serializable {  
    public String name = "aaaa";  
} 

public class MySerializ implements Serializable{  
    
    private static final long serialVersionUID = -7918349215312458095L;  
    public NotSerializ  not;  
    public long num = 9;  
      
    public static void main(String[] args) {  
        MySerializ mySerializ = new MySerializ();  
        mySerializ.doSerializ();  
        mySerializ.readSerializ();  
    }  
  
    private void readSerializ() {  
        File file = new File("text.txt");     
        try {  
            FileInputStream out  = new FileInputStream(file);  
            ObjectInputStream  oin = new ObjectInputStream (out);  
            Object o = oin.readObject();  
              
            System.out.println(((MySerializ)o).not.name);  
            System.out.println(((MySerializ)o).num);  
            oin.close();          
        } catch (Exception e) {  
            e.printStackTrace();  
        }     
    }  
  
    private void doSerializ() {  
        MySerializ o = new MySerializ();  
        o.num = 3;  
        o.not = new NotSerializ();  
          
        File file = new File("text.txt");      
        try {  
            FileOutputStream out  = new FileOutputStream(file);  
            ObjectOutputStream  oput  = new ObjectOutputStream (out);  
            oput.writeObject(o);  
            oput.close();         
        } catch (Exception e) {  
            e.printStackTrace();  
        }     
          
    }  
}  
