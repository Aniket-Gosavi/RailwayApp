import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletetrainComponent } from './deletetrain.component';

describe('DeletetrainComponent', () => {
  let component: DeletetrainComponent;
  let fixture: ComponentFixture<DeletetrainComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeletetrainComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeletetrainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
