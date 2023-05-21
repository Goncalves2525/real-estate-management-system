package pt.ipp.isep.dei.esoft.project.application.controller;

import pt.ipp.isep.dei.esoft.project.domain.Agency;
import pt.ipp.isep.dei.esoft.project.domain.Employee;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;
import pt.ipp.isep.dei.esoft.project.repository.AgencyRepository;
import pt.ipp.isep.dei.esoft.project.repository.EmployeeRepository;
import pt.ipp.isep.dei.esoft.project.repository.OrderRepository;
import pt.ipp.isep.dei.esoft.project.repository.Repositories;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 * This class is the controller for the user story of publishing announcements.
 */
public class ImportController {

    private OrderRepository orderRepository;

    private EmployeeRepository employeeRepository;

    private AgencyRepository agencyRepository;

    /**
     * Instantiates a new Import controller.
     */
    public ImportController() {
        this.orderRepository = Repositories.getInstance().getOrderRepository();
        this.employeeRepository = Repositories.getInstance().getEmployeeRepository();
        this.agencyRepository = Repositories.getInstance().getAgencyRepository();
    }

    public ArrayList<String[]> readFile(String file, String delimiter) {
        ArrayList<String[]> readResult = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(file));
            sc.useDelimiter(delimiter);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                line = line.replaceAll("\\s+", "").replaceAll("\n", "").replaceAll("\"", ""); //To remove all whitespaces and new lines and quotes
                readResult.add(line.split(delimiter));
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File not found");
        }
        return readResult;
    }

    public boolean checkFile(String fileName) {
        File f = new File(fileName);
        return f.exists();
    }

    public String importData(ArrayList<String[]> dataToImport, String className) {
        String importResult = "";
        int totalImported = 0;
        int totalItemsToImport = dataToImport.size();
        try {
            switch (className) {
                case "Employee":
                    for (String[] data : dataToImport) {
                        //String[] employeeData = data.split(",");
                        if(data.length == 2){
                            try {
                                employeeRepository.add(new Employee(data[0], Integer.parseInt(data[1])));
                                totalImported++;
                                importResult += "\nEmployee imported: " + data[0];
                            } catch (Exception e) {
                                importResult += "\nError importing employee: " + data[0];
                            }
                        }
                        else{
                            importResult += "\nError: data not in correct format";
                        }
                    }
                    break;
                case "Agency":
                    for (String[] data : dataToImport) {
                        if(data.length == 3){
                            try {
                                agencyRepository.add(new Agency(data[0], data[1], Integer.parseInt(data[2])));
                                totalImported++;
                                importResult += "\nAgency imported: " + data[0];
                            } catch (Exception e) {
                                importResult += "\nError importing Agency: " + data[0];
                            }
                        }
                        else{
                            importResult += "\nError: data not in correct format";
                        }
                    }
                    break;
                case "Order":
                    for (String[] data : dataToImport) {
                        if(data.length == 4){
                            try {
                                orderRepository.addOrder(Double.parseDouble(data[0]), Integer.parseInt(data[1]), data[2], parseDate(data[3]), OrderState.PENDING);
                                totalImported++;
                                importResult += "\nOrder imported: " + data[1];
                            } catch (Exception e) {
                                importResult += "\nError importing Order: " + data[1];
                            }
                        }
                        else{
                            importResult += "\nError: data not in correct format";
                        }
                    }
                    break;
                default:
                    importResult += "\nError: invalid class name";
            }
        } catch (Exception e) {
            importResult += "Error importing data";
        }
        return importResult;
    }

    public String getClassNameFromOption(String importOption) {
        String className = "";
        try {
            switch (importOption) {
                case "1":
                    className = "Employee";
                    break;
                case "2":
                    className = "Agency";
                    break;
                case "3":
                    className = "Order";
                    break;
                default:
                    className = "";
            }
        } catch (Exception e) {
            className = "";
        }
        return className;
    }

    private Date parseDate(String date) {
        String[] dateSplit = date.split("-");
        return new Date(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
    }
}
