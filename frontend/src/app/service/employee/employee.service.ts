import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {HttpClient} from '@angular/common/http';
import {EmployeeDto} from './EmployeeDto';
import {Employee} from '../../domain/Employee';
import {EmployeeDtoMapper} from './EmployeeDtoMapper';

@Injectable({providedIn: 'root'})
export class EmployeeService {

  constructor(private http: HttpClient) {
  }

  public getAllEmployees(): Observable<Employee[]> {
    return this.http
      .get<EmployeeDto[]>('/api/employees')
      .pipe(
        catchError(this.handleError('getAllEmployees', [])),
        map(employees => EmployeeDtoMapper.mapFromDtos(employees))
      );
  }

  public getEmployee(id: string): Observable<Employee> {
    return this.http
      .get<EmployeeDto>(`/api/employee/${id}`)
      .pipe(
        catchError(this.handleError('getEmployee', null)),
        map(employee => EmployeeDtoMapper.mapFromDto(employee))
      );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  private log(s: string): void {
    console.log(`${this}: ${s}`);
  }
}
