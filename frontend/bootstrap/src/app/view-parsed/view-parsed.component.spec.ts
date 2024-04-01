import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewParsedComponent } from './view-parsed.component';

describe('ViewParsedComponent', () => {
  let component: ViewParsedComponent;
  let fixture: ComponentFixture<ViewParsedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewParsedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewParsedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
