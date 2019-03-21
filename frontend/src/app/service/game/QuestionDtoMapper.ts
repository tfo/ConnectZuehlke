import {QuestionDto} from './QuestionDto';
import {Question} from '../../domain/Question';
import {AnswerDtoMapper} from './AnswerDtoMapper';

export class QuestionDtoMapper {
  public static mapFromDto(dto: QuestionDto): Question {
    const answers = AnswerDtoMapper.mapFromDtos(dto.answers);

    return new Question(dto.id, dto.title, answers);
  }

  public static mapFromDtos(dtos: QuestionDto[]): Question[] {
    return dtos.map((dto: QuestionDto) => this.mapFromDto(dto));
  }
}
