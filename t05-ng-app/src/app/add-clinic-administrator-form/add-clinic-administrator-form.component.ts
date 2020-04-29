import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpErrorResponse} from '@angular/common/http';
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
    selector: 'app-add-admin-form',
    templateUrl: './add-clinic-administrator-form.component.html',
    styleUrls: ['./add-clinic-administrator-form.component.css']
})
export class AddClinicAdminFromComponent implements OnInit{

        model: clinicAdminModel = {
        name: '',
        surname: '',
        email: '',
        password: '',
        clinic: new class implements clinicModel {
          address: string;
          description: string;
          name: string | RegExp;
        }
    }
    clinics: any=[];
    hide: boolean;

    constructor(private _snackBar: MatSnackBar, private http: HttpClient){

    }


    ngOnInit(): void{
         this.http.get("http://localhost:8081/clinic/getClinics")
          .subscribe((res)=>{
            this.clinics = res;
            this.model.clinic = res[0];
        });
        this.hide = true;
    }

    AddClinicAdmin(): void{
        let url = "http://localhost:8081/clinicAdministrator/addClinicAdministrator"
        this.http.post(url, this.model).subscribe(
            res => {
              this._snackBar.open("Admin added successfully!", "Close", {
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
                  this._snackBar.open("Error has occurred while adding admin!", "Close", {
                    duration: 2000,
                  });

                  console.log(err);
                }
            }
        );

    }
}

export interface clinicAdminModel
{
    name: string | RegExp;
    surname: string | RegExp;
    email: string | RegExp;
    password: string;
    clinic: clinicModel;
}
