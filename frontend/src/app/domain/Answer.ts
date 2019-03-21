export class Answer {
  id: string;
  title: string;
  matchingEmployeeIds: number[];
  fact: string;

  constructor(id: string, title: string, matchingEmployeeIds: number[], fact: string) {
    this.id = id;
    this.title = title;
    this.matchingEmployeeIds = matchingEmployeeIds;
    this.fact = fact;
  }
}
