import {AnswerDto} from './AnswerDto';
import {Answer} from '../../domain/Answer';

export class AnswerDtoMapper {

  public static mapFromDto(dto: AnswerDto): Answer {
    return new Answer(dto.id, dto.title, dto.ids);
  }

  public static mapFromDtos(dtos: AnswerDto[]): Answer[] {
    return dtos.map((dto: AnswerDto) => this.mapFromDto(dto));
  }
}
