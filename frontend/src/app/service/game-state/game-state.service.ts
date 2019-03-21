import {Injectable} from '@angular/core';
import {Employee} from '../../domain/Employee';
import {Game} from '../../domain/Game';
import {Question} from '../../domain/Question';
import {Answer} from '../../domain/Answer';

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

  public guess(question: Question, answer: Answer): boolean {
    this.questionCount++;

    const matchSecretEmployee = this.matchSecretEmployee(answer);
    if (matchSecretEmployee === true) {
      const answers = question.getAllAnswersExcept(answer);

      this.hideMissmatchingEmployeesByAnswers(answers);
    } else {
      this.missedQuestionCount++;
      this.hideMissmatchingEmployees(answer);
    }

    return matchSecretEmployee;
  }

  private matchEmployee(answer: Answer, employee: Employee): boolean {
    return answer.ids.includes(employee.id);
  }

  private matchSecretEmployee(answer: Answer): boolean {
    return this.matchEmployee(answer, this.secretEmployee);
  }

  private hideMissmatchingEmployeesByAnswers(answers: Answer[]): void {
    answers.forEach(answer => this.hideMissmatchingEmployees(answer));
  }

  private hideMissmatchingEmployees(answer: Answer): void {
    this.employees
      .filter(employee => employee.hidden === false)
      .filter(employee => answer.ids.includes(employee.id))
      .every(employee => employee.hidden = true);
  }
}
