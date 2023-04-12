import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'topics',
    loadChildren: () =>
      import('./pages/topic/topic.module').then((m) => m.TopicModule),
  },
  {
    path: 'reports',
    loadChildren: () =>
      import('./pages/report-gpt/report-gpt.module').then(
        (m) => m.ReportGPTModule
      ),
  },
  {
    path: 'templates',
    loadChildren: () =>
      import('./pages/template/template.module').then((m) => m.TemplateModule),
  },

  {
    path: '**',
    redirectTo: 'reports',
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
