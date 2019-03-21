import {Game} from "../../domain/Game";
import {Employee} from "../../domain/Employee";
import {Question} from "../../domain/Question";
import {Injectable} from "@angular/core";
import {Answer} from "../../domain/Answer";

@Injectable({
  providedIn: 'root'
})
export class GameStateService {

  id: string;
  employees: Employee[];
  secretEmployee: Employee;
  questions: Question[];

  questionCount: number;
  missedQuestionCount: number;

  constructor() {}

  public initialize(game: Game): void {
    this.id = game.id;
    this.employees = game.employees;
    this.secretEmployee = game.selectedEmployee;
    this.questions = game.questions;

    this.questionCount = 0;
    this.missedQuestionCount = 0;
  }

  public guess(answer: Answer): boolean {
    this.questionCount++;

    let matchSecretEmployee = this.matchSecretEmployee(answer);
    if (matchSecretEmployee === true) {
      this.missedQuestionCount++;
    }

    return matchSecretEmployee;
  }

  private matchSecretEmployee(answer: Answer): boolean {
    return answer.ids.includes(this.secretEmployee.id);
  }
}
