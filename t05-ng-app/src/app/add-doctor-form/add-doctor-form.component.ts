import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-doctor-form',
  templateUrl: './add-doctor-form.component.html',
  styleUrls: ['./add-doctor-form.component.css']
})
export class AddDoctorFormComponent implements OnInit {

  model: doctorModel = {
    name : '',
    surname : '',
    email : '',
    password : '',
    workEnd : '',
    workStart : '',
    appointment_type_id : 0,
    //ovde treba preuzeti od administratora koji dodaje kliniku zapravo
    clinic_id : parseInt(sessionStorage.getItem('id'))
  }

  appointmentTypes : any=[];

  hide: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) {

  }

  ngOnInit(): void {
    let url = "http://localhost:8081/appointmentType/getAppointmentTypes";
    this.http.get(url).subscribe(
      res => {
            this.appointmentTypes = res;
      });


  }

  addDoctor(): void{
    let url =  "http://localhost:8081/doctors/addDoctor"
    this.http.post(url,this.model).subscribe(
        res => {
          this._snackBar.open("Doctor added successfully", "Close", {
            duration: 2000,
          });

        },
        err => {
          this._snackBar.open("Error has occurred while adding doctor", "Close", {
            duration: 2000,
          });

          console.log(err)
        }
    );

  }
}

export interface doctorModel
{
    name: string;
    surname: string;
    email : string;
    password : string;
    workStart : string;
    workEnd : string;
    appointment_type_id : number;
    clinic_id : number;

}
