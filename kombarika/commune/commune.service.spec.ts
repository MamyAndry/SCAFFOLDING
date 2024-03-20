import { TestBed } from '@angular/core/testing';

import { CommuneService } from './commune.service';

describe('CommuneService', () => {
  let service: CommuneService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommuneService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
