package br.com.neosaude.api.domain.alergia.service;

import br.com.neosaude.api.domain.alergia.dto.DTOContagemAlergias;
import br.com.neosaude.api.domain.alergia.Alergia;
import br.com.neosaude.api.domain.alergia.AlergiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContagemAlergiasService {

    @Autowired
    private AlergiaRepository alergiaRepository;

    public DTOContagemAlergias contarAlergias(Long idPaciente){
        List<Alergia> listaAlergias =alergiaRepository.findByIdPaciente(idPaciente);
        int baixas = 0;
        int medias = 0;
        int altas = 0;

        for(Alergia a : listaAlergias){
            switch (a.getIntensidade()) {
                case BAIXA:
                    baixas = baixas+1;
                    break;

                case MEDIA:
                    medias = medias+1;
                    break;

                case ALTA:
                    altas = altas+1;
                    break;

                default:
                    throw new RuntimeException("Uma ou mais alergias desse paciente não possui intensidade declarada");
            }
        }

        return new DTOContagemAlergias(baixas, medias, altas);
    }
}
