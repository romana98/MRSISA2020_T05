import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class LoginGuardService {

  constructor(private router: Router,private authservice: AuthenticationService) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    //ovde ide check da li je trenutni korisnik pacijent
    if(this.authservice.isUserLoggedIn()){

      if (sessionStorage.getItem('role') === "ROLE_CLINIC_ADMIN"){
        this.router.navigate(['/AddDoctorFormComponent']);
      }
      else if (sessionStorage.getItem('role') === "ROLE_CLINIC_CENTER_ADMIN"){
        
        this.router.navigate(['/addClinicCenterAdministrator']);
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
      return false;
    }
    else{

      return true;
    }
  }
}
