import {Game} from '../../domain/Game';
import {GameDto} from './GameDto';
import {EmployeeDtoMapper} from '../employee/EmployeeDtoMapper';
import {QuestionDtoMapper} from './QuestionDtoMapper';

export class GameDtoMapper {

  public static mapFromDto(dto: GameDto): Game {
    const employees = EmployeeDtoMapper.mapFromDtos(dto.employees);
    const selectedEmployee = EmployeeDtoMapper.mapFromDto(dto.selectedEmployee);
    const questions = QuestionDtoMapper.mapFromDtos(dto.questions);

    return new Game(dto.id, employees, selectedEmployee, questions, dto.funFact);
  }
}
