import { BaseResourceModel } from '../../../shared/models/base-resource.model';

export class Topic extends BaseResourceModel {
  constructor(override id?: number, public description?: string) {
    super();
  }

  static fromJson(jsonData: any): Topic {
    return Object.assign(new Topic(), jsonData);
  }
}
