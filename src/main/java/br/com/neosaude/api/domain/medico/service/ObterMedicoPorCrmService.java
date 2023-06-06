package br.com.neosaude.api.domain.medico.service;

import br.com.neosaude.api.domain.medico.Medico;
import br.com.neosaude.api.domain.medico.MedicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObterMedicoPorCrmService {

    @Autowired
    private MedicoRepository medicoRepository;

    public Medico obter(String crm){
        if(crm == null || crm == "") throw new ValidationException("O campo o crm não pode estar vázio ou em branco");
        if(!medicoRepository.existsByCrm(crm)) throw new ValidationException("Não existem médicos com esse crm");
        Medico medico = medicoRepository.getReferenceByCrm(crm);
        return medico;
    }
}
