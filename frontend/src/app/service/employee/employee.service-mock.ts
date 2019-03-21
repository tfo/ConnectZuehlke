import {EmployeeDto} from './EmployeeDto';
import {Employee} from '../../domain/Employee';

export const SECRET_EMPLOYEES_AS_DTO: EmployeeDto[] = [
  {firstName: 'Max', lastName: 'Mustermann', id: 2, code: 'mmu', pictureId: '7'},
  {firstName: 'Rita', lastName: 'Richterich', id: 3, code: 'riri', pictureId: '32'},
  {firstName: 'John', lastName: 'Doe', id: 1, code: 'jdo', pictureId: '2'},
  {firstName: 'Janine', lastName: 'Wilson', id: 4, code: 'wija', pictureId: '43'},
  {firstName: 'Santiago', lastName: 'Garcias', id: 5, code: 'saga', pictureId: '54'},
  {firstName: 'Donald', lastName: 'Mratsov', id: 6, code: 'domr', pictureId: '66'},
  {firstName: 'Leslie', lastName: 'Gorbatshov', id: 7, code: 'lego', pictureId: '67'},
  {firstName: 'Thomas', lastName: 'Schmid', id: 8, code: 'thsc', pictureId: '85'},
];

export const SECRET_EMPLOYEES: Employee[] = SECRET_EMPLOYEES_AS_DTO.map(dto =>
  new Employee(dto.firstName, dto.lastName, dto.id, dto.code, dto.pictureId)
);
