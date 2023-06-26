import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ClassifierService } from './classifier.service';

describe('ClassifierService', () => {
  let service: ClassifierService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(ClassifierService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
