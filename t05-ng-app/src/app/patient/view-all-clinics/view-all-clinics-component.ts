import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpClient, HttpParams } from '@angular/common/http';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'view-all-clinics',
  templateUrl: './view-all-clinics.component.html',
  styleUrls: ['./view-all-clinics.component.css']
})
export class ViewAllClinicsComponent implements OnInit {

  clinics: any= [];
  apt_types: any = [];
  doctors: any = [];
  searchRes: any = [];

  displayedColumns: string[] = ['name', 'address', 'avg_rating'];
  displayedColumns2: string[] = ['name', 'surname', 'avg_rate', 'apt_type']

  dataSource = new MatTableDataSource(); //klinike
  dataSource2 = new MatTableDataSource(); //lekari

  selectedRowIndex: number = -1;
  selectedClinic : any = null;

  tomorrow : Date;

  model : ClinicModel = {
    id : -1,
    name : '',
    address : '',
    avg_rating : null,
  }

  searchModel : SearchClinicModel = {
    date_field : null,
    date : '',
    appointmentType : null,
    address : '',
    avg_rate_lowest : 0,
    avg_rate_highest : 0
  }

  @ViewChild(MatSort, {static: true}) sort: MatSort;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private _snackBar: MatSnackBar, private http: HttpClient, private router: Router, private r:ActivatedRoute) { }

  ngOnInit(): void {

    let today = new Date();
    this.tomorrow = new Date(today.setDate(today.getDate() + 1));
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
    this.selectedRowIndex  = -1;

    this.http.get("https://eclinic05.herokuapp.com/clinic/getClinics").subscribe(
      (res) =>{
        this.clinics = res;
        // @ts-ignore
        this.dataSource.data = res;
      }
    )

  }

  viewProfile(row){
    this.model.id = row.id;
    this.model.name = row.name;
    this.model.address = row.address;
    this.model.avg_rating = row.avg_rating;

    this.http.get("https://eclinic05.herokuapp.com/appointmentType/getAllApointmentTypes").subscribe(
      (res) =>{
        this.apt_types = res;
      }
    );

    let params = new HttpParams();
    params = params.append('clinic_id', this.model.id.toString())
    this.http.get("https://eclinic05.herokuapp.com/doctors/getClinicsDoctors",{params:params}).subscribe(
      (res)=>{
        this.dataSource2.sort = this.sort;
        this.dataSource2.paginator = this.paginator;
        // @ts-ignore
        this.dataSource2.data = res;
        console.log(this.dataSource2.data);
      }
    );

  }

  search() {
    this.searchModel.date = this.searchModel.date_field.getDate() + "/" + (this.searchModel.date_field.getMonth()+1) + "/" + this.searchModel.date_field.getFullYear();
    let params = new HttpParams();
    params = params.append('date', this.searchModel.date.toString())
    params = params.append('appointmentType_id', this.searchModel.appointmentType.id.toString());
    this.http.get("https://eclinic05.herokuapp.com/patients/getClinics",{params:params})
      .subscribe((res) => {
          this.searchRes = res;
          if(this.searchRes.length === 0){
            this._snackBar.open("No results!", "Close", {
              duration: 2000,
            });
          }else{
            this.searchRes.forEach(element=>{
              if(element.id === this.model.id){
                this.router.navigate(['/patient/doctors'],{queryParams : {'clinic_id' : this.model.id, 'clinic': this.model.name,
                    'clinic_addr': this.model.address, 'appointment_type_id': this.searchModel.appointmentType.id,
                    'appointment_type_name': this.searchModel.appointmentType.name, 'date' : this.searchModel.date.toString()}});
              }
            })
          }
        },(err) => {
        this._snackBar.open("Something went wrong!", "Close", {
          duration: 2000,
        });
        }
      );
  }

  goToPredefinedAppointments() {
    this.router.navigate(['/patient/predefineAppointments'],{queryParams : {'clinicId' : this.model.id,}});

  }
}

export interface ClinicModel{
  id : number,
  name: String,
  address : String,
  avg_rating : number,
}

export interface SearchClinicModel{
  date_field : Date,
  date : String,
  appointmentType : {id:number, name:String},
  address : String,
  avg_rate_lowest : number,
  avg_rate_highest : number
}
