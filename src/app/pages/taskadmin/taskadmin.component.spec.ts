import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TaskadminComponent } from './taskadmin.component';

describe('TaskadminComponent', () => {
  let component: TaskadminComponent;
  let fixture: ComponentFixture<TaskadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TaskadminComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TaskadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
