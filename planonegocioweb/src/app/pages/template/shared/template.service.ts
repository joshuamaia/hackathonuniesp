import { Template } from './template.model';
import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';
import { UtilService } from 'src/app/shared/services/util.service';
import { BaseResourceService } from 'src/app/shared/services/base-resource.service';

import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TemplateService extends BaseResourceService<Template> {
  constructor(override injector: Injector) {
    super(`${UtilService.BASE_URL}/templates`, injector, Template.fromJson);
  }

  getAllFilter(
    page: number,
    size: number,
    title?: string,
    description?: string
  ): Observable<any> {
    const url = `${this.apiPath}/filter`;
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    if (title) {
      params = params.set('title', title);
    }
    if (description) {
      params = params.set('description', description);
    }
    return this.http.get<any>(url, {
      params: params,
    });
  }
}
