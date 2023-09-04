package com.br.agilize.dash;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.br.agilize.dash.repository.ColaboradorRepository;

@SpringBootTest
class DashApplicationTests {
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Test
	void contextLoads() {
	}

}
