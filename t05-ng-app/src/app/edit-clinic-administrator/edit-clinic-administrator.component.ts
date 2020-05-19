import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-edit-clinic-administrator',
  templateUrl: './edit-clinic-administrator.component.html',
  styleUrls: ['./edit-clinic-administrator.component.css']
})
export class EditClinicAdministratorComponent implements OnInit{

  model: clinicAdminModel = {
    name: '',
    surname: '',
    email: '',
    password: '',
    clinic: new class implements clinicModel {
      address: '';
      description: '';
      name: '';
    }
  }
  hide: boolean;
  clinic_info : string;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: Router, private r:ActivatedRoute){

  }

  ngOnInit(): void{

    this.http.get("/clinicAdministrator/getClinicAdministrator")
      .subscribe((res)=>{
        this.model = <clinicAdminModel>res;
        this.model.password = '';
        this.clinic_info = this.model.clinic.name + ", " + this.model.clinic.address;
      });
    this.hide = true;

  }

  editClinicAdmin(): void{
    let url = "/clinicAdministrator/editClinicAdministrator"
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Your profile has been updated successfully!", "Close", {
          duration: 2000,
        });

        let booleanPromise = this.router.navigate(["../viewProfile"], {relativeTo: this.r});

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

  checkPassword() {

    return this.model.password.length == 0 || this.model.password.length >= 8;
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
