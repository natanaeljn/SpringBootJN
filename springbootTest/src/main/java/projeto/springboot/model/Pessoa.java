package projeto.springboot.model;
import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



@Entity
public class Pessoa implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotEmpty(message = "Nome nao pode estar vazio")
	@NotNull(message = "Nome nao pode ser nulo")
	private String nome;
	@NotEmpty(message = "Sobrenome nao pode estar vazio")
	@NotNull(message = "Sobrenome nao pode ser nulo")
	private String sobrenome;
	
	@OneToMany(mappedBy = "pessoa", orphanRemoval = true , cascade = CascadeType.ALL)
	private List<Telefone>telefones ;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}
	
	
	
	
}
