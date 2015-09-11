/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Josh
 */
public class ATM 
{
   // Account[] acctArray = new Account[3];
    ArrayList<Account> acctList = new ArrayList<>();
    
    public static void main (String args[]) throws IOException, Exception
    {
        ATM myATM = new ATM();
        
        try
        {
            myATM.readArray();
            myATM.startMenu();
            myATM.writeArray();
        }
        catch (FileNotFoundException fnfe)
        {
            myATM.readArray();
            myATM.startMenu();
            myATM.writeArray();
        }
        catch (IOException | ClassNotFoundException ioe)
        {
            myATM.readArray();
            myATM.startMenu();
            myATM.writeArray();
        }
        
    }
     
    public void readArray() throws IOException, FileNotFoundException, ClassNotFoundException, Exception
    {
        try (FileInputStream fis = new FileInputStream ("test.out")) 
        {
            ObjectInputStream ois = new ObjectInputStream(fis);
            //acctArray = (Account[])ois.readObject();
            acctList = (ArrayList<Account>)ois.readObject();
            fis.close();
        }
        catch(FileNotFoundException fnfe)
        {
           //System.err.println("ERROR try again");
           startMenu();
        }
        catch (IOException ioe)
        {
           // System.err.println("ERROR try again");
            startMenu();
        }
        
    }
    
    public void writeArray() throws IOException
    {
        try (FileOutputStream fos = new FileOutputStream("test.out")) 
        {
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            
            oos.writeObject(acctList);    
            //oos.writeObject(acctArray);
            oos.flush();
            fos.close();
        }
        
        catch (Throwable e)
	{
        	System.err.println(e);
        }
    }
    
    public void startMenu() throws Exception
    {
        int input;
        Scanner sc = new Scanner(System.in);
        
        
         //for(int i=0; i<acctArray.length;i++)
         //{
           System.out.println("Press (1) Open a new account: \n (2) Open an existing account: \n (3) Exit: ");
           input = sc.nextInt();
           if (input == 1)
           {
                System.out.println("Please select the account you would like to open");
                System.out.println(" 1). Checking \n 2). Savings ");
                input = sc.nextInt();
                if (input == 1)
                {
                    int i = acctList.size();
                   // acctArray[i] = new Checking();
                    System.out.println("Enter the account number you would like to use:");
                    int ID = sc.nextInt();
                    //acctArray[i].setID(ID);
                   // acctArray[i].menu();
                    acctList.add(new Checking());
//                    acctList.set(ID,new Checking());
                    acctList.get(i).setID(ID);
//                    acctList.add(ID, new Checking());
                    acctList.get(i).menu();
                }
                else if (input == 2)
                {
                   // acctArray[i] = new Savings();
                    System.out.println("Enter the account number you would like to use:");
                    int ID = sc.nextInt();
                    //acctArray[i].setID(ID);
                   // acctArray[i].menu();
                    acctList.get(ID).setID(ID);
                    acctList.add(ID, new Savings());
                    acctList.get(ID).menu();
                }
                
                else
                {
                    System.out.println("Wrong input try again");
                    startMenu();
                }
           }
           else if (input == 2)
           {   
               lookupAccounts(); 
           }
           else if (input == 3)
           {
               System.exit(0);
           }
           
           else 
           {
               System.out.println("Wrong input!!");
               startMenu();
           }          
 
        //}
         
    }
    
    public void lookupAccounts() throws IOException
    {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your account number: ");
        int input = sc.nextInt();
        
        for (int i = 0; i < acctList.size(); i++)
        {
           if (acctList.get(i).equals(input)) 
           {
               acctList.get(input).menu();
               
           }
        }
        
//        int index = -1;
//        for (int i = 0; i < acctArray.length; i++)
//        {
//            if(acctList[i].getID().equalsIgnoreCase(secondinput))
//            {
//                index = i;
//                acctList[i].menu();           
//        }
//     }
    }
    
}
