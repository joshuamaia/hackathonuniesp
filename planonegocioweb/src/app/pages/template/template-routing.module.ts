import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TemplateFormComponent } from './template-form/template-form.component';
import { TemplateListComponent } from './template-list/template-list.component';

const routes: Routes = [
  { path: '', component: TemplateListComponent },
  { path: 'new', component: TemplateFormComponent },
  { path: ':id/edit', component: TemplateFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TemplateRoutingModule {}
