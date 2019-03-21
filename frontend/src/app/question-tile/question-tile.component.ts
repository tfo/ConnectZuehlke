import {Component, Input, OnInit} from '@angular/core';

import { faCheckCircle, faTimesCircle } from '@fortawesome/free-solid-svg-icons';

import {Question} from '../domain/Question';
import {Answer} from '../domain/Answer';
import {GameStateService} from '../service/game-state/game-state.service';

@Component({
  selector: 'app-question-tile',
  templateUrl: './question-tile.component.html',
  styleUrls: ['./question-tile.component.scss']
})
export class QuestionTileComponent implements OnInit {
  @Input() question: Question;

  answerResult;

  constructor(private gameState: GameStateService) {
  }

  ngOnInit() {
  }

  public selectAnswer(question: Question, answer: Answer): void {
    const result = this.gameState.guess(question, answer);

    if (result === true) {
      this.answerResult = faCheckCircle;
    } else {
      this.answerResult = faTimesCircle;
    }
  }
}
