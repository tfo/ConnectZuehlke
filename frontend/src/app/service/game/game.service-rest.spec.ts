import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {Game} from '../../domain/Game';
import {GameRestService} from './game.service-rest';
import {GameService} from './game.service';
import {GAME} from './game.service-mock';

describe('GameRestService', () => {
  let httpMock: HttpTestingController;
  let service: GameRestService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [GameRestService]
    });

    service = TestBed.get(GameRestService);
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call create game', () => {
    const expectedGame = GAME;

    service.createNewGame().subscribe((game: Game) => {
      expect(game.id).toBe(expectedGame.id);
      expect(game).toEqual(expectedGame);
    });

    const request = httpMock.expectOne('/api/game?numberOfEmployees=15');
    expect(request.request.method).toBe('GET');
    request.flush(expectedGame);
  });
});
