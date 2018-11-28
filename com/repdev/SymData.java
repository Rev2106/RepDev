package com.repdev;

public class SymData {
	private int sym = 0;
	private String server = "127.0.0.1";
	private int port = 23;
	private String lastUsername = "", lastPassword = "", lastUserID = "";
	
	public SymData(int sym,String server,int port,String un,String ps,String uid) {
		this.sym=sym;
		this.server=server;
		this.port=port;
		this.lastUsername=un;
		this.lastPassword=ps;
		this.lastUserID=uid;
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
	
	
}
