import { Component, Input, OnInit } from '@angular/core';
import { ReportGPT } from '../shared/report-gpt.model';

@Component({
  selector: 'app-report-gptdetail',
  templateUrl: './report-gptdetail.component.html',
  styleUrls: ['./report-gptdetail.component.css'],
})
export class ReportGPTDetailComponent implements OnInit {
  @Input()
  reportGPTSelected: ReportGPT = {};

  constructor() {}

  ngOnInit(): void {}
}
