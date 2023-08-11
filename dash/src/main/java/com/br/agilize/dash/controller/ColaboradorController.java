package com.br.agilize.dash.controller;

import com.br.agilize.dash.model.dto.ColaboradorDto;
import com.br.agilize.dash.service.ColaboradorService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "ColaboradorControoler", description = "Api para gerenciar os colaboradores")
@Controller
@RequestMapping("/colaborador")
public class ColaboradorController extends ControllerCrudBase<ColaboradorDto> {

    public ColaboradorController(@Autowired ColaboradorService service) {
        super(service);
    }


}
