import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DistrictService {
    url : string = "http://localhost:8080/testkarana/";


    constructor(
        private http:HttpClient
    ) { }

    get() : Observable<any[]>{
        const headers = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.get<any[]>(
        this.url + "district", 
        {headers : headers}
        );
    }

    save(item: any) : Observable<any>{
        console.log(item);
        const headers = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.post<any>(
        this.url + "district",
        JSON.stringify(item),
        {headers:headers});
    }
  
    update(item: any) : Observable<any>{
        const headers = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.put<any>(
        this.url + "district",
        JSON.stringify(item),
        {headers:headers});
    }

    delete(item: any) : Observable<any>{
        const header = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.delete<any>(
        this.url + "district",
        {headers : header, body : JSON.stringify(item)});
    }
}
