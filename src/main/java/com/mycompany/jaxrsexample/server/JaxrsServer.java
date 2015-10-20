/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jaxrsexample.server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.lifecycle.SingletonResourceProvider;

/**
 *
 * @author User
 */
public class JaxrsServer {
    
    /**
     *
     * @throws Exception
     */
    protected JaxrsServer()throws Exception 
    {
         JAXRSServerFactoryBean sf=new JAXRSServerFactoryBean();
        sf.setResourceClasses(StudentService.class);
        sf.setResourceProvider(StudentService.class, new SingletonResourceProvider(new StudentService()));
        sf.setAddress("http://localhost:9000/");
        sf.create();
        
    }
    public static void main(String args[]) throws Exception 
    {
        new JaxrsServer();
        System.out.println("Server ready...");

        Thread.sleep(5 * 6000 * 1000);
        System.out.println("Server exiting");
        System.exit(0);
       

        
        
    }
    
    
}
