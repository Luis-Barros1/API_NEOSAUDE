package br.com.neosaude.api.domain.alergia;

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
