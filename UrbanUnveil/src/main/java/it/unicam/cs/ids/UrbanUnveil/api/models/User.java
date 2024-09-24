package it.unicam.cs.ids.UrbanUnveil.api.models;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import it.unicam.cs.ids.UrbanUnveil.api.Enum.RoleEnum;
import jakarta.persistence.*;

@Entity
@Table(name="users")

public class User {
	@Id
	  @GeneratedValue(strategy=GenerationType.IDENTITY)
	 	private long id;
	    private String name;
	    private String surname;
	    private String email;
		private String CF;
		private String password;
		@Column(name = "role", nullable = false)
	    private RoleEnum role;
		
		public User (String name, String surname, String email, String CF, String password, RoleEnum role) {
			this.name = name;
			this.surname = surname;
			this.email = email;
			this.CF=CF;
			if(role==null) {
				this.role=RoleEnum.TOURIST;
			}
			else {
				this.role=role;
			}
			this.password = password;

		}
		@Autowired
	    public User() {
	    }
	    
		public void setName(String name) {
			this.name = name;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setCF(String cF) {
			CF = cF;
		}

		public void setRole(RoleEnum role) {
			this.role = role;
		}

	    public long getId() {
	        return id;
	    }

	    public String getName() {
	        return name;
	    }

	    public String getSurname() {
	        return surname;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public String getCF() {
	        return CF;
	    }

	    public RoleEnum getRole() {
	        return role;
	    }
	    
	    public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
		
		public boolean isEmpty() {
			return (name == null) && (surname == null) && (email == null) && (CF == null) && (password == null);
		}

		@java.lang.Override
	    public java.lang.String toString() {
	        return "User{" +
	                "id=" + id +
	                ", name=" + name +
	                ", surname=" + surname +
	                ", email='" + email + '\'' +
	                ", CF='" + CF + '\'' +
	                ", role=" + role +
	                '}';
	    }

		@Override
		public int hashCode() {
			return Objects.hash(CF, email, name, role, surname, id);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			return Objects.equals(CF, other.CF) && Objects.equals(email, other.email)
					&& Objects.equals(name, other.name) && Objects.equals(role, other.role)
					&& Objects.equals(surname, other.surname) && id == other.id;
		}
}
