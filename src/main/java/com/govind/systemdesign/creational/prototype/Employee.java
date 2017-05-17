package com.govind.systemdesign.creational.prototype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 609600403 on 22/04/2016.
 */
public class Employee implements Cloneable{
    private List<String> employeesList;

    public Employee() {
        employeesList = new ArrayList<>();
    }

    public List<String> getList(){
        return employeesList;
    }

    public void loadData(){
        employeesList.add("Govind");
        employeesList.add("Breath");
        employeesList.add("Concentrate");
        employeesList.add("Supreme");
    }

    public Employee(List<String> employeesList) {
        this.employeesList = employeesList;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        List<String> employeeList = new ArrayList<>();
        for (String name : this.getList()){
            employeeList.add(name);
        }
        return new Employee(employeeList);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Employee employee = new Employee();
        employee.loadData();
        Employee employee1 = (Employee) employee.clone();
        List<String> list = employee.employeesList;
        list.add("Concentrate");
        List<String> list1 = employee1.employeesList;
        list1.add("Everything is temporary");
        System.out.println(list);
        System.out.println(list1);
        System.out.println(employee1.employeesList);
    }
}
