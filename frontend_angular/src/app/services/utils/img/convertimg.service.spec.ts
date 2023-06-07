import { TestBed } from '@angular/core/testing';

import { ConvertImgService } from './convertimg.service';

describe('ConvertImgService', () => {
  let service: ConvertImgService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConvertImgService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
