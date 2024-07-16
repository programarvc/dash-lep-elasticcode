package com.br.agilize.dash.service.dashboardService;
import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.EsteiraDeDesenvolvimentoEntity;

import java.util.*;

import com.br.agilize.dash.repository.ColaboradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.br.agilize.dash.exception.DashNotFoundException;
import com.br.agilize.dash.mapper.dashboardMapper.UserEsteiraMapper;
import com.br.agilize.dash.mapper.dashboardMapper.UserMapper;
import com.br.agilize.dash.model.dto.dashboardDto.UserDto;
import com.br.agilize.dash.model.dto.dashboardDto.UserEsteiraDto;
import com.br.agilize.dash.model.entity.dashboardEntity.UserEntity;
import com.br.agilize.dash.model.entity.dashboardEntity.UserEsteiraEntity;
import com.br.agilize.dash.repository.dashboardRepository.UserEsteiraRepository;
import com.br.agilize.dash.repository.dashboardRepository.UserRepository;
import com.br.agilize.dash.service.ServiceCrudBase;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService extends ServiceCrudBase<UserDto> {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserEsteiraRepository userEsteiraRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserEsteiraMapper userEsteiraMapper;

    @Override
    public UserDto obterPorId(Long id) {
        return this.mapper.modelToDTO(this.repository.findById(id)
                .orElseThrow(DashNotFoundException::new));
    }

    @Override
    public List<UserDto> obterTodos() {
        List<UserEntity> Users = this.repository.findAll();

        return Users.stream().map(this.mapper::modelToDTO).toList();
    }

    @Override
    public UserDto salvar(UserDto payload) {
        Optional<UserEntity> existingUser = this.repository.findByNome(payload.getNome());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        UserEntity userEntity = this.mapper.dtoToModel(payload);

        // Verifica se colaborador está presente e tem um ID válido
        if (userEntity.getColaborador() != null && userEntity.getColaborador().getId() != null) {
            ColaboradorEntity colaborador = colaboradorRepository.findById(userEntity.getColaborador().getId())
                    .orElseThrow(() -> new RuntimeException("Colaborador não encontrado"));
            userEntity.setColaborador(colaborador);
        } else {
            userEntity.setColaborador(null); // Define colaborador como null se não for válido
        }

        UserEntity userSalvo = this.repository.save(userEntity);
        return this.mapper.modelToDTO(userSalvo);
    }

    @Override
    public void excluirPorId(Long id) {
        this.repository.deleteById(id);
    }

    public UserEsteiraDto salvarUserEsteira(UserEsteiraDto payload) {
        UserEsteiraEntity entity = this.userEsteiraMapper.dtoToModel(payload);
        if (entity == null) {
            throw new IllegalArgumentException("Payload cannot be mapped to entity");
        }
        return userEsteiraMapper.modelToDTO(this.userEsteiraRepository.save(entity));
    }

    public List<Map<String, String>> buscarNomesDosUsersPorEsteiraId(Long esteiraId) {
        return this.userEsteiraRepository.findUserAndNamesByEsteiraId(esteiraId);
    }

    public List<Map<String, Object>> getEsteiraIdAndUsername() {
        return userEsteiraRepository.findEsteiraIdAndUsername();
    }

    public List<EsteiraDeDesenvolvimentoEntity> buscarEsteirasPorUserId(Long userId) {
        return this.userEsteiraRepository.findEsteirasByUserId(userId);
    }

    public Optional<Long> buscarIdPorNome(String nome) {
        return this.repository.findIdByNome(nome);
    }

    public Long findIdByEsteiraIdAndUsernameId(Long esteiraId, Long usernameId) {
        return this.userEsteiraRepository.findIdByEsteiraIdAndUsernameId(esteiraId, usernameId);
    }
}