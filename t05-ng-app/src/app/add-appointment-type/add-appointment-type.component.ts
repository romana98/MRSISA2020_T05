import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";


@Component({
  selector: 'app-add-appointment-type',
  templateUrl: './add-appointment-type.component.html',
  styleUrls: ['./add-appointment-type.component.css']
})
export class AddAppointmentTypeComponent implements OnInit {

  model : AppointmentTypeModel = {
    name : '',
    admin_id : parseInt(sessionStorage.getItem('id'))
  }

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) { }

  ngOnInit(): void {
  }


  addAppointmentType() : void {
    let url = "http://localhost:8081/appointmentType/addAppointmentType";
    this.model.admin_id = parseInt(sessionStorage.getItem('user_id'));
    console.log(this.model.admin_id);
    this.http.post(url,this.model).subscribe(
      res => {
        this._snackBar.open("Appointment type successfully added", "Close", {
          duration: 2000,
        });

      },
      err => {
        this._snackBar.open("Error has occurred while adding appointment type", "Close", {
          duration: 2000,
        });
        console.log(err);
      });
    }

    checkValidation() : boolean {
      return (/^[a-zA-z]+$/.test(this.model.name));

    }

}


export interface AppointmentTypeModel{
    name: string;
    admin_id : number;

}
