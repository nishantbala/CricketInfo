import { AppPage } from './app.po';
import {browser, by, element, protractor} from 'protractor';

describe('workspace-project App', () => {
  let page: AppPage;

  beforeEach(() => {
    page = new AppPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
   // expect(page.getParagraphText()).toEqual('Welcome to CMatchesUI!');
  });

  it('should have a title', () => {
          expect(browser.getTitle()).toEqual('CMatchesUI');
      });

      it('should be redirected to /login route on opening the application', () => {
          expect(browser.getCurrentUrl()).toContain('/login');
          browser.element(by.id('register-btn')).click();
          expect(browser.getCurrentUrl()).toContain('/register');
      });

      it('should be able to register', () => {
          browser.element(by.id('username')).sendKeys('testss23');
          browser.element(by.id('pass')).sendKeys('udhay');
          browser.element(by.id('fname')).sendKeys('udhay');
          browser.element(by.id('lname')).sendKeys('udhay');
          browser.element(by.id('isadmin')).click();
          browser.element(by.id('jumbotron.btn')).submit();
          expect(browser.getCurrentUrl()).toContain('/login');

      });

      it('should be able to login', () => {
          browser.element(by.id('form-user')).sendKeys('udhay');
          browser.element(by.id('form-pass')).sendKeys('udhay');
          browser.element(by.id('jumbotron.btn')).click();
          expect(browser.getCurrentUrl()).toContain('/dashboard');
      });

      it('should be able to search for match', () => {
          browser.element(by.id('col-md-4')).sendKeys('Federal');
          const searchItems = element.all(by.id('name'));
          expect(searchItems.count()).toBe(1);
          for (let i = 0; i < 1; i++) {
              expect(searchItems.get(i).getText()).toContain('Federal');
          }
      });




      it('should be able to logout', () => {
          browser.element(by.id('logout')).click();
          expect(browser.getCurrentUrl()).toContain('/login');
      });

      it('public user should be able to login', () => {
          browser.element(by.id('form-user')).sendKeys('test');
          browser.element(by.id('form-pass')).sendKeys('udhay');
          browser.element(by.id('jumbotron.btn')).click();
          expect(browser.getCurrentUrl()).toContain('/dashboard');
      });

      it('should be able to add to watchlist', () => {
          const text = browser.element(by.id('watchListBtn')).getText();
          if (text.toString() === 'Add To Watchlist') {
              browser.element(by.id('watchListBtn')).click();
          }
          expect(browser.element(by.id('watchListBtn')).getText()).toContain('Remove From Watchlist');
          browser.element(by.id('logout')).click();
      });
});
