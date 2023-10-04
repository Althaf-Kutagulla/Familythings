package com.googlecalender;

import com.google.api.client.auth.oauth2.Credential;

import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;

import com.google.api.services.calendar.Calendar;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* class to demonstrate use of Calendar events list API */
public class GoogleCalendarAPI {
    /**
     * Application name.
     */
    private static final String APPLICATION_NAME = "JavaCalender";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "token";

    
    private static final List<String> SCOPES =
            Collections.singletonList(CalendarScopes.CALENDAR);
    
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT)
            throws IOException {
        // Load client secrets.
        InputStream in = GoogleCalenderAPI.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }
    
    public Credential refreshExpiredToken(Credential credential) throws IOException {
        if (credential.getExpiresInSeconds() != null && credential.getExpiresInSeconds() <= 60) {
            credential.refreshToken();
        }
        return credential;
    }

    public List<Event> getEvents() throws IOException, GeneralSecurityException{

        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service =
                new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, refreshExpiredToken(getCredentials(HTTP_TRANSPORT)))
                        .setApplicationName(APPLICATION_NAME)
                        .build();

        DateTime now = new DateTime(System.currentTimeMillis());

        Events events = service.events().list("primary")
                .setMaxResults(10)
                .setTimeMin(now)
                .setOrderBy("startTime")
                .setSingleEvents(true)
                .execute();


        List<Event> items = events.getItems();
        if(items.isEmpty()){
           return null;
        }else{
            return items;
        }

    }

 


   
    public String setNewEvent(String summary,String location,String description,String startDateTimeP,String endDateTimeP) throws IOException, GeneralSecurityException {
        

    	System.out.println(startDateTimeP);
    	System.out.println(endDateTimeP);
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, refreshExpiredToken(getCredentials(HTTP_TRANSPORT)))
                .setApplicationName(APPLICATION_NAME).build();

        Event event = new Event()
                .setSummary(summary)
                .setLocation(location)
                .setDescription(description);
        
        
        //2015-05-28T09:00:00-07:00
        DateTime startDateTime = new DateTime(startDateTimeP+":00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("Asia/Kolkata");
        event.setStart(start);
        
        

        DateTime endDateTime = new DateTime(endDateTimeP+":00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("Asia/Kolkata");
        event.setEnd(end);

        

        
        String calendarId = "primary";
        event = service.events().insert(calendarId, event).execute();
       String eventID = event.getId();
       System.out.println(eventID);
        System.out.printf("Event created: %s\n", event.getHtmlLink());
        
        return eventID;
    }
    
    public void deleteEvent(String eventId) throws IOException, GeneralSecurityException {
    	final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
    	
    	Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, refreshExpiredToken(getCredentials(HTTP_TRANSPORT)))
    		    .setApplicationName("applicationName").build();

    		// Delete an event
    		service.events().delete("primary", eventId).execute();
    		System.out.println("Event Deleted");
    		//service.events().delete('primary',eventId).execute();
    }
    
//    public static void main(String args[]) throws IOException, GeneralSecurityException {
//    	GoogleCalendarAPI cal = new GoogleCalendarAPI();
//    	List<Event> list =cal.getEvents();
//    	System.out.println(list);
//    	String id =cal.setNewEvent("Family Eventc","Kadirix","Dinnerf","2023-12-11T10:00","2023-12-11T11:00");
//    	System.out.println(id);
//    }


  
}
