import {Component, Directive, Input, OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import {patientModel} from "../edit-patient/edit-patient.component";
import {AbstractControl, FormControl, NG_VALIDATORS, Validator, ValidatorFn, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-view-medical-staff',
  templateUrl: './view-medical-staff.component.html',
  styleUrls: ['./view-medical-staff.component.css']
})
export class ViewMedicalStaff implements OnInit{

  model: medicalStaffModel = {
    email : '',
    password : '',
    name : '',
    surname : '',
    type: '',
    workStart: '',
    workEnd: '',
  }
  hide: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: Router, private r:ActivatedRoute){

  }

  ngOnInit(): void{
    this.hide = true;
    let url = "https://eclinic05.herokuapp.com/medicalStaff/getData";
    this.http.get(url).subscribe(
      res => {
        this.model = <medicalStaffModel>res;
        this.model.password = '';
      }
    )
  }

  goToEdit(): void {
    let booleanPromise = this.router.navigate(["../editProfile"], {relativeTo: this.r});
  }

}
export interface medicalStaffModel{
  email : string |RegExp;
  password : string;
  name : string |RegExp;
  surname : string |RegExp;
  type : string;
  workStart : string;
  workEnd : string;

}
