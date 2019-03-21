import {Employee} from './Employee';
import {Question} from './Question';

export interface Game {
  id: string;
  employees: Employee[];
  selectedEmployee: Employee;
  questions: Question[];
}
