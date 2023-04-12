import { Component, Injector, OnDestroy, OnInit } from '@angular/core';
import { Validators } from '@angular/forms';
import { BaseResourceFormComponent } from 'src/app/shared/components/base-resource-form/base-resource-form.component';
import { Topic } from '../shared/topic.model';
import { TopicService } from '../shared/topic.service';

@Component({
  selector: 'app-topic-form',
  templateUrl: './topic-form.component.html',
  styleUrls: ['./topic-form.component.css'],
})
export class TopicFormComponent
  extends BaseResourceFormComponent<Topic>
  implements OnInit, OnDestroy
{
  constructor(
    protected topicService: TopicService,
    override injector: Injector
  ) {
    super(injector, new Topic(), topicService, Topic.fromJson);
  }

  protected buildResourceForm() {
    this.resourceForm = this.formBuilder.group({
      id: [null],
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
    return 'Adicionar novo tópico';
  }

  override editionPageTitle(): string {
    const TopicName = this.resource.description || '';
    return 'Editando tópico: ' + TopicName;
  }
}
