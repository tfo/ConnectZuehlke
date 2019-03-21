import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GameComponent} from './game.component';
import {FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import {QuestionTileComponent} from '../question-tile/question-tile.component';
import {MatButtonToggleModule} from '@angular/material';
import {MatCardModule} from '@angular/material/card';
import {QuestionsComponent} from '../questions/questions.component';

describe('GameComponent', () => {
  let component: GameComponent;
  let fixture: ComponentFixture<GameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        GameComponent,
        QuestionsComponent,
        QuestionTileComponent
      ],
      imports: [
        FontAwesomeModule,
        MatButtonToggleModule,
        MatCardModule
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it(`should have as title 'Zühlke Connect'`, () => {
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Zühlke Connect');
  });

  it('should render title in a h1 tag', () => {
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Guess the secret Zühlke Employee!');
  });
});
