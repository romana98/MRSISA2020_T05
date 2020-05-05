import {Component, Directive, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {patientModel} from "../edit-patient/edit-patient.component";
import {AbstractControl, FormControl, NG_VALIDATORS, Validator, ValidatorFn, Validators} from "@angular/forms";

@Component({
    selector: 'app-edit-medical-staff',
    templateUrl: './edit-medical-staff.component.html',
    styleUrls: ['./edit-medical-staff.component.css']
})
export class EditMedicalStaff implements OnInit{

    model: medicalStaffModel = {
        email : '',
        password : '',
        name : '',
        surname : '',
        type: ''
    }
  hide: boolean;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient){

    }

    ngOnInit(): void{
        this.hide = true;
      let url = "http://localhost:8081/medicalStaff/getData";
      this.http.get(url).subscribe(
        res => {
          this.model = <medicalStaffModel>res;
          this.model.password = '';
        }
      )
    }

    editMedicalStaff(): void{
        let url = "http://localhost:8081/medicalStaff/editMedicalStaff"
        this.http.post(url,this.model).subscribe(
            res => {
              this._snackBar.open("Your profile has been updated successfully!", "Close", {
                duration: 2000,
              });

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

    return this.model.password.length === 0 || this.model.password.length >= 8;
  }
}
    export interface medicalStaffModel{
        email : string |RegExp;
        password : string;
        name : string |RegExp;
        surname : string |RegExp;
        type : string;
    }
