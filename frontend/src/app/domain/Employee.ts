export class Employee {
  firstName: string;
  lastName: string;
  id: number;
  code: string;
  hidden: boolean;
  location: string;

  constructor(firstName: string, lastName: string, id: number, code: string, location: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.code = code;
    this.hidden = false;
    this.location = location;
  }
}
