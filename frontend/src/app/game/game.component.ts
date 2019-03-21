import {Component, OnInit} from '@angular/core';
import {GameServiceMock} from "../service/game/game.service-mock";
import {Employee} from "../domain/Employee";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {
  secretEmployees: Employee[];

  constructor(private gameService: GameServiceMock) {
  }

  ngOnInit() {
    this.gameService.createNewGame().subscribe(game => {
      this.secretEmployees = game.employees;
    });
  }
}
