package lucas.notas.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucas.notas.models.Usuario;
import lucas.notas.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticar(String usuario, String senha) {
        Optional<Usuario> usuarioDB = usuarioRepository.findByUsuario(usuario);
        return usuarioDB.isPresent() && usuarioDB.get().getSenha().equals(senha);
    }
}
