import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ClinicCenterAdminGuardService {

  constructor(private router: Router,private authservice: AuthenticationService) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    if(sessionStorage.getItem('role')===("ROLE_CLINIC_CENTER_ADMIN")){
      return true;
    }
    else{
      this.router.navigate(['']);
      return false;
    }
  }
}
