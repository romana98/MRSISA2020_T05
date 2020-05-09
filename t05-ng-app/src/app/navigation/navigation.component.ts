import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  currentRole : String = null;
  editProfile : String = null;

  constructor(private authservice : AuthenticationService) {

   }

  ngOnInit(): void {

  }

  checkPatient(){
    if(sessionStorage.getItem('role') === "ROLE_PATIENT"){
      this.editProfile = "patient/viewProfile";
      return true;
    }
    return false;
  }

  checkDoctor(){
    if(sessionStorage.getItem('role') === "ROLE_DOCTOR"){
      this.editProfile = "staff/viewProfile";
      return true;
    }
    return false;
  }

  checkCAdmin(){
    if(sessionStorage.getItem('role') === "ROLE_CLINIC_ADMIN"){
      this.editProfile = "clinicAdmin/viewProfile"
      return true;
    }
    return false;
  }

  checkCCAdmin(){
    if(sessionStorage.getItem('role') === "ROLE_CLINIC_CENTER_ADMIN"){
      this.editProfile = "clinicCenterAdmin/viewProfile"
      return true;
    }
    return false;
  }

  checkNurse(){
    if(sessionStorage.getItem('role') === "ROLE_NURSE"){
      this.editProfile = "staff/viewProfile";
      return true;
    }
    return false;
  }

  checkLogged(){
    if(sessionStorage.getItem('role') != null){
      return true;
    }
    return false;
  }

  logout(){
    this.authservice.logOut();
  }

}
