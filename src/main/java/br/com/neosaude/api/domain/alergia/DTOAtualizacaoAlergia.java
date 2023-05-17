package br.com.neosaude.api.domain.alergia;

import java.time.LocalDate;

public record DTOAtualizacaoAlergia(Long id, IntensidadeAlergia intensidadeAlergia, String descricao, String tratamento, LocalDate dataUltimaReacao) {
}
