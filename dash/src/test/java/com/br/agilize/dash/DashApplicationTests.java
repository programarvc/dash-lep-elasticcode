package com.br.agilize.dash;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.agilize.dash.model.entity.ColaboradorEntity;
import com.br.agilize.dash.model.entity.HabilitadeEntity;
import com.br.agilize.dash.repository.ColaboradorRepository;

@SpringBootTest
class DashApplicationTests {
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Test
	void contextLoads() {
		HabilitadeEntity habilidades = new HabilitadeEntity();
		ColaboradorEntity leandro = new ColaboradorEntity();
		leandro.setNome("Leandro");
		leandro.setEmail("leandro@email.com");
		leandro.setGithub("Leolucas12");
		ColaboradorEntity colaboradorSalvo = colaboradorRepository.save(leandro);

		colaboradorRepository.getReferenceById(colaboradorSalvo.getId());
		assertEquals(leandro, colaboradorSalvo, "ok");
	}

}
