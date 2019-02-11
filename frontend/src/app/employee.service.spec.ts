import {TestBed} from '@angular/core/testing';

import {EmployeeService} from './employee.service';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Employee} from './domain/Employee';
import {environment} from '../environments/environment';

describe('EmployeeService', () => {
  let httpMock: HttpTestingController;
  let service: EmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [EmployeeService]
    });
    service = TestBed.get(EmployeeService);
    httpMock = TestBed.get(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should call get employees', () => {
    const dummyEmployees: Employee[] = [
      {firstName: "John", lastName: "Doe"},
      {firstName: "Max", lastName: "Mustermann"},
    ];
    service.getAllEmployees().subscribe((employees: Employee[]) => {
      expect(employees.length).toBe(2);
      expect(employees).toEqual(dummyEmployees);
    });

    const req = httpMock.expectOne(`${environment.apiUrl}/employees`);
    expect(req.request.method).toBe("GET");
    req.flush(dummyEmployees);
  });
});
