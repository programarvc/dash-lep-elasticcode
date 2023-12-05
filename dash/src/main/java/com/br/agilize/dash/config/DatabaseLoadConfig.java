package com.br.agilize.dash.config;

import com.br.agilize.dash.model.entity.*;
import com.br.agilize.dash.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Configuration
public class DatabaseLoadConfig {
    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private HabilidadeRepository habilidadeRepository;

    @Autowired
    private CompetenciaRepository competenciaRepository;

    @Autowired
    private AcoesRepository acoesRepository;

    @Autowired
    private CompetenciaColaboradorRepository competenciaColaboradorRepository;

    @Autowired
    private AcoesColaboradorRepository acoesColaboradorRepository;

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            List<HabilitadeEntity> habilitadeEntities = cargaInicialHabilidades();
            List<CompetenciaEntity> competenciaEntities = cargaInicialCompetencia();
            List<AcoesEntity> acoesEntities = cargaInicialAcoes();

            ColaboradorEntity colaborador = new ColaboradorEntity();
            colaborador.setNome("Allyson Santana");
            colaborador.setEmail("allyson.Santana@gmail.com");
            colaborador.setGithub("allyson.Santana");
            colaborador.setGithub("allyson.Santana");
            colaborador.setHabilidades(habilitadeEntities);

            ColaboradorEntity colaboradorSalvo = colaboradorRepository.save(colaborador);

            vincularCompetencia(colaboradorSalvo, competenciaEntities);
            vincularAcoes(colaboradorSalvo, acoesEntities);
        };
    }

    private void vincularCompetencia(ColaboradorEntity colaboradorSalvo, List<CompetenciaEntity> competenciaEntities) {
        competenciaEntities.forEach(competenciaEntity -> {
            CompetenciaColaboradorEntity competenciaColaboradorEntity = new CompetenciaColaboradorEntity();
            competenciaColaboradorEntity.setColaborador(colaboradorSalvo);
            competenciaColaboradorEntity.setCompetencia(competenciaEntity);
            competenciaColaboradorEntity.setProgresso(new Random().nextInt(10)+1);
            competenciaColaboradorRepository.save(competenciaColaboradorEntity);
        });
    }

    private void vincularAcoes(ColaboradorEntity colaboradorSalvo, List<AcoesEntity> acoesEntities) {
        acoesEntities.forEach(item -> {
            AcoesColaboradorEntity acoesColaboradorEntity = new AcoesColaboradorEntity();
            acoesColaboradorEntity.setColaborador(colaboradorSalvo);
            acoesColaboradorEntity.setAcao(item);
            acoesColaboradorEntity.setProgresso((new Random().nextInt(10)+1)*10);
            acoesColaboradorRepository.save(acoesColaboradorEntity);
        });
    }

    private List<HabilitadeEntity> cargaInicialHabilidades() {
        return Arrays.asList("Full Stack",
                "Angular",
                "React",
                "NodeJs",
                "Python").stream().map(habilidade -> {
            HabilitadeEntity habilidadeEntity = new HabilitadeEntity();
            habilidadeEntity.setNome(habilidade);
            return habilidadeRepository.save(habilidadeEntity);
        }).collect(Collectors.toList());
    }

    private List<CompetenciaEntity> cargaInicialCompetencia() {
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
    }

    private List<AcoesEntity> cargaInicialAcoes() {
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
    }
}
