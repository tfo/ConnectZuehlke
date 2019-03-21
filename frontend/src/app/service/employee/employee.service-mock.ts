import {EmployeeDto} from './EmployeeDto';
import {Employee} from '../../domain/Employee';

export const SECRET_EMPLOYEES_AS_DTO: EmployeeDto[] = [
  {firstName: 'Max', lastName: 'Mustermann', id: 2, code: 'mmu', location: 'CH'},
  {firstName: 'Rita', lastName: 'Richterich', id: 3, code: 'riri', location: 'DE'},
  {firstName: 'John', lastName: 'Doe', id: 1, code: 'jdo', location: 'CH'},
  {firstName: 'Janine', lastName: 'Wilson', id: 4, code: 'wija', location: 'DE'},
  {firstName: 'Santiago', lastName: 'Garcias', id: 5, code: 'saga', location: 'SRB'},
  {firstName: 'Donald', lastName: 'Mratsov', id: 6, code: 'domr', location: 'DE'},
  {firstName: 'Leslie', lastName: 'Gorbatshov', id: 7, code: 'lego', location: 'CH'},
  {firstName: 'Thomas', lastName: 'Schmid', id: 8, code: 'thsc', location: 'CH'}
];

export const SECRET_EMPLOYEES: Employee[] = SECRET_EMPLOYEES_AS_DTO.map(dto =>
  new Employee(dto.firstName, dto.lastName, dto.id, dto.code, dto.location)
);
