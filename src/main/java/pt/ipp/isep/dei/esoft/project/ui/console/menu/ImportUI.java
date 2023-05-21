package pt.ipp.isep.dei.esoft.project.ui.console.menu;


import pt.ipp.isep.dei.esoft.project.application.controller.ImportController;
import pt.ipp.isep.dei.esoft.project.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 *
 * @author Diogo Santo 1212039@isep.ipp.pt
 */

public class ImportUI implements Runnable {

    private final ImportController controller;
    public ImportUI() {
        controller = new ImportController();
    }

    public void run() {
        List<MenuItem> options = new ArrayList<MenuItem>();
        showImportMenu();

        Scanner sc = new Scanner(System.in);
        boolean validOption = false;

        do{
            String importOption= Utils.readLineFromConsole("");
            validOption = selectImportOption(importOption);
            if(validOption){
                String fileName = selectFile(sc);
                if(!Objects.equals(fileName, "")){
                    String delimiter = Utils.readLineFromConsole("Please select the delimiter of the file you want to import:");
                    if(!Objects.equals(delimiter, "")){
                        ArrayList<String[]> dataToImport = controller.readFile(fileName, delimiter);
                        if(dataToImport.size() > 0){

                            System.out.println("Data to import:");
                            for(String[] s : dataToImport){
                                for(String s1 : s){
                                    System.out.print(s1 + " ");
                                }
                                System.out.println();
                            }

                            String importAnswer = Utils.readLineFromConsole("\nIs this the data you want to import? (Y/N)");
                            do{
                                if(importAnswer.equals("Y") || importAnswer.equals("y")){
                                    String importResult = controller.importData(dataToImport, controller.getClassNameFromOption(importOption));
                                    if(!importResult.equals("")){
                                        System.out.println("Operation completed with the following result:");
                                        System.out.println(importResult + "\n");
                                    }
                                }
                                //validOption = false;
                                //showImportMenu();
                                break;
                            }while (!importAnswer.equals("Y") && !importAnswer.equals("y") && !importAnswer.equals("N") && !importAnswer.equals("n"));
                        }
                        else{
                            System.out.println("Missing/with error import data.");
                        }
                    }
                }
            }
        }while (!validOption);



        int option = 0;
        do {
            //option = Utils.showAndSelectIndex(options, "\n\nImport Menu:");
            option = Utils.showAndSelectIndex(options, "");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }

    private String selectFile(Scanner sc) {
        String fileName = "";
        boolean validFile = false;

        while (!validFile){
            //fileName = sc.nextLine();
            fileName = Utils.readLineFromConsole("Please select the file you want to import:");
            validFile = controller.checkFile(fileName);
        }
        return fileName;
    }

    private boolean selectImportOption(String i) {
        try{
            switch (i){
                case "1":
                    return true;
                case "2":
                    return true;
                case "3":
                    return true;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
                    return false;
            }
        }
        catch (Exception e){
            System.out.println("Invalid option. Please select a valid option.");
            return false;
        }
    }

    private void showImportMenu(){
        System.out.println("Please select the type of data you want to import:");
        System.out.println("1 - Import Agents");
        System.out.println("2 - Import Agencies");
        System.out.println("3 - Import Orders");
    }
}
