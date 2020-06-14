import {Component, Directive, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {AbstractControl, FormControl, NG_VALIDATORS, Validator, ValidatorFn, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
    selector: 'app-edit-patient',
    templateUrl: './edit-patient.component.html',
    styleUrls: ['./edit-patient.component.css']
})
export class EditPatientProfile implements OnInit{

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

    constructor(private _snackBar: MatSnackBar, private http: HttpClient,
                private router: Router, private r:ActivatedRoute){

    }

    ngOnInit(): void{
        this.hide = true;
        let url = "https://eclinic05.herokuapp.com/patients/getPatient";
        this.http.get(url).subscribe(
          res => {
            this.model = <patientModel>res;
            this.model.password = '';
          }
        )
    }

    editPatient(): void{
        let url = "https://eclinic05.herokuapp.com/patients/editPatient"
        this.http.post(url,this.model).subscribe(
            res => {
              this._snackBar.open("Your profile has been updated successfully!", "Close", {
                duration: 2000,
              });

              let booleanPromise = this.router.navigate(["../viewProfile"], {relativeTo: this.r});

            },
            err => {
              this._snackBar.open("Error has occurred while updating your profile!", "Close", {
                duration: 2000,
              });
              console.log(err)
            }
        );

    }
  checkPassword() {

    return this.model.password.length == 0 || this.model.password.length >= 8;
  }
}
    export interface patientModel{
        email : string |RegExp;
        password : string;
        name : string |RegExp;
        surname : string |RegExp;
        address : string |RegExp;
        city : string |RegExp;
        country : string |RegExp;
        phone_number : string |RegExp;
        insurance_number : string |RegExp;
    }



