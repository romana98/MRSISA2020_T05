import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Component({
  selector: 'app-add-appointment-type',
  templateUrl: './add-appointment-type.component.html',
  styleUrls: ['./add-appointment-type.component.css']
})
export class AddAppointmentTypeComponent implements OnInit {

  model : AppointmentTypeModel = {
    name : ''
  }

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }


  addAppointmentType() : void {
    let url = "http://localhost:8081//appointmentType/addAppointmentType"
    this.http.post(url,this.model).subscribe(
      res => {
        alert("Appointment type successfully added.");
      },
      err => {
        alert("Error has occured while adding appointment type");
        console.log(err);
      });
    }

    checkValidation() : boolean {
      return (/^[a-zA-z]+$/.test(this.model.name));

    }

}


export interface AppointmentTypeModel{
    name: string;

}