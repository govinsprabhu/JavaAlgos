package com.govind.algo.platform.hackerearth;

class Base
{
    public void display()
    {
        System.out.println("hack");
    }
}

class Derived extends Base
{
    public void display()
    {
        System.out.println("Derived");
    }
}

class Test
{
    public static void main(String args[])
    {
        Derived d=new Derived();
        d.display();
    }
}