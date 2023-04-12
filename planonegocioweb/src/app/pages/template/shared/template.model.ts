import { BaseResourceModel } from '../../../shared/models/base-resource.model';

export class Template extends BaseResourceModel {
  constructor(
    override id?: number,
    public title?: string,
    public description?: string
  ) {
    super();
  }

  static fromJson(jsonData: any): Template {
    return Object.assign(new Template(), jsonData);
  }
}
