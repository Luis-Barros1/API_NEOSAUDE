package br.com.neosaude.api.domain.receita.dto;

import br.com.neosaude.api.domain.medico.dto.DTODetalhamentoMedico;
import br.com.neosaude.api.domain.prescricao.dto.DTODetalhamentoPrescricao;
import br.com.neosaude.api.domain.receita.Receita;

import java.time.LocalDate;
import java.util.List;

public record DTODetalhamentoReceita(Boolean ativo, Long idReceita, Long idPaciente, String nomePaciente, String nomeClinica, DTODetalhamentoMedico dadosMedico, LocalDate dataExpedicao, List<DTODetalhamentoPrescricao> listaPrescricoes, String observacao) {

    public DTODetalhamentoReceita(Receita receita, List<DTODetalhamentoPrescricao> listaPrescricoes) {
        this(receita.isAtivo(), receita.getId(), receita.getPacienteTratado().getId(), receita.getPacienteTratado().getNome(), receita.getMedicoResponsavel().getClinica(), new DTODetalhamentoMedico(receita.getMedicoResponsavel()), receita.getDataExpedicao(), listaPrescricoes, receita.getObservacao());
    }
}
