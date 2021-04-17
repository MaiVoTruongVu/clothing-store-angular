import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DefultLayoutComponent } from './defult-layout.component';

describe('DefultLayoutComponent', () => {
  let component: DefultLayoutComponent;
  let fixture: ComponentFixture<DefultLayoutComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DefultLayoutComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DefultLayoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
