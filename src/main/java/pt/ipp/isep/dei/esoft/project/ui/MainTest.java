package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.domain.*;

import java.util.HashSet;
import java.util.Set;

public class MainTest {
    public static void main(String[] args) {
       // Set<Role> roles = new HashSet<>();
       // roles.add(Role.ADMIN);
       // Employee employee = new Employee("João", "wfreb", 123, 123, 123, roles);
       // System.out.println(employee.toString());

        Comission comission = new Comission(10, 10);

        System.out.println(comission);
    }

}
