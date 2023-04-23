package pt.ipp.isep.dei.esoft.project.ui;

import pt.ipp.isep.dei.esoft.project.domain.Address;
import pt.ipp.isep.dei.esoft.project.domain.House;
import pt.ipp.isep.dei.esoft.project.domain.Residency;

public class MainTest {
    public static void main(String[] args) {
        Address address = new Address("Suny Street", "Vila Verde", "Braga", 1240111);
        House house = new House(100, 200, 100000, address, 2, 1, true, false);

        System.out.println(house.toString());
        //não deveria dizer "false" nos equipamentos, por exemplo.

    }
}
