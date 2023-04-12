import { ReportGPT } from './report-gpt.model';
import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';
import { UtilService } from 'src/app/shared/services/util.service';
import { BaseResourceService } from 'src/app/shared/services/base-resource.service';

import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ReportGPTService extends BaseResourceService<ReportGPT> {
  constructor(override injector: Injector) {
    super(`${UtilService.BASE_URL}/reportgpts`, injector, ReportGPT.fromJson);
  }

  getAllFilter(
    page: number,
    size: number,
    description?: string,
    topicDescription?: string,
    templateTitle?: string
  ): Observable<any> {
    const url = `${this.apiPath}/filter`;
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    if (description) {
      params = params.set('description', description);
    }
    if (topicDescription) {
      params = params.set('topicDescription', topicDescription);
    }
    if (templateTitle) {
      params = params.set('templateTitle', templateTitle);
    }
    return this.http.get<any>(url, {
      params: params,
    });
  }
}
