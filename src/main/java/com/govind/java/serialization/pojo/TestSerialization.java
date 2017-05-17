package com.govind.java.serialization.pojo;

import java.io.*;

/**
 * Created by 609600403 on 17/05/2017.
 */
public class TestSerialization {
    public static void main(String[] args) {
        Parent parent = new Parent();
        parent.childrens = 1;
        parent.age = 56;
        parent.name = "Krisha";
        Child child = new Child();
        child.married = false;
        child.parent = parent;
        child.age = 20;
        child.name = "silence";
        try {
            //write to file
            FileOutputStream fileOutputStream = new FileOutputStream("TestSerialization.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(child);


            //read from file;
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("TestSerialization.txt"));
            Child child1 = (Child) objectInputStream.readObject();
            System.out.println(child);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
