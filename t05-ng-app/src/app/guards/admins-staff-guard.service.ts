import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AdminsStaffGuardService {

  constructor(private router: Router,private authservice: AuthenticationService) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    //ovde ide check da li je trenutni korisnik pacijent
    if(sessionStorage.getItem('role')===("ROLE_NURSE") || sessionStorage.getItem('role')===("ROLE_DOCTOR")
      || sessionStorage.getItem('role')===("ROLE_CLINIC_CENTAR_ADMIN")|| sessionStorage.getItem('role')===("ROLE_CLINIC_ADMIN")){
      return true;
    }
    else{
      this.router.navigate(['']);
      return false;
    }
  }
}
