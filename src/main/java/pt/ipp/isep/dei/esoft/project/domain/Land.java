package pt.ipp.isep.dei.esoft.project.domain;

public class Land extends Property {


    public Land(double area, double distanceFromCenter, double price, Address address) {
        super(area, distanceFromCenter, price, address);
    }


    @Override
    public String toString(){
        return String.format(super.toString() + "\n-This is a Land");
    }
}
