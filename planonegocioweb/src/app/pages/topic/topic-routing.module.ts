import { TopicFormComponent } from './topic-form/topic-form.component';
import { TopicListComponent } from './topic-list/topic-list.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', component: TopicListComponent },
  { path: 'new', component: TopicFormComponent },
  { path: ':id/edit', component: TopicFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TopicRoutingModule {}
