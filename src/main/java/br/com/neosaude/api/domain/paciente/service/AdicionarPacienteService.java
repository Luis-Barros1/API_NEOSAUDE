package br.com.neosaude.api.domain.paciente.service;


import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.paciente.PacienteRepository;
import br.com.neosaude.api.domain.paciente.dto.DTOCadastroPaciente;
import br.com.neosaude.api.domain.usuario.Usuario;
import br.com.neosaude.api.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdicionarPacienteService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente cadastrar(DTOCadastroPaciente dados){
        Usuario usuario = new Usuario(dados.cpf(), passwordEncoder.encode(dados.senha()));
        Paciente paciente = new Paciente(dados, usuario);
        usuarioRepository.save(usuario);
        pacienteRepository.save(paciente);

        return paciente;
    }
}
