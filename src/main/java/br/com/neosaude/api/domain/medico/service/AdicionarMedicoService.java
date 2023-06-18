package br.com.neosaude.api.domain.medico.service;


import br.com.neosaude.api.domain.medico.Medico;
import br.com.neosaude.api.domain.medico.MedicoRepository;
import br.com.neosaude.api.domain.medico.dto.DTOCadastroMedico;
import br.com.neosaude.api.domain.usuario.Usuario;
import br.com.neosaude.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AdicionarMedicoService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Medico cadastrar(DTOCadastroMedico dados){
        Usuario usuario = new Usuario(dados.crm(), passwordEncoder.encode(dados.senha()));
        Medico medico = new Medico(dados, usuario);
        usuarioRepository.save(usuario);
        medicoRepository.save(medico);

        return medico;
    }
}
