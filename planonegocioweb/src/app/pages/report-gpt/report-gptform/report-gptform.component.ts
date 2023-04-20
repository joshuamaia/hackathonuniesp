import { Template } from './../../template/shared/template.model';
import { Topic } from './../../topic/shared/topic.model';
import { TemplateService } from './../../template/shared/template.service';
import { TopicService } from './../../topic/shared/topic.service';
import { ReportGPTService } from './../shared/report-gpt.service';
import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { ReportGPT } from '../shared/report-gpt.model';
import { BaseResourceFormComponent } from 'src/app/shared/components/base-resource-form/base-resource-form.component';
import { Validators } from '@angular/forms';

@Component({
  selector: 'app-report-gptform',
  templateUrl: './report-gptform.component.html',
  styleUrls: ['./report-gptform.component.css'],
})
export class ReportGPTFormComponent
  extends BaseResourceFormComponent<ReportGPT>
  implements OnInit, OnDestroy
{
  topics: Topic[] = [];
  templates: Template[] = [];

  constructor(
    protected reportGPTService: ReportGPTService,
    protected topicService: TopicService,
    protected templateService: TemplateService,
    override injector: Injector
  ) {
    super(injector, new ReportGPT(), reportGPTService, ReportGPT.fromJson);
  }

  protected buildResourceForm() {
    this.resourceForm = this.formBuilder.group({
      id: [null],
      topic: [null, [Validators.required]],
      template: [null, [Validators.required]],
      description: [null],
      abnt: [true],
    });
  }

  override ngOnInit(): void {
    super.ngOnInit();
    this.fillTopics();
    this.fillTemplates();
  }

  override ngOnDestroy() {
    super.ngOnDestroy();
  }

  haveDescription() {
    const descriptionControl = this.resourceForm.controls['description'].value;
    return descriptionControl ? true : false;
  }

  getQuestionChatGpt() {
    const topicControl: Topic = this.resourceForm.controls['topic'].value;
    const templateControl: Template =
      this.resourceForm.controls['template'].value;
    if (topicControl && templateControl && topicControl.description) {
      const abntControl: boolean = this.resourceForm.controls['abnt'].value;
      const abntText = abntControl ? 'De acordo com as regras da ABNT ' : '';
      const sumaryText = `Será solicitado Resumo das Ideias,
      Oportunidades de Negócios,
      Análise de Mercado,
      Proposta de Valor,
      Estratégia de Negócio,
      Desenvolvimento do Produto,
      Requisitos e Processos de Venda,
      Diretrizes de Operações,
      Finanças,
      Plano de Marketing e Conclusão `;
      return (
        abntText +
        sumaryText +
        templateControl.description?.replace(':topic', topicControl.description)
      );
    }
    return '';
  }

  hasQuestionChatGpt() {
    const topicControl = this.resourceForm.controls['topic'].value;
    const templateControl = this.resourceForm.controls['template'].value;
    return topicControl && templateControl ? true : false;
  }

  getDescription() {
    return this.resourceForm.controls['description'].value;
  }

  fillTopics() {
    this.topicService.getAll().subscribe((response) => {
      this.topics = response;
    });
  }

  fillTemplates() {
    this.templateService.getAll().subscribe((response) => {
      this.templates = response;
    });
  }

  override creationPageTitle(): string {
    return 'Adicionar novo relatório GPT';
  }

  override editionPageTitle(): string {
    const descriptionTopic = this.resource.topic?.description || '';
    const titleTemplate = this.resource.template?.title || '';
    return `Editando relatório GPT: ${titleTemplate} -  ${descriptionTopic}`;
  }
}
