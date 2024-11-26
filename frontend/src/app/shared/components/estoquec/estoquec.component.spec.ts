import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstoquecComponent } from './estoquec.component';

describe('EstoquecComponent', () => {
  let component: EstoquecComponent;
  let fixture: ComponentFixture<EstoquecComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EstoquecComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EstoquecComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
