/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.mycompany.jaxrsexample.client;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.PutMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

import org.apache.cxf.helpers.IOUtils;
import org.apache.cxf.io.CachedOutputStream;
import org.apache.cxf.resource.URIResolver;

public final class Client {

    private Client() {
    }

    public static void main(String args[]) throws Exception {
        // Sent HTTP GET request to query all students info
        
        
         
          System.out.println("Invoking server through HTTP GET to query all students info");
          URL url = new URL("http://localhost:9000/studentservice/students");
          InputStream in = url.openStream();
          System.out.println(getStringFromInputStream(in));

        // Sent HTTP GET request to query student info
        System.out.println("Sent HTTP GET request to query customer info");
        url = new URL("http://localhost:9000/studentservice/students/1");
        in = url.openStream();
        System.out.println(getStringFromInputStream(in));

        // Sent HTTP PUT request to update student info
        System.out.println("\n");
        System.out.println("Sent HTTP PUT request to update student info");
        Client client = new Client();
        String inputFile = client.getClass().getResource("/update_student.xml").getFile();
        URIResolver resolver = new URIResolver(inputFile);
        File input = new File(resolver.getURI());
        PutMethod put = new PutMethod("http://localhost:9000/studentservice/students");
        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");
        put.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(put);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(put.getResponseBodyAsString());
        } finally {
            // Release current connection to the connection pool once you are
            // done
            put.releaseConnection();
        }

        // Sent HTTP POST request to add student
        System.out.println("\n");
        System.out.println("Sent HTTP POST request to add student");
        inputFile = client.getClass().getResource("/add_student.xml").getFile();
        resolver = new URIResolver(inputFile);
        input = new File(resolver.getURI());
        PostMethod post = new PostMethod("http://localhost:9000/studentservice/students");
        post.addRequestHeader("Accept" , "text/xml");
        entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");
        post.setRequestEntity(entity);
        httpclient = new HttpClient();

        try {
            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } finally {
            // Release current connection to the connection pool once you are
            // done
            post.releaseConnection();
        }

        System.out.println("\n");
        System.exit(0);
    }

    private static String getStringFromInputStream(InputStream in) throws Exception {
        CachedOutputStream bos = new CachedOutputStream();
        IOUtils.copy(in, bos);
        in.close();
        bos.close();
        return bos.getOut().toString();
    }

}
