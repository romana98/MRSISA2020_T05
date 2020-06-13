import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-add-another-appointment',
  templateUrl: './add-another-appointment.component.html',
  styleUrls: ['./add-another-appointment.component.css']
})
export class AddAnotherAppointmentComponent implements OnInit {

  patient_name = 'Pat_name';
  patient_surname = 'Pat_surname';

  duration_invalid : boolean = true;

  today: Date;

  passModel : DataModel = {
    date : '',
    date_field : null,
    time : '',
    duration : '',
    type : '',
    app_id : ''
  }
  
  durControl : FormControl = new FormControl(10, [Validators.max(60), Validators.min(10)])

  constructor(private http:HttpClient,private _snackBar:MatSnackBar,private router: ActivatedRoute, private r: Router) { }

  ngOnInit(): void {
    this.today = new Date();
    this.passModel.app_id = this.router.snapshot.queryParamMap.get('app_id');
  }

  addAppointment(){
    let url = "http://localhost:8081/appointment/addAnotherAppointment";
    this.passModel.duration = this.durControl.value;
    this.passModel.date = this.passModel.date_field.getDate() + "/" + (this.passModel.date_field.getMonth()+1) + "/" + this.passModel.date_field.getFullYear();
    this.http.post(url,this.passModel).subscribe(
      res => {
        //poziv kako bismo nakon dodavanja appointmenta dobili novije podatke u tabel
        let booleanPromise = this.r.navigate(["../viewPatients"]);
        this._snackBar.open("Appointment added successfully.", "Close", {
          duration: 2000,
        });
      },
      err => {
        this._snackBar.open("Error has occurred while adding appointment", "Close", {
          duration: 2000,
        });
        console.log(err);
      });
  }

}

export interface DataModel{
  date_field : Date,
  date : String,
  time : String,
  duration : String,
  type : String,
  app_id : String
}