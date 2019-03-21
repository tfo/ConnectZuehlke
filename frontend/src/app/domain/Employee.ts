export class Employee {
  firstName: string;
  lastName: string;
  id: number;
  code: string;
  pictureId: string;
  hidden: boolean;

  constructor(firstName: string, lastName: string, id: number, code: string, pictureId: string) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.code = code;
    this.pictureId = pictureId;
    this.hidden = false;
  }
}
