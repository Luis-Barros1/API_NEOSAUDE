package br.com.neosaude.api.domain.receita;

import br.com.neosaude.api.domain.medico.DTODetalhamentoMedico;
import br.com.neosaude.api.domain.prescricao.DTODetalhamentoPrescricao;
import br.com.neosaude.api.domain.prescricao.Prescricao;
import br.com.neosaude.api.domain.prescricao.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public record DTODetalhamentoReceita(Boolean ativo, Long idReceita, Long idPaciente, String nomePaciente, String nomeClinica, DTODetalhamentoMedico dadosMedico, LocalDate dataExpedicao, List<DTODetalhamentoPrescricao> listaPrescricoes, String observacao) {

    public DTODetalhamentoReceita(Receita receita, List<DTODetalhamentoPrescricao> listaPrescricoes) {
        this(receita.isAtivo(), receita.getId(), receita.getPacienteTratado().getId(), receita.getPacienteTratado().getNome(), receita.getMedicoResponsavel().getClinica(), new DTODetalhamentoMedico(receita.getMedicoResponsavel()), receita.getDataExpedicao(), listaPrescricoes, receita.getObservacao());
    }
}
