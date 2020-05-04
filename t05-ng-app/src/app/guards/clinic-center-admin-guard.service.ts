import { Injectable } from '@angular/core';
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class ClinicCenterAdminGuardService {

  constructor(private router: Router,private authservice: AuthenticationService) { }
  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

<<<<<<< HEAD
    let bar : String = String(sessionStorage.getItem('role'));
    let str : String = String("ROLE_CLINIC_CENTER_ADMIN");

    console.log(sessionStorage.getItem('role').localeCompare(str.toString()));
    console.log(bar === str);

    console.log(this.authservice.isUserLoggedIn());
    if(sessionStorage.getItem('role').localeCompare("ROLE_CLINIC_CENTER_ADMIN") === 0){
=======
    if(sessionStorage.getItem('role')===("ROLE_CLINIC_CENTER_ADMIN")){
>>>>>>> refs/remotes/origin/master
      return true;
    }
    else{
      this.router.navigate(['']);
      return false;
    }
  }
}
