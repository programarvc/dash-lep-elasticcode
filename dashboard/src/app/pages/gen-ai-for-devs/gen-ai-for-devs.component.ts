import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gen-ai-for-devs',
  templateUrl: './gen-ai-for-devs.component.html',
  styleUrls: ['./gen-ai-for-devs.component.sass']
})
export class GenAiForDevsComponent implements OnInit {

  public genaiMenu = [
    { id: 1, title: 'Manipulação de Repositório', icon: 'assets/images/repository_manipulation.png', status: 'disabled' },
    { id: 2, title: 'Build', icon: 'assets/images/build.png', status: 'disabled' },
    { id: 3, title: 'Code Review', icon: 'assets/images/code_review.png', status: 'disabled' },
    { id: 4, title: 'Bug Tracker', icon: 'assets/images/bug_tracker.png', status: 'disabled' },
    { id: 5, title: 'QA', icon: 'assets/images/qa.png', status: 'disabled' },
    { id: 6, title: 'Geração de Código', icon: 'assets/images/code_generation.png', status: '' },
    { id: 7, title: 'Inspeção de Segurança', icon: 'assets/images/security_inspect.png', status: 'disabled' },
    { id: 8, title: 'Geração de SQL', icon: 'assets/images/sql_generation.png', status: 'disabled' },
  ];

  public selectedButtonId: number | null = null;
  public showCard = false;
  public generatedCode: string | null = null;

  constructor() { }

  ngOnInit(): void { }

  genAiButtonSelected(buttonId: number): void {
    this.selectedButtonId = buttonId;
    if (buttonId === 6) {
      this.showCard = true;
      this.generatedCode = null;
    }
  }

  generateCode(
    entityName: string,
    packageName: string,
    additionalPackages: string,
    dependencies: string,
    additionalComments: string,
    stackType: string
  ): void {
    this.generatedCode = `
package ${packageName}.controller;

import ${packageName}.model.entity.${entityName};
import ${packageName}.model.dto.${entityName}DTO;
import ${packageName}.model.response.Response;
import ${packageName}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Pacotes adicionais
${additionalPackages.split(',').map(pkg => `import ${pkg.trim()};`).join('\n')}

@RestController
@RequestMapping("/api/${entityName.toLowerCase()}s")
public class ${entityName}Controller {

    @Autowired
    private ${entityName}Service ${entityName.toLowerCase()}Service;

    @PostMapping
    public ResponseEntity<Response<${entityName}>> create(@RequestBody ${entityName}DTO ${entityName.toLowerCase()}DTO) {
        ${entityName} ${entityName.toLowerCase()} = ${entityName.toLowerCase()}Service.create(${entityName.toLowerCase()}DTO);
        return ResponseEntity.ok(new Response<>(${entityName.toLowerCase()}));
    }

    @GetMapping
    public ResponseEntity<Response<List<${entityName}>>> getAll() {
        List<${entityName}> ${entityName.toLowerCase()}List = ${entityName.toLowerCase()}Service.getAll();
        return ResponseEntity.ok(new Response<>(${entityName.toLowerCase()}List));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<${entityName}>> getById(@PathVariable Long id) {
        ${entityName} ${entityName.toLowerCase()} = ${entityName.toLowerCase()}Service.getById(id);
        return ResponseEntity.ok(new Response<>(${entityName.toLowerCase()}));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<${entityName}>> update(@PathVariable Long id, @RequestBody ${entityName}DTO ${entityName.toLowerCase()}DTO) {
        ${entityName} ${entityName.toLowerCase()} = ${entityName.toLowerCase()}Service.update(id, ${entityName.toLowerCase()}DTO);
        return ResponseEntity.ok(new Response<>(${entityName.toLowerCase()}));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Void>> delete(@PathVariable Long id) {
        ${entityName.toLowerCase()}Service.delete(id);
        return ResponseEntity.ok(new Response<>(null));
    }
}

// Comentários adicionais
// ${additionalComments}

// Dependências
// ${dependencies}

// Stack: ${stackType}
    `;
  }
}
