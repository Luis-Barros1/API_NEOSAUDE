package br.com.neosaude.api.domain.paciente.service;


import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.paciente.PacienteRepository;
import br.com.neosaude.api.domain.paciente.dto.DTODetalhamentoPaciente;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObterPacientePorCpfService {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente obter(String cpf){
        if(!cpf.contains(".") || !cpf.contains("-")) throw new ValidationException("Formato inválido para cpf, favor utilizar o padrão: 000.000.000-00");
        if(!pacienteRepository.existsByCpf(cpf)) throw new ValidationException("cpf informado não corresponde a nenhum usuário cadastrado");
        return pacienteRepository.findByCpf(cpf);
    }
}
