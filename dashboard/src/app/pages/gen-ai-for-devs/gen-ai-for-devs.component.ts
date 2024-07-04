import { Component, OnInit, HostListener } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { CognitoService } from 'src/app/cognito.service';
import { UserService } from 'src/services/usuario/usuario.service';
import { PromptService } from 'src/services/prompts/prompts.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { PromptHistoryModalComponent } from 'src/app/components/prompt-history-modal/prompt-history-modal.component';

interface GenAiMenuItem {
  id: number;
  title: string;
  icon: string;
  status: string;
}

interface CodeType {
  id: number;
  tipo_de_codigo: string;
  stack: string;
  disabled?: boolean;
}

interface contGenAiPrompts {
  id: number;
  contador: number;
  esteiraId: number;
}

interface JiraAtividades {
  id: number;
  esteiraId: number;
  atividade: string;
}

@Component({
  selector: 'app-gen-ai-for-devs',
  templateUrl: './gen-ai-for-devs.component.html',
  styleUrls: ['./gen-ai-for-devs.component.sass']
})
export class GenAiForDevsComponent implements OnInit {
  public stackType: string | null = null;
  public stackValue: string | null = null;
  public codeType: string | null = null;
  public codeTypeValue: string | null = null;
  public entity: string | null = null;
  public entityValue: string | null = null;
  public table: string | null = null;
  public tableValue: string | null = null;
  public generatedCodeValue: string | null = null;
  public isFormValid: boolean = false;

  public esteiraSelecionada: any = [];
  public isEsteiraSelected: boolean = false;
  public esteiraSelecionadaId: number = 0;

  public username: string = '';
  public userId: number = 0;
  public userEsteiraId: number = 0;
  public userEsteiraValue: number = 0;

  public contElasticPrompts: number = 0;

  public genaiMenu: GenAiMenuItem[] = [
    { id: 1, title: 'Manipulação de Repositório', icon: 'assets/images/repository_manipulation.png', status: 'disabled' },
    { id: 2, title: 'Build', icon: 'assets/images/build.png', status: 'disabled' },
    { id: 3, title: 'Code Review', icon: 'assets/images/code_review.png', status: 'disabled' },
    { id: 4, title: 'Bug Tracker', icon: 'assets/images/bug_tracker.png', status: 'disabled' },
    { id: 5, title: 'QA', icon: 'assets/images/qa.png', status: 'disabled' },
    { id: 6, title: 'Geração de Código', icon: 'assets/images/code_generation.png', status: '' },
    { id: 7, title: 'Inspeção de Segurança', icon: 'assets/images/security_inspect.png', status: 'disabled' },
    { id: 8, title: 'Geração de SQL', icon: 'assets/images/sql_generation.png', status: 'disabled' },
  ];

  public jiraAtividades: JiraAtividades[] = [
    { id: 1, esteiraId: 1, atividade: 'Desenvolver API Rest para a entidade Cliente' },
    { id: 2, esteiraId: 1, atividade: 'Desenvolver API Rest para a entidade Produto' },
    { id: 3, esteiraId: 2, atividade: 'Desenvolver API Rest para a entidade Pedido' },
  ]

  public codeTypes: CodeType[] = [
    { id: 1, tipo_de_codigo: 'CRUD', stack: 'backend' },
    { id: 2, tipo_de_codigo: 'API Rest', stack: 'backend' },
    { id: 3, tipo_de_codigo: 'Algoritmo de Pesquisa e Ordenação', stack: 'backend' },
    { id: 4, tipo_de_codigo: 'Código de Integração', stack: 'backend' },
    { id: 5, tipo_de_codigo: 'Código de Validação', stack: 'backend' },
    { id: 6, tipo_de_codigo: 'Código de Autenticação e Autorização', stack: 'backend' },
    { id: 7, tipo_de_codigo: 'Código de Interface de Usuário', stack: 'frontend', disabled: true },
    { id: 8, tipo_de_codigo: 'Código de Manipulação de Arquivos', stack: 'backend' },
    { id: 9, tipo_de_codigo: 'Código de Testes Automatizados', stack: 'backend' },
    { id: 10, tipo_de_codigo: 'Código de Manipulação de Banco de Dados', stack: 'backend' },
    { id: 11, tipo_de_codigo: 'Código de Processamento de Dados', stack: 'backend' },
    { id: 12, tipo_de_codigo: 'Código de Redes e Comunicação', stack: 'backend' },
    { id: 13, tipo_de_codigo: 'Código de Estilização', stack: 'frontend', disabled: true },
    { id: 14, tipo_de_codigo: 'Código de Animação', stack: 'frontend', disabled: true },
    { id: 15, tipo_de_codigo: 'Código de Gerenciamento de Estado', stack: 'frontend', disabled: true },
    { id: 16, tipo_de_codigo: 'Código de Interação com o Usuário', stack: 'frontend', disabled: true },
    { id: 17, tipo_de_codigo: 'Código de Renderização', stack: 'frontend', disabled: true },
    { id: 18, tipo_de_codigo: 'Código de Responsividade', stack: 'frontend', disabled: true },
    { id: 19, tipo_de_codigo: 'Código de Manipulação do DOM', stack: 'frontend', disabled: true },
    { id: 20, tipo_de_codigo: 'Código de Acessibilidade', stack: 'frontend', disabled: true },
    { id: 21, tipo_de_codigo: 'Código de Otimização de Performance', stack: 'frontend', disabled: true }
  ];

  public selectedButtonId: number | null = null;
  public showCard = false;
  public generatedCode: string | null = null;
  public filteredCodeTypes: CodeType[] = [];
  public suggestionsVisible = false;
  private selectedStack: string | null = null;
  private inputTimeout: any;

  public promptsByUserEsteiraId: any = [];

  public showModalContent: boolean = false;

  constructor(
    private router: Router,
    private cognitoService: CognitoService,
    private userService: UserService,
    private promptsService: PromptService,
    private modalService: NgbModal,
  ) { }

  ngOnInit(): void {

    const savedEsteira = localStorage.getItem('selectedEsteira');
    if (savedEsteira) {
      this.esteiraSelecionada = JSON.parse(savedEsteira);
      this.isEsteiraSelected = true;
      this.esteiraSelecionadaId = this.esteiraSelecionada.id;

      this.router.navigate([`elastic-devs-ai/${this.esteiraSelecionada.id}`]);
    }


    //Obtém username do usuário logado
    this.cognitoService.getLoggedInUsername().then((username: any) => {
      this.username = username;

      //Obtém id do usuário por username
      this.userService.getUsuarioIdPorUsername(this.username).subscribe((userId: any) => {
        this.userId = userId;

        //Obtém userEsteiraId por esteiraId e userId
        this.userService.getUserEsteiraIdPorEsteiraIdAndUsuarioId(this.esteiraSelecionadaId, this.userId).subscribe((userEsteiraId: any) => {
          this.userEsteiraId = userEsteiraId;

        //Obtém prompts por userEsteiraId
        this.promptsService.getPromptsHistoryByUserEsteiraId(this.userEsteiraId).subscribe((prompts: any) => {
          this.promptsByUserEsteiraId = prompts;
        })

        //Obtém contagem de prompts por userEsteiraId
        this.promptsService.getContPromptsByUserEsteiraId(this.userEsteiraId).subscribe((contPrompts: any) => {
          this.contElasticPrompts = contPrompts;
        })
        });
      });
    });
  }
  
  genAiButtonSelected(buttonId: number): void {
    this.selectedButtonId = buttonId;
    if (buttonId === 6) {
      this.showCard = true;
      this.generatedCode = null;
    }
  }

  filterCodeTypes(): void {
    const selectedStack = this.stackType ? this.stackType.toLowerCase() : null;
    this.selectedStack = selectedStack;
    this.filteredCodeTypes = [];
    this.clearGeneratedCode();
    this.checkFormValidity();
  }

  updateSuggestions(query: string): void {
    clearTimeout(this.inputTimeout);
    this.inputTimeout = setTimeout(() => {
      if (this.selectedStack) {
        this.filteredCodeTypes = this.codeTypes.filter(codeType =>
          codeType.stack === this.selectedStack &&
          (this.selectedStack === 'backend' ? codeType.tipo_de_codigo === 'API Rest' : true) &&
          codeType.tipo_de_codigo.toLowerCase().includes(query.toLowerCase())
        ).slice(0, 3);
        this.suggestionsVisible = this.filteredCodeTypes.length > 0;
      }
    }, 500); // Delay de 0,5 segundos para reduzir o tempo de espera
    this.clearGeneratedCode();
  }

  selectSuggestion(suggestion: CodeType): void {
    this.codeType = suggestion.tipo_de_codigo;
    this.filteredCodeTypes = [];
    this.suggestionsVisible = false;
    this.clearGeneratedCode();
    this.checkFormValidity();
  }

  generateCode(): void {
    const stackType = this.stackType;
    const codeType = this.codeType;
    const entity = this.entity;
    const inputTable = this.table;

    const existingCodeType = this.codeTypes.find(type => type.tipo_de_codigo.toLowerCase() === codeType?.toLowerCase() && type.stack === stackType?.toLowerCase());

    if (!existingCodeType && codeType && stackType) {
      const newCodeType: CodeType = {
        id: this.codeTypes.length + 1,
        tipo_de_codigo: this.capitalize(codeType),
        stack: stackType.toLowerCase()
      };
      this.codeTypes.push(newCodeType);
    }

    this.generatedCode = `
Prompt para geração de código:

aja como um copiloto de desenvolvimento, sua função é gerar o código em java para uma api-rest já existente para a entidade ${entity}.
\n`;

    if (inputTable) {
      this.generatedCode += `O nome da tabela no banco de dados é ${inputTable} no modelo relacional.\n`;
    }

    this.generatedCode += `
Esse é o package dele: com.br.agilize.dash

aqui segue alguns packages dentro do package principal:

- com.br.agilize.dash.controller
- com.br.agilize.dash.model.entity
- com.br.agilize.dash.model.dto
- com.br.agilize.dash.model.enums
- com.br.agilize.dash.model.response

essas são as dependências atuais do projeto:

- implementation 'org.springframework.boot:spring-boot-starter-web'
- implementation 'org.springframework.boot:spring-boot-starter-actuator'
- implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
- implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.0.3'
- implementation 'org.mapstruct:mapstruct:1.5.5.Final'
- implementation 'com.amazonaws.serverless:aws-serverless-java-container-springboot3:2.0.0-M2'
- implementation 'io.github.cdimascio:dotenv-java:3.0.0'
- implementation 'com.google.code.gson:gson:2.10.1'
- implementation 'software.amazon.awssdk:secretsmanager:2.21.15'
- implementation 'org.postgresql:postgresql'
- implementation 'io.github.crac.com.amazonaws:aws-lambda-java-runtime-interface-client:1.0.0'
- implementation group: 'org.apache.maven.plugins', name: 'maven-shade-plugin', version: '3.5.1'
- implementation 'software.amazon.awssdk:bom:2.23.3'
- implementation 'software.amazon.awssdk:cognitoidentityprovider:2.23.3'
- implementation 'com.graphql-java:graphql-java:14.0'
- implementation 'com.fasterxml.jackson.core:jackson-databind:2.13.0'
- implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.12.3'
- implementation 'org.apache.httpcomponents.client5:httpclient5:5.1.2'
- implementation 'jakarta.ws.rs:jakarta.ws.rs-api:3.1.0'

Como pode ver ele usa o jakarta persistence, então todas as entidades são gerenciadas pelo framework. Tente fornecer algo que funcione de ponta a ponta desde a API até a parte do banco de dados.
    `;

    this.registrarNovoPrompt();
  }

  copyToClipboard(): void {
    const textarea = document.createElement('textarea');
    textarea.value = this.generatedCode || '';
    document.body.appendChild(textarea);
    textarea.select();
    document.execCommand('copy');
    document.body.removeChild(textarea);
  }

  private capitalize(text: string): string {
    return text.replace(/\w\S*/g, (txt) => txt.charAt(0).toUpperCase() + txt.substr(1).toLowerCase());
  }

  @HostListener('document:click', ['$event'])
  handleClick(event: MouseEvent): void {
    const target = event.target as HTMLElement;
    if (this.codeType && target && !target.closest('.suggestions')) {
      this.suggestionsVisible = false;
    }
  }

  showSuggestions(): void {
    this.updateSuggestions(this.codeType || '');
    this.suggestionsVisible = this.filteredCodeTypes.length > 0;
  }

  hideSuggestions(): void {
    setTimeout(() => {
      this.suggestionsVisible = false;
    }, 200);
  }

  clearGeneratedCode(): void {
    this.generatedCode = null;
  }

  checkFormValidity(): void {
    this.isFormValid = !!(this.stackType && this.codeType && this.entity);
  }

  public registrarNovoPrompt(): void {
    this.entityValue = this.entity;
    this.tableValue = this.table;
    this.codeTypeValue = this.codeType;
    this.stackValue = this.stackType;
    this.generatedCodeValue = this.generatedCode;
    this.userEsteiraValue = this.userEsteiraId;

    const promptData = {
      entidade: this.entityValue,
      prompt: this.generatedCodeValue,
      stack: this.stackValue,
      tabela: this.tableValue,
      tipo_codigo: this.codeTypeValue,
      userEsteira: {
        id: this.userEsteiraValue
      }
    }

    this.promptsService.postPromptHistory(promptData).subscribe((response: any) => {
    }, (erro: any) => {
      console.log('Erro ao registrar prompt', erro);
    })    
  }

  open(content: any) {
    this.modalService.open(content)
  }
}