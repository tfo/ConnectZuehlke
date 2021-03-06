import {Answer} from './Answer';

export class Question {
  id: string;
  title: string;
  answers: Answer[];
  answered: boolean;

  constructor(id: string, title: string, answers: Answer[]) {
    this.id = id;
    this.title = title;
    this.answers = answers;
    this.answered = false;
  }

  public getAllAnswersExcept(answer: Answer): Answer[] {
    return this.answers.filter(value => value.id !== answer.id);
  }

  public getAnswerForEmployeeId(employeeId: number): Answer {
    return this.answers.find(answer => answer.matchingEmployeeIds.includes(employeeId));
  }

  public setAnswered(): void {
    this.answered = true;
  }
}
