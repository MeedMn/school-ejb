package entities;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Student extends User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String lastName;
	private String telephone;
	@ManyToOne(fetch = FetchType.LAZY)
	private Filiere filiere;

	
	
	public Student() {}
	
	public Student(String email, String password ,String firstName, String lastName, String telephone) {
		super(email,password);
		this.firstName = firstName;
		this.lastName = lastName;
		this.telephone = telephone;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Filiere getFiliere() {
		return filiere;
	}

	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}
	
	
	
	

}
