import {Component, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-edit-clinic-center-administrator',
  templateUrl: './edit-clinic-center-administrator.component.html',
  styleUrls: ['./edit-clinic-center-administrator.component.css']
})
export class EditClinicCenterAdministratorComponent implements OnInit{

  model: clinicCenterAdminModel = {
    name: '',
    surname: '',
    email: '',
    password: '',

  }
  hide: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: Router, private r:ActivatedRoute){

  }

  ngOnInit(): void{

    this.http.get("https://eclinic05.herokuapp.com/clinicCenterAdministrator/getClinicCenterAdministrator")
      .subscribe((res)=>{
        this.model = <clinicCenterAdminModel>res;
        this.model.password = '';
      });
    this.hide = true;

  }

  editClinicCenterAdmin(): void{
    let url = "https://eclinic05.herokuapp.com/clinicCenterAdministrator/editClinicCenterAdministrator"
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


export interface clinicCenterAdminModel
{
  name: string | RegExp;
  surname: string | RegExp;
  email: string | RegExp;
  password: string;
}
