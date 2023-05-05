package pt.ipp.isep.dei.esoft.project.ui.console;

import pt.ipp.isep.dei.esoft.project.application.controller.CreateOrderController;

public class CreateOrderUI implements Runnable {

    private final CreateOrderController controller = new CreateOrderController();

    private CreateOrderController getController() {
        return controller;
    }

    public CreateOrderUI() {
    }

    @Override
    public void run() {

    }



}
