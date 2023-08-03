import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookreceiptComponent } from './bookreceipt.component';

describe('BookreceiptComponent', () => {
  let component: BookreceiptComponent;
  let fixture: ComponentFixture<BookreceiptComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookreceiptComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookreceiptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
