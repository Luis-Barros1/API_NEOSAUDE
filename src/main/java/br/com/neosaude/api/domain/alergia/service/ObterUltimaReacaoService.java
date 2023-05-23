package br.com.neosaude.api.domain.alergia.service;

import br.com.neosaude.api.domain.alergia.dto.DTOUltimaReacaoAlergica;
import br.com.neosaude.api.domain.alergia.Alergia;
import br.com.neosaude.api.domain.alergia.AlergiaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObterUltimaReacaoService {

    @Autowired
    private AlergiaRepository alergiaRepository;

    public DTOUltimaReacaoAlergica obterUltimaReacao(Long idPaciente){

        List<Alergia> listaAlergiasPaciente = alergiaRepository.findByIdPaciente(idPaciente);
        for(Alergia a : listaAlergiasPaciente){
            if(a.getDataUltimaReacao() != null){
                return new DTOUltimaReacaoAlergica(a);
            }
        }

        throw new ValidationException("Sem ultima reação alérgica cadastrada");
    }
}
