import { Component, OnInit, Inject} from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-predifined-appointment',
  templateUrl: './add-predifined-appointment.component.html',
  styleUrls: ['./add-predifined-appointment.component.css']
})
export class AddPredifinedAppointmentComponent implements OnInit {

  doctors: any=[];
  halls: any=[];
  appointmentTypes: any = [];
  duration_invalid : boolean = true;

  durControl : FormControl = new FormControl(10, [Validators.max(60), Validators.min(10)])

  model : appointmentModel = {
    date_field : null,
    date : '',
    time : '',
    duration : 0,
    price : 0,
    request : false,
    predefined : true,
    doctor_id : null,
    hall_id : null,
    appointmentType_id: null,
    clinic_id : 1
  };

  today: Date;

  constructor(private _snackBar: MatSnackBar,private http: HttpClient) {

   }
  /**  Treba dodati deo u kom se inicijalizuje za koju kliniku se trazi admin
   * u ovom slucaju je to hardcodovana vrednost jer jos nemamo logovanje **/

  ngOnInit(): void {
    this.today = new Date();
    let params1 = new HttpParams().set('admin_id',sessionStorage.getItem('user_id'))
    this.http.get("http://localhost:8081/clinicAdministrator/getAdminsClinic",{params:params1}).subscribe(
      res => {
            this.model.clinic_id = res;
            console.log(this.model.clinic_id);
            let params = new HttpParams().set('clinic_id', this.model.clinic_id.toString() );
            let url = "http://localhost:8081/halls/getClinicHall";
            this.http.get(url,{params:params}).subscribe(
              res => {
                    this.halls = res;
              });


      });
    console.log(sessionStorage.getItem('token'));
        this.http.get("http://localhost:8081/appointmentType/getAllApointmentTypes").subscribe(
          res => {
        // @ts-ignore
              this.appointmentTypes = res;
              
        });


  }

  addAppointment() : void {
    let url = "http://localhost:8081/appointment/addAppointment";
    this.model.date = this.model.date_field.getDate() + "/" + (this.model.date_field.getMonth()+1) + "/" + this.model.date_field.getFullYear();
    this.model.duration = this.durControl.value;
    this.http.post(url, this.model).subscribe(
      res => {
            this._snackBar.open("Appointment added successfully", "Close", {
              duration: 2000,
      });
    },err=>{
      this._snackBar.open("Failed to add apointment, hall or doctor are busy.", "Close", {
        duration: 2000,
    })});

  }

  onChange() {
    let params = new HttpParams();
    params = params.append('clinic_id', this.model.clinic_id.toString());
    params = params.append('appointment_type_id',this.model.appointmentType_id.toString());
    let url = "http://localhost:8081/doctors/getDoctorsAppointment";
    this.http.get(url,{params:params}).subscribe(
      res => {
            this.doctors = res;
            console.log(res);
      });
    let url2 = "http://localhost:8081/pricelist/getAptypePrice";
    let params2 = new HttpParams();
    params2 = params2.append('clinic_id', this.model.clinic_id.toString());
    params2 = params2.append('appointment_type_id',this.model.appointmentType_id.toString());
      this.http.get(url2,{params:params2}).subscribe(
        res => {
          //@ts-ignore
          this.model.price = res;
          console.log(res);
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
    clinic_id :any;

}

