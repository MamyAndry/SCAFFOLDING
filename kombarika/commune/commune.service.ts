import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CommuneService {
    url : string = "http://localhost:8080/testkarana";


    constructor(
        private http:HttpClient
    ) { }

    get() : Observable<any[]>{
        const headers = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.get<any[]>(
        this.url + "commune", 
        {headers : headers}
        );
    }

    save(item: any) : Observable<any>{
        console.log(item);
        const headers = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.post<any>(
        this.url + "commune",
        JSON.stringify(item),
        {headers:headers});
    }
  
    update(item: any) : Observable<any>{
        const headers = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        return this.http.put<any>(
        this.url + "commune",
        JSON.stringify(item),
        {headers:headers});
    }

    delete(item: any) : Observable<any>{
        const header = new HttpHeaders({
        'Content-Type': 'application/json'
        });
        const options = {
        headers: header,
        params: new HttpParams().set('data', JSON.stringify(item))
        }
        return this.http.delete<any>(
        this.url + "commune",
        options);
    }
}
