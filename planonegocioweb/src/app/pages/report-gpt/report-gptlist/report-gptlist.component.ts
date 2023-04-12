import { ReportGPTService } from './../shared/report-gpt.service';
import { Component, OnInit } from '@angular/core';
import { BaseResourceListComponent } from 'src/app/shared/components/base-resource-list/base-resource-list.component';
import { ReportGPT } from '../shared/report-gpt.model';
import { DownloadService } from 'src/app/shared/services/download.service';

@Component({
  selector: 'app-report-gptlist',
  templateUrl: './report-gptlist.component.html',
  styleUrls: ['./report-gptlist.component.css'],
})
export class ReportGPTListComponent
  extends BaseResourceListComponent<ReportGPT>
  implements OnInit
{
  description: string | undefined;
  topicDescription: string | undefined;
  templateTitle: string | undefined;
  reportGPTSelected: ReportGPT = {};

  constructor(
    private reportGPTService: ReportGPTService,
    private downloadService: DownloadService
  ) {
    super(reportGPTService);
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  selectReportGPT(reportGPT: ReportGPT) {
    this.reportGPTSelected = reportGPT;
  }

  filter() {
    this.subscribeGeneral.add(
      this.reportGPTService
        .getAllFilter(
          this.pageNumber,
          this.size,
          this.description,
          this.topicDescription,
          this.templateTitle
        )
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }

  override paginate(event: any) {
    this.subscribeGeneral.add(
      this.reportGPTService
        .getAllFilter(
          event.page,
          event.rows,
          this.description,
          this.topicDescription,
          this.templateTitle
        )
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }

  downloadReportPdf(
    topicID: number | undefined,
    templateId: number | undefined
  ) {
    this.downloadService
      .downloadReportPdf('reportgpt', topicID, templateId)
      .subscribe((response) => {
        this.downloadService.downloadFile(
          response,
          'reportgpt.pdf',
          'application/pdf'
        );
      });
  }

  downloadReportOdt(
    topicID: number | undefined,
    templateId: number | undefined
  ) {
    this.downloadService
      .downloadReportOdt('reportgpt', topicID, templateId)
      .subscribe((response) => {
        this.downloadService.downloadFile(
          response,
          'reportgpt.odt',
          'application/vnd.oasis.opendocument.text'
        );
      });
  }
}
