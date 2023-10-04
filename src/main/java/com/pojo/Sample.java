package com.pojo;
import java.io.IOException;
import java.security.GeneralSecurityException;

import com.googlecalender.*;
public class Sample {
	
	public static void main(String args[]) throws IOException, GeneralSecurityException {
		GoogleCalenderAPI calendar = new GoogleCalenderAPI();
		calendar.deleteEvent("ng5benv40mkoavlq31i3vnfl18");
		//calendar.setNewEvent("School Friends Time","Kolkata","time for dinner","2023-12-12T12:00","2023-12-12T13:00");
	}

}
