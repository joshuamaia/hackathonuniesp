import { Component, OnInit } from '@angular/core';
import { BaseResourceListComponent } from 'src/app/shared/components/base-resource-list/base-resource-list.component';
import { Topic } from '../shared/topic.model';
import { TopicService } from '../shared/topic.service';

@Component({
  selector: 'app-topic-list',
  templateUrl: './topic-list.component.html',
  styleUrls: ['./topic-list.component.css'],
})
export class TopicListComponent
  extends BaseResourceListComponent<Topic>
  implements OnInit
{
  description: string | undefined;

  constructor(private topicService: TopicService) {
    super(topicService);
  }

  override ngOnInit(): void {
    super.ngOnInit();
  }

  filter() {
    this.subscribeGeneral.add(
      this.topicService
        .getAllFilter(this.pageNumber, this.size, this.description)
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }

  override paginate(event: any) {
    this.subscribeGeneral.add(
      this.topicService
        .getAllFilter(event.page, event.rows, this.description)
        .subscribe((response) => {
          this.page = response;
          this.resources = this.page.content;
          this.totalElementos = this.page.totalElements;
        })
    );
  }
}
