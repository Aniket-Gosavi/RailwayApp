import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTrainsComponent } from './show-trains.component';

describe('ShowTrainsComponent', () => {
  let component: ShowTrainsComponent;
  let fixture: ComponentFixture<ShowTrainsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ShowTrainsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ShowTrainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
