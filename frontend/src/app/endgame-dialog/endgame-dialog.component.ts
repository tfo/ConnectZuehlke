import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {GameStateService} from '../service/game-state/game-state.service';
import {faHeartBroken} from '@fortawesome/free-solid-svg-icons';
import {GameRestService} from '../service/game/game.service-rest';

@Component({
  selector: 'app-endgame-dialog',
  templateUrl: './endgame-dialog.component.html',
  styleUrls: ['./endgame-dialog.component.scss']
})
export class EndgameDialogComponent implements OnInit {

  brokenIcon = faHeartBroken;

  // used for creating array in html
  Arr = Array;

  constructor(public dialogRef: MatDialogRef<EndgameDialogComponent>,
              private gameService: GameRestService,
              public gameState: GameStateService) {}


  startNewGame(): void {
    this.dialogRef.close();
    this.gameService.startNewGame.next();
  }

  ngOnInit() {
  }

}
