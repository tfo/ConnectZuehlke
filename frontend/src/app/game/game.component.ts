import {Component, OnInit} from '@angular/core';
import {GameServiceMock} from '../service/game/game.service-mock';
import {GameStateService} from '../service/game-state/game-state.service';

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  constructor(private gameService: GameServiceMock, private gameState: GameStateService) {
  }

  ngOnInit() {
    this.gameService.createNewGame().subscribe(game => {
      this.gameState.initialize(game);
    });
  }
}
