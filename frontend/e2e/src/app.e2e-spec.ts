import {AppPage} from './pages/app.po';
import {browser, logging} from 'protractor';

describe('Main App Page', () => {
  let appPage: AppPage;

  beforeEach(() => {
    appPage = new AppPage();
  });

  it('should display welcome message', () => {
    appPage.navigateTo();
    expect(appPage.getTitleText()).toEqual('Guess the secret Zühlke Employee!');
  });


  afterEach(async () => {
    // Assert that there are no errors emitted from the browser
    const logs = await browser.manage().logs().get(logging.Type.BROWSER);
    expect(logs).not.toContain(jasmine.objectContaining({
      level: logging.Level.SEVERE,
    }));
  });
});
