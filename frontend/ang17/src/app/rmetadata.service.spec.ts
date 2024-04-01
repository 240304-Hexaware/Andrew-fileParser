import { TestBed } from '@angular/core/testing';

import { RmetadataService } from './rmetadata.service';

describe('RmetadataService', () => {
  let service: RmetadataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RmetadataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
