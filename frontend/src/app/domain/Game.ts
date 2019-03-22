import {Question} from './Question';
import {Employee} from './Employee';

export class Game {
  id: string;
  employees: Employee[];
  selectedEmployee: Employee;
  questions: Question[];
  funFact: string;

  constructor(id: string, employees: Employee[], selectedEmployee: Employee, questions: Question[], funFact: string) {
    this.id = id;
    this.employees = employees;
    this.selectedEmployee = selectedEmployee;
    this.questions = questions;
    this.funFact = funFact;
  }
}
