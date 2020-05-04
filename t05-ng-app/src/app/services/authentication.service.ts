import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import * as jwt_decode from 'jwt-decode';
import { Router } from '@angular/router';

export class User{
  constructor(
    public token:string,
     ) {}
  
}


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(
    private httpClient:HttpClient,private router: Router
  ) { }

  authenticate(model) {
    this.httpClient.post('http://localhost:8081/auth/login',model).subscribe(
       res => {
        let tokenStr= 'Bearer ' + res['accessToken'];
        sessionStorage.setItem('token', tokenStr);
        let decoded = jwt_decode(res['accessToken']);
        sessionStorage.setItem('user_id',JSON.stringify(decoded['id']));
        sessionStorage.setItem('role', JSON.stringify(decoded['role']));
        /*if (sessionStorage.getItem('role').localeCompare("Role")){
          this.router.navigate(['/patient/clinics']);
        }*/
        console.log(sessionStorage.getItem('user_id') + " " + sessionStorage.getItem('role'));
        return res;  
      }
     );
  }


isUserLoggedIn() {
  let token = sessionStorage.getItem('token');
  return !(token === null)
}

logOut() {
  sessionStorage.removeItem('token');
  sessionStorage.removeItem('role');
  sessionStorage.removeItem('id');
}

getCurrentUserId(){
    return sessionStorage.getItem('id');

  }

}
