import {Component, OnInit} from '@angular/core';
import {GameStateService} from '../service/game-state/game-state.service';
import {GameRestService} from '../service/game/game.service-rest';
import {EndgameDialogComponent} from "../endgame-dialog/endgame-dialog.component";
import {MatDialog} from "@angular/material";

@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.scss']
})
export class GameComponent implements OnInit {

  constructor(private gameService: GameRestService,
              public gameState: GameStateService,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    this.gameService.startNewGame.subscribe( () => {
      this.loadNewGame();
    } );
    this.gameService.startNewGame.next();
  }

  private loadNewGame() {
    this.gameState.deinitialize();
    this.gameService.createNewGame().subscribe(game => {
      this.gameState.initialize(game);
      this.subscribeToGameEndSubject();
    });
  }

  private subscribeToGameEndSubject() {
    this.gameState.gameEnded.subscribe(gameEnded => {
      if (gameEnded) {
        this.openEndgameDialog()
      }
    });
  }

  openEndgameDialog(): void {
    const dialogRef = this.dialog.open(EndgameDialogComponent, {
      //width: '600px',
      //height: '400px'
    });
  }

}
