import {EmployeeDto} from './EmployeeDto';
import {Employee} from '../../domain/Employee';

export const SECRET_EMPLOYEES_AS_DTO: EmployeeDto[] = [
  {firstName: 'Max', lastName: 'Mustermann', id: 2, code: 'mmu'},
  {firstName: 'Rita', lastName: 'Richterich', id: 3, code: 'riri'},
  {firstName: 'John', lastName: 'Doe', id: 1, code: 'jdo'},
  {firstName: 'Janine', lastName: 'Wilson', id: 4, code: 'wija'},
  {firstName: 'Santiago', lastName: 'Garcias', id: 5, code: 'saga'},
  {firstName: 'Donald', lastName: 'Mratsov', id: 6, code: 'domr'},
  {firstName: 'Leslie', lastName: 'Gorbatshov', id: 7, code: 'lego'},
  {firstName: 'Thomas', lastName: 'Schmid', id: 8, code: 'thsc'},
];

export const SECRET_EMPLOYEES: Employee[] = SECRET_EMPLOYEES_AS_DTO.map(dto =>
  new Employee(dto.firstName, dto.lastName, dto.id, dto.code)
);
