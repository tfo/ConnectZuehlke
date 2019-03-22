import {Component} from '@angular/core';
import {GameStateService} from '../../service/game-state/game-state.service';
import {faHeart, faHeartBroken} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-topbar',
  templateUrl: './topbar.component.html',
  styleUrls: ['./topbar.component.scss']
})
export class TopbarComponent {

  heartIcon = faHeart;
  brokenIcon = faHeartBroken;

  fullHeartsNumbers = [0, 1, 2, 3];

  constructor(public gameState: GameStateService) {
  }

  Arr = Array; // Array type captured in a variable

  public static toArray(n: number): any[] {
    return Array(n);
  }
}
