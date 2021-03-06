import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import {MatSnackBar} from "@angular/material/snack-bar";
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-add-doctor-form',
  templateUrl: './add-doctor-form.component.html',
  styleUrls: ['./add-doctor-form.component.css']
})
export class AddDoctorFormComponent implements OnInit {

  displayedColumns: string[] = ['name', 'surname', 'email','appointmentName','delete'];

  model: doctorModel = {
    name : '',
    surname : '',
    email : '',
    password : '',
    workEnd : '',
    workStart : '',
    appointment_type_id : 0,
    //ovde treba preuzeti od administratora koji dodaje kliniku zapravo
    clinic_id : null
  }

  searchModel : searchModel = {
    parameter : '',
    value : '',
    clinic_id : -1
  }

  dataSource = new MatTableDataSource();

  appointmentTypes : any=[];

  hide: boolean;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient) {

  }

  ngOnInit(): void {
    this.http.get("http://localhost:8081/appointmentType/getAllApointmentTypes").subscribe(
      res => {
        // @ts-ignore
        this.appointmentTypes= res;

      });

    let params1 = new HttpParams().set('admin_id',sessionStorage.getItem('user_id'))
    this.http.get("http://localhost:8081/clinicAdministrator/getAdminsClinic",{params:params1}).subscribe(
      res => {
        this.model.clinic_id = res;
        console.log(this.model.clinic_id)
        let params2 = new HttpParams().set('clinic_id',res.toString());
        this.http.get("http://localhost:8081/doctors/getClinicsDoctors",{params:params2}).subscribe(
          res => {
            // @ts-ignore
            this.dataSource.data = res;

          });
      });
  }

  addDoctor(): void{
    console.log(this.model.name + this.model.surname);
    let url =  "http://localhost:8081/doctors/addDoctor"
    console.log(this.model);
    this.http.post(url,this.model).subscribe(
        res => {
          console.log(this.model.clinic_id)
          let params2 = new HttpParams().set('clinic_id',this.model.clinic_id.toString());
              this.http.get("http://localhost:8081/doctors/getClinicsDoctors",{params:params2}).subscribe(
                  res => {
                    // @ts-ignore
                    this.dataSource.data = res;

          });
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

  deleteDoctor(element): void{
    let url =  "http://localhost:8081/doctors/deleteDoctor"
    let params = new HttpParams().set('doctor_id', element.id)
    this.http.delete(url,{params:params}).subscribe(
        res => {
          let params2 = new HttpParams().set('clinic_id',this.model.clinic_id.toString());
              this.http.get("http://localhost:8081/doctors/getClinicsDoctors",{params:params2}).subscribe(
                  res => {
                    // @ts-ignore
                    this.dataSource.data = res;

          });
          this._snackBar.open("Doctor deleted successfully", "Close", {
            duration: 2000,
          });

        },
        err => {
          this._snackBar.open("Error has occurred while deleting doctor", "Close", {
            duration: 2000,
          });

          console.log(err)
        }
    );
  }

  search() : void {
    let params = new HttpParams();
    params = params.append('parameter', this.searchModel.parameter );
    params = params.append('value',this.searchModel.value);
    params = params.append('clinic_id' , this.model.clinic_id);
    this.http.get("http://localhost:8081/clinicAdministrator/searchDoctors", {params:params})
      .subscribe((res) => {
        // @ts-ignore
        this.dataSource.data = res;
        document.getElementById("errorMsg").style.display = "none";
    },
    err => {
        let params2 = new HttpParams().set('clinic_id',this.model.clinic_id.toString());
        this.http.get("http://localhost:8081/doctors/getClinicsDoctors",{params:params2}).subscribe(
          res => {
            // @ts-ignore
            this.dataSource.data = res;

        });
        document.getElementById("errorMsg").style.display = "block";
        this.searchModel.value = "";
    });
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
    clinic_id : any;

}

export interface searchModel
{
   parameter: string;
   value : string;
   clinic_id : any;

}
