import {Question} from './Question';
import {Employee} from './Employee';

export class Game {
  id: string;
  employees: Employee[];
  selectedEmployee: Employee;
  questions: Question[];

  constructor(id: string, employees: Employee[], selectedEmployee: Employee, questions: Question[]) {
    this.id = id;
    this.employees = employees;
    this.selectedEmployee = selectedEmployee;
    this.questions = questions;
  }
}
