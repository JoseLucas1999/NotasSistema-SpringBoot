package lucas.notas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import lucas.notas.models.Alunos;
import lucas.notas.models.Notas;
import lucas.notas.repository.AlunosRepository;

@Controller			
public class MyController {
	@Autowired
	private AlunosRepository alunosRepository;
	
    public void AlunosController(AlunosRepository alunosRepository) {
        this.alunosRepository = alunosRepository;
    }
    
    //acessar index.html
	@RequestMapping("/")	
	public String index() {
		return "index";
	}
	
	@RequestMapping(value = "/logarhome", method = RequestMethod.GET)
	public String home() {
		return "view/home"; //folder/page
	}
	
	//logar home
	@RequestMapping(value = "/nologin", method = RequestMethod.GET)
	public String nologinHome() {
		return "view/home";
	}
	
	//logar home
	@RequestMapping(value = "/logar", method = RequestMethod.GET)
	public String logarHome() {
		return "view/home";
	}
	
	//página cadastrar
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastrarAluno2() {
		return "view/cadastrar";
	}
	
	//cadastrar aluno no banco
    @PostMapping("/inserir")
    public String inserirAluno(@RequestParam String nome,@RequestParam int ra,@RequestParam Byte nota1,@RequestParam Byte nota2,@RequestParam Byte nota3,@RequestParam Byte nota4,Model model) {
        // Criar objeto Notas
        Notas notas = new Notas(nota1, nota2, nota3, nota4);       
        // Criar objeto Alunos
        Alunos aluno = new Alunos(nome, ra, notas);
        // Salvar no banco de dados
         alunosRepository.save(aluno);

        // Redirecionar para a página de sucesso ou listar alunos
        return "view/home";
    }
	
	//excluir
	@RequestMapping(value = "/excluir", method = RequestMethod.GET)
	public String excluirAluno() {
		return "view/excluir";
	}
	
	//pesquisar
	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	public String pesquisarAluno() {
		return "view/pesquisar";
	}
	
	//logar home
	@RequestMapping(value = "/aprovados", method = RequestMethod.GET)
	public String aprovados() {
		return "view/home";
	}
	
	//logar home
	@RequestMapping(value = "/reprovados", method = RequestMethod.GET)
	public String reprovados() {
		return "view/home";
	}
}
