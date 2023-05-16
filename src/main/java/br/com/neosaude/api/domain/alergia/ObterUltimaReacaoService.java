package br.com.neosaude.api.domain.alergia;

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
