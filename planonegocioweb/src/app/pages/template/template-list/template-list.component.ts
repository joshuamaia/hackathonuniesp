import { Component, OnInit } from '@angular/core';
import { BaseResourceListComponent } from 'src/app/shared/components/base-resource-list/base-resource-list.component';
import { Template } from '../shared/template.model';
import { TemplateService } from '../shared/template.service';

@Component({
  selector: 'app-template-list',
  templateUrl: './template-list.component.html',
  styleUrls: ['./template-list.component.css'],
})
export class TemplateListComponent
  extends BaseResourceListComponent<Template>
  implements OnInit
{
  title: string | undefined;
  description: string | undefined;

  constructor(private templateService: TemplateService) {
    super(templateService);
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  filter() {
    this.subscribeGeneral.add(
      this.templateService
        .getAllFilter(this.pageNumber, this.size, this.title, this.description)
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }

  override paginate(event: any) {
    this.subscribeGeneral.add(
      this.templateService
        .getAllFilter(event.page, event.rows, this.title, this.description)
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }
}
