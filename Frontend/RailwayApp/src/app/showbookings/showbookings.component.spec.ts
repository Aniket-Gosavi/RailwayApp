import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowbookingsComponent } from './showbookings.component';

describe('ShowbookingsComponent', () => {
  let component: ShowbookingsComponent;
  let fixture: ComponentFixture<ShowbookingsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowbookingsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowbookingsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
