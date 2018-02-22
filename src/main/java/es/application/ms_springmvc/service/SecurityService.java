package es.application.ms_springmvc.service;

public interface SecurityService {
    
	String findLoggedInUsername();
	void autologin(String username, String password);
}
