import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DistrictService } from './district.service';
import { FormsModule } from '@angular/forms';
import { District } from './District';
import { Region } from "../region/Region";
import { RegionService } from "../region/region.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './district.component.html',
  styleUrl: './district.component.scss'
})
export class DistrictComponent implements OnInit{
    title = 'CRUD District';
    data : District = new District;
    toUpdate : District = new District;
    toDelete : District = new District;
    districtData : any;
	regionData : any;

    constructor(
        private district: DistrictService
        ,private region:RegionService
    ){}
    
    ngOnInit(): void {
        this.district.get().subscribe(
            (data) => {
                this.districtData = data;
            },
            (error) => {
                console.error('Error fetching data:', error);
            }
        )
		this.region.get().subscribe(
			(data) => {
				this.regionData = data;	},
			(error) => {
				console.error('Error fetching data:', error);
			})
    }

    setUpdate(item : District): void{
        this.toUpdate = item;
        this.data.id = this.toUpdate.id;
    }

    deleteItem(item : District): void{
        this.district.delete(item).subscribe(
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
        this.district.save(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }

    submitUpdate(): any{
        this.district.update(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }
  

}
