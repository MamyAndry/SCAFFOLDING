import { TestBed } from '@angular/core/testing';

import { BureauvoteService } from './bureauvote.service';

describe('BureauvoteService', () => {
  let service: BureauvoteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BureauvoteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
