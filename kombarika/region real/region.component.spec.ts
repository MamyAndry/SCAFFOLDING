import { TestBed } from '@angular/core/testing';
import { RegionComponent } from './region.component';

describe('RegionComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegionComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(RegionComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the 'CRUD' title`, () => {
    const fixture = TestBed.createComponent(RegionComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('CRUD');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(RegionComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, CRUD');
  });
});
