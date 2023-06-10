import { TestBed } from '@angular/core/testing';

import { CookieExpirationInterceptor } from './cookie-expiration.interceptor';

describe('CookieExpirationInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      CookieExpirationInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: CookieExpirationInterceptor = TestBed.inject(CookieExpirationInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
