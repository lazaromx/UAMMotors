import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EstoqueFuncComponent } from './estoque-func.component';

describe('EstoqueFuncComponent', () => {
  let component: EstoqueFuncComponent;
  let fixture: ComponentFixture<EstoqueFuncComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EstoqueFuncComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EstoqueFuncComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
