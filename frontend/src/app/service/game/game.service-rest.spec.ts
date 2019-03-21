import {TestBed} from '@angular/core/testing';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';

import {GameDto} from './GameDto';
import {GameRestService} from './game.service-rest';


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
});
