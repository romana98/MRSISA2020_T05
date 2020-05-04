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
      this.router.navigate(['/403']);
      return false;
    }
    else{
      return true;
    }
  }
}
