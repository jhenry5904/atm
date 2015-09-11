/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 *
 * @author Josh
 */
public abstract class Account implements Serializable
{
 protected double balance = 100;
 protected int firstdate;
 protected int seconddate;
 protected Calendar date1 = new GregorianCalendar();
 protected Calendar date2 = new GregorianCalendar();
 protected boolean dateflag = false;
 protected double rate;
 private int ID;

 // Initial Constructor
 // Once called the Account1 constructor presents a menu for users to make
 // a deposit, withdrawal, check balance or exit.
 // The menu is a loop that based on the selection will call either the
 // deposit method, withdrawal method or exit the program.

 public Account (double begin_balance)
  {
      balance = begin_balance;
  
  } // End Initial Constructor

 public void menu() throws IOException
  {
      char mychar = 'z';
      while (mychar != 'e')
                {
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("*********************************");
    System.out.println("*    WELCOME TO RIPUOFF BANK    *");
    System.out.println("*                               *");
    System.out.println("*     Come in and check out     *");
    System.out.println("*          our low 30%          *");
    System.out.println("*      interest rate loans      *");
    System.out.println("*                               *");
    System.out.println("*********************************");
    System.out.println();
    System.out.println("Please enter a choice:");
    System.out.println("Deposit(d)");
    System.out.println("Withdraw(w)");
    System.out.println("CheckBalance(c)");
    System.out.println("Exit(e)");
           BufferedReader br;
           String input;
           int index = 0;
           br = new BufferedReader(new InputStreamReader(System.in));

           input = br.readLine();

           mychar = input.charAt (index);

   if (mychar == 'd' || mychar == 'D') 
   {
     System.out.println("Your current balance is:" + getBalance());
    if (dateflag == true)
    {
     getDate2();
     getInterest();
     deposit();
    }

    else
    {
     getDate1();
     deposit();
    }

   }
    else if (mychar == 'w') 
    {
     System.out.println("Your current balance is: " + getBalance());
    if (dateflag == true)
    {
        getDate2();
        getInterest();
        withdraw();
    }
    else
    {
        getDate1();
        withdraw();
                                }
    }

    else if (mychar == 'c')
        System.out.println("Your current balance is: " + getBalance());

         }
  }


 //Main method instantiates the initial account balance of 100 hundred dollars
 //Then creates the account and lets the Account class take over from there.

//  public static void main(String[] args) throws IOException
//        {
//            double init_amount = 100.00;
//            Account firstacct = new Account(init_amount);
//            firstacct.menu();
//        }


 // This method retrieves the balance field and returns it in currency format

 public String getBalance()
 {
     NumberFormat currencyFormatter;
     String currencyOut;

     currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
     currencyOut = currencyFormatter.format(balance);

     return currencyOut;

 }

 // This method prompts the user for the deposit and then adds it to the
 // balance field.

 public void deposit() throws IOException 
 {
     BufferedReader br;
     String entered_amount;

     System.out.print("How much would you like to deposit? :");
     br = new BufferedReader(new InputStreamReader(System.in));
     entered_amount = br.readLine();
     double amount = Double.valueOf(entered_amount).doubleValue();
     balance = balance + amount;

     System.out.println("Your balance is: " + getBalance());

 }

 // This method prompts the user for the withdraw amount and then subtracts
 // it from the balance field.

 public void withdraw() throws IOException 
 {
     BufferedReader br;
     String entered_amount;

     System.out.print("How much would you like to withdraw? :");
     br = new BufferedReader(new InputStreamReader(System.in));
     entered_amount = br.readLine();
     double amount = Double.valueOf(entered_amount).doubleValue();

     if (balance < amount)
      System.out.println("Insufficient funds.");
     else
      balance = balance - amount;


     System.out.println("Your balance is: " + getBalance());
 }


 // This function is only called on the first transaction after the
 // account has been initialized to set the first time a transaction
 // occurs for the account for the current year.

 public void getDate1() throws IOException 
 {

     System.out.print("Enter todays date(mm/dd/yyyy): ");
     BufferedReader br;
     br = new BufferedReader(new InputStreamReader(System.in));
     String inputText = br.readLine();
     SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
     ParsePosition pos = new ParsePosition(0);
     Date date= new Date();
     date = formatter.parse(inputText, pos);

     date1.setTime(date);

     firstdate = date1.get(date1.DAY_OF_YEAR);
     dateflag = true;

        }

 // This method is called for every date entered after the first date.
 // The previous second date is passed to the first date to keep track of
 // time.

  public void getDate2() throws IOException 
  {
      System.out.print("Enter todays date(mm/dd/yyyy): ");
      BufferedReader br;
      br = new BufferedReader(new InputStreamReader(System.in));
      String inputText = br.readLine();
      SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
      ParsePosition pos = new ParsePosition(0);
      Date date= new Date();
      date = formatter.parse(inputText, pos);

      date2.setTime(date);

      seconddate = date2.get(date2.DAY_OF_YEAR);

  if (firstdate > seconddate) 
  {
   System.out.println("You must enter a future date.");
   getDate2();
  }

 }

 // This method calulates the interest based on the previous date and the
 // current date

 protected abstract void getInterest(); 
 
//  int datediff = seconddate - firstdate;
//  rate = .05/365;
//  double ratetime = Math.pow(1+rate,datediff);
//  balance = balance * ratetime;
//  firstdate = seconddate;



    /**
     * @return the acctNumber
     */
    public int getID() 
    {
        return ID;
    }

    /**
     * @param s
     */
    public void setID(int s) 
    {
        ID = s;
    }
 
}

