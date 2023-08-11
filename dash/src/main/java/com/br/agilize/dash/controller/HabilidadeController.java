package com.br.agilize.dash.controller;

import com.br.agilize.dash.model.dto.HabilitadeDto;
import com.br.agilize.dash.service.HabilidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "HabilidadeController", description = "Api para gerenciar as habilidades")
@Controller
@RequestMapping("/habilidades")
public class HabilidadeController extends ControllerCrudBase<HabilitadeDto> {

    public HabilidadeController(@Autowired HabilidadeService service) {
        super(service);
    }


}
