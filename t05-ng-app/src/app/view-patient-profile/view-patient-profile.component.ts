import { Component, OnInit } from '@angular/core';
import {MatSnackBar} from "@angular/material/snack-bar";
import {HttpClient, HttpParams} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-view-patient-profile',
  templateUrl: './view-patient-profile.component.html',
  styleUrls: ['./view-patient-profile.component.css']
})
export class ViewPatientProfileComponent implements OnInit {

  model: patientModel = {
    email : '',
    name : '',
    surname : '',
    address : '',
    city : '',
    country : '',
    phone_number : '',
    insurance_number : '',
    password: '',
  }

  constructor(private _snackBar: MatSnackBar, private http: HttpClient,
              private router: Router, private r:ActivatedRoute) { }

  ngOnInit(): void {

    let url = "http://localhost:8081/patients/getPatientDoctor";

    this.http.get(url, {params:{email: this.r.snapshot.queryParamMap.get('email')}}).subscribe(
      res => {
        this.model = <patientModel>res;
      }
    )
  }

  goToMedicalRecord() {

  }

  startAppointment() {
    this.router.navigate(['/staff/currentAppointment'],{queryParams : {'patient': this.model.name + ' ' + this.model.surname,
       'insurance': this.model.insurance_number, 'email': this.model.email, 'appId' : this.model.password}});

  }
}

export interface patientModel{
  email : string |RegExp;
  name : string |RegExp;
  surname : string |RegExp;
  address : string |RegExp;
  city : string |RegExp;
  country : string |RegExp;
  phone_number : string |RegExp;
  insurance_number : string |RegExp;
  password: string;
}
