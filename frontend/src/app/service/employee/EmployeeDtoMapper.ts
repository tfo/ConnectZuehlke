import {EmployeeDto} from "./EmployeeDto";
import {Employee} from "../../domain/Employee";

export class EmployeeDtoMapper {

  public static mapFromDto(dto: EmployeeDto): Employee {
    return new Employee(dto.firstName, dto.lastName, dto.id, dto.code, dto.pictureId)
  }

  public static mapFromDtos(employeeDtos: EmployeeDto[]): Employee[] {
    return employeeDtos.map(dto => this.mapFromDto(dto))
  }
}
