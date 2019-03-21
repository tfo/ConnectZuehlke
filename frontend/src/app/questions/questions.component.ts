import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../domain/Question';

@Component({
  selector: 'app-questions',
  templateUrl: './questions.component.html',
  styleUrls: ['./questions.component.scss']
})
export class QuestionsComponent implements OnInit {
  @Input() questions: Question[];

  constructor() {
  }

  ngOnInit() {
  }
}
