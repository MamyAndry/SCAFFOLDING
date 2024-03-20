import { Routes } from '@angular/router';
import { BureauvoteComponent } from "./bureauvote/bureauvote.component";
import { CommuneComponent } from "./commune/commune.component";
import { DistrictComponent } from "./district/district.component";
import { FokontanyComponent } from "./fokontany/fokontany.component";
import { RegionComponent } from "./region/region.component";


export const routes: Routes = [
	{path:"bureauvote", component: BureauvoteComponent},
	{path:"commune", component: CommuneComponent},
	{path:"district", component: DistrictComponent},
	{path:"fokontany", component: FokontanyComponent},
	{path:"region", component: RegionComponent},
	
];
