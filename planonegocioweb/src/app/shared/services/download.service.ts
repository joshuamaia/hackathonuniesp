import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';
import { UtilService } from 'src/app/shared/services/util.service';

import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { saveAs } from 'file-saver';

@Injectable({
  providedIn: 'root',
})
export class DownloadService {
  protected http: HttpClient;
  protected apiPath: string;

  constructor(protected injector: Injector) {
    this.apiPath = `${UtilService.BASE_URL}`;
    this.http = injector.get(HttpClient);
  }

  downloadFile(data: any, filename: string, type: string) {
    const blob = new Blob([data], { type: type });
    // const url = window.URL.createObjectURL(blob);
    // window.open(url);
    saveAs(blob, filename);
  }

  downloadReportPdf(
    nameReport: string,
    topicId?: number,
    templateId?: number
  ): Observable<any> {
    let headers = new HttpHeaders();
    let params = new HttpParams();

    headers = headers.set('Accept', 'application/pdf');
    if (topicId) {
      params = params.set('topicId', topicId.toString());
    }
    if (templateId) {
      params = params.set('templateId', templateId.toString());
    }

    return this.http.get(
      `${this.apiPath}/reportgpt-reports/pdf/${nameReport}`,
      {
        headers: headers,
        responseType: 'blob',
        params: params,
      }
    );
  }

  downloadReportOdt(
    nameReport: string,
    topicId?: number,
    templateId?: number
  ): Observable<any> {
    let headers = new HttpHeaders();
    let params = new HttpParams();

    headers = headers.set('Accept', 'application/vnd.oasis.opendocument.text');
    if (topicId) {
      params = params.set('topicId', topicId.toString());
    }
    if (templateId) {
      params = params.set('templateId', templateId.toString());
    }

    return this.http.get(
      `${this.apiPath}/reportgpt-reports/odt/${nameReport}`,
      {
        headers: headers,
        responseType: 'blob',
        params: params,
      }
    );
  }
}
