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
    if (question.answered === true) {
      return;
    }

    const answerCorrect = this.gameState.guess(question, answer);

    if (answerCorrect === true) {
      this.answerResult = {
        color: 'green',
        icon: faCheckCircle,
        text: `Your guess was correct. ${answer.fact}`
      };
    } else {
      const correctAnswer = this.gameState.getCorrectAnswerForSecretEmployee(question);

      this.answerResult = {
        color: 'red',
        icon: faTimesCircle,
        text: `Your guess was wrong. ${correctAnswer.fact}`
      };
    }
  }
}
