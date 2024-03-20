import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RegionService } from './region.service';
import { FormsModule } from '@angular/forms';
import { Region } from './Region';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './region.component.html',
  styleUrl: './region.component.scss'
})
export class RegionComponent implements OnInit{
  title = 'CRUD Region';
  data : Region = new Region;
  toUpdate : Region = new Region;
  toDelete : any;
  regionData : any;

  constructor(private region: RegionService){}
  
  ngOnInit(): void {
    this.region.get().subscribe(
      (data) => {
        this.regionData = data;
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    )
  }

  setUpdate(item : Region): void{
    this.toUpdate = item;
    this.data.id = this.toUpdate.id
  }

  deleteItem(item : any): void{
    this.region.delete(item).subscribe(
      (data) => {
        console.log(data);
        // location.reload();
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  submitInsert(): any{
    this.region.save(this.data).subscribe(
      (data) => {
        console.log(data);
        location.reload();
      }
    )
  }

  submitUpdate(): any{
    console.log(this.toUpdate)
    console.log(this.data)
    this.region.update(this.data).subscribe(
      (data) => {
        console.log(data);
        // location.reload();
      }
    )
  }
  

}
