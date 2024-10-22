package app.ui.console;

import app.controller.RegisterSNSUserController;
import app.ui.console.utils.Utils;

public class RegisterSNSUserUI implements Runnable{

    private RegisterSNSUserController snsuserController;
    public RegisterSNSUserUI(){
        snsuserController = new RegisterSNSUserController();
    }

    public void run()
    {
        int option=0;
        do{
            System.out.println("1 - Load CSV file with SNS Users.");
            System.out.println("2 - Show registered SNS users.");
            System.out.println();
            System.out.println();
            System.out.println("0 - Cancel");

            try{
                option = Integer.valueOf(Utils.readLineFromConsole("Type your option"));


            }catch (Exception e){
                System.out.println("\nInvalid option.\n");
                run();
            }
            if(option==1){
                new ReadCSVFileUI().run();
            } else if(option==2){
                if(snsuserController.checkIfSNSUserStoreNull()){
                    System.out.println("There hasn't been registered any SNS users yet.");
                    System.out.println();
                }else{
                    this.snsuserController.printSNSUserStore();
                }
            }

        }while(option!=0);
    }

}
