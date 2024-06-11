package com.br.agilize.dash.repository.dashboardRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByNome(String nome);

    @Query("SELECT u.id FROM UserEntity u WHERE u.nome = :nome")
    Optional<Long> findIdByNome(@Param("nome") String nome);

}