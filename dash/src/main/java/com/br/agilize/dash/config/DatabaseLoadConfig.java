package com.br.agilize.dash.config;

import com.br.agilize.dash.model.entity.*;
import com.br.agilize.dash.repository.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class DatabaseLoadConfig {
   
    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private AcoesRepository acoesRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {

            List<HabilidadeEntity> habilidadeEntities = cargaInicialHabilidades();
            List<CompetenciaEntity> competenciaEntities = cargaInicialCompetencia();
            List<AcoesEntity> acoesEntities = cargaInicialAcoes();
            List<EmpresaEntity> empresaEntities = cargaInicialEmpresas();
            
        };
    }

    private List<HabilidadeEntity> cargaInicialHabilidades() {
        List<HabilidadeEntity> habilidadesSalvos = this.habilidadeRepository.findAll();
        if (habilidadesSalvos == null ||  habilidadesSalvos.isEmpty()) {
            return Arrays.asList("Full Stack",
                    "Back End",
                    "Front End",
                    "Spring Boot",
                    "Angular",
                    "React",
                    "NodeJs",
                    "Python",
                    "Java",
                    "JavaScript",
                    "C#").stream().map(habilidade -> {
                        HabilidadeEntity habilidadeEntity = new HabilidadeEntity();
                        habilidadeEntity.setNome(habilidade);
                        return habilidadeRepository.save(habilidadeEntity);
                    }).collect(Collectors.toList());
        } else {
            return habilidadesSalvos;
        }
    }

    private List<CompetenciaEntity> cargaInicialCompetencia() {
        List<CompetenciaEntity> competenciasSalvos = this.competenciaRepository.findAll();
        if (competenciasSalvos  == null || competenciasSalvos.isEmpty()) {

            return Arrays.asList("Alavancar/reutilizar código",
                    "Técnica",
                    "Testes com codificação",
                    "Experiência",
                    "Treinar/aprender",
                    "Usar novos métodos e ferramentas",
                    "Planejar e estimar",
                    "Usar protótipos",
                    "Comunicação",
                    "Desejo por contribuir",
                    "Foco no problema e não na solução",
                    "Pró-atividade",
                    "Ação",
                    "Thinking",
                    "Design",
                    "Responder à pressão",
                    "Interpretação de texto e requisito",
                    "Pedir ajuda",
                    "Trabalho em equipe",
                    "Contrargumentação").stream().map(item -> {
                        CompetenciaEntity entity = new CompetenciaEntity();
                        entity.setNome(item);
                        return competenciaRepository.save(entity);
                    }).collect(Collectors.toList());
        } else {
            return competenciasSalvos;
        }

    }

    private List<AcoesEntity> cargaInicialAcoes() {
        List<AcoesEntity> acoesSalvos = this.acoesRepository.findAll();
        if (acoesSalvos == null || acoesSalvos.isEmpty()) {

            return Arrays.asList("Recuperar códigos da Web e do próprio projeto",
                    "Estudar a tecnologia",
                    "Testar durante o desenvolvimento do software",
                    "Avaliar curriculo para entender se há experiência similar ao Stack de tecnologias e FIT cultural com as empresas",
                    "Ler documentação de API, buscar soluções em Foruns, realizar experimentos e estudos acessorios para resolução de problemas",
                    "Se atualizar",
                    "Avaliar antes do produto final",
                    "Usar técnicas e ferramentas para explicar o design sem formalismos",
                    "Priorizar a solução sobre a fonte da solução",
                    "Tomar iniciativas para começar as coisas",
                    "Senso de urgência e resultados",
                    "Estruturar o um problema direcionando para a solução",
                    "Usar técnicas de representação visual",
                    "Saber o que sacrificar quando o prazo está acabando",
                    "Procurar assistência de terceiros no aprendizado, pesquisa, debug, resultados etc",
                    "Valorizar sinergia com os demais mesmo sacificando resultados pessoais",
                    "Sustentar opinião ainda que discorde do gestor").stream().map(item -> {
                        AcoesEntity entity = new AcoesEntity();
                        entity.setNome(item);
                        return acoesRepository.save(entity);
                    }).collect(Collectors.toList());
        } else {
            return acoesSalvos;
        }
    }

    private List<EmpresaEntity> cargaInicialEmpresas() {
        List<EmpresaEntity> empresasSalvos = this.empresaRepository.findAll();
        if (empresasSalvos == null || empresasSalvos.isEmpty()) {
            return Arrays.asList("Elastic Code",
                    "Programar Com Vc",
                    "Mutant",
                    "ESims",
                    "A5").stream().map(item -> {
                        EmpresaEntity entity = new EmpresaEntity();
                        entity.setNome(item);
                        return empresaRepository.save(entity);
                    }).collect(Collectors.toList());
        } else {
            return empresasSalvos;
        }
    }

}