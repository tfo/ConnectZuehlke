import {Component, OnInit} from '@angular/core';
import {GameStateService} from '../service/game-state/game-state.service';
import {GameRestService} from "../service/game/game.service-rest";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  constructor(private gameService: GameRestService, public gameState: GameStateService) {
  }

  ngOnInit() {
    this.gameService.createNewGame().subscribe(game => {
      this.gameState.initialize(game);
    });
  }
}
