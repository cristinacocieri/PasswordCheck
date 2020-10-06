package com.company;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class PasswordGeneration {

    static String sendOTP(int length){
        String numbers = "ABCDEGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Random r  = new Random();

        char[] otp = new char[length];

        for(int i = 0; i<length; i++){
            otp[i] = numbers.charAt(r.nextInt(numbers.length()));
        }

        String stringOTP = new String(otp);
        return stringOTP;

    }

   static void checkPassword(String otp){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Password: ");
        String userText = scanner.next();
        if(otp.equals(userText)){
            System.out.println("Access Granted! Welcome!");
        }else
        {
            System.out.println("Invalid Password!");
        }
    }

    public static void main(String[] args){
        //System.out.println("**************************");
        //System.out.println("Generating OTP...");
        //System.out.println(sendOTP(8));
        //System.out.println("**************************");
        SendEmail sendEmail = new SendEmail();
        String otp = sendOTP(8);
        sendEmail.sendmail(otp);
        checkPassword(otp);
    }
}

class SendEmail{

    void sendmail(String otp){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Your E-Mail : ");
        String to = scanner.next();
        String from = "web@gmail.com";
        String host = "localhost";

        //Get the session object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        //compose the message
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Password");
            message.setText(otp);

            // Send message
            Transport.send(message);
            System.out.println("Message sent successfully!");

        }catch (MessagingException mex) {mex.printStackTrace();}
    }
}