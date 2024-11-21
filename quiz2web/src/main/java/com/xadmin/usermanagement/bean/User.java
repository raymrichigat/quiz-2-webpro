package com.xadmin.usermanagement.bean;

public class User {

	private int id;
	private String nrp;
	private String name;
	private String email;
	private String alamat;
	private String jurusan;
	private String gender;
	
	public User() {}

	public User(int id, String nrp, String name, String email, String alamat, String jurusan, String gender) {
		super();
		this.id = id;
		this.nrp = nrp;
		this.name = name;
		this.email = email;
		this.alamat = alamat;
		this.jurusan = jurusan;
		this.gender = gender;
	}
	
	public User(String nrp, String name, String email, String alamat, String jurusan, String gender) {
		super();
		this.nrp = nrp;
		this.name = name;
		this.email = email;
		this.alamat = alamat;
		this.jurusan = jurusan;
		this.gender = gender;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNrp() {
		return nrp;
	}
	public void setNrp(String nrp) {
		this.nrp = nrp;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	public String getJurusan() {
		return jurusan;
	}
	public void setJurusan(String jurusan) {
		this.jurusan = jurusan;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	}
