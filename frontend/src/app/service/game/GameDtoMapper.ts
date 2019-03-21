import {Game} from '../../domain/Game';
import {GameDto} from './GameDto';
import {EmployeeDtoMapper} from '../employee/EmployeeDtoMapper';
import {QuestionDtoMapper} from './QuestionDtoMapper';

export class GameDtoMapper {

  public static mapFromDto(dto: GameDto): Game {
    let employees = EmployeeDtoMapper.mapFromDtos(dto.employees);
    let selectedEmployee = EmployeeDtoMapper.mapFromDto(dto.selectedEmployee);
    let questions = QuestionDtoMapper.mapFromDtos(dto.questions);

    return new Game(dto.id, employees, selectedEmployee, questions);
  }
}
