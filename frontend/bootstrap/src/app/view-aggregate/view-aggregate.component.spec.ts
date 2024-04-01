import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAggregateComponent } from './view-aggregate.component';

describe('ViewAggregateComponent', () => {
  let component: ViewAggregateComponent;
  let fixture: ComponentFixture<ViewAggregateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAggregateComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewAggregateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
