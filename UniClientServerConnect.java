//This is the class where the primary protocol between the client and server will be

package unisourceclient;

import java.net.*;
import java.io.*;
import java.util.*;


public class UniClientServerConnect {
	
	private Socket connection; // socket to server
	
	private BufferedReader fromUniServer; // reading and writing to socket
	private DataOutputStream toUniServer;
	
	private static final int SMTP_PORT = 25;
	private static final String CRLF = "\r\n";
	
	private boolean connected = false; // used to close connection

	public UniClientServerConnect {
		
	}

}