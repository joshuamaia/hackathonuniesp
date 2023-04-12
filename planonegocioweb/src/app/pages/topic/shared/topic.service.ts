import { Topic } from './topic.model';
import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';
import { UtilService } from 'src/app/shared/services/util.service';
import { BaseResourceService } from 'src/app/shared/services/base-resource.service';

import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class TopicService extends BaseResourceService<Topic> {
  constructor(override injector: Injector) {
    super(`${UtilService.BASE_URL}/topics`, injector, Topic.fromJson);
  }

  getAllFilter(
    page: number,
    size: number,
    description?: string
  ): Observable<any> {
    const url = `${this.apiPath}/filter`;
    let params = new HttpParams();
    params = params.set('page', page.toString());
    params = params.set('size', size.toString());
    if (description) {
      params = params.set('description', description);
    }
    return this.http.get<any>(url, {
      params: params,
    });
  }
}
