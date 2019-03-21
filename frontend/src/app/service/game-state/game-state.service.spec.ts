import {async, TestBed} from '@angular/core/testing';
import {GameStateService} from './game-state.service';
import {GameStateTestData} from './game-state-test-data';
import {Game} from '../../domain/Game';

describe('GameStateService', () => {
  let service: GameStateService;
  let game: Game;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [],
      providers: [
        GameStateService
      ]
    });

    game = GameStateTestData.game();
    service = TestBed.get(GameStateService);
    service.initialize(game);
  }));

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should disable employees with wrong answer match', () => {
    const question03 = game.questions[2];
    const actual = service.guess(question03, question03.answers[1]);

    expect(actual).toEqual(false);

    const hiddenEmployees = service.employees.filter(employee => employee.hidden);
    const visibleEmployees = service.employees.filter(employee => !employee.hidden);

    expect(hiddenEmployees).toContain(game.employees[0]);
    expect(hiddenEmployees).toContain(game.employees[3]);
    expect(visibleEmployees).toContain(game.employees[1]);
    expect(visibleEmployees).toContain(game.employees[2]);
    expect(visibleEmployees).toContain(game.selectedEmployee);
  });

  it('should disable employees having not right answer match', () => {
    const question01 = game.questions[0];
    const actual = service.guess(question01, question01.answers[0]);

    expect(actual).toEqual(true);

    const hiddenEmployees = service.employees.filter(employee => employee.hidden);
    const visibleEmployees = service.employees.filter(employee => !employee.hidden);

    expect(hiddenEmployees).toContain(game.employees[2]);
    expect(hiddenEmployees).toContain(game.employees[3]);
    expect(visibleEmployees).toContain(game.employees[0]);
    expect(visibleEmployees).toContain(game.employees[1]);
    expect(visibleEmployees).toContain(game.selectedEmployee);
  });

  it('should disable all employees when won', () => {
    const question01 = game.questions[0];
    const actual1 = service.guess(question01, question01.answers[1]);

    expect(actual1).toEqual(false);
    expect(service.hasWon).toEqual(false);

    const question02 = game.questions[1];
    const actual2 = service.guess(question02, question02.answers[1]);

    expect(actual2).toEqual(true);
    expect(service.hasWon).toEqual(true);

    const hiddenEmployees = service.employees.filter(employee => employee.hidden);
    const visibleEmployees = service.employees.filter(employee => !employee.hidden);

    expect(hiddenEmployees).toContain(game.employees[0]);
    expect(hiddenEmployees).toContain(game.employees[2]);
    expect(hiddenEmployees).toContain(game.employees[3]);
    expect(visibleEmployees).toContain(game.employees[1]);
    expect(visibleEmployees).toContain(game.selectedEmployee);
  });
});
