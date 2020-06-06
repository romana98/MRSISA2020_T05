import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpParams, HttpClient } from '@angular/common/http';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material/dialog";
import {DialogOverview} from "../../request-list-patients/request-list-patients.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  displayedColumns: string[] = ['name', 'surname', 'avg_rating', 'times', 'check'];

  dataSource = new MatTableDataSource();

  selectedRowIndex: number = 0;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;


  constructor(private _snackBar: MatSnackBar,private router: ActivatedRoute, private http: HttpClient, public dialog: MatDialog) { }

  label : String = '';
  sent: boolean = false;
  clinic: String = '';
  clinic_addr: String = '';
  appoint_type: String = '';

  model : doctorModel = {
    name : '',
    surname : '',
    average_rate : 0,
    available_times : null,
    time: ''
  }

  ngOnInit(): void {
    this.label = this.router.snapshot.queryParamMap.get('clinic_id');
    this.label = this.router.snapshot.queryParamMap.get('date');
    this.clinic_addr = this.router.snapshot.queryParamMap.get('clinic_addr');
    this.clinic = this.router.snapshot.queryParamMap.get('clinic');
    this.appoint_type = this.router.snapshot.queryParamMap.get('appointment_type_name');
    let params = new HttpParams();
    params = params.append('date', this.router.snapshot.queryParamMap.get('date'))
    params = params.append('clinic_id', this.router.snapshot.queryParamMap.get('clinic_id'));
    params = params.append('appointmentType_id', this.router.snapshot.queryParamMap.get('appointment_type_id'));
    this.http.get("http://localhost:8081/doctors/getAvailableDoctors",{params:params})
    .subscribe((res) => {
      // @ts-ignore
      this.dataSource.data = res;
    });

    this.dataSource.paginator = this.paginator;

  }


  Check(req): void {
    const dialogRef = this.dialog.open(DialogConfirm, {
      width: '50%',height: '60%',
      data: {price: 0, name: req.name, surname: req.surname, time: req.time, date:this.label, sent:this.sent,
        clinic:this.clinic, clinic_addr:this.clinic_addr, appoint_type:this.appoint_type}
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result.sent === true)
      {
        let url = "http://localhost:8081/appointment/"
        this.http.post(url, req).subscribe(
          res => {
            let index = this.dataSource.data.indexOf(req);
            this.dataSource.data.splice(index, 1);
            this.dataSource._updateChangeSubscription()
            this._snackBar.open("Request for appointment sent!", "Close", {
              duration: 2000,
            });

          },
          err => {
              this._snackBar.open("Error has occurred while making the appointment!", "Close", {
                duration: 2000,
              });
              console.log(err);
          }
        );
      }

    });
  }

}


export interface doctorModel{
  name: string,
  surname: string,
  average_rate : number,
  available_times : string[],
  time: string
}



export interface DialogData {
  clinic: string;
  clinic_addr: string;
  time: string;
  name: string;
  surname: string;
  date: string;
  price: number;
  appoint_type: string;
  sent: boolean;
}

@Component({
  selector: 'app-doctors',
  templateUrl: './dialog-confirm.html',
  styleUrls: ['./doctors.component.css']
})
export class DialogConfirm {

  constructor(
    public dialogRef: MatDialogRef<DialogConfirm>,
    @Inject(MAT_DIALOG_DATA) public data: DialogData) {}

  onClick(): boolean {

    return false;
  }

  Confirm(): boolean{
    return true;
  }

}
