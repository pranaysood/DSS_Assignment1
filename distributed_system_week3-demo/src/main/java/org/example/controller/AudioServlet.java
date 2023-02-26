package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentHashMap;

import org.example.model.Audio;

import com.google.gson.Gson;
import com.google.gson.JsonElement;



@WebServlet(name = "audio", value = "audio")
public class AudioServlet extends HttpServlet {
	
	ConcurrentHashMap<String, Audio> audiomirchi = new ConcurrentHashMap<>();
	
	@Override
	 public void init() throws ServletException {
		Audio audio1 = new Audio("singer_1","audio_title_number_1","album_number_title_1",12,2600,111,110);
		Audio audio2 = new Audio("singer_2","audio_title_number_2","album_number_title_2",24,2601,112,210);
		Audio audio3 = new Audio("singer_3","audio_title_number_3","album_number_title_3",35,2603,113,310);
		Audio audio4 = new Audio("singer_4","audio_title_number_4","album_number_title_4",48,2604,114,410);
		Audio audio5 = new Audio("singer_5","audio_title_number_5","album_number_title_5",60,2605,115,510);
		Audio audio6 = new Audio("singer_6","audio_title_number_6","album_number_title_6",72,2606,116,610);
		 
		audiomirchi .put("singer_1", audio1);
		audiomirchi .put("singer_2", audio2);
		audiomirchi .put("singer_3", audio3);
		audiomirchi .put("singer_4", audio4);
		audiomirchi .put("singer_5", audio5);
		audiomirchi .put("singer_6", audio6);
		 
	 }
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		String singerName = request.getParameter("singerName");
		Audio audiosoundbox = audiomirchi .get(singerName);
		
	
	   Gson gson = new Gson();
	   JsonElement element = gson.toJsonTree(audiomirchi );
	    
		PrintWriter out = response.getWriter();
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    out.println("GET RESPONSE IN JSON - single element: " + gson.toJson(audiosoundbox));
	    
	   out.println("GET RESPONSE IN JSON - all elements " + element.toString());
	        
	   out.flush();
	
	}
	 @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
		   
		    String singerName = request.getParameter("artistName");
		    String audiotrackTitle = request.getParameter("audiotrackTitle");
		    String nameofalbumTitle = request.getParameter("nameofalbumTitle");
		    String track_Number = request.getParameter("track_Number");
		    int inttrack_Number = Integer.parseInt(track_Number);
		    String year = request.getParameter("year");
		    int intYear = Integer.parseInt(year);
		    String reviewsCount = request.getParameter("reviewsCount");
		    int intreviewsCount = Integer.parseInt(reviewsCount);
		    String noofcopiesSold = request.getParameter("noofcopiesSold");
		    int intnoofcopiesSold = Integer.parseInt(noofcopiesSold);
		        
		    Audio audiosoundbox = new Audio();
		   
		    audiosoundbox.setSingerName(singerName);
		    audiosoundbox.setAudiotrackTitle(audiotrackTitle);
		    audiosoundbox.setNameofalbumTitle(nameofalbumTitle);
		    audiosoundbox.setTrack_Number(inttrack_Number);
		    audiosoundbox.setYear(intYear);
		    audiosoundbox.setReviewsCount(intreviewsCount);
		    audiosoundbox.setNoofcopiesSold(intnoofcopiesSold);
	        
	        
		    audiomirchi.put(singerName, audiosoundbox);
	        response.setStatus(200);
	    	
	    	response.getOutputStream().println("POST RESPONSE: Audio Item of " + singerName + " is added to the database.");
	    }

}