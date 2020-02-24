package com.repdev;

public class SymData {
	private int sym = 0;
	private String server = "127.0.0.1";
	private int port = 23;
	private String lastUsername = "", lastPassword = "", lastUserID = "";
	
	public SymData(int sym,String server,int port,String un,String ps,String uid) {
		this.sym=sym;
		this.server=server;
		this.setPort(port);
		this.setLastUsername(un);
		this.setLastPassword(ps);
		this.setLastUserID(uid);
	}
	
	public int getSym() {
		return this.sym;
	}
	public void setSym(int sym) {
		this.sym=sym;
	}
	
	public String getServer() {
		return this.server;
	}
	public void setServer(String server) {
		this.server=server;
	}

	public String getLastPassword() {
		return lastPassword;
	}

	public void setLastPassword(String lastPassword) {
		this.lastPassword = lastPassword;
	}

	public String getLastUserID() {
		return lastUserID;
	}

	public void setLastUserID(String lastUserID) {
		this.lastUserID = lastUserID;
	}

	public String getLastUsername() {
		return lastUsername;
	}

	public void setLastUsername(String lastUsername) {
		this.lastUsername = lastUsername;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	
}
