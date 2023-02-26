package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.example.model.Artist;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

//@WebServlet(name = "skiiers", value = "skiiers")
@WebServlet(name = "artists", value = "artists")
public class ResourceServlet extends HttpServlet {
    
	
	/*
	 * ConcurrentHashMap is thread safe; 
	 */
	ConcurrentHashMap<String, String> artistDB = new ConcurrentHashMap<>();
	
	/*
	 * simply emulation of in memory database;  
	 */
	@Override
	 public void init() throws ServletException {
		 artistDB.put("id_1", "artist_name_1");
		 artistDB.put("id_2", "artist_name_2");
		 artistDB.put("id_3", "artist_name_3");
		 artistDB.put("id_4", "artist_name_4");
		 artistDB.put("id_5", "artist_name_5");
		 artistDB.put("id_6", "artist_name_6");
		 
	 }
	
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String id = request.getParameter("id");
		String name = artistDB.get(id);
		
		Artist art = new Artist();
		art.setId(id);
		art.setName(name);
		
	    Gson gson = new Gson();
	    JsonElement element = gson.toJsonTree(artistDB);
	    
	    /*
	     * response in normal string message;
	     */
		//response.getOutputStream().println("Artist id is " + id +" name is " + name);
    
		
		/*
		 * response in json with as a data model
		 */
		PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(art));
        
        out.println("GET RESPONSE IN JSON - all elements " + element.toString());
     
        out.flush();   
	
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String id = request.getParameter("id");
        String name = request.getParameter("name");
        
        
        
        artistDB.put(id, name);
        response.setStatus(200);
    	
    	response.getOutputStream().println("POST RESPONSE: Artist " + name + " is added to the database.");
    }
}
