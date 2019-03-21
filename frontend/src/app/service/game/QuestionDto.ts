import {AnswerDto} from './AnswerDto';

export interface QuestionDto {
  id: string;
  title: string;
  answers: AnswerDto[];
}
