package lucas.notas.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
public class Alunos implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Melhor para bancos como MySQL
    private long id;
    @Column(unique = true)
    private int ra; // Não precisa @NotNull, pois int nunca é null
    @NotEmpty(message = "O nome não pode estar vazio")
    private String nome;
    @Embedded
    private Notas notas = new Notas();
    @Transient
    private double media; // não precisa ser inserido no banco
    @Transient
    private String situacao; // não precisa ser inserido no banco
    
    
	public Alunos() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Alunos(@NotEmpty(message = "O nome não pode estar vazio") String nome, int ra,  Notas notas, double media,String situacao) {
		super();
		this.ra = ra;
		this.nome = nome;
		this.notas = notas;
		this.media = (byte) getMedia(); // Calcula e atribui a média
		this.situacao = (String) getSituacao(); // Calcula e atribui a situação
	}
	
	public Alunos(@NotEmpty(message = "O nome não pode estar vazio") String nome, int ra,  Notas notas) {
		super();
		this.ra = ra;
		this.nome = nome;
		this.notas = notas;
	}

	public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public int getRa() { return ra; }
    public void setRa(int ra) { this.ra = ra; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Notas getNotas() { return notas; }
    public void setNotas(Notas notas) { this.notas = notas; }

    public void setMedia(byte media) { this.media = media; }
    public void setSituacao(String situacao) { this.situacao = situacao; }
    
	// Calcula a média automaticamente
    public double getMedia() {
        if (notas == null) return 0;
        return (notas.getNota1() + notas.getNota2() + notas.getNota3() + notas.getNota4()) / 4.0;
    }

    // Determina se o aluno foi aprovado ou reprovado
    public String getSituacao() {
        return getMedia() >= 7 ? "Aprovado" : "Reprovado";
    }
}
