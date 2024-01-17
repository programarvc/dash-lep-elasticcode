package com.br.agilize.dash.repository.dashboardRepository;

import java.util.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.agilize.dash.model.entity.dashboardEntity.UserEsteiraEntity;



@Repository
public interface UserEsteiraRepository extends JpaRepository<UserEsteiraEntity, Long> {

    List<UserEsteiraEntity> findByEsteiraId(Long esteiraId);

    @Query("SELECT new map(u.username.nome as nomeUser) FROM UserEsteiraEntity u WHERE u.esteira.id = :esteiraId")
    List<Map<String, String>> findUserAndNamesByEsteiraId(@Param("esteiraId") Long esteiraId);

    @Query("SELECT new map(u.esteira.id as esteiraId, u.username.nome as username) FROM UserEsteiraEntity u")
    List<Map<String, Object>> findEsteiraIdAndUsername(); 
}