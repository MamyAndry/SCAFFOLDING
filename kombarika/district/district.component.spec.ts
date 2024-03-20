import { TestBed } from '@angular/core/testing';
import { DistrictComponent } from './district.component';

describe('DistrictComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DistrictComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(DistrictComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the 'CRUD' title`, () => {
    const fixture = TestBed.createComponent(DistrictComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('CRUD');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(DistrictComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, CRUD');
  });
});
