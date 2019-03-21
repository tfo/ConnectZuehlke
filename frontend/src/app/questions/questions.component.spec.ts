import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuestionsComponent } from './questions.component';
import {QuestionTileComponent} from '../question-tile/question-tile.component';
import {MatCardModule} from '@angular/material/card';
import {MatButtonToggleModule} from '@angular/material';


describe('QuestionsComponent', () => {
  let component: QuestionsComponent;
  let fixture: ComponentFixture<QuestionsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        QuestionsComponent,
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
    fixture = TestBed.createComponent(QuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
