package br.com.neosaude.api.domain.alergia.service;

import br.com.neosaude.api.domain.alergia.dto.DTOCadastroAlergia;
import br.com.neosaude.api.domain.alergia.Alergia;
import br.com.neosaude.api.domain.alergia.AlergiaRepository;
import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CriarAlergiaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private AlergiaRepository alergiaRepository;

    public Alergia criarAlergia(DTOCadastroAlergia dados){
        Paciente pacienteAlergico = pacienteRepository.getReferenceById(dados.idPacienteDiagnosticado());
        Alergia alergia = new Alergia(dados, pacienteAlergico);
        alergiaRepository.save(alergia);

        return alergia;
    }
}
