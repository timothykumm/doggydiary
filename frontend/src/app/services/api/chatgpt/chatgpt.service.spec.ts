import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ChatgptService } from './chatgpt.service';

describe('ChatgptService', () => {
  let service: ChatgptService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule]
    });
    service = TestBed.inject(ChatgptService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
