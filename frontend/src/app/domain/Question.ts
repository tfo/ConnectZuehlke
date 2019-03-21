import {Answer} from './Answer';

export interface Question {
  id: string;
  title: string;
  answers: Answer[];
}
