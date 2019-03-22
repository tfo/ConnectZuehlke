import {EmployeeDto} from '../employee/EmployeeDto';
import {QuestionDto} from './QuestionDto';

export interface GameDto {
  id: string;
  employees: EmployeeDto[];
  selectedEmployee: EmployeeDto;
  questions: QuestionDto[];
  funFact: string;
}
