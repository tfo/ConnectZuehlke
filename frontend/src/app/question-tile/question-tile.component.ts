import {Component, Input, OnInit} from '@angular/core';
import {Question} from '../domain/Question';
import {Answer} from "../domain/Answer";

@Component({
  selector: 'app-question-tile',
  templateUrl: './question-tile.component.html',
  styleUrls: ['./question-tile.component.scss']
})
export class QuestionTileComponent implements OnInit {

  @Input() question: Question;

  constructor() { }

  ngOnInit() {
  }

  public selectAnswer(answer: Answer): void {
    console.log(`Answer: ${answer.title}`);
  }
}
