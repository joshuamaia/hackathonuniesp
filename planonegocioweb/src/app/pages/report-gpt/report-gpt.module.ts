import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ReportGPTRoutingModule } from './report-gpt-routing.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReportGPTFormComponent } from './report-gptform/report-gptform.component';
import { ReportGPTListComponent } from './report-gptlist/report-gptlist.component';
import { ReportGPTDetailComponent } from './report-gptdetail/report-gptdetail.component';

@NgModule({
  declarations: [
    ReportGPTFormComponent,
    ReportGPTListComponent,
    ReportGPTDetailComponent
  ],
  imports: [CommonModule, ReportGPTRoutingModule, SharedModule],
})
export class ReportGPTModule {}
