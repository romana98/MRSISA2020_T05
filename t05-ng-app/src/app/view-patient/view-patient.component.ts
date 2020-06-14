import {Component, Directive, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {AbstractControl, FormControl, NG_VALIDATORS, Validator, ValidatorFn, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";


@Component({
  selector: 'app-view-patient',
  templateUrl: './view-patient.component.html',
  styleUrls: ['./view-patient.component.css']
})
export class ViewPatientProfile implements OnInit{

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

  goToEdit(): void {
    let booleanPromise = this.router.navigate(["../editProfile"], {relativeTo: this.r});
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



