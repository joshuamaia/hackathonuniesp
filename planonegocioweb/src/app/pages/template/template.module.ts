import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TemplateRoutingModule } from './template-routing.module';
import { TemplateFormComponent } from './template-form/template-form.component';
import { TemplateListComponent } from './template-list/template-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { TutorialDescriptionComponent } from './tutorial-description/tutorial-description.component';

@NgModule({
  declarations: [
    TemplateFormComponent,
    TemplateListComponent,
    TutorialDescriptionComponent,
  ],
  imports: [CommonModule, TemplateRoutingModule, SharedModule],
})
export class TemplateModule {}
