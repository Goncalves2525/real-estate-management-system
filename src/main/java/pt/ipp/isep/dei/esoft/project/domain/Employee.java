package pt.ipp.isep.dei.esoft.project.domain;

import java.util.Objects;

public class Employee extends User{
   private Role role;

    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Address address, Role role) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber, address);
        this.role = role;
    }

    //address is optional
    public Employee(String name, String email, int passportCardNumber, int taxNumber, int telephoneNumber, Role role) {
        super(name, email, passportCardNumber, taxNumber, telephoneNumber);
        this.role = role;
    }

    //para não dar problema com o outro
    public Employee(String email) {
        super(email);
    }

   // @Override
   // public boolean equals(Object o) {
   //     if (this == o) {
   //         return true;
   //     }
   //     if (!(o instanceof Employee)) {
   //         return false;
   //     }
   //     Employee employee = (Employee) o;
   //     return email.equals(employee.email);
   // }

   // @Override
   // public int hashCode() {
   //     return Objects.hash(email);
   // }

    //resolver isto
    public boolean hasEmail(String email) {
        return true;
    }


    /**
     * Clone method.
     *
     * @return A clone of the current instance.
     */

    //resolver isto
    public Employee clone() {
        return new Employee("nada");
    }
}
