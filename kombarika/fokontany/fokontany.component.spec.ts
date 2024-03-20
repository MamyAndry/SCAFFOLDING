import { TestBed } from '@angular/core/testing';
import { FokontanyComponent } from './fokontany.component';

describe('FokontanyComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FokontanyComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(FokontanyComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the 'CRUD' title`, () => {
    const fixture = TestBed.createComponent(FokontanyComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('CRUD');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(FokontanyComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, CRUD');
  });
});
