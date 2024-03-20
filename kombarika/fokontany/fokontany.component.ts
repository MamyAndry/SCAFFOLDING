import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FokontanyService } from './fokontany.service';
import { FormsModule } from '@angular/forms';
import { Fokontany } from './Fokontany';
import { Commune } from "../commune/Commune";
import { CommuneService } from "../commune/commune.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './fokontany.component.html',
  styleUrl: './fokontany.component.scss'
})
export class FokontanyComponent implements OnInit{
    title = 'CRUD Fokontany';
    data : Fokontany = new Fokontany;
    toUpdate : Fokontany = new Fokontany;
    toDelete : Fokontany = new Fokontany;
    fokontanyData : any;
	communeData : any;

    constructor(
        private fokontany: FokontanyService
        ,private commune:CommuneService
    ){}
    
    ngOnInit(): void {
        this.fokontany.get().subscribe(
            (data) => {
                this.fokontanyData = data;
            },
            (error) => {
                console.error('Error fetching data:', error);
            }
        )
		this.commune.get().subscribe(
			(data) => {
				this.communeData = data;	},
			(error) => {
				console.error('Error fetching data:', error);
			})
    }

    setUpdate(item : Fokontany): void{
        this.toUpdate = item;
        this.data.id = this.toUpdate.id;
    }

    deleteItem(item : Fokontany): void{
        this.fokontany.delete(item).subscribe(
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
        this.fokontany.save(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }

    submitUpdate(): any{
        this.fokontany.update(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }
  

}
