import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {QuestionTileComponent} from './question-tile.component';
import {MatButtonToggleModule} from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {Question} from '../domain/Question';
import {Answer} from '../domain/Answer';

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
    component.question = new Question('q1', 'What is your question?', [
        new Answer('a1', 'Answer 1', [], ''),
        new Answer('a2', 'Answer 2', [], '')
      ]);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
