import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-predifined-appointment',
  templateUrl: './add-predifined-appointment.component.html',
  styleUrls: ['./add-predifined-appointment.component.css']
})
export class AddPredifinedAppointmentComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

export interface appointmentModel
{
    Date : Date;
    duration : number;
    price : number;
    request : boolean;
    predefined : boolean;

    
}
