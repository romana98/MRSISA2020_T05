import {Component, Directive, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {clinicModel} from "../add-clinic-form/add-clinic-form.component";
import {AbstractControl, FormControl, NG_VALIDATORS, Validator, ValidatorFn, Validators} from "@angular/forms";

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

  constructor(private _snackBar: MatSnackBar, private http: HttpClient){

  }

  ngOnInit(): void{

    this.http.get("http://localhost:8081/clinicAdministrator/getClinicAdministrator")
      .subscribe((res)=>{
        this.model = <clinicAdminModel>res;
        this.model.password = '';
      });
    this.hide = true;

  }

  editPatient(): void{
    let url = "http://localhost:8081/clinicAdministrator/editClinicAdministrator"
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Your profile has been updated successfully!", "Close", {
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

@Directive({
  selector: '[requiredLen]',
  providers: [
    {provide: NG_VALIDATORS,useExisting:RequiredPassDirective, multi: true}
  ]
})
export class RequiredPassDirective implements Validator {
  @Input("requiredLen")
  requiredLen: boolean;

  validate(c:AbstractControl) {

    let value = c.value;
    if (value == null) value = '';
    if ((value.length > 0 && value.length < 8)) {
      return {
        requiredLen: {condition:false}
      };
    }
    return null;
  }

}
