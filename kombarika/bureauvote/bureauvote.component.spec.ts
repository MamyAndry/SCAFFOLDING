import { TestBed } from '@angular/core/testing';
import { BureauvoteComponent } from './bureauvote.component';

describe('BureauvoteComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BureauvoteComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(BureauvoteComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the 'CRUD' title`, () => {
    const fixture = TestBed.createComponent(BureauvoteComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('CRUD');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(BureauvoteComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, CRUD');
  });
});
