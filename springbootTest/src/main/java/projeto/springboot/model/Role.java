package projeto.springboot.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role implements GrantedAuthority {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy  = GenerationType.AUTO)
	private Long id;
	
	private String roleName;
	
	
	@Override
	public String getAuthority() {
		
		return this.roleName;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
}
