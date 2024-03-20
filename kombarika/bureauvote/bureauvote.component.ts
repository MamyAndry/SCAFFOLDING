import { Component, OnInit } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { BureauvoteService } from './bureauvote.service';
import { FormsModule } from '@angular/forms';
import { Bureauvote } from './Bureauvote';
import { Fokontany } from "../fokontany/Fokontany";
import { FokontanyService } from "../fokontany/fokontany.service";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, FormsModule],
  templateUrl: './bureauvote.component.html',
  styleUrl: './bureauvote.component.scss'
})
export class BureauvoteComponent implements OnInit{
    title = 'CRUD Bureauvote';
    data : Bureauvote = new Bureauvote;
    toUpdate : Bureauvote = new Bureauvote;
    toDelete : Bureauvote = new Bureauvote;
    bureauvoteData : any;
	fokontanyData : any;

    constructor(
        private bureauvote: BureauvoteService
        ,private fokontany:FokontanyService
    ){}
    
    ngOnInit(): void {
        this.bureauvote.get().subscribe(
            (data) => {
                this.bureauvoteData = data;
            },
            (error) => {
                console.error('Error fetching data:', error);
            }
        )
		this.fokontany.get().subscribe(
			(data) => {
				this.fokontanyData = data;	},
			(error) => {
				console.error('Error fetching data:', error);
			})
    }

    setUpdate(item : Bureauvote): void{
        this.toUpdate = item;
        this.data.id = this.toUpdate.id;
    }

    deleteItem(item : Bureauvote): void{
        this.bureauvote.delete(item).subscribe(
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
        this.bureauvote.save(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }

    submitUpdate(): any{
        this.bureauvote.update(this.data).subscribe(
            (data) => {
                console.log(data);
                location.reload();
            }
        )
    }
  

}
