/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.jaxrsexample.server;

import java.util.ArrayList;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.core.Response;
/**
 *
 * @author User
 */
@Path("/studentservice/")
@Produces("text/xml")

public class StudentService {
    long initid=1;
    Map<Long,Student> Students=new HashMap<Long, Student>();
    public StudentService()
    {
        init();
    }

    private void init() {
        String name="manikanta";
        for(int i=5;i>=0;i--,initid++)
        {
            Student s=new Student();
            s.setId(initid);
            s.setName(name.substring(0,i+4));
            s.setContact(98988989);
            Address ad=new Address();
            
            s.setAddress(ad);
            Students.put(initid, s);
            
        }
        
     // Student s=new Student();
      //s.setId(initid);
      //s.setName("Mani");
     // Students.put(s.getId(), s);
     }
    @GET
    @Path("/students/{id}/")
    public Student getStudent(@PathParam("id") String id)
    {
        System.out.println("entered in the init methd");
        System.out.println("invoking the getStudent method for Id"+id);
        long studentId=Long.parseLong(id);
        Student s= Students.get(studentId);
        return s;
    }
    @GET
    @Path("/students/")
    public List<Student> getStudents()
    {
        List<Student> list=new ArrayList<>();
        //Student x=new Student();
        for(Student s:Students.values())
        {
            list.add(s);
            
        }
        return list;
           
    }
    
    @DELETE
    @Path("/students/{id}/")
    public Response deleteStudent(@PathParam("id") String id)
    {
        System.out.println("Entered inside delete student method for id"+ id);
        Response r;
        long sid= Long.parseLong(id);
        Student s= Students.get(sid);
        if(s!=null)
        {
            r=Response.ok().build();
            Students.remove(sid);
            
        }
        else
        {
            r=Response.notModified().build();
        }
        return r;
        
    }
    @POST
    @Path("/students/")
    public Response addStudent(Student s)
    {
        System.out.println("Entered inside the addcustomer method with name"+s.getName());
        s.setId(initid++);
        Students.put(s.getId(),s);
        Response r=Response.ok().build();
        return r;
    }
    @PUT
    @Path("/students/")
    public Response updateStudent(Student s)
    {
        System.out.println("entered inside the updatecustomer methd for customer"+ s.getId());
        Student x=Students.get(s.getId());
        Response r;
        if(x!=null)
        {
            Students.put(s.getId(), s);
            r= Response.ok().build();
        }
        else
        {
            r=Response.notModified().build();
        }
        return r;
        
            
        
    }
}
