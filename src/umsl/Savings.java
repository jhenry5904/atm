/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package umsl;

/**
 *
 * @author Josh
 */
public class Savings extends Account
{
    public Savings()
    {
        super(100);
    }
   @Override
   protected void getInterest()
   {
       int datediff = seconddate - firstdate;
       rate = .85/365;
       double ratetime = Math.pow(1+rate,datediff);
       balance = balance * ratetime;
       firstdate = seconddate;
   } 
}
