import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { BaseResourceFormComponent } from 'src/app/shared/components/base-resource-form/base-resource-form.component';
import { Template } from '../shared/template.model';
import { TemplateService } from '../shared/template.service';

@Component({
  selector: 'app-template-form',
  templateUrl: './template-form.component.html',
  styleUrls: ['./template-form.component.css'],
})
export class TemplateFormComponent
  extends BaseResourceFormComponent<Template>
  implements OnInit, OnDestroy
{
  constructor(
    protected templateService: TemplateService,
    override injector: Injector
  ) {
    super(injector, new Template(), templateService, Template.fromJson);
  }

  protected buildResourceForm() {
    this.resourceForm = this.formBuilder.group({
      id: [null],
      title: [null, [Validators.required]],
      description: [null, [Validators.required]],
    });
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  override ngOnDestroy() {
    super.ngOnDestroy();
  }

  override creationPageTitle(): string {
    return 'Adicionando novo Template';
  }

  override editionPageTitle(): string {
    const templateName = this.resource.title || '';
    return 'Editando Template: ' + templateName;
  }
}
