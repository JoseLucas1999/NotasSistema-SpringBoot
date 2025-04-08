package lucas.notas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import lucas.notas.services.UsuarioService;

@Controller
public class LoginController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/logar")
    public String logar(@RequestParam String usuario,
                        @RequestParam String senha,
                        Model model,
                        HttpSession session) {

        if (usuarioService.autenticar(usuario, senha)) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/home"; // redireciona para a página principal do sistema
        } else {
            model.addAttribute("mensagem", "Usuário ou senha inválidos!");
            return "index"; // volta para o login com erro
        }
    }
}

