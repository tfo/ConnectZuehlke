export class Answer {
  id: string;
  title: string;
  matchingEmployeeIds: number[];

  constructor(id: string, title: string, ids: number[]) {
    this.id = id;
    this.title = title;
    this.matchingEmployeeIds = ids;
  }
}
