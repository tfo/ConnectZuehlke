import {GameService} from './game.service';
import {Observable, of} from 'rxjs';
import {Game} from "../../domain/Game";
import {EMPLOYEES} from "../employee/employee.service-mock";
import {Question} from "../../domain/Question";

export const QUESTIONS: Question[] = [
  {title: "What gender is the person?", answers: [{title: "Male", ids: [1,2]}, {title: "Female", ids: [3,4]}]},
  {title: "Where does the person work?", answers: [{title: "Switzerland", ids: [1]}, {title: "Everywhere else", ids: [2,3,4]}]},
  {title: "What's the role of the person?", answers: [{title: "Engineer", ids: [2,3]}, {title: "Everything else", ids: [1,4]}]}
  ];

export const GAME: Game = {
  id: 'game-id',
  employees: EMPLOYEES,
  selectedEmployee: EMPLOYEES[2],
  questions: QUESTIONS
};

export class GameServiceMock implements GameService {
  createNewGame(): Observable<Game> {
    return of(GAME);
  }
}
