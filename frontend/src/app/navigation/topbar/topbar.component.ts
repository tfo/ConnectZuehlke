import {Component} from '@angular/core';
import {GameStateService} from '../../service/game-state/game-state.service';
import {faHeart, faHeartBroken} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {

  heartIcon = faHeart;
  brokenIcon = faHeartBroken;

  constructor(public gameState: GameStateService) {
  }
}
