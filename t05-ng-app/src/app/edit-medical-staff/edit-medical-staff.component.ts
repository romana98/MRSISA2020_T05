import { Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
    selector: 'app-edit-medical-staff',
    templateUrl: './edit-medical-staff.component.html',
    styleUrls: ['./edit-medical-staff.component.css']
})
export class EditMedicalStaff implements OnInit{

    model: medicalStaffModel = {
        email : 'email1@email.com',
        password : 'password1',
        name : 'Ime1',
        surname : 'Prezime1',
        type : 'doctor'
    }
  hide: boolean;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient){

    }

    ngOnInit(): void{
        this.hide = true;
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
}
    export interface medicalStaffModel{
        email : string |RegExp;
        password : string;
        name : string |RegExp;
        surname : string |RegExp;
        type : string;
    }

