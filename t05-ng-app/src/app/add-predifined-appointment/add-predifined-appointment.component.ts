import { Component, OnInit } from '@angular/core';

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
    Date : null,
    duration : 0,
    price : 0,
    request : false,
    predefined : true,
    doctor_id : 0,
    hall_id : 0,
    appointmentType_id: 0
  };

  constructor() { }
  /**  Treba dodati deo u kom se inicijalizuje za koju kliniku se trazi admin
   * u ovom slucaju je to hardcodovana vrednost jer jos nemamo logovanje **/
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
    doctor_id : number;
    hall_id : number;
    appointmentType_id: number;
    
}
