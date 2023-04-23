package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.domain.*;

public class MainTest {
    public static void main(String[] args) {
        Role adminRole = Role.ADMIN;
        Employee employee = new Employee("João", "wfreb", 123, 123, 123, adminRole);

        System.out.println(employee.toString());
    }
}
