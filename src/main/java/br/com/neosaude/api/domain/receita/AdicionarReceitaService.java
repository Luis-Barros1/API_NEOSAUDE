package br.com.neosaude.api.domain.receita;

import br.com.neosaude.api.domain.medico.Medico;
import br.com.neosaude.api.domain.medico.MedicoRepository;
import br.com.neosaude.api.domain.paciente.Paciente;
import br.com.neosaude.api.domain.paciente.PacienteRepository;
import br.com.neosaude.api.domain.prescricao.AdicionarPrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdicionarReceitaService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private AdicionarPrescricaoService adicionarPrescricaoService;

    public Receita adicionar(DTOCadastroReceita dados){
        Paciente paciente = pacienteRepository.findByCpf(dados.cpfPaciente());
        Medico medico = medicoRepository.getReferenceById(dados.idMedico());
        Receita receita = new Receita(paciente, medico, LocalDate.now(), dados.observacao());
        receitaRepository.save(receita);
        adicionarPrescricaoService.adicionarListaPrescricoes(dados.prescricoes(), receita.getId());

        return receita;
    }
}
