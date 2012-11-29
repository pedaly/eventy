package com.app.eventy.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import android.content.SharedPreferences;
import android.location.Location;

import com.app.eventy.dao.EventDAO;
import com.app.eventy.model.Event;
import com.app.eventy.model.UserContext;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class UpdatingService {
	private static final String endpoint ="http://nonamesevent.appspot.com/WSgetEventList/gson";
	private static final String[] categories={"imprezyOkolicznosciowe","imprezy","kino","koncerty","pokazy","spektakle","wydarzenia","targi","wystawy"};
	private SharedPreferences settings;
	
	
	public UpdatingService(SharedPreferences settings){
		this.settings=settings;
		
	}
	
	public  List<Event> SendRequest(Location loc) throws IOException{
	
		        URL url;
		        List<Event> listOfEvent;
		        try {
		            url = new URL(endpoint);
		        } catch (MalformedURLException e) {
		            throw new IllegalArgumentException("invalid url: " + endpoint);
		        }
		        HttpURLConnection conn = null;
		        UserContext userConetext=new UserContext();
		        userConetext.setCategories( getCategoryList());
		        userConetext.setSearchRadius(settings.getInt("promien", 0));
		      
		        
		        userConetext.setLat(loc.getLatitude());
		        userConetext.setLon(loc.getLongitude());
		        double lat=userConetext.getLat();
		        double lon=userConetext.getLon();
		        
		        Gson gson =new Gson();
		        String toSend=gson.toJson(userConetext);
		        byte[] data=toSend.getBytes();
		        
		      
		        
		        
		        
		        try {
		            conn = (HttpURLConnection) url.openConnection();
		            conn.setDoOutput(true);
		            conn.setUseCaches(false);
		            conn.setFixedLengthStreamingMode(data.length);
		            conn.setRequestMethod("POST");
		            conn.setRequestProperty("Content-Type",
		                    "application/json");
		           
		        
		            // post the request
		            OutputStream out = conn.getOutputStream();
		            out.write(data);
		            out.close();
		            // handle the response
		            int status = conn.getResponseCode();
		            
		            if (status != 200) {
		              throw new IOException("Post failed with error code " + status);
		            }
		            String resp="";
		            InputStream inputStream=(InputStream)conn.getContent();
		            InputStreamReader in= new InputStreamReader(inputStream);
		            BufferedReader bin= new BufferedReader(in);
		            String line;
		     
		            while((line = bin.readLine()) != null){
		                   resp+=line;
		            }
		 
		                Type collectionType = new TypeToken<List<Event>>(){}.getType();
		                listOfEvent = gson.fromJson(resp, collectionType);                
		               
		                
		        } finally {
		            if (conn != null) {
		                conn.disconnect();
		            }
		        }
		     
		      
		
		
		
		
		return listOfEvent;		
	}
	
	List<String> getCategoryList(){
		List<String> ls= new LinkedList<String>();
		for(String str :categories){
			if(settings.getBoolean(str, false)){
				ls.add(str);
			}
		}
		return ls;
	}
	

}
