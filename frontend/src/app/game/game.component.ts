import {Component, OnInit} from '@angular/core';
import {GameServiceMock} from '../service/game/game.service-mock';
import {Employee} from '../domain/Employee';
import {Question} from '../domain/Question';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  secretEmployees: Employee[];
  questions: Question[];

  constructor(private gameService: GameServiceMock) {
  }

  ngOnInit() {
    this.gameService.createNewGame().subscribe(game => {
      this.secretEmployees = game.employees;
      this.questions = game.questions;
    });
  }


}
