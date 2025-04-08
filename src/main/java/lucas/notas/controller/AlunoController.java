package lucas.notas.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lucas.notas.models.Alunos;
import lucas.notas.models.Notas;
import lucas.notas.repository.AlunosRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunosRepository alunosRepository;

    @GetMapping("/todos")
    public ModelAndView listarTodos() {
        List<Alunos> alunos = alunosRepository.findAll();
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("alunos", alunos);
        mv.addObject("mensagem", "Todos os Alunos");
        return mv;
    }

    @GetMapping("/aprovados")
    public ModelAndView listarAprovados() {
        List<Alunos> alunos = alunosRepository.findAll();
        List<Alunos> aprovados = alunos.stream().filter(a -> a.getMedia() >= 7).collect(Collectors.toList());
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("alunos", aprovados);
        mv.addObject("mensagem", "Alunos Aprovados");
        return mv;
    }

    @GetMapping("/reprovados")
    public ModelAndView listarReprovados() {
        List<Alunos> alunos = alunosRepository.findAll();
        List<Alunos> reprovados = alunos.stream().filter(a -> a.getMedia() < 7).collect(Collectors.toList());
        ModelAndView mv = new ModelAndView("home");
        mv.addObject("alunos", reprovados);
        mv.addObject("mensagem", "Alunos Reprovados");
        return mv;
    }

    @GetMapping("/cadastrar")
    public String cadastrarForm() {
        return "cadastrar";
    }

    @PostMapping("/inserir")
    public String inserirAluno(@RequestParam String nome, @RequestParam int ra,
                                @RequestParam Byte nota1, @RequestParam Byte nota2,
                                @RequestParam Byte nota3, @RequestParam Byte nota4) {

        Notas notas = new Notas(nota1, nota2, nota3, nota4);
        Alunos aluno = new Alunos(nome, ra, notas);
        alunosRepository.save(aluno);
        return "redirect:/backHome";
    }

    @GetMapping("/excluir")
    public String excluirForm() {
        return "excluir";
    }
    
    @GetMapping("/excluirAluno")
    public String excluirAlunoPorRa(@RequestParam("ra") int ra, Model model) {
        List<Alunos> alunos = alunosRepository.findByRa(ra);
        
        if (!alunos.isEmpty()) {
        	alunosRepository.deleteAll(alunos);
            model.addAttribute("mensagem", "Aluno(s) excluído(s) com sucesso.");
        } else {
            model.addAttribute("mensagem", "Aluno não encontrado.");
        }

        return "excluir"; // retorna para a mesma página
    }


    @GetMapping("/pesquisar")
    public String pesquisarForm() {
        return "pesquisar";
    }
    
    @GetMapping("/pesquisarAluno")
    public String pesquisarAluno(@RequestParam(name = "ra", required = false) Integer ra, Model model) {
        if (ra != null) {
        	List<Alunos> alunos = alunosRepository.findByRa(ra);
            model.addAttribute("alunos", alunos);
        }
        return "pesquisar"; // Nome do seu arquivo JSP/HTML
    }
    
}
