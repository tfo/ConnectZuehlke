export class Answer {
  id: string;
  title: string;
  ids: number[];

  constructor(id: string, title: string, ids: number[]) {
    this.id = id;
    this.title = title;
    this.ids = ids;
  }
}
