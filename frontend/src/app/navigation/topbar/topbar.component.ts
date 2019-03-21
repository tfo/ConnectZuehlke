import {Component} from '@angular/core';
import {GameStateService} from "../../service/game-state/game-state.service";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {


  constructor(public gameState: GameStateService) {
  }
}
