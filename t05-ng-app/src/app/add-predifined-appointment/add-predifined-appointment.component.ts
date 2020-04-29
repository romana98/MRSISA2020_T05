import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-add-predifined-appointment',
  templateUrl: './add-predifined-appointment.component.html',
  styleUrls: ['./add-predifined-appointment.component.css']
})
export class AddPredifinedAppointmentComponent implements OnInit {

  doctors: any[];
  halls: any[];
  appointmentTypes: any[];
  clinic_id : 1;
  model : appointmentModel = {
    date_field : null,
    date : '',
    time : '',
    duration : 0,
    price : 0,
    request : false,
    predefined : true,
    doctor_id : 0,
    hall_id : 0,
    appointmentType_id: 0
  };

  constructor(private _snackBar: MatSnackBar,private http: HttpClient) { }
  /**  Treba dodati deo u kom se inicijalizuje za koju kliniku se trazi admin
   * u ovom slucaju je to hardcodovana vrednost jer jos nemamo logovanje **/
  ngOnInit(): void {

  }

  addAppointment() : void {
    let url = "http://localhost:8081/appointment/addAppointment";
    this.model.date = this.model.date_field.getDate() + "/" + (this.model.date_field.getMonth()+1) + "/" + this.model.date_field.getFullYear();
    this.http.post(url, this.model).subscribe(
      res => {
            this._snackBar.open("Appointment added successfully", "Close", {
              duration: 2000,
      });
    });

  }

}

export interface appointmentModel
{
    date_field : Date;
    date : string;
    time : string;
    duration : number;
    price : number;
    request : boolean;
    predefined : boolean;
    doctor_id : number;
    hall_id : number;
    appointmentType_id: number;
    
}
