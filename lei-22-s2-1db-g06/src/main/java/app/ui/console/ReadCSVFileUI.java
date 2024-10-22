package app.ui.console;

import app.controller.ReadCSVFileController;
import app.domain.model.SNSUser;
import app.domain.shared.Constants;
import app.mapper.dto.SNSUserDTO;
import app.ui.console.utils.Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Implements an UI for reading a csv file containing information about sns users to register
 * @author Daniel Braga <1200801@isep.ipp.pt>
 */

public class ReadCSVFileUI implements Runnable{

    private ReadCSVFileController readCSVFileController;
    String path;
    String pwd;

    public ReadCSVFileUI(){readCSVFileController=new ReadCSVFileController();}

    public void run(){
        String currentLine;

        try {
            this.path = Utils.readLineFromConsole("Please type the name of the file you wish to upload.");
            System.out.println();
            boolean valid = true, haslines, legacyData = false;
            String splitBy = ",";
            BufferedReader br = new BufferedReader(new FileReader(path));
            do{
                currentLine = br.readLine();
                haslines = currentLine != null;
                if (haslines){
                    String[] snsUser = currentLine.split(splitBy);
                    if(snsUser.length==1){
                        splitBy=";";
                        if(snsUser[0].equalsIgnoreCase("\uFEFFName;Sex;BirthDate;Address;PhoneNumber;Email;SNSUSerNumber;CitizenCardNumber")){
                            legacyData = true;
                        }
                        currentLine= br.readLine();
                    }

                    try{
                        if(legacyData){
                            snsUser = currentLine.split(splitBy);
                            valid =SNSUser.checkNameFormat(snsUser[0]) && SNSUser.checkSexFormat(snsUser[1]) && SNSUser.checkAddressFormat(snsUser[3]) &&
                                    SNSUser.checkPhoneNumberFormat(Long.parseLong(snsUser[4])) &&
                                    SNSUser.checkEmail(snsUser[5]) && SNSUser.checkSNSNumberFormat(Long.parseLong(snsUser[6])) &&
                                    SNSUser.checkCitizenCardNumberFormat(Long.parseLong(snsUser[7]));
                            LocalDate.parse(snsUser[2], DateTimeFormatter.ofPattern("d/M/yyyy"));
                        }else{
                            snsUser = currentLine.split(splitBy);
                            valid =SNSUser.checkNameFormat(snsUser[0]) && SNSUser.checkAddressFormat(snsUser[1]) &&
                                SNSUser.checkSexFormat(snsUser[2]) && SNSUser.checkPhoneNumberFormat(Long.parseLong(snsUser[3])) &&
                                SNSUser.checkEmail(snsUser[4]) && SNSUser.checkSNSNumberFormat(Long.parseLong(snsUser[6])) &&
                                SNSUser.checkCitizenCardNumberFormat(Long.parseLong(snsUser[7]));
                            LocalDate.parse(snsUser[5], DateTimeFormatter.ofPattern("d/M/yyyy"));}
                    }catch (Exception e){
                        System.out.println("number excep");
                        valid = false;
                    }finally {
                        if (!valid)
                            System.out.println("File with invalid data. Please try another file.\n\n");
                    }
                }
            }while(valid && haslines);
            br.close();
            if (valid){
                BufferedReader br2 = new BufferedReader(new FileReader(path));
                if(splitBy.equals(";")){
                    currentLine = br2.readLine();
                }
                while ((currentLine = br2.readLine()) != null){
                    try{
                        String snsUser[] = currentLine.split(splitBy);
                        if(snsUser.length==1){
                            currentLine = br2.readLine();
                        }
                        snsUser = currentLine.split(splitBy);
                        if(legacyData){
                            readCSVFileController.createUser(new SNSUserDTO(snsUser[0],snsUser[3],snsUser[1],Long.parseLong(snsUser[4]),snsUser[5], LocalDate.parse(snsUser[2], DateTimeFormatter.ofPattern("d/M/yyyy")),Long.parseLong(snsUser[6]),Long.parseLong(snsUser[7])));
                        }else{
                            readCSVFileController.createUser(new SNSUserDTO(snsUser[0],snsUser[1],snsUser[2],Long.parseLong(snsUser[3]),snsUser[4], LocalDate.parse(snsUser[5], DateTimeFormatter.ofPattern("d/M/yyyy")),Long.parseLong(snsUser[6]),Long.parseLong(snsUser[7])));
                        }

                        if(readCSVFileController.saveUser()){
                            pwd=String.valueOf((int) (Math.random() * (999999 - 100000 + 1) + 100000));
                            if(legacyData){
                                readCSVFileController.addUser(snsUser[0],snsUser[5],pwd, Constants.ROLE_SNS_USER);
                            }else{
                                readCSVFileController.addUser(snsUser[0],snsUser[4],pwd, Constants.ROLE_SNS_USER);
                            }
                            System.out.println("------------------------------------------------------------------------\n");
                            if(legacyData){
                                System.out.println("User info: \nName: "+snsUser[0]+"\nAddress: "+snsUser[3]+"\nSex: "+snsUser[1]+"\nPhone number: "+Long.parseLong(snsUser[4])+"\nEmail: "+snsUser[5]+"\nBirthdate: "+ LocalDate.parse(snsUser[2], DateTimeFormatter.ofPattern("d/M/yyyy")).toString()+"\nSNS number: "+Long.parseLong(snsUser[6])+"\nCitizen card number: "+Long.parseLong(snsUser[7]));
                                System.out.println("\nLogin info: \nEmail: " +snsUser[5]+ "\nPassword: " + pwd +"\n");
                            }else{
                                System.out.println("User info: \nName: "+snsUser[0]+"\nAddress: "+snsUser[1]+"\nSex: "+snsUser[2]+"\nPhone number: "+Long.parseLong(snsUser[3])+"\nEmail: "+snsUser[4]+"\nBirthdate: "+ LocalDate.parse(snsUser[5], DateTimeFormatter.ofPattern("d/M/yyyy")).toString()+"\nSNS number: "+Long.parseLong(snsUser[6])+"\nCitizen card number: "+Long.parseLong(snsUser[7]));
                                System.out.println("\nLogin info: \nEmail: " +snsUser[4]+ "\nPassword: " + pwd +"\n");
                            }
                        }
                    }catch(Exception e){
                        e.printStackTrace();
                        System.out.println("Error registering user.");
                    }
                }
                br2.close();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
