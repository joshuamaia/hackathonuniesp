import { Template } from './../../template/shared/template.model';
import { Topic } from './../../topic/shared/topic.model';
import { BaseResourceModel } from '../../../shared/models/base-resource.model';

export class ReportGPT extends BaseResourceModel {
  constructor(
    override id?: number,
    public topic?: Topic,
    public template?: Template,
    public description?: string,
    public abnt?: boolean
  ) {
    super();
  }

  static fromJson(jsonData: any): ReportGPT {
    return Object.assign(new ReportGPT(), jsonData);
  }
}
