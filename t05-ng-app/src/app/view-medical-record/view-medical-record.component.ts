import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {MatSnackBar} from "@angular/material/snack-bar";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-view-medical-record',
  templateUrl: './view-medical-record.component.html',
  styleUrls: ['./view-medical-record.component.css']
})
export class ViewMedicalRecordComponent implements OnInit {

  displayedColumns: string[] = ['date', 'time', 'duration', 'price', 'doctor', 'hall', 'appointmentType'];

  displayedColumns1: string[] = ['name', 'description', 'date'];

  dataSource = new MatTableDataSource();

  dataSource1 = new MatTableDataSource();

  selected:string = "";

  modelDis: any[];

  selected_radio:any;
  rate:number = 0;
  ratedDoctor:boolean = false;
  ratedClinic:boolean = false;
  selected_row:number = -1;

  elements: any[];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatPaginator, {static: true}) paginator1: MatPaginator;


  constructor(private _snackBar: MatSnackBar,private router: ActivatedRoute, private http: HttpClient) { }

  ngOnInit(): void {

    let url = "http://localhost:8081/patients/getDiseasePatient";
    let url1 = "http://localhost:8081/appointment/getFinishedAppointments";

    this.http.get(url).subscribe(
      res => {
        // @ts-ignore
        this.modelDis = res;
      }
    )

    this.http.get(url1).subscribe(
      res => {

        // @ts-ignore
        this.dataSource.data = res;
        // @ts-ignore
        this.elements = res;
        this.dataSource.data.forEach(data =>{
          // @ts-ignore
          this.dataSource1.data.push(data.diagnosis)
        })
        this.dataSource1._updateChangeSubscription()
      }
    )

    this.dataSource.paginator = this.paginator;
    this.dataSource1.paginator = this.paginator1;
  }

  Select(row: any) {
    this.ratedClinic = row.ratedClinic;
    this.ratedDoctor = row.ratedDoctor;
    this.selected = row.description;
    this.model.apt_id = row.id;
    this.selected_row = this.dataSource.data.indexOf(row);

  }


  model:rateModel = {
    param: 0,
    rate: 0,
    apt_id: 0
  }

  rating() {
    this.model.param = this.selected_radio;
    this.model.rate = this.rate;

    console.log(this.model);
    this.http.post("https://eclinic05.herokuapp.com/patients/rate", this.model).subscribe(
      res => {
        this._snackBar.open("Successfully rated!", "Close", {
          duration: 2000,
        });

        console.log(this.selected_radio)

        if(this.selected_radio == 1){
        // @ts-ignore
        this.dataSource.data[this.selected_row].ratedClinic = !this.ratedClinic;
        this.ratedClinic = !this.ratedClinic;
        }else{
        // @ts-ignore
        this.dataSource.data[this.selected_row].ratedDoctor = !this.ratedDoctor;
          this.ratedDoctor = !this.ratedDoctor;}
        this.dataSource._updateChangeSubscription()
        //this.refreshData();
      }, err =>{
        this._snackBar.open("Something went wrong!", "Close", {
          duration: 2000,
        });
      }
    )

  }

  refreshData(){
    let url = "https://eclinic05.herokuapp.com/patients/getDiseasePatient";
    let url1 = "https://eclinic05.herokuapp.com/getFinishedAppointments";
    this.selected = "";
    this.http.get(url).subscribe(
      res => {
        // @ts-ignore
        this.modelDis = res;
      }
    )

    this.http.get(url1).subscribe(
      res => {

        // @ts-ignore
        this.dataSource.data = res;
        // @ts-ignore
        this.elements = res;
        this.dataSource.data.forEach(data =>{
          // @ts-ignore
          this.dataSource1.data.push(data.diagnosis)
        })
        this.dataSource1._updateChangeSubscription()
      }
    )

    this.dataSource.paginator = this.paginator;
    this.dataSource1.paginator = this.paginator1;
  }
}

export interface rateModel{
  param: number,
  rate: number,
  apt_id: number
}
