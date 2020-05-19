import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {ReactiveFormsModule}  from "@angular/forms";

@Component({
    selector: 'app-add-admin-clinic-form',
    templateUrl: './add-clinic-center-administrator-form.component.html',
    styleUrls: ['./add-clinic-center-administrator-form.component.css']
})
export class AddClinicCenterAdminFromComponent implements OnInit{

    model: clinicCenterAdminModel = {
        name: '',
        surname: '',
        email: '',
        password: ''
    }
  hide: boolean;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient){

    }

    ngOnInit(): void{
        this.hide = true;
    }

    AddClinicCenterAdmin(): void{
        let url = "http://localhost:8081/clinicCenterAdministrator/addClinicCenterAdministrator"
        this.http.post(url, this.model).subscribe(
            res => {
              this._snackBar.open("Clinic center administrator added successfully!", "Close", {
                duration: 2000,
              });

            },
            err => {
              if(err.status == 409)
              {
                this._snackBar.open("Email already taken!", "Close", {
                  duration: 2000,
                });

              }
              else
              {
                this._snackBar.open("Error has occurred while adding clinic center administrator!", "Close", {
                  duration: 2000,
                });
                console.log(err);
              }
            }
        );

    }
}

export interface clinicCenterAdminModel
{
    name: string | RegExp;
    surname: string | RegExp;
    email: string | RegExp;
    password: string;
}
