import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UserService} from './user.service';

@Injectable({
  providedIn: 'root'
})
export class MatchService {

private matchListURL : string;
private addToFavURL : string;
private getFavList : string;
private getDetails : string;
private removeFromFavURL : string;

  constructor(
    private http: HttpClient,
    private userService: UserService
  ) { 
        this.matchListURL = 'http://localhost:8083/current';
        this.addToFavURL = 'http://localhost:8083/addToFavList/';
        this.removeFromFavURL = 'http://localhost:8083/removeFromFavList/';
        this.getFavList = "http://localhost:8083/favList/";
        this.getDetails = "http://localhost:8083/details/";
  }

  getAllMatches(): Observable<any> {
   
    return this.http.get<any>(this.matchListURL);

}

addToFavMatches(favMatchId): Observable<any> {
  const httpOptions = {
      headers: new HttpHeaders({
          'userID': this.userService.userId
      })
  };
  return this.http.post<any>(this.addToFavURL + favMatchId, null, httpOptions);
}

getAllWishListMatches(): Observable<any> {
  return this.http.get<any>(this.getFavList+this.userService.userId);
}

getMatchDetails(favMatchId): Observable<any> {
  return this.http.get<any>(this.getDetails+favMatchId);
}

async addToFavMatchesAsync(favMatchId): Promise<any> {
  const httpOptions = {
      headers: new HttpHeaders({
          'userID': this.userService.userId
      })
  };
  return this.http.post<any>(this.addToFavURL + favMatchId, null, httpOptions).toPromise();
}

removeFromFavMatches(favMatchId): Observable<any> {
  const httpOptions = {
      headers: new HttpHeaders({
          'userID': this.userService.userId
      })
  };
  
  return this.http.delete<any>(this.removeFromFavURL + favMatchId, httpOptions);
  
}

async removeFromFavMatchesAsync(favMatchId): Promise<any> {
  const httpOptions = {
      headers: new HttpHeaders({
          'userID': this.userService.userId
      })
  };
  
  return this.http.delete<any>(this.removeFromFavURL + favMatchId, httpOptions).toPromise();
  
}



}
