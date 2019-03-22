import {Employee} from '../../domain/Employee';
import {Answer} from '../../domain/Answer';
import {Question} from '../../domain/Question';
import {Game} from '../../domain/Game';

export class GameStateTestData {

  static employee01(): Employee {
    return new Employee('John', 'Doe', 1, 'jdo', 'CH');
  }

  static employee02(): Employee {
    return new Employee('Max', 'Mustermann', 2, 'mmu', 'CH');
  }

  static employee03(): Employee {
    return new Employee('Rita', 'Richterich', 3, 'riri', 'DE');
  }

  static employee04(): Employee {
    return new Employee('Janine', 'Wilson', 4, 'wija', 'DE');
  }

  static selectedEmployee(): Employee {
    return this.employee02();
  }

  static question01(): Question {
    return new Question('q1', 'What gender is the person?', [
      this.question01_answer01(),
      this.question01_answer02()
    ]);
  }

  static question01_answer01(): Answer {
    return new Answer('q1-a1', 'Male', [1, 2], 'The person is male.');
  }

  static question01_answer02(): Answer {
    return new Answer('q1-a2', 'Female', [3, 4], 'The person is female.');
  }

  static question02(): Question {
    return new Question('q2', 'Where does the person work?', [
      this.question02_answer01(),
      this.question02_answer02()
    ]);
  }

  static question02_answer01(): Answer {
    return new Answer('q2-a1', 'Switzerland', [1], 'The person works in Switzerland.');
  }

  static question02_answer02(): Answer {
    return new Answer('q2-a2', 'Everywhere else', [2, 3, 4], 'The person works somewhere else.');
  }

  static question03(): Question {
    return new Question('q3', 'What\'s the role of the person?', [
      this.question03_answer01(),
      this.question03_answer02()
    ]);
  }

  static question03_answer01(): Answer {
    return new Answer('q3-a1', 'Engineer', [2, 3], 'The person is engineer.');
  }

  static question03_answer02(): Answer {
    return new Answer('q3-a1', 'Everything else', [1, 4], 'The person is something else.');
  }

  static game(): Game {
    return new Game(
      'game-id',
      [this.employee01(), this.employee02(), this.employee03(), this.employee04()],
      this.selectedEmployee(),
      [this.question01(), this.question02(), this.question03()],
      'The secret person\'s phone number ends with 45.');
  }
}
