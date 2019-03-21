import {Employee} from './Employee';

export interface Game {
  id: string;
  employees: Employee[];
  selectedEmployee: Employee;
}
