/** Service call to login service */

import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable({
    providedIn: 'root'
})
export class LoginService {
    private loginUrl: string;
    constructor(
        private http: HttpClient
    ) {
        this.loginUrl = 'http://localhost:8084/user/login';
    }
    login(user: any): Observable<any> {
        return this.http.post<any>(this.loginUrl, user);
    }
}
