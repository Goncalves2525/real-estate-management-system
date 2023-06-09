package pt.ipp.isep.dei.esoft.project.repository;

import java.io.*;

/**
 *  Repositories.
 * <p>
 * This class will be responsible for managing all repositories.
 /**
 *
 */
public class Repositories implements Serializable{

    private static final Repositories instance = new Repositories();
    AuthenticationRepository authenticationRepository = new AuthenticationRepository();
    AnnouncementRepository announcementRepository = new AnnouncementRepository();
    AnnouncementRepository dealRepository = new AnnouncementRepository();
    AgencyRepository agencyRepository = new AgencyRepository();
    EmployeeRepository employeeRepository = new EmployeeRepository();
    RoleRepository roleRepository = new RoleRepository();
    ClientRepository clientRepository = new ClientRepository();
    OrderRepository orderRepository = new OrderRepository();
    PropertyRepository propertyRepository = new PropertyRepository();
    VisitScheduleRepository visitScheduleRepository = new VisitScheduleRepository();

    PropertyRepository propertyDealRepository = new PropertyRepository();

    private static final String SERIALIZATION_FILE_NAME = "RealEstateUSA.ser";

    private Repositories() {
    }

    public static Repositories getInstance() {
        return instance;
    }

    public AnnouncementRepository getAnnouncementRepository() {
        return announcementRepository;
    }

    public AnnouncementRepository getDealRepository() {
        return dealRepository;
    }
    public PropertyRepository getPropertyDealRepository() {
        return propertyDealRepository;
    }

    public AuthenticationRepository getAuthenticationRepository() {
        return authenticationRepository;
    }

    public AgencyRepository getAgencyRepository() {
        return agencyRepository;
    }

    public EmployeeRepository getEmployeeRepository() {
        return employeeRepository;
    }


    public RoleRepository getRoleRepository() {
        return roleRepository;
    }

    public ClientRepository getClientRepository() {
        return clientRepository;
    }

    public OrderRepository getOrderRepository() {
        return orderRepository;
    }

    public PropertyRepository getPropertyRepository() {
        return propertyRepository;
    }

    public VisitScheduleRepository getVisitScheduleRepository() {
        return visitScheduleRepository;
    }

    //Implement serialization
    public void serialize() {
        try {
            //authenticationRepository.serialize();
            FileOutputStream file = new FileOutputStream(SERIALIZATION_FILE_NAME);
            ObjectOutputStream out = new ObjectOutputStream(file);

            //out.writeObject(authenticationRepository.getUsers());
            out.writeObject(announcementRepository);
            //out.writeObject(dealRepository); //ver se é preciso, pois se estiver vazio põe o announcementRepository a vazio
            out.writeObject(agencyRepository);
            out.writeObject(employeeRepository);
            out.writeObject(roleRepository);
            out.writeObject(clientRepository);//erro
            out.writeObject(orderRepository);//erro
            out.writeObject(propertyRepository);
            out.writeObject(visitScheduleRepository);
            out.writeObject(propertyDealRepository);

            out.close();
            file.close();
        } catch (Exception e) {
            System.out.println("Error serializing");
        }
    }

    public void deserialize(){
        try {
            FileInputStream file = new FileInputStream(SERIALIZATION_FILE_NAME);
            ObjectInputStream in = new ObjectInputStream(file);

//            List<UserDTO> users = (List<UserDTO>) in.readObject();
//
//            for(UserDTO user : users) {
//                String name = user.getName();
//                authenticationRepository.addUserWithRole(user.getName(),"","","");
//            }
            //authenticationRepository = (AuthenticationRepository) in.readObject();
            announcementRepository = (AnnouncementRepository) in.readObject();
            agencyRepository = (AgencyRepository) in.readObject();
            employeeRepository = (EmployeeRepository) in.readObject();
            roleRepository = (RoleRepository) in.readObject();
            clientRepository = (ClientRepository) in.readObject();
            orderRepository = (OrderRepository) in.readObject();
            propertyRepository = (PropertyRepository) in.readObject();
            visitScheduleRepository = (VisitScheduleRepository) in.readObject();
            propertyDealRepository = (PropertyRepository) in.readObject();

            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
