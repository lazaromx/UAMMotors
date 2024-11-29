import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoVeiculoComponent } from './info-veiculo.component';

describe('InfoVeiculoComponent', () => {
  let component: InfoVeiculoComponent;
  let fixture: ComponentFixture<InfoVeiculoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfoVeiculoComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfoVeiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
