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

  authenticate(model):Promise<number> {
    let status = 0;
    let promise  =  new Promise<number>((resolve,reject) => {
      this.httpClient.post('https://eclinic05.herokuapp.com/auth/login',model).toPromise().then(
        res => {
          let tokenStr= 'Bearer ' + res['accessToken'];
          sessionStorage.setItem('token', tokenStr);
          let decoded = jwt_decode(res['accessToken']);
          sessionStorage.setItem('user_id', decoded['id']);
          sessionStorage.setItem('role', decoded['role']);
          console.log(decoded['role']);
          console.log("USAO");
          console.log(sessionStorage.getItem('role'));
          console.log(tokenStr);
          if (sessionStorage.getItem('role') === "ROLE_CLINIC_ADMIN"){
            this.router.navigate(['/clinicAdmin/addDoctor']);
          }
          else if (sessionStorage.getItem('role') === "ROLE_CLINIC_CENTER_ADMIN"){
            this.router.navigate(['/clinicCenterAdmin/addClinicCenterAdministrator']);
          }
          else if (sessionStorage.getItem('role') === "ROLE_DOCTOR"){
            this.router.navigate(['staff/viewPatients']);
          }
          else if (sessionStorage.getItem('role') === "ROLE_NURSE"){
            this.router.navigate(['staff/viewPatients']);
          }
          else if (sessionStorage.getItem('role') === "ROLE_PATIENT"){
            this.router.navigate(['/patient/viewMedicalRecord']);
          }
          status = 200;
          console.log(200);
          resolve(200);
        },
        err => {
          if (err.status === 401) {
            console.log(401);

            status = 401;
            resolve(401);
          }
          else if (err.status === 409) {
            console.log(409);

            status = 409;
            resolve(409);
          }
        }
      )
    });

    return promise;

    /*this.httpClient.post('http://localhost:8081/auth/login',model).subscribe(
       res => {
        let tokenStr= 'Bearer ' + res['accessToken'];
        sessionStorage.setItem('token', tokenStr);
        let decoded = jwt_decode(res['accessToken']);
        sessionStorage.setItem('user_id', decoded['id']);
        sessionStorage.setItem('role', decoded['role']);
        if (sessionStorage.getItem('role') === "ROLE_CLINIC_ADMIN"){
          this.router.navigate(['/clinicAdmin/addDoctor']);
        }
        else if (sessionStorage.getItem('role') === "ROLE_CLINIC_CENTER_ADMIN"){
          this.router.navigate(['/clinicCenterAdmin/addClinicCenterAdministrator']);
        }
        else if (sessionStorage.getItem('role') === "ROLE_DOCTOR"){
          this.router.navigate(['/viewPatients']);
        }
        else if (sessionStorage.getItem('role') === "ROLE_NURSE"){
          this.router.navigate(['/viewPatients']);
        }
        else if (sessionStorage.getItem('role') === "ROLE_PATIENT"){
          this.router.navigate(['/patient/clinics']);
        }
        status = 200;
        return new Promise<>
      },
      err => {
        console.log(err.status);
        if (err.status === 401) {
          status = 401;
          console.log("Vracam status" + status)
          return status;
        }
      }
     );*/
  }


isUserLoggedIn() {
  let token = sessionStorage.getItem('token');
  return !(token === null)
}

logOut() {
  sessionStorage.removeItem('token');
  sessionStorage.removeItem('role');
  sessionStorage.removeItem('id');
  this.router.navigate(['']);
}

getCurrentUserId(){
    return sessionStorage.getItem('id');

  }

}
