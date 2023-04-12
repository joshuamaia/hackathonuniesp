import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ReportGPTFormComponent } from './report-gptform/report-gptform.component';
import { ReportGPTListComponent } from './report-gptlist/report-gptlist.component';

const routes: Routes = [
  { path: '', component: ReportGPTListComponent },
  { path: 'new', component: ReportGPTFormComponent },
  { path: ':id/edit', component: ReportGPTFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class ReportGPTRoutingModule {}
