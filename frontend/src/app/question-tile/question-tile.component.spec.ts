import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuestionTileComponent} from './question-tile.component';
import {MatButtonToggleModule} from '@angular/material';
import {MatCardModule} from '@angular/material/card';

describe('QuestionTileComponent', () => {
  let component: QuestionTileComponent;
  let fixture: ComponentFixture<QuestionTileComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        QuestionTileComponent
      ],
      imports: [
        MatButtonToggleModule,
        MatCardModule
      ]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuestionTileComponent);
    component = fixture.componentInstance;
    component.question = {id: 'q1', title: 'What is your question?', answers: [
        {id: 'a1', title: 'Answer 1', ids: []},
        {id: 'a2', title: 'Answer 2', ids: []}
      ]};
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
