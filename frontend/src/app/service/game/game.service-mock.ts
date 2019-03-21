import {GameService} from './game.service';
import {Observable, of} from 'rxjs';
import {Game} from '../../domain/Game';
import {SECRET_EMPLOYEES} from '../employee/employee.service-mock';
import {Question} from '../../domain/Question';
import {Injectable} from "@angular/core";

export const QUESTIONS: Question[] = [
  {
    id: 'q1',
    title: 'What gender is the person?', answers: [
      {id: 'q1-a1', title: 'Male', ids: [1, 2]},
      {id: 'q1-a2', title: 'Female', ids: [3, 4]}
    ]
  },
  {
    id: 'q2',
    title: 'Where does the person work?',
    answers: [
      {id: 'q2-a1', title: 'Switzerland', ids: [1]},
      {id: 'q2-a2', title: 'Everywhere else', ids: [2, 3, 4]}
    ]
  },
  {
    id: 'q3',
    title: 'What\'s the role of the person?',
    answers: [
      {id: 'q3-a1', title: 'Engineer', ids: [2, 3]},
      {id: 'q3-a1', title: 'Everything else', ids: [1, 4]}
    ]
  }
];

export const GAME: Game = {
  id: 'game-id',
  questions: QUESTIONS,
  employees: SECRET_EMPLOYEES,
  selectedEmployee: SECRET_EMPLOYEES[2]
};

@Injectable({providedIn: 'root'})
export class GameServiceMock implements GameService {
  createNewGame(): Observable<Game> {
    return of(GAME);
  }
}
