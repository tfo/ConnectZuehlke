import {Component} from '@angular/core';
import {GameStateService} from '../../service/game-state/game-state.service';
import {faHeart, faHeartBroken} from '@fortawesome/free-solid-svg-icons';
import {MAX_LIVES} from '../../service/game/Constants';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {

  maxLives = MAX_LIVES;
  heartIcon = faHeart;
  brokenIcon = faHeartBroken;

  // used for creating array in html
  Arr = Array;

  constructor(public gameState: GameStateService) {
  }
}
