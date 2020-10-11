package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column
	String name;
	
	@Column
	String password;
	
	@Column
	String email;
	
	@Column
	String token;
	
	@Column
	Long lockerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getLockerId() {
		return lockerId;
	}

	public void setLockerId(Long lockerId) {
		this.lockerId = lockerId;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", token="
				+ token + ", lockerId=" + lockerId + "]";
	}
	
	
}
