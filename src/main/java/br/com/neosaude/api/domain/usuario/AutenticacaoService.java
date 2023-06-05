package br.com.neosaude.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AutenticacaoService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username){
        if(username == null || !usuarioRepository.existsByLogin(username)) throw new UsernameNotFoundException("Usuário não encontrado");
        return usuarioRepository.findByLogin(username);
    }
}
