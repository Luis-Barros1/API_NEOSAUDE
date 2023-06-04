package br.com.neosaude.api.domain.receita.dto;

import br.com.neosaude.api.domain.medico.dto.DTODetalhamentoMedico;
import br.com.neosaude.api.domain.receita.Receita;

import java.time.LocalDate;

public record DTOListagemReceita(Long idReceita, LocalDate dataExpedicao, boolean ativo, DTODetalhamentoMedico dadosMedico) {


    public DTOListagemReceita(Receita r) {
        this(r.getId(), r.getDataExpedicao(), r.isAtivo(), new DTODetalhamentoMedico(r.getMedicoResponsavel()));
    }
}
