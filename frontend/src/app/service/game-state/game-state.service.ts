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
  hasWon: boolean;

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
    question.setAnswered();

    this.questionCount++;

    const matchSecretEmployee = this.matchSecretEmployee(answer);
    if (matchSecretEmployee === true) {
      const answers = question.getAllAnswersExcept(answer);

      this.hideMismatchingEmployeesByAnswers(answers);
    } else {
      this.missedQuestionCount++;
      this.hideMismatchingEmployeesByAnswer(answer);
    }
    this.hasWon = this.employees.filter(value => value.hidden == false).length == 1;
    if (this.hasWon) {
      this.employees.filter(value => value.hidden == false).forEach(value => value.hidden = true);
    }

    return matchSecretEmployee;
  }

  private matchSecretEmployee(answer: Answer): boolean {
    return answer.matchingEmployeeIds.includes(this.secretEmployee.id);
  }

  private hideMismatchingEmployeesByAnswers(answers: Answer[]): void {
    answers.forEach(answer => this.hideMismatchingEmployeesByAnswer(answer));
  }

  private hideMismatchingEmployeesByAnswer(answer: Answer): void {
    this.employees
      .filter(employee => employee.hidden === false)
      .filter(employee => answer.matchingEmployeeIds.includes(employee.id))
      .every(employee => employee.hidden = true);
  }
}
