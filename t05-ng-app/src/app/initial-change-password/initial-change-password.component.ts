import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute, Params, Router} from "@angular/router";
import * as jwt_decode from "jwt-decode";

@Component({
  selector: 'initial-change-password',
  templateUrl: './initial-change-password.component.html',
  styleUrls: ['./initial-change-password.component.css']
})
export class InitialChangePasswordComponent implements OnInit {

  model: passwordModel = {
      password: '',
      password2: '',
      email: ''
  }

  hide : boolean = true;
  hide1: boolean = true;

  constructor(private _snackBar: MatSnackBar, private http : HttpClient, private router: Router,
              private route: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.model.email = params['email']);
  }

  changePassword(): void{
    let url = "https://eclinic05.herokuapp.com/auth/changePassword"
    this.http.post(url,{email: this.model.email, password:this.model.password}).subscribe(
      res => {
        this._snackBar.open("Password successfully changed!", "Close", {
          duration: 2000,
        });

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
          this.router.navigate(['staff/viewPatients']);
        }
        else if (sessionStorage.getItem('role') === "ROLE_NURSE"){
          this.router.navigate(['staff/viewPatients']);
        }
        else if (sessionStorage.getItem('role') === "ROLE_PATIENT"){
          this.router.navigate(['/patient/clinics']);
        }

      },
      err => {
         this._snackBar.open("Error has occurred while changing your password!", "Close", {
            duration: 2000,
          });
      }
    );
  }

  checkPassword():boolean
  {
    if(this.model.password === this.model.password2)
    {
      return true;
    }
    else
    {
      this._snackBar.open("Passwords do not match!", "Close", {
        duration: 2000,
      });
      return false;
    }

  }

}

export interface passwordModel{
 password: String;
 password2: String;
 email: String;
}
