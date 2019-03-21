import {Component, Input, OnInit} from '@angular/core';
import {GameStateService} from '../service/game-state/game-state.service';
import {Question} from '../domain/Question';
import {Answer} from '../domain/Answer';

@Component({
  selector: 'app-question-tile',
  templateUrl: './question-tile.component.html',
  styleUrls: ['./question-tile.component.scss']
})
export class QuestionTileComponent implements OnInit {

  @Input() question: Question;

  constructor(private gameState: GameStateService) { }

  ngOnInit() {
  }

  public selectAnswer(answer: Answer): void {
    let result = this.gameState.guess(answer);
    console.log(`Guess answer: ${answer.title} > ${result}`);
  }
}
