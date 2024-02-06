package com.br.agilize.dash.service.dashboardService;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.MaturidadeMapper;
import com.br.agilize.dash.model.dto.dashboardDto.MaturidadeDto;
import com.br.agilize.dash.model.entity.dashboardEntity.MaturidadeEntity;
import com.br.agilize.dash.repository.dashboardRepository.MaturidadeRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class MaturidadeService extends ServiceCrudBase<MaturidadeDto> {
    @Autowired
    private MaturidadeRepository repository;

    @Autowired
    private MaturidadeMapper mapper;

    @Override
    public MaturidadeDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<MaturidadeDto> obterTodos() {
        List<MaturidadeEntity> Maturidades = this.repository.findAll();

        return Maturidades.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public MaturidadeDto salvar(MaturidadeDto payload) {
        Optional<MaturidadeEntity> existente = this.repository.findTopByEsteiraIdOrderByNumeroDesc(payload.getEsteira().getId());
        MaturidadeEntity maturidade = this.mapper.dtoToModel(payload);
           
        //Multiplicando valores especificos por 100 antes de salvar
        maturidade.setLeadTime(multiplicarPor100(maturidade.getLeadTime()));
        maturidade.setFrequencyDeployment(multiplicarPor100(maturidade.getFrequencyDeployment()));
        maturidade.setChangeFailureRate(multiplicarPor100(maturidade.getChangeFailureRate()));
        maturidade.setTimeToRecovery(multiplicarPor100(maturidade.getTimeToRecovery()));
        maturidade.setMediaDeJornada(multiplicarPor100(maturidade.getMediaDeJornada()));
        maturidade.setSaude(multiplicarPor100(maturidade.getSaude()));
        maturidade.setMetricas4(multiplicarPor100(maturidade.getMetricas4()));
        maturidade.setCapacidadeDora(multiplicarPor100(maturidade.getCapacidadeDora()));

        if (existente.isPresent()) {
            maturidade.setNumero(existente.get().getNumero() + 1);
        } else {
            maturidade.setNumero(1);
        }

        MaturidadeEntity maturidadeSalva = this.repository.save(maturidade);
        return this.mapper.modelToDTO(maturidadeSalva);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }
    
    // metodo privado para multiplicar valores por 100
    private Double multiplicarPor100(Double valor){
        if(valor != null){
            return valor * 100;
        }
        return null;
       
    }
    

    public Map<String, Object> getLatestMaturidadeByEsteiraId(Long esteiraId) {
        return repository.findTopByEsteiraIdOrderByDataHoraDesc(esteiraId).findFirst().orElse(null);
    }

    
public List<MaturidadeDto> getMaturidadeByEsteiraId(Long esteiraId) {
       List<MaturidadeEntity> maturidades = repository.findMaturidadeByEsteiraId(esteiraId);
       return maturidades.stream().map(mapper::modelToDTO).toList();
}

   /*  public MaturidadeDto salvarMaturidade(MaturidadeDto maturidadeDto) {
        MaturidadeEntity novaMaturidade = this.mapper.dtoToModel(maturidadeDto);
    
        Optional<MaturidadeEntity> maturidadeExistente = this.repository.findTopByEsteiraIdOrderByNumeroDesc(novaMaturidade.getEsteira().getId());
    
        if (maturidadeExistente.isPresent() && novaMaturidade.getId() > maturidadeExistente.get().getId()) {
            MaturidadeEntity maturidadeSalva = this.repository.save(novaMaturidade);
            return this.mapper.modelToDTO(maturidadeSalva);
        } else {
            throw new DashNotFoundException();
        }
    }*/

}