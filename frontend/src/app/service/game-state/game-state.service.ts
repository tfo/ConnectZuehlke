import {Game} from "../../domain/Game";
import {Employee} from "../../domain/Employee";
import {Question} from "../../domain/Question";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class GameStateService {

  id: string;
  employees: Employee[];
  secretEmployee: Employee;
  questions: Question[];

  constructor() {}

  public initialize(game: Game): void {
    this.id = game.id;
    this.employees = game.employees;
    this.secretEmployee = game.selectedEmployee;
    this.questions = game.questions;
  }

}
