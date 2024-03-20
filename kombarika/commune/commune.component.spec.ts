import { TestBed } from '@angular/core/testing';
import { CommuneComponent } from './commune.component';

describe('CommuneComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CommuneComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(CommuneComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the 'CRUD' title`, () => {
    const fixture = TestBed.createComponent(CommuneComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('CRUD');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(CommuneComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, CRUD');
  });
});
