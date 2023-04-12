import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TopicRoutingModule } from './topic-routing.module';
import { TopicFormComponent } from './topic-form/topic-form.component';
import { TopicListComponent } from './topic-list/topic-list.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [TopicFormComponent, TopicListComponent],
  imports: [CommonModule, TopicRoutingModule, SharedModule],
})
export class TopicModule {}
