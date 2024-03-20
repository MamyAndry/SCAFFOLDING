import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommuneService } from './commune.service';
import { FormsModule } from '@angular/forms';
import { Commune } from './Commune';
import { District } from "../district/District";
import { DistrictService } from "../district/district.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './commune.component.html',
  styleUrl: './commune.component.scss'
})
export class CommuneComponent implements OnInit{
    title = 'CRUD Commune';
    data : Commune = new Commune;
    toUpdate : Commune = new Commune;
    toDelete : Commune = new Commune;
    communeData : any;
	districtData : any;

    constructor(
        private commune: CommuneService
        ,private district:DistrictService
    ){}
    
    ngOnInit(): void {
        this.commune.get().subscribe(
            (data) => {
                this.communeData = data;
            },
            (error) => {
                console.error('Error fetching data:', error);
            }
        )
		this.district.get().subscribe(
			(data) => {
				this.districtData = data;	},
			(error) => {
				console.error('Error fetching data:', error);
			})
    }

    setUpdate(item : Commune): void{
        this.toUpdate = item;
        this.data.id = this.toUpdate.id;
    }

    deleteItem(item : Commune): void{
        this.commune.delete(item).subscribe(
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
        this.commune.save(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }

    submitUpdate(): any{
        this.commune.update(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }
  

}
