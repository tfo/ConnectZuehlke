import {Component, OnInit} from '@angular/core';
import {GameServiceMock} from '../service/game/game.service-mock';
import {Employee} from '../domain/Employee';
import {Question} from '../domain/Question';
import {GameStateService} from '../service/game-state/game-state.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  secretEmployees: Employee[];
  questions: Question[];

  constructor(private gameService: GameServiceMock, private gameState: GameStateService) {
  }

  ngOnInit() {
    this.gameService.createNewGame().subscribe(game => {
      this.gameState.initialize(game);
      this.secretEmployees = game.employees;
      this.questions = game.questions;
    });
  }

  isGrayedOut() {
    return Math.random() > 0.5;
  }
}
