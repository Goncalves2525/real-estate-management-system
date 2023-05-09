package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateOrderController;
import pt.ipp.isep.dei.esoft.project.domain.OrderState;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateOrderUI implements Runnable {

    private final CreateOrderController controller = new CreateOrderController();

    public CreateOrderUI() {
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        int id = -1;
        double propertyPrice;
        double amount = -1;
        boolean alreadyMadeOrder = false;
        boolean sameAmount = false;
        String clientEmail;
        OrderState orderState = OrderState.PENDING;

        System.out.println("Insert the id of the property you want to buy:");
        do {
            try {
                id = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please insert a number");
                sc.nextLine();
                run();
            }
        }while(id < 0);

        propertyPrice =controller.getPropertyPriceByAnnouncementId(id);

        System.out.println("Insert the amount you want to offer for the property (lower or equal to actual price):");
        do {
            try {
                amount = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Please insert a number");
                sc.nextLine();
                run();
            }
        }while(amount >propertyPrice);

        sameAmount =controller.someoneAlreadyMadeOrderWithSameAmountForThisAnnouncement(amount,id);
        clientEmail =controller.getClientEmail();
        alreadyMadeOrder =controller.clientAlreadyMadeOrderForThisAnnouncement(id,clientEmail);


        if(alreadyMadeOrder) {
            System.out.println("INFO: You already made an order for this property!");
        } else {
            if (sameAmount) {
                System.out.println("INFO: Someone already made an order with same amount for this property");
            }
            Date date = new Date();
            controller.createOrder(amount, id, clientEmail, date, orderState);
            System.out.println("Order created successfully!");
        }
    }


}
