package lucas.notas;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lucas.notas.models.Alunos;
import lucas.notas.models.Notas;
import lucas.notas.repository.AlunosRepository;

@Controller
public class MyController {
	@Autowired
	private AlunosRepository alunosRepository;

	@Autowired
	// private UsuarioRepository usuarioRepository;

	public void AlunosController(AlunosRepository alunosRepository) {
		this.alunosRepository = alunosRepository;
	}

	// acessar index.html
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	//voltar para home
	@RequestMapping("/backHome")
	public String homeBacking() {
		return "home";
	}
	
	// voltar a login
	@RequestMapping("/indexBack")
	public String indexBacking() {
		return "index";
	}

	// logar home
	@RequestMapping(value = { "/logar", "/nologin" }, method = RequestMethod.GET)
	public String nologinHome() {
		return "home";
	}

	// criar tabela de alunos
	@GetMapping(value = "/buscarAlunos")
	public ModelAndView tabelaAlunos() {
		ModelAndView mv = new ModelAndView("home"); // que representa a página que será renderizada
		List<Alunos> alunos = alunosRepository.findAll();// Recupera os eventos do banco de dados
		mv.addObject("alunos", alunos); // Adiciona alunos ao ModelAndView
		mv.addObject("mensagem", "Todos os Alunos"); // Adiciona a mensagem
		return mv;// Retornando o ModelAndView
	}

	// criar tabela aprovados
	@GetMapping(value = "/aprovados")
	public ModelAndView tabelaAprovados() {
		ModelAndView mv = new ModelAndView("home"); // Representa a página que será renderizada
		List<Alunos> alunos = alunosRepository.findAll(); // Recupera os alunos do banco de dados

		// Filtrando alunos com média acima de 7
		List<Alunos> alunosAprovados = alunos.stream().filter(aluno -> aluno.getMedia() >= 7)
				.collect(Collectors.toList()); // Filtra e coleta alunos com média acima de 7

		mv.addObject("alunos", alunosAprovados); // Adiciona a lista filtrada ao ModelAndView
		mv.addObject("mensagem", "Alunos Aprovados"); // Adiciona a mensagem
		return mv; // Retorna o ModelAndView
	}
	
	// criar tabela reprovados
	@GetMapping(value = "/reprovados")
	public ModelAndView tabelaReprovados() {
		ModelAndView mv = new ModelAndView("home"); // Representa a página que será renderizada
		List<Alunos> alunos = alunosRepository.findAll(); // Recupera os alunos do banco de dados

		// Filtrando alunos com média acima de 7
		List<Alunos> alunosReprovados = alunos.stream().filter(aluno -> aluno.getMedia() < 7)
				.collect(Collectors.toList()); // Filtra e coleta alunos com média acima de 7

		mv.addObject("alunos", alunosReprovados); // Adiciona a lista filtrada ao ModelAndView
		mv.addObject("mensagem", "Alunos Reprovados"); // Adiciona a mensagem
		return mv; // Retorna o ModelAndView
	}

	// página cadastrar
	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastrarAluno2() {
		return "cadastrar";
	}

	// cadastrar aluno no banco
	@PostMapping("/inserir")
	public String inserirAluno(@RequestParam String nome, @RequestParam int ra, @RequestParam Byte nota1,
			@RequestParam Byte nota2, @RequestParam Byte nota3, @RequestParam Byte nota4, Model model) {
		Notas notas = new Notas(nota1, nota2, nota3, nota4); // Criar objeto Notas
		Alunos aluno = new Alunos(nome, ra, notas);// Criar objeto Alunos
		alunosRepository.save(aluno);// Salvar no banco de dados
		return "home";// Redirecionar para a página de sucesso ou listar alunos
	}

	// excluir
	@RequestMapping(value = "/excluir", method = RequestMethod.GET)
	public String excluirAluno() {
		return "/excluir";
	}

	// pesquisar
	@RequestMapping(value = "/pesquisar", method = RequestMethod.GET)
	public String pesquisarAluno() {
		return "pesquisar";
	}

}
