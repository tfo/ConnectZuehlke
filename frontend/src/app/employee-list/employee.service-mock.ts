import {Observable, of} from 'rxjs';
import {Employee} from '../domain/Employee';

export const EMPLOYEES: Employee[] = [
  {firstName: 'John', lastName: 'Doe', id: 1, code: 'jdo', location: 'Schlieren'},
  {firstName: 'Max', lastName: 'Mustermann', id: 2, code: 'mmu', location: 'Eschborn'},
];

export class EmployeeServiceMock {
  getAllEmployees(): Observable<Employee[]> {
    return of(EMPLOYEES);
  }

  getEmployee(code: string): Observable<Employee> {
    return of(EMPLOYEES.find(e => e.code === code));
  }
}
