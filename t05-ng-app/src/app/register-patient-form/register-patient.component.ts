import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-register-patient-form',
    templateUrl: './register-patient.component.html',
    styleUrls: ['./register-patient.component.css']
})
export class RegisterPatientForm implements OnInit{

    password2 : '';

    model: patientModel = {
        email : '',
        password : '',
        name : '',
        surname : '',
        address : '',
        city : '',
        country : '',
        phone_number : '',
        insurance_number : ''
    }
  hide: boolean;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient){
    this.hide = true;
    }

    ngOnInit(): void{

    }

    registerPatient(): void{
        let url = "http://localhost:8081/registrationRequests/registerPatient"
        this.http.post(url,this.model).subscribe(
            res => {
              this._snackBar.open("Your registration request has been sent! Your activation link will be sent to you soon", "Close", {
                duration: 2000,
              });

            },
            err => {
              if(err.status == 409)
              {
                this._snackBar.open("Email already taken", "Close", {
                  duration: 2000,
                });

              }
            else
              {
                this._snackBar.open("Error has occurred while registering your profile", "Close", {
                  duration: 2000,
                });

              }
            }
        );
    }

    checkPassword(): boolean{
        var pass1 = this.model.password;
        var pass2 = this.password2;

        if(pass1 === pass2){
            return true;
        }else{
            return false;
        }
    }
}
    export interface patientModel{
        email : string;
        password : string;
        name : string;
        surname : string;
        address : string;
        city : string;
        country : string;
        phone_number : string;
        insurance_number : string;
    }

