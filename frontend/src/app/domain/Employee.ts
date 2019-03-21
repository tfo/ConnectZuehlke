export class Employee {
  firstName: string;
  lastName: string;
  id: number;
  code: string;
  hidden: boolean;

  constructor(firstName: string, lastName: string, id: number, code: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.code = code;
    this.hidden = false;
  }
}
