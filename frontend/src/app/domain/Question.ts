import {Answer} from './Answer';

export class Question {
  id: string;
  title: string;
  answers: Answer[];

  constructor(id: string, title: string, answers: Answer[]) {
    this.id = id;
    this.title = title;
    this.answers = answers;
  }
}
