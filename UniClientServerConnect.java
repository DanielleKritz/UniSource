/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unisourceclient;

import java.net.*;
import java.io.*;
import java.util.*;


public class UniClientServerConnect {
	
	private Socket connection; // socket to server
	
	private BufferedReader fromUniServer; // reading and writing to socket
	private PrintWriter toUniServer;
	
	private static final int UNI_SERVER_PORT = 6666; // the UniServer opening a new socket port
	//private static final String CRLF = "\r\n";
	
	private boolean connected = false; // used to close connection

	public UniClientServerConnect() throws IOException {
		connection = new Socket("127.0.0.1", UNI_SERVER_PORT);
                fromUniServer = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                toUniServer = new  PrintWriter(connection.getOutputStream(), true);
                
                toUniServer.println("bye");
                
	}

}